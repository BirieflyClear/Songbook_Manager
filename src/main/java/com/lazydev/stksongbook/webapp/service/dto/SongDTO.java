package com.lazydev.stksongbook.webapp.service.dto;

import com.lazydev.stksongbook.webapp.util.Constants;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Value
@Builder
public class SongDTO {

  @NotNull(message = "ID must be defined.")
  Long id;

  @Valid
  AuthorDTO author;

  @Valid
  CategoryDTO category;

  @Pattern(regexp = Constants.NAME_REGEX_SHORT, message = Constants.TITLE_INVALID_MESSAGE)
  String title;

  @NotNull(message = "Coauthors list must be initialized.")
  Set<SongCoauthorDTO> coauthors;

  @NotBlank(message = "Lyrics can't be blank.")
  String lyrics;

  @NotBlank(message = "Guitar tabs can't be blank.")
  String guitarTabs;

  String curio;

  String creationTime;

  Double averageRating;

  @NotNull(message = "Tags list must be initialized.")
  List<TagDTO> tags;
}
