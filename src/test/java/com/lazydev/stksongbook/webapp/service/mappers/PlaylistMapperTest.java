package com.lazydev.stksongbook.webapp.service.mappers;

import com.lazydev.stksongbook.webapp.StkSongbookApplication;
import com.lazydev.stksongbook.webapp.data.model.Playlist;
import com.lazydev.stksongbook.webapp.data.model.Song;
import com.lazydev.stksongbook.webapp.data.model.User;
import com.lazydev.stksongbook.webapp.service.SongService;
import com.lazydev.stksongbook.webapp.service.UserService;
import com.lazydev.stksongbook.webapp.service.dto.PlaylistDTO;
import com.lazydev.stksongbook.webapp.service.dto.creational.CreatePlaylistDTO;
import com.lazydev.stksongbook.webapp.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {StkSongbookApplication.class, PlaylistMapperImpl.class})
class PlaylistMapperTest {

  @Mock
  private SongService songService;

  @Mock
  private UserService userService;

  @Autowired
  private PlaylistMapperImpl impl;

  private PlaylistMapper mapper;

  @BeforeEach
  void setUp() {
    impl.setSongService(songService);
    impl.setUserService(userService);
    mapper = impl;
  }

  @Test
  void testMapToDTO() {
    Playlist playlist = getSample();

    PlaylistDTO dto = mapper.map(playlist);

    assertEquals(playlist.getName(), dto.getName());
    assertEquals(playlist.getId(), dto.getId());
    assertEquals(playlist.getOwner().getId(), dto.getOwnerId());
    assertEquals(playlist.getCreationTime(), dto.getCreationTime());
    assertEquals(playlist.getSongs().size(), dto.getSongs().size());
  }

  @Test
  void testMapToEntity() {
    PlaylistDTO dto = PlaylistDTO.builder().id(1L).name("dummy name").creationTime(Instant.now()).isPrivate(true).ownerId(4L).songs(Set.of(2L)).build();
    Playlist playlist = getSample();

    given(songService.findById(2L)).willReturn(getSong());
    given(userService.findById(4L)).willReturn(playlist.getOwner());

    Playlist mapped = mapper.map(dto);

    assertEquals(playlist.getId(), mapped.getId());
    assertEquals(playlist.getName(), mapped.getName());
    assertEquals(1, mapped.getSongs().size());
    assertTrue(mapped.getSongs().contains(getSong()));
    assertEquals(playlist.getOwner(), mapped.getOwner());
  }

  private Playlist getSample() {
    Playlist playlist = new Playlist();
    playlist.setId(1L);
    playlist.setName("dummy name");
    playlist.setPrivate(true);
    playlist.setCreationTime(Instant.now());
    playlist.setSongs(Set.of(getSong()));
    User user = getOwner();
    user.addPlaylist(playlist);
    return playlist;
  }

  private User getOwner() {
    User user = new User();
    user.setId(4L);
    user.setPlaylists(new HashSet<>());
    return user;
  }

  private Song getSong() {
    Song song = new Song();
    song.setId(2L);
    song.setTitle("dummy title");
    return song;
  }
}
