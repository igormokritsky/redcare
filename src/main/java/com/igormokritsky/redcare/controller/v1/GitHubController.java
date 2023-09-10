package com.igormokritsky.redcare.controller.v1;

import com.igormokritsky.redcare.dto.GitHubSearchDTO;
import com.igormokritsky.redcare.dto.response.Response;
import com.igormokritsky.redcare.service.GitHubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/github")
public class GitHubController {

    private final GitHubService service;

    public GitHubController(GitHubService service) {
        this.service = service;
    }

    @GetMapping("/popular-repositories")
    public ResponseEntity<Response<GitHubSearchDTO>> getPopularRepositories(
        @RequestParam(required = false) String createdSince,
        @RequestParam(required = false) String language,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int perPage) {
        Response<GitHubSearchDTO> response = new Response<>();
        GitHubSearchDTO repositories = service.getPopularRepositories(language, page, perPage, createdSince);
        response.setData(repositories);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
