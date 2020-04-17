package com.lazydev.stksongbook.webapp.api.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class SongAuthorDTO {

    private final Long songId;
    private final Long authorId;
    private final String function;

    private SongAuthorDTO(Long songId, Long authorId, String function) {
        this.songId = songId;
        this.authorId = authorId;
        this.function = function;
    }

    public static SongAuthorDTO.Builder builder() {
        return new SongAuthorDTO.Builder();
    }

    public static final class Builder {
        private Long songId;
        private Long authorId;
        private String function;

        public SongAuthorDTO create() {
            return new SongAuthorDTO(songId, authorId, function);
        }
        public SongAuthorDTO.Builder songId(Long id) {
            this.songId = id;
            return this;
        }
        public SongAuthorDTO.Builder authorId(Long id) {
            this.authorId = id;
            return this;
        }
        public SongAuthorDTO.Builder function(String function) {
            this.function = function;
            return this;
        }
    }
}
