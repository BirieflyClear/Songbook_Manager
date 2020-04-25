package com.lazydev.stksongbook.webapp.web.rest;

import com.lazydev.stksongbook.webapp.data.model.SongCoauthor;
import com.lazydev.stksongbook.webapp.service.SongCoauthorService;
import com.lazydev.stksongbook.webapp.service.dto.SongCoauthorDTO;
import com.lazydev.stksongbook.webapp.service.exception.EntityNotFoundException;
import com.lazydev.stksongbook.webapp.service.mappers.SongCoauthorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/coauthors")
@AllArgsConstructor
public class SongCoauthorRestController {

  private SongCoauthorMapper songCoauthorMapper;
  private SongCoauthorService songCoauthorService;

  @GetMapping("/author/{id}")
  public ResponseEntity<List<SongCoauthorDTO>> getByAuthorId(@PathVariable("id") Long id) {
    List<SongCoauthorDTO> list = songCoauthorService.findByAuthorId(id)
        .stream().map(songCoauthorMapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/song/{id}")
  public ResponseEntity<List<SongCoauthorDTO>> getBySongId(@PathVariable("id") Long id) {
    List<SongCoauthorDTO> list = songCoauthorService.findBySongId(id)
        .stream().map(songCoauthorMapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/function/{function}")
  public ResponseEntity<List<SongCoauthorDTO>> getByFunction(@PathVariable("function") String function) {
    List<SongCoauthorDTO> list = songCoauthorService.findByFunction(function)
        .stream().map(songCoauthorMapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<SongCoauthorDTO> create(@RequestBody SongCoauthorDTO songCoauthorDTO) {
    SongCoauthor entity = songCoauthorMapper.map(songCoauthorDTO);
    SongCoauthor created = songCoauthorService.save(entity);
    return new ResponseEntity<>(songCoauthorMapper.map(created), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<SongCoauthorDTO> update(@RequestBody SongCoauthorDTO songCoauthorDTO) {
    if(songCoauthorService.findBySongIdAndAuthorIdNoException(
        songCoauthorDTO.getSongId(), songCoauthorDTO.getAuthorId()).isEmpty()) {
      throw new EntityNotFoundException(SongCoauthor.class);
    }
    SongCoauthor author = songCoauthorMapper.map(songCoauthorDTO);
    var saved = songCoauthorService.save(author);
    return new ResponseEntity<>(songCoauthorMapper.map(saved), HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<Void> delete(@RequestBody SongCoauthorDTO songCoauthorDTO) {
    var entity = songCoauthorMapper.map(songCoauthorDTO);
    songCoauthorService.delete(entity);
    return ResponseEntity.noContent().build();
  }
}
