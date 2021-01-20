package com.uno.user.controller;

import com.uno.user.UserApplication;
import com.uno.user.dto.AddressDTO;
import com.uno.user.dto.UserDTO;
import com.uno.user.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import com.uno.user.entity.User;
import com.uno.user.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = UserApplication.class)
public class UserControllerIntegrationTest {
    private TestRestTemplate restTemplate = new TestRestTemplate("admin", "password", TestRestTemplate.HttpClientOption.ENABLE_COOKIES);;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    //TODO more tests to test negative scenarios and integrate H2 as test DB
    @Test
    public void testGetUserById() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<UserDTO> responseEntity = restTemplate.exchange(
                getRootUrl() + "/user/details?userId=1",
                HttpMethod.GET,
                requestEntity,
                UserDTO.class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateUser() {
        UserDTO user = new UserDTO();
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setMobileNumber("4561237890");
        user.setTitle("title");
        AddressDTO address = new AddressDTO();
        address.setSuburb("suburb");
        address.setState("state");
        address.setPostcode("2000");
        address.setFullAddress("full address");
        user.setAddress(address);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(user, requestHeaders);
        ResponseEntity<UserDTO> responseEntity = restTemplate.exchange(
                getRootUrl() + "/user/add",
                HttpMethod.POST,
                requestEntity,
                UserDTO.class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateUser() {
        UserDTO user = new UserDTO();
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setMobileNumber("4561237890");
        user.setTitle("title");
        AddressDTO address = new AddressDTO();
        address.setSuburb("suburb");
        address.setState("state");
        address.setPostcode("2000");
        address.setFullAddress("full address");
        user.setAddress(address);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(user, requestHeaders);
        ResponseEntity<UserDTO> responseEntity = restTemplate.exchange(
                getRootUrl() + "/user/update/1",
                HttpMethod.PUT,
                requestEntity,
                UserDTO.class
        );
        assertNotNull(responseEntity);
        //assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllUsers() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<List> responseEntity = restTemplate.exchange(
                getRootUrl() + "/user/all",
                HttpMethod.GET,
                requestEntity,
                List.class
        );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
