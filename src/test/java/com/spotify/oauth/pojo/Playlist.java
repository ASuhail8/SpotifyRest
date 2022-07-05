package com.spotify.oauth.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Playlist {

        @JsonProperty("name")
        private String name;
        @JsonProperty("description")
        private String description;
        @JsonProperty("public")
        private Boolean _public;

        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public Playlist setName(String name) {
            this.name = name;
            return this;
        }

        @JsonProperty("description")
        public String getDescription() {
            return description;
        }

        @JsonProperty("description")
        public Playlist setDescription(String description) {
            this.description = description;
            return this;
        }

        @JsonProperty("public")
        public Boolean getPublic() {
            return _public;
        }

        @JsonProperty("public")
        public Playlist setPublic(Boolean _public) {
            this._public = _public;
            return this;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }
    }
