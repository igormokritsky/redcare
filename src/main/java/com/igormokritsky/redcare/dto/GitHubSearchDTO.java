package com.igormokritsky.redcare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.igormokritsky.redcare.dto.GitHubDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GitHubSearchDTO {

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("incomplete_results")
    private boolean incompleteResults;

    private List<GitHubDTO> items;

}
