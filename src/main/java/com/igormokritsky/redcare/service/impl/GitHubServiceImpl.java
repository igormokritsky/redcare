package com.igormokritsky.redcare.service.impl;

import com.igormokritsky.redcare.dto.GitHubSearchDTO;
import com.igormokritsky.redcare.exception.GitHubApiException;
import com.igormokritsky.redcare.service.GitHubService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GitHubServiceImpl implements GitHubService {

    @Value("${github.api.url}")
    private String githubApiUrl;

    private final RestTemplate restTemplate;

    public GitHubServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public GitHubSearchDTO getPopularRepositories(String language, int page, int perPage, String createdSince) {
        String url = getUrl(language, page, perPage, createdSince);
        ResponseEntity<GitHubSearchDTO> response = restTemplate.getForEntity(url, GitHubSearchDTO.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new GitHubApiException("Error fetching GitHub data");
        }
    }

    private String getUrl(String language, int page, int perPage, String createdSince) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(githubApiUrl)
            .path("/search/repositories")
            .queryParam("sort", "stars")
            .queryParam("order", "desc")
            .queryParam("page", page)
            .queryParam("per_page", perPage);

        if (createdSince != null && !createdSince.isEmpty()) {
            builder.queryParam("q", "created:" + createdSince);
        }
        if (language != null && !language.isEmpty()) {
            builder.queryParam("q", "language:" + language);
        }
        return builder.build().toUriString();
    }
}
