package com.lazydev.stksongbook.webapp.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "users_songs_ratings")
@Check(constraints = "rating >= 0 AND rating <= 1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSongRating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @MapsId("user_id")
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @MapsId("song_id")
  @JoinColumn(name = "song_id")
  private Song song;

  @Column(name = "rating", nullable = false)
  private BigDecimal rating;
}
