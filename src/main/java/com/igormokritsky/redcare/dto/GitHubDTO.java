package com.igormokritsky.redcare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GitHubDTO {

    private Long id;

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private String description;

    private String language;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("stargazers_count")
    private int stargazersCount;

}
