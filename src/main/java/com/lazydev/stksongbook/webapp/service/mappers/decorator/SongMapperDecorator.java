package com.lazydev.stksongbook.webapp.service.mappers.decorator;

import com.lazydev.stksongbook.webapp.data.model.Song;
import com.lazydev.stksongbook.webapp.service.PlaylistService;
import com.lazydev.stksongbook.webapp.service.UserService;
import com.lazydev.stksongbook.webapp.service.UserSongRatingService;
import com.lazydev.stksongbook.webapp.service.dto.SongDTO;
import com.lazydev.stksongbook.webapp.service.mappers.SongMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashSet;

@Setter
public abstract class SongMapperDecorator implements SongMapper {

  @Autowired
  @Qualifier("delegate")
  private SongMapper delegate;
  @Autowired
  private UserService userService;
  @Autowired
  private UserSongRatingService userSongRatingService;
  @Autowired
  private PlaylistService playlistService;

  @Override
  public Song map(SongDTO dto) {
    Song song = delegate.map(dto);
    song.setUsersSongs(new HashSet<>(userService.findBySong(dto.getId())));
    song.setRatings(new HashSet<>(userSongRatingService.findBySongId(dto.getId())));
    song.setPlaylists(new HashSet<>(playlistService.findBySongId(dto.getId(), true)));
    return song;
  }
}
