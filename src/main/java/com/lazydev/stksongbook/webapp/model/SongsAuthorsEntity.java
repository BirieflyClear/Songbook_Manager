package com.lazydev.stksongbook.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;

@Entity
@Table(name = "songs_authors")
@Check(constraints = "function IN ('" + SongsAuthorsEntity._Function_Music_Polish + "', '" + SongsAuthorsEntity._Function_Text_Polish + "')")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SongsAuthorsEntity {

    //public final static String _Function_Author_Polish = "Autor";
    public final static String _Function_Music_Polish = "Muzyka";
    public final static String _Function_Text_Polish = "Tekst";

    @EmbeddedId
    private SongsAuthorsKey id;

    @ManyToOne
    @MapsId("song_id")
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne
    @MapsId("author_id")
    @JoinColumn(name = "author_id")
    private Author author;

    /**
     * if null then it is the main author displayed with the title
     */
    @Column(name = "function", nullable = true)
    private String function;
}
