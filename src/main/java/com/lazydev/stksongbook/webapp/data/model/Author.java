package com.lazydev.stksongbook.webapp.data.model;

import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.util.Set;

/**
 * This is the model class of the Author entity. The table stores all authors of the songs from the SONGS table.
 *
 * @author Andrzej Przybysz
 * @version 1.0
 */

@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"songs", "biographyUrl", "photoResource", "coauthorSongs"})
public class Author {

  /**
   * @param id is the Primary Key in the table.
   * By definition, it must be unique.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  /**
   * @param name stores the name of the author. It can be a real person or a band name.
   * It must be unique.
   */
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "biography_url")
  private URL biographyUrl;

  @Column(name = "photo_resource")
  private String photoResource;

  @OneToMany(mappedBy = "author")
  private Set<SongCoauthor> coauthorSongs;

  @OneToMany(mappedBy = "author")
  private Set<Song> songs;

  public void addCoauthorSong(SongCoauthor coauthor) {
    coauthorSongs.add(coauthor);
  }

  public void addSong(Song song) {
    songs.add(song);
    song.setAuthor(this);
  }

  public void removeCoauthor(SongCoauthor coauthor) {
    this.coauthorSongs.remove(coauthor);
  }
}
