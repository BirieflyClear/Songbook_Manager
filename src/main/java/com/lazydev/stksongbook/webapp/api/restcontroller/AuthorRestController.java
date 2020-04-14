package com.lazydev.stksongbook.webapp.api.restcontroller;

import com.lazydev.stksongbook.webapp.api.dto.AuthorDTO;
import com.lazydev.stksongbook.webapp.api.dto.SongDTO;
import com.lazydev.stksongbook.webapp.api.mappers.AuthorMapper;
import com.lazydev.stksongbook.webapp.api.mappers.SongMapper;
import com.lazydev.stksongbook.webapp.api.restcontroller.events.ResourceCreated;
import com.lazydev.stksongbook.webapp.api.restcontroller.events.SingleResourceRetrieved;
import com.lazydev.stksongbook.webapp.data.model.Author;
import com.lazydev.stksongbook.webapp.data.service.AuthorService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/authors")
public class AuthorRestController {

    private AuthorService service;
    private AuthorMapper authorMapper;
    private ApplicationEventPublisher eventPublisher;

    public AuthorRestController(AuthorService service, AuthorMapper mapper, ApplicationEventPublisher publisher) {
        this.service = service;
        this.authorMapper = mapper;
        this.eventPublisher = publisher;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<AuthorDTO> getAll(HttpServletResponse response){
        //eventPublisher.publishEvent(new SingleResourceRetrieved(this, response));
        return service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public AuthorDTO getById(@PathVariable("id") Long id, HttpServletResponse response) {
        Optional<Author> optAuthor = service.findById(id);

        eventPublisher.publishEvent(new SingleResourceRetrieved(this, response));
        return optAuthor.map(this::convertToDto).orElse(null);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<AuthorDTO> getByName(@PathVariable("name") String name, HttpServletResponse response) {
        //eventPublisher.publishEvent(new SingleResourceRetrieved(this, response));
        return service.findByName(name).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AuthorDTO create(@RequestBody AuthorDTO authorDto, HttpServletResponse response) {
        //Preconditions.checkNotNull(authorDto);
        Author author = convertToEntity(authorDto);
        Author created = service.save(author);

        //eventPublisher.publishEvent(new ResourceCreated(this, response, created.getId()));
        return convertToDto(created);
    }

    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody AuthorDTO authorDto) {
        Author author = convertToEntity(authorDto);
        author.setId(id);
        service.save(author);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    public AuthorDTO convertToDto(Author author) {
        return authorMapper.authorToAuthorDTO(author);
    }

    public Author convertToEntity(AuthorDTO authorDto){
        return authorMapper.authorDTOToAuthor(authorDto);
    }
}
