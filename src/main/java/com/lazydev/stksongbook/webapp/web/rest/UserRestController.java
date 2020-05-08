package com.lazydev.stksongbook.webapp.web.rest;

import com.lazydev.stksongbook.webapp.data.model.User;
import com.lazydev.stksongbook.webapp.service.UserService;
import com.lazydev.stksongbook.webapp.service.dto.LoginForm;
import com.lazydev.stksongbook.webapp.service.dto.PlaylistDTO;
import com.lazydev.stksongbook.webapp.service.dto.UserDTO;
import com.lazydev.stksongbook.webapp.service.dto.UserSongRatingDTO;
import com.lazydev.stksongbook.webapp.service.dto.creational.RegisterNewUserForm;
import com.lazydev.stksongbook.webapp.service.exception.*;
import com.lazydev.stksongbook.webapp.service.mappers.PlaylistMapper;
import com.lazydev.stksongbook.webapp.service.mappers.UserMapper;
import com.lazydev.stksongbook.webapp.service.mappers.UserSongRatingMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {

  private UserMapper mapper;
  private UserService service;
  private UserSongRatingMapper userSongRatingMapper;
  private PlaylistMapper playlistMapper;

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAll(@RequestParam(value = "limit", required = false) Integer limit) {
    if(limit != null) {
      List<UserDTO> list = service.findLimited(limit).stream().map(mapper::map).collect(Collectors.toList());
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    List<UserDTO> list = service.findAll().stream().map(mapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<UserDTO> getById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(mapper.map(service.findById(id)), HttpStatus.OK);
  }

  @GetMapping("/id/{id}/ratings")
  public ResponseEntity<List<UserSongRatingDTO>> getRatingsByUserId(@PathVariable("id") Long id) {
    var tmp = service.findById(id);
    List<UserSongRatingDTO> list = tmp.getUserRatings()
        .stream().map(userSongRatingMapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/id/{id}/playlists")
  public ResponseEntity<List<PlaylistDTO>> getPlaylistsByUserId(@PathVariable("id") Long id) {
    var tmp = service.findById(id);
    List<PlaylistDTO> list = tmp.getPlaylists().stream().map(playlistMapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<UserDTO> register(@RequestBody @Valid RegisterNewUserForm form) {
    if(service.findByEmailNoException(form.getEmail()).isPresent()) {
      throw new EmailAlreadyUsedException();
    }
    if(service.findByUsernameNoException(form.getUsername()).isPresent()) {
      throw new UsernameAlreadyUsedException(form.getUsername());
    }
    User user = mapper.mapFromRegisterForm(form);
    User created = service.save(user);
    return new ResponseEntity<>(mapper.map(created), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginForm form) {
    var optional = service.findByEmailNoException(form.getEmail());
    if(optional.isEmpty()) {
      throw new UserNotExistsException(form.getEmail());
    }
    if(!optional.get().getPassword().equals(form.getPassword())) {
      throw new InvalidPasswordException();
    }
    return new ResponseEntity<>(mapper.map(optional.get()), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO dto) {
    if(service.findByIdNoException(dto.getId()).isEmpty()) {
      throw new UserNotExistsException(dto.getId());
    }
    var user = mapper.map(dto);
    var saved = service.save(user);
    return new ResponseEntity<>(mapper.map(saved), HttpStatus.OK);
  }

  @DeleteMapping("/id/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
