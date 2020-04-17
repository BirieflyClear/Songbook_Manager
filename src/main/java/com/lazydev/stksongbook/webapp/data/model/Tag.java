package com.lazydev.stksongbook.webapp.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * This is the model class of the Tag entity. The table stores all tags used for tagging songs.
 *
 * @author Andrzej Przybysz
 * @version 1.0
 */

@Entity
@Table(name = "tags")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "songs")
//@EntityListeners(AuditingEntityListener.class)
public class Tag {

  /**
   * @param id is the Primary Key in the table.
   * By definition, it must be unique.
   */
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * @param name stores the name of the tag. It must be unique.
   */
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  //TODO Don't cascade-delete songs - change their tags to some default value (empty list)
  @ManyToMany(mappedBy = "tags")
  private Set<Song> songs;


  // TODO add the prefix to the DB
  /**
   * @param tagPrefix says what character to use to display tags in the application.
   */
  private static final char tagPrefix = '#';
}
