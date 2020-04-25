package com.lazydev.stksongbook.webapp.service.mappers.decorator;

import com.lazydev.stksongbook.webapp.data.model.UserSongRating;
import com.lazydev.stksongbook.webapp.data.model.UsersSongsRatingsKey;
import com.lazydev.stksongbook.webapp.service.SongService;
import com.lazydev.stksongbook.webapp.service.UserService;
import com.lazydev.stksongbook.webapp.service.dto.UserSongRatingDTO;
import com.lazydev.stksongbook.webapp.service.mappers.UserSongRatingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class UserSongRatingMapperDecorator implements UserSongRatingMapper {

  @Autowired
  @Qualifier("delegate")
  private UserSongRatingMapper delegate;
  @Autowired
  private SongService songService;
  @Autowired
  private UserService userService;

  @Override
  public UserSongRating map(UserSongRatingDTO dto) {
    var entity = delegate.map(dto);
    entity.setSong(songService.findById(dto.getSongId()));
    entity.setUser(userService.findById(dto.getUserId()));
    entity.setId(new UsersSongsRatingsKey(dto.getUserId(), dto.getSongId()));
    return entity;
  }
}
