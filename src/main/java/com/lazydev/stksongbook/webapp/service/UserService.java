package com.lazydev.stksongbook.webapp.service;

import com.lazydev.stksongbook.webapp.data.model.Song;
import com.lazydev.stksongbook.webapp.data.model.User;
import com.lazydev.stksongbook.webapp.data.model.UserRole;
import com.lazydev.stksongbook.webapp.repository.SongRepository;
import com.lazydev.stksongbook.webapp.repository.UserRepository;
import com.lazydev.stksongbook.webapp.repository.UserRoleRepository;
import com.lazydev.stksongbook.webapp.security.UserContextService;
import com.lazydev.stksongbook.webapp.service.dto.EmailChangeDTO;
import com.lazydev.stksongbook.webapp.service.dto.creational.RegisterNewUserForm;
import com.lazydev.stksongbook.webapp.service.exception.*;
import com.lazydev.stksongbook.webapp.util.Constants;
import com.lazydev.stksongbook.webapp.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final Logger log = LoggerFactory.getLogger(UserService.class);

  private final UserRepository repository;
  private final PlaylistService playlistService;
  private final PasswordEncoder passwordEncoder;
  private final UserRoleRepository roleRepository;
  private final UserSongRatingService ratingService;
  private final SongRepository songRepository;
  @Value("${spring.flyway.placeholders.role.user}")
  private String userRoleName;
  @Value("${spring.flyway.placeholders.role.superuser}")
  private String superuserRoleName;
  @Value("${spring.flyway.placeholders.role.admin}")
  private String adminRoleName;
  @Value("${application.default-user-image}")
  private String defaultUserImageUrl;
  private final UserContextService userContextService;

  public UserService(UserRepository repository, PlaylistService playlistService, PasswordEncoder passwordEncoder, UserRoleRepository roleRepository, UserSongRatingService ratingService, SongRepository songRepository, UserContextService userContextService) {
    this.repository = repository;
    this.playlistService = playlistService;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
    this.ratingService = ratingService;
    this.songRepository = songRepository;
    this.userContextService = userContextService;
  }

  public Optional<User> findByIdNoException(Long id) {
    return repository.findById(id);
  }

  public User findById(Long id) {
    return repository.findById(id).orElseThrow(() -> new UserNotExistsException(id));
  }

  public Optional<User> findByUsernameNoException(String name) {
    return repository.findByUsername(name);
  }

  public User findByUsername(String name) {
    return repository.findByUsername(name).orElseThrow(() -> new UserNotExistsException(name));
  }

  public Optional<User> findByEmailNoException(String email) {
    return repository.findByEmail(email);
  }

  public User findByEmail(String email) {
    return repository.findByEmail(email).orElseThrow(() -> new UserNotExistsException(email));
  }

  public List<User> findByUsernameContains(String text) {
    return repository.findByUsernameContainingIgnoreCase(text);
  }

  public List<User> findByUserRole(Long id) {
    return repository.findByUserRoleId(id);
  }

  public List<User> findBySong(Long id) {
    return repository.findBySongsId(id);
  }

  public List<User> findAll() {
    return repository.findAll();
  }

  public List<User> findLimited(int limit) {
    return repository.findAll(PageRequest.of(0, limit)).toList();
  }

  public User save(User saveUser) {
    User currentUser = userContextService.getCurrentUser();
    if(!saveUser.getId().equals(currentUser.getId()) && !currentUser.getUserRole().getName().equals(superuserRoleName)
        && !currentUser.getUserRole().getName().equals(adminRoleName)) {
      throw new ForbiddenOperationException("No permission.");
    }
    if(saveUser.getUserRole().getName().equals(superuserRoleName)) {
      boolean superuserExists = !roleRepository.findByName(superuserRoleName).map(role -> role.getUsers().isEmpty())
          .orElseThrow(() -> new EntityDependentNotInitialized(superuserRoleName));
      if(superuserExists) {
        throw new SuperUserAlreadyExistsException();
      }
    }
    return repository.save(saveUser);
  }

  public void deleteById(Long id) {
    var user = findById(id);
    User currentUser = userContextService.getCurrentUser();
    if(!id.equals(currentUser.getId()) && !currentUser.getUserRole().getName().equals(superuserRoleName)
        && !currentUser.getUserRole().getName().equals(adminRoleName)) {
      throw new ForbiddenOperationException("No permission.");
    }
    if(user.getUserRole().getName().equals(superuserRoleName)) {
      throw new BadRequestErrorException("Cannot delete superuser.");
    }
    user.getPlaylists().forEach(it -> playlistService.deleteById(it.getId()));
    user.getUserRatings().forEach(it -> ratingService.delete(it.getUser().getId(), it.getSong().getId()));
    repository.deleteById(id);
  }

  public User register(RegisterNewUserForm form) {
    User user = new User();
    user.setRegistrationDate(Instant.now());
    user.setId(Constants.DEFAULT_ID);
    user.setUsername(form.getUsername());
    user.setEmail(form.getEmail());
    user.setFirstName(form.getFirstName());
    user.setLastName(form.getLastName());
    user.setPassword(passwordEncoder.encode(form.getPassword()));
    user.setUserRole(roleRepository.findByName(userRoleName).orElseThrow(() -> new EntityDependentNotInitialized(userRoleName)));
    user.setActivated(false);
    user.setActivationKey(RandomUtil.generateActivationKey());
    user.setImageUrl(defaultUserImageUrl);
    return repository.save(user);
  }

  public User activate(String key) {
    return repository.findByActivationKey(key)
        .map(user -> {
          user.setActivated(true);
          user.setActivationKey(null);
          return repository.save(user);
        }).orElseThrow(() -> new InternalServerErrorException("No user was found for this activation key"));
  }

  public User updateUser(User newUser, User userToUpdate) {
    if(!userToUpdate.getUsername().equals(newUser.getUsername())) {
      throw new BadRequestErrorException("Cannot change username.");
    }
    if(!userToUpdate.getEmail().equals(newUser.getEmail())) {
      throw new BadRequestErrorException("Cannot change email.");
    }
    userToUpdate.setFirstName(newUser.getFirstName());
    userToUpdate.setLastName(newUser.getLastName());
    userToUpdate.setImageUrl(newUser.getImageUrl());
    userToUpdate.setSongs(newUser.getSongs());
    return repository.save(userToUpdate);
  }

  public User changePassword(String oldPassword, String newPassword) {
    User user = userContextService.getCurrentUser();
    if(!user.getPassword().equals(oldPassword)) {
      throw new InvalidPasswordException();
    }
    log.debug("Changing password of {}", user.getUsername());
    user.setPassword(newPassword);
    return repository.save(user);
  }

  public User requestPasswordReset(String mail) {
    return repository.findByEmailIgnoreCase(mail)
        .filter(User::isActivated)
        .map(user -> {
          user.setResetKey(RandomUtil.generateResetKey());
          user.setResetDate(Instant.now());
          return repository.save(user);
        }).orElseThrow(() -> new UserNotExistsException(mail));
  }

  public User completePasswordReset(String token, String newPassword) {
    User user = repository.findByResetKey(token).orElseThrow(() -> new BadRequestErrorException("User for this key does not exist."));
    boolean expired = user.getResetDate().isAfter(Instant.now().minusSeconds(86400));
    if(expired) {
      throw new BadRequestErrorException("Reset key is expired");
    }
    user.setPassword(passwordEncoder.encode(newPassword));
    user.setResetKey(null);
    user.setResetDate(null);
    log.debug("Completing password change for {}", user.getUsername());
    return repository.save(user);
  }

  public User changeEmail(EmailChangeDTO email) {
    User user = userContextService.getCurrentUser();
    if(repository.findByEmailIgnoreCase(email.getEmail()).isPresent()) {
      throw new EmailAlreadyUsedException();
    }
    log.debug("Changing email of {} to {}", user.getUsername(), email);
    user.setEmail(email.getEmail());
    return repository.save(user);
  }

  public User changeRole(Long userId, Long roleId) {
    if(!(userContextService.getCurrentUser().getUserRole().getName().equals(superuserRoleName)
        || userContextService.getCurrentUser().getUserRole().getName().equals(adminRoleName))) {
      throw new ForbiddenOperationException("No permission.");
    }
    User user = repository.findById(userId).orElseThrow(() -> new UserNotExistsException(userId));
    UserRole role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException(UserRole.class, roleId));
    if(role.getName().equals(superuserRoleName)) {
      boolean superuserExists = !roleRepository.findByName(superuserRoleName).map(it -> it.getUsers().isEmpty())
          .orElseThrow(() -> new EntityDependentNotInitialized(superuserRoleName));
      if(superuserExists) {
        throw new SuperUserAlreadyExistsException();
      }
    }
    log.debug("Changing role of {} to {}", user.getUsername(), role.getName());
    user.setUserRole(role);
    return repository.save(user);
  }

  public User activateUser(Long userId) {
    if(!(userContextService.getCurrentUser().getUserRole().getName().equals(superuserRoleName)
        || userContextService.getCurrentUser().getUserRole().getName().equals(adminRoleName))) {
      throw new ForbiddenOperationException("No permission.");
    }
    log.debug("Activate user {}", userId);
    return repository.findById(userId)
        .map(user -> {
          user.setActivated(true);
          user.setActivationKey(null);
          return repository.save(user);
        }).orElseThrow(() -> new UserNotExistsException(userId));
  }

  public void addSongToLibrary(Long userId, Long songId) {
    var user = findById(userId);
    User currentUser = userContextService.getCurrentUser();
    if(!userId.equals(currentUser.getId()) && !currentUser.getUserRole().getName().equals(superuserRoleName)
        && !currentUser.getUserRole().getName().equals(adminRoleName)) {
      throw new ForbiddenOperationException("No permission.");
    }
    Song song = songRepository.findById(songId).orElseThrow(() -> new EntityNotFoundException(Song.class, songId));
    user.addSong(song);
    repository.save(user);
  }

  public void removeSongFromLibrary(Long userId, Long songId) {
    var user = findById(userId);
    User currentUser = userContextService.getCurrentUser();
    if(!userId.equals(currentUser.getId()) && !currentUser.getUserRole().getName().equals(superuserRoleName)
        && !currentUser.getUserRole().getName().equals(adminRoleName)) {
      throw new ForbiddenOperationException("No permission.");
    }
    Song song = songRepository.findById(songId).orElseThrow(() -> new EntityNotFoundException(Song.class, songId));
    user.removeSong(song);
    repository.save(user);
  }
}
