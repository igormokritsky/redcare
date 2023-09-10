package com.igormokritsky.redcare.service.impl;

import com.igormokritsky.redcare.dto.GitHubSearchDTO;
import com.igormokritsky.redcare.exception.GitHubApiException;
import com.igormokritsky.redcare.service.GitHubService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        String url = githubApiUrl + "/search/repositories?q=";
        StringBuilder urlBuilder = new StringBuilder(url);
        if (createdSince != null && !createdSince.isEmpty()) {
            urlBuilder.append("created:").append(createdSince);
        }

        if (language != null && !language.isEmpty()) {
            if (urlBuilder.length() > url.length()) {
                urlBuilder.append('+');
            }
            urlBuilder.append("language:").append(language);
        }
        return urlBuilder
            .append("&sort=stars&order=desc")
            .append("&page=").append(page)
            .append("&per_page=").append(perPage)
            .toString();
    }
}
