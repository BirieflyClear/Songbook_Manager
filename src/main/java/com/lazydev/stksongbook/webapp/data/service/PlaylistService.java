package com.lazydev.stksongbook.webapp.data.service;

import com.lazydev.stksongbook.webapp.data.model.Playlist;
import com.lazydev.stksongbook.webapp.data.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaylistService {

  private PlaylistRepository repository;

  public Optional<Playlist> findById(Long id, boolean isPrivate) {
    return repository.findByIdAndIsPrivate(id, isPrivate);
  }


  public List<Playlist> findByName(String name, boolean isPrivate) {
    return repository.findByNameIgnoreCaseAndIsPrivate(name, isPrivate);
  }

  public List<Playlist> findByOwnerId(Long id, boolean isPrivate) {
    return repository.findByOwnerIdAndIsPrivate(id, isPrivate);
  }

  public List<Playlist> findAll(boolean isPrivate) {
    return repository.findByIsPrivate(isPrivate);
  }

  public Playlist save(Playlist saveAuthor) {
    return repository.save(saveAuthor);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
