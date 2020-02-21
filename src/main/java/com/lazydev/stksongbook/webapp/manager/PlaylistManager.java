package com.lazydev.stksongbook.webapp.manager;

import com.lazydev.stksongbook.webapp.dao.PlaylistDAO;
import com.lazydev.stksongbook.webapp.model.Playlist;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaylistManager {

    @Autowired
    private PlaylistDAO dao;

    public Optional<Playlist> findById(Long id) {
        return dao.findById(id);
    }

    public Optional<Playlist> findPublicById(Long id) {
        Optional<Playlist> var = dao.findById(id);
        if(var.isPresent() && !var.get().isPrivate()) return var;
        else return Optional.empty();
    }

    public Iterable<Playlist> findByName(String name) {
        List<Playlist> list = new ArrayList<>();
        for (Playlist element : dao.findAll()) {
            if(element.getName().equals(name)) list.add(element);
        }
        return list;
    }

    public Iterable<Playlist> findPublicByName(String name) {
        List<Playlist> list = new ArrayList<>();
        for (Playlist element : dao.findAll()) {
            if(element.getName().equals(name) && !element.isPrivate()) list.add(element);
        }
        return list;
    }

    public Iterable<Playlist> findByOwnerId(Long id) {
        List<Playlist> list = new ArrayList<>();
        for (Playlist element : dao.findAll()) {
            if(element.getOwnerId() == id) list.add(element);
        }
        return list;
    }

    public Iterable<Playlist> findPublicByOwnerId(Long id) {
        List<Playlist> list = new ArrayList<>();
        for (Playlist element : dao.findAll()) {
            if(element.getOwnerId() == id && !element.isPrivate()) list.add(element);
        }
        return list;
    }

    public Iterable<Playlist> findAllPublic() {
        List<Playlist> list = new ArrayList<>();
        for (Playlist element : dao.findAll()) {
            if(!element.isPrivate()) list.add(element);
        }
        return list;
    }

    public Iterable<Playlist> findAll() {
        return dao.findAll();
    }

    public Playlist save(Playlist saveAuthor) {
        return dao.save(saveAuthor);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
