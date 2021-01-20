package com.uno.user.serviceimpl;

import com.uno.user.dto.AddressDTO;
import com.uno.user.dto.UserDTO;
import com.uno.user.entity.Address;
import com.uno.user.entity.User;
import com.uno.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Before
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }
    //TODO more tests to test negative scenarios
    @Test
    public void addUser() {
        when(repository.save(any(User.class))).thenReturn(buildUserDetails().get());
        service.addUser(buildUserDTORequest());
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    public void updateUser() {
        when(repository.save(any(User.class))).thenReturn(buildUserDetails().get());
        when(repository.findById(anyInt())).thenReturn(buildUserDetails());
        service.updateUser(buildUserDTORequest(),anyInt());
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    public void getUser() {
        when(repository.findById(anyInt())).thenReturn(buildUserDetails());
        UserDTO userDTO = service.getUser(anyInt());
        verify(repository, times(1)).findById(anyInt());
        Assert.assertEquals(userDTO.getFirstName(), "first");
    }

    @Test
    public void findAll() {
        when(repository.findAll()).thenReturn(buildUserDetailsList());
        List<UserDTO> userDTO = service.findAll();
        verify(repository, times(1)).findAll();
    }

    private UserDTO buildUserDTORequest() {
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
        return user;
    }

    private Optional<User> buildUserDetails() {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setMobileNumber("49999999");
        user.setTitle("title");
        Address address = new Address();
        address.setSuburb("Sydney");
        address.setState("NSW");
        address.setPostcode("2000");
        address.setFullAddress("full address");
        user.setAddress(address);

        return Optional.ofNullable(user);
    }

    private List<User> buildUserDetailsList() {
        List<User> users =new ArrayList<>();
        User user = new User();
        user.setFirstName("list1");
        user.setLastName("list user");
        user.setMobileNumber("411111");
        user.setTitle("Mr");
        Address address = new Address();
        address.setSuburb("Sydney");
        address.setState("VIC");
        address.setPostcode("2020");
        address.setFullAddress("full address list");
        user.setAddress(address);
        users.add(user);
        return users;
    }
}