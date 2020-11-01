package com.lazydev.stksongbook.webapp.service;

import com.lazydev.stksongbook.webapp.data.model.Tag;
import com.lazydev.stksongbook.webapp.repository.TagRepository;
import com.lazydev.stksongbook.webapp.service.dto.TagDTO;
import com.lazydev.stksongbook.webapp.service.dto.creational.UniversalCreateDTO;
import com.lazydev.stksongbook.webapp.service.exception.EntityAlreadyExistsException;
import com.lazydev.stksongbook.webapp.service.exception.EntityNotFoundException;
import com.lazydev.stksongbook.webapp.util.Constants;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@AllArgsConstructor
public class TagService {

  private final Logger log = LoggerFactory.getLogger(TagService.class);

  private final TagRepository repository;

  public Optional<Tag> findByIdNoException(Long id) {
    return repository.findById(id);
  }

  public Tag findById(Long id) {
    return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Tag.class, id));
  }

  public List<Tag> findByNameFragment(String name) {
    return repository.findByNameContainingIgnoreCase(name);
  }

  public Optional<Tag> findByNameNoException(String name) {
    return repository.findByName(name);
  }

  public Tag findByName(String name) {
    return repository.findByName(name).orElseThrow(() -> new EntityNotFoundException(Tag.class, name));
  }

  public List<Tag> findAll() {
    return repository.findAll();
  }

  public List<Tag> findLimited(int limit) {
    return repository.findAll(PageRequest.of(0, limit)).toList();
  }

  public Tag create(@Valid UniversalCreateDTO dto) {
    var optional = findByNameNoException(dto.getName());
    if(optional.isPresent()) {
      throw new EntityAlreadyExistsException(Tag.class.getSimpleName(), optional.get().getName());
    }
    Tag tag = new Tag();
    tag.setId(Constants.DEFAULT_ID);
    tag.setName(dto.getName());
    return repository.save(tag);
  }

  public Tag update(@Valid TagDTO dto) {
    Tag tag = findById(dto.getId());
    tag.setName(dto.getName());
    return repository.save(tag);
  }

  public void deleteById(Long id) {
    var tag = findById(id);
    tag.getSongs().forEach(it -> it.removeTag(tag));
    repository.deleteById(id);
  }

  public Tag findOrCreateTag(String name) {
    var tag = repository.findByName(name);
    if(tag.isPresent()) {
      return tag.get();
    } else {
      Tag newTag = new Tag(Constants.DEFAULT_ID, name, new HashSet<>());
      return repository.save(newTag);
    }
  }
}
