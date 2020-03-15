package com.lazydev.stksongbook.webapp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongAuthorDTO {

    private Long authorId;
    private String authorName;
    private String function;

    // link to self
    // link to this author
    // link link to the song

    // link to all SongAuthors of this song
}
