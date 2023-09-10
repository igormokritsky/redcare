package com.igormokritsky.redcare;


import com.igormokritsky.redcare.dto.GitHubSearchDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GitHubControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetPopularRepositoriesEndpoint() {
        ResponseEntity<GitHubSearchDTO> entity = restTemplate.getForEntity(
            "/api/v1/github/popular-repositories?createdSince=2020-01-01&language=java&perPage=10", GitHubSearchDTO.class);
        Assertions.assertTrue(entity.getStatusCode().is2xxSuccessful());
        Assertions.assertNotNull(entity.getBody());
    }

}
