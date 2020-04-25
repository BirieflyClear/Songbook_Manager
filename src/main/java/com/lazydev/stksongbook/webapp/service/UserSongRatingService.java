package com.lazydev.stksongbook.webapp.service;

import com.lazydev.stksongbook.webapp.data.model.UserSongRating;
import com.lazydev.stksongbook.webapp.repository.UserSongRatingRepository;
import com.lazydev.stksongbook.webapp.service.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSongRatingService {

  private UserSongRatingRepository repository;

  public List<UserSongRating> findAll() {
    return repository.findAll();
  }

  public Optional<UserSongRating> findByUserIdAndSongIdNoException(Long userId, Long songId) {
    return repository.findByUserIdAndSongId(userId, songId);
  }

  public UserSongRating findByUserIdAndSongId(Long userId, Long songId) {
    return repository.findByUserIdAndSongId(userId, songId)
        .orElseThrow(() -> new EntityNotFoundException(UserSongRating.class, "User ID " + userId + " Song ID " + songId));
  }

  public List<UserSongRating> findBySongId(Long id) {
    return repository.findBySongId(id);
  }

  public List<UserSongRating> findByUserId(Long id) {
    return repository.findByUserId(id);
  }

  public List<UserSongRating> findByRating(Double rating) {
    return repository.findByRating(rating);
  }

  public List<UserSongRating> findByRatingGreaterThanEqual(Double rating) {
    return repository.findByRatingGreaterThanEqual(rating);
  }

  public List<UserSongRating> findByRatingLessThanEqual(Double rating) {
    return repository.findByRatingLessThanEqual(rating);
  }

  public UserSongRating save(UserSongRating rating) {
    return repository.save(rating);
  }

  public void delete(UserSongRating obj) {
    repository.delete(obj);
  }
}
