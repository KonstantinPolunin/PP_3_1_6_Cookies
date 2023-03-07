package ru.kata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kata.entity.User;

import java.util.Arrays;
import java.util.List;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";


    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {});
        HttpHeaders headers = responseEntity.getHeaders();
        return headers.getFirst(HttpHeaders.SET_COOKIE);
    }


    public String save(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("cookie", getAllUsers());
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();

    }

    public String update(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("cookie", getAllUsers());
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody();

    }

    public String delete(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("cookie", getAllUsers());
        HttpEntity<User> requestEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL + id, HttpMethod.DELETE, requestEntity, String.class).getBody();
    }



}

