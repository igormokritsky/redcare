package com.igormokritsky.redcare.service;

import com.igormokritsky.redcare.dto.GitHubSearchDTO;

public interface GitHubService {

    GitHubSearchDTO getPopularRepositories(String language, int page, int perPage, String createdSince);

}
