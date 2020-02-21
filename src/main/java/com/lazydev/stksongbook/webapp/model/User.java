package com.lazydev.stksongbook.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This is the model class of the User entity. It represents real users of the application.
 *
 * @author Andrzej Przybysz
 * @version 1.0
 */

/* TODO
    Add instances of user's library and playlists.
    IDEA: add instances of added and edited songs by the user.
*/

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
public @Data class User {

    /**
     * @param id is the Primary Key in the table. By definition, it must be unique
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * @param login must be unique. It is used for logging in to the database and the application.
     */
    @NotBlank
    @Column(name = "login")
    @NotNull
    private String login;

    /**
     * @param password must be at least 5 characters. It is used for logging in to the database and the application.
     */
    @NotBlank
    @Column(name = "password")
    @NotNull
    private String password;

    /**
     * @param username must be unique. It is the name displayed for other users.
     */
    @NotBlank
    @Column(name = "username")
    @NotNull
    private String username;

    /**
     * @param userRoleId is the Foreign Key from the user_roles table.
     *                   It is used for deteriminimg whether the user is an administrator, moderator or regular user.
     */
    @NotBlank
    @Column(name = "user_role")
    @NotNull
    private long userRoleId;

    /**
     * @param firstName is user's real first name. It is optional and not displayed for other users.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * @param lastName is user's real last name. It is optional and not displayed for other users.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * @param addedSongsCount counts songs added to the database by the user. New users have it automatically set to 0
     */
    @NotBlank
    @NotNull
    @Column(name = "added_songs_count")
    private int addedSongsCount;

    /**
     * @param addedSongsCount counts songs edited by the user. New users have automatically set to 0
     */
    @NotBlank
    @NotNull
    @Column(name = "edited_songs_count")
    private int editedSongsCount;

    // TODO add lists of added songs and edited songs instead
}
