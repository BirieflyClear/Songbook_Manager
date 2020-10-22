package com.lazydev.stksongbook.webapp.web.rest;

import com.lazydev.stksongbook.webapp.data.model.Category;
import com.lazydev.stksongbook.webapp.service.CategoryService;
import com.lazydev.stksongbook.webapp.service.dto.CategoryDTO;
import com.lazydev.stksongbook.webapp.service.dto.SongDTO;
import com.lazydev.stksongbook.webapp.service.dto.creational.UniversalCreateDTO;
import com.lazydev.stksongbook.webapp.service.mappers.CategoryMapper;
import com.lazydev.stksongbook.webapp.service.mappers.SongMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryResource {

  private final CategoryService service;
  private final CategoryMapper modelMapper;
  private final SongMapper songMapper;

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    List<CategoryDTO> list = service.findAll().stream().map(modelMapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(modelMapper.map(service.findById(id)), HttpStatus.OK);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<CategoryDTO>> getCategoryByName(@PathVariable("name") String name) {
    List<CategoryDTO> list = service.findByNameFragment(name).stream().map(modelMapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/{id}/songs")
  public ResponseEntity<List<SongDTO>> getSongsByCategoryId(@PathVariable("id") Long id) {
    var tmp = service.findById(id);
    List<SongDTO> list = tmp.getSongs().stream().map(songMapper::map).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid UniversalCreateDTO categoryDto) {
    Category saved = service.create(categoryDto);
    return new ResponseEntity<>(modelMapper.map(saved), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<CategoryDTO> updateCategory(@RequestBody @Valid CategoryDTO categoryDto) {
    Category saved = service.update(categoryDto);
    return new ResponseEntity<>(modelMapper.map(saved), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
