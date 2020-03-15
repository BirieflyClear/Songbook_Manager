package com.lazydev.stksongbook.webapp.api.restcontroller;

import com.lazydev.stksongbook.webapp.api.dto.TagDTO;
import com.lazydev.stksongbook.webapp.api.mappers.TagMapper;
import com.lazydev.stksongbook.webapp.data.model.Tag;
import com.lazydev.stksongbook.webapp.data.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tags")
public class TagRestController {

    @Autowired
    private TagService service;

    @Autowired
    private TagMapper modelMapper;

    @GetMapping
    public List<TagDTO> getAll(){
        List<Tag> list = (List<Tag>) service.findAll();
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public TagDTO getById(@PathVariable("id") Long id) {
        Optional<Tag> tag = service.findById(id);
        return tag.map(this::convertToDto).orElse(null);
    }

    @GetMapping("/name/{name}")
    public List<TagDTO> getByName(@PathVariable("name") String name){
        List<Tag> list = (List<Tag>) service.findByName(name);
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDTO create(@RequestBody TagDTO obj) {
        return convertToDto(service.save(convertToEntity(obj)));
    }

    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody TagDTO obj) {
        service.save(convertToEntity(obj));
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    public TagDTO convertToDto(Tag tag) {
        return modelMapper.tagToTagDTO(tag);
    }

    public Tag convertToEntity(TagDTO tagDto) {
        return modelMapper.tagDTOToTag(tagDto);
    }
}
