package com.lazydev.stksongbook.webapp.web.rest;

import com.lazydev.stksongbook.webapp.data.model.UserSongRating;
import com.lazydev.stksongbook.webapp.service.UserSongRatingService;
import com.lazydev.stksongbook.webapp.service.dto.UserSongRatingDTO;
import com.lazydev.stksongbook.webapp.service.exception.EntityAlreadyExistsException;
import com.lazydev.stksongbook.webapp.service.mappers.UserSongRatingMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ratings")
@AllArgsConstructor
public class UserSongRatingResource {

  private final UserSongRatingMapper mapper;
  private final UserSongRatingService service;

  @GetMapping("/user/{id}")
  public ResponseEntity<List<UserSongRatingDTO>> getByUserId(@PathVariable("id") Long userId) {
    List<UserSongRatingDTO> list = service.findByUserId(userId).stream()
        .map(mapper::map)
        .collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/song/{id}")
  public ResponseEntity<List<UserSongRatingDTO>> getBySongId(@PathVariable("id") Long songId) {
    List<UserSongRatingDTO> list = service.findBySongId(songId).stream()
        .map(mapper::map)
        .collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/{userId}/{songId}")
  public ResponseEntity<UserSongRatingDTO> getByUserIdAndSongId(
      @PathVariable("userId") Long userId, @PathVariable("songId") Long songId) {
    var tmp = service.findByUserIdAndSongId(userId, songId);
    return new ResponseEntity<>(mapper.map(tmp), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<UserSongRatingDTO>> getAll(
      @RequestParam(value = "greaterThanEqual", required = false) BigDecimal greaterValue,
      @RequestParam(value = "lessThanEqual", required = false) BigDecimal lessValue,
      @RequestParam(value = "equal", required = false) BigDecimal value) {
    List<UserSongRating> list;
    if(greaterValue != null) {
      list = service.findByRatingGreaterThanEqual(greaterValue);
    } else if(lessValue != null) {
      list = service.findByRatingLessThanEqual(lessValue);
    } else if(value != null) {
      list = service.findByRating(value);
    } else {
      list = service.findAll();
    }
    List<UserSongRatingDTO> dtos = list.stream()
        .map(mapper::map)
        .collect(Collectors.toList());
    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UserSongRatingDTO> create(@RequestBody @Valid UserSongRatingDTO dto) {
    if(service.findByUserIdAndSongIdNoException(dto.getUserId(), dto.getSongId()).isPresent()) {
      throw new EntityAlreadyExistsException(UserSongRating.class.getSimpleName());
    }
    var saved = service.create(dto);
    return new ResponseEntity<>(mapper.map(saved), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<UserSongRatingDTO> update(@RequestBody @Valid UserSongRatingDTO dto) {
    var saved = service.update(dto);
    return new ResponseEntity<>(mapper.map(saved), HttpStatus.OK);
  }

  @DeleteMapping("/{userId}/{songId}")
  public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long songId) {
    service.delete(userId, songId);
    return ResponseEntity.noContent().build();
  }
}
