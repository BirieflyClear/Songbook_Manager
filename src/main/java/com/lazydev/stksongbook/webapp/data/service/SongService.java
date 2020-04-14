package com.lazydev.stksongbook.webapp.data.service;

import com.lazydev.stksongbook.webapp.data.repository.SongRepository;
import com.lazydev.stksongbook.webapp.data.model.Song;
import com.lazydev.stksongbook.webapp.data.model.SongAuthor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongService {

    @Autowired
    private SongRepository dao;

    public Optional<Song> findById(Long id) {
        return dao.findById(id);
    }

    public Iterable<Song> findByTitle(String name) {
        List<Song> list = new ArrayList<>();
        for (Song element : dao.findAll()) {
            if(element.getTitle().equals(name)) list.add(element);
        }
        return list;
    }

    public Iterable<Song> findByAuthorId(Long authorId) {
        return dao.findByAuthorId(authorId);
    }

    public Iterable<Song> findByCategoryId(Long id) {
        return dao.findByCategoryId(id);
    }

    public Iterable<Song> findByTagId(Long id) {
        return dao.findByTagId(id);
    }

    public Iterable<Song> findAll() {
        return dao.findAll();
    }

    public Song save(Song saveSong) {
        return dao.save(saveSong);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
