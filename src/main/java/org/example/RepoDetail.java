package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoDetail {
    @JsonProperty("clone_url")
    private String url;

    @JsonProperty
    private String name;

}
