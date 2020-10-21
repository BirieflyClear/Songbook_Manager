package com.lazydev.stksongbook.webapp.web.rest;

import com.lazydev.stksongbook.webapp.service.UserService;
import com.lazydev.stksongbook.webapp.service.dto.PlaylistDTO;
import com.lazydev.stksongbook.webapp.service.dto.UserDTO;
import com.lazydev.stksongbook.webapp.service.dto.UserSongRatingDTO;
import com.lazydev.stksongbook.webapp.service.mappers.PlaylistMapper;
import com.lazydev.stksongbook.webapp.service.mappers.UserMapper;
import com.lazydev.stksongbook.webapp.service.mappers.UserSongRatingMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserResource {

  private final Logger log = LoggerFactory.getLogger(UserResource.class);

  private final UserMapper mapper;
  private final UserService service;
  private final UserSongRatingMapper userSongRatingMapper;
  private final PlaylistMapper playlistMapper;

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAll(@RequestParam(value = "limit", required = false) Integer limit) {
    if(limit != null) {
      List<UserDTO> list = service.findLimited(limit).stream().map(mapper::map).collect(Collectors.toList());
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    List<UserDTO> list = service.findAll().stream().map(mapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(mapper.map(service.findById(id)), HttpStatus.OK);
  }

  @GetMapping("/{id}/ratings")
  public ResponseEntity<List<UserSongRatingDTO>> getRatingsByUserId(@PathVariable("id") Long id) {
    var tmp = service.findById(id);
    List<UserSongRatingDTO> list = tmp.getUserRatings()
        .stream().map(userSongRatingMapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/{id}/playlists")
  public ResponseEntity<List<PlaylistDTO>> getPlaylistsByUserId(@PathVariable("id") Long id) {
    var tmp = service.findById(id);
    List<PlaylistDTO> list = tmp.getPlaylists().stream().map(playlistMapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}/add-song/{songId}")
  public ResponseEntity<Void> addSongToLibrary(@PathVariable Long id, @PathVariable Long songId) {
    log.debug("Add song {} to user {} library", songId, id);
    service.addSongToLibrary(id, songId);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}/remove-song/{songId}")
  public ResponseEntity<Void> removeSongFromLibrary(@PathVariable Long id, @PathVariable Long songId) {
    log.debug("Remove song {} from user {} library", songId, id);
    service.removeSongFromLibrary(id, songId);
    return ResponseEntity.noContent().build();
  }
}
