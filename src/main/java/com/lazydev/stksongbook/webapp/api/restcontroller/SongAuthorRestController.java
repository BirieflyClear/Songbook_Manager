package com.lazydev.stksongbook.webapp.api.restcontroller;

import com.lazydev.stksongbook.webapp.api.dto.SongAuthorDTO;
import com.lazydev.stksongbook.webapp.api.mappers.SongAuthorMapper;
import com.lazydev.stksongbook.webapp.data.model.SongAuthor;
import com.lazydev.stksongbook.webapp.data.service.SongAuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/authors")
@AllArgsConstructor
public class SongAuthorRestController {

  private SongAuthorMapper songAuthorMapper;
  private SongAuthorService songAuthorService;

  @GetMapping("/id/{id}/songs")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<SongAuthorDTO> getSongsAuthor(@PathVariable("id") Long id, HttpServletResponse response) {
    List<SongAuthor> authors = songAuthorService.findByAuthorId(id);
    return authors.stream().map(songAuthorMapper::songsAuthorsEntityToSongAuthorDTO).collect(Collectors.toList());
  }

  @GetMapping("/song/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<SongAuthorDTO> getBySong(@PathVariable("id") Long id, HttpServletResponse response) {
    List<SongAuthor> authors = songAuthorService.findBySongId(id);
    return authors.stream().map(songAuthorMapper::songsAuthorsEntityToSongAuthorDTO).collect(Collectors.toList());
  }

  @GetMapping("/function/{function}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<SongAuthorDTO> getByFunction(@PathVariable("function") String function, HttpServletResponse response) {
    List<SongAuthor> authors = songAuthorService.findByFunction(function);
    return authors.stream().map(songAuthorMapper::songsAuthorsEntityToSongAuthorDTO).collect(Collectors.toList());
  }

  /*@PostMapping("id/{id}/songs")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public SongAuthorDTO create(@PathVariable("id") Long id, @RequestBody SongAuthorDTO songAuthorDTO, HttpServletResponse response) {
    //Preconditions.checkNotNull(authorDto);
    SongAuthor entity = songAuthorMapper.songsAuthorsEntityDTOToSongAuthor(songAuthorDTO);
    SongAuthor created = songAuthorService.save(entity);

    //eventPublisher.publishEvent(new ResourceCreated(this, response, created.getId()));
    return songAuthorMapper.songsAuthorsEntityToSongAuthorDTO(created);
  }*/

  /*@PutMapping("/id/{id}/songs")
  @ResponseStatus(HttpStatus.OK)
  public void updateSong(@PathVariable("id") Long id, @RequestBody SongAuthorDTO songAuthorDTO) {
    SongAuthor author = songAuthorMapper.songsAuthorsEntityDTOToSongAuthor(songAuthorDTO);
    songAuthorService.save(author);
  }*/

  /*@DeleteMapping("/id/{id}/songs")
    public void deleteSong(@PathVariable("id") Long id, SongAuthorDTO songAuthorDTO) {
      var entity = songAuthorMapper.songsAuthorsEntityDTOToSongAuthor(songAuthorDTO);
      songAuthorService.delete(entity);
    }*/
}
