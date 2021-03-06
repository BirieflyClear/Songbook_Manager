package com.lazydev.stksongbook.webapp.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.lazydev.stksongbook.webapp.util.Constants;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Value
@JsonDeserialize(builder = AuthorDTO.Builder.class)
@Builder(builderClassName = "Builder", toBuilder = true)
public class AuthorDTO {

    @NotNull(message = "ID must be defined.")
    Long id;

    @NotNull(message = "Can't be null.")
    @Pattern(regexp = Constants.NAME_REGEX_SHORT, message = Constants.NAME_SHORT_INVALID_MESSAGE)
    String name;

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private Long id;
        private String name;

        public AuthorDTO create() {
            return new AuthorDTO(id, name);
        }
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
    }
}
