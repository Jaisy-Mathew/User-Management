package com.uno.user.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.uno.user.dto.AddressDTO;
import com.uno.user.dto.UserDTO;
import com.uno.user.entity.Address;
import com.uno.user.entity.User;
import com.uno.user.exception.ServiceException;
import com.uno.user.repository.UserRepository;
import com.uno.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    
	@Override
	@Transactional
	public UserDTO addUser(UserDTO userDTO) {
		try {
			User user = new User();
			getUserObject(userDTO, user);
			User savedUser = userRepository.save(user);
			return getUserDTO(savedUser);
		} catch (Exception e) {
			LOG.error("Exception while saving data : {}", e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		if(null != userId) {
			try {
				User user = userRepository.findById(userId)
						.orElseThrow(() -> new ServiceException("User not found for this id :: " + userId));
				userDTO.setUserId(user.getUserId());
				getUserObject(userDTO, user);
				return getUserDTO(userRepository.save(user));
			} catch (Exception e) {
				LOG.error("Exception while saving data : {}", e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		}else {
			LOG.error("Invalid userId to update");
			throw new ServiceException("Invalid userId to update");
		}
	}

	@Override
	public UserDTO getUser(Integer userId) {
		LOG.debug("userId of the user to fetch details {}", userId);
		Optional<User> user= userRepository.findById(userId);
		if(user.isPresent()) {
			return getUserDTO(user.get());
		}else {
			return null;//return user not found
		}
	}

	@Override
	public List<UserDTO> findAll() {
		List<User> users = (List<User>) userRepository.findAll();
		List<UserDTO> userDTOS = new ArrayList<>();
		users.stream().forEach(user -> mapToUserDTO(user, userDTOS));
		return userDTOS;
	}

	private void mapToUserDTO(User user, List<UserDTO> userDTOS) {
		UserDTO userDTO = getUserDTO(user);
		userDTOS.add(userDTO);
	}

	private UserDTO getUserDTO(User user) {
		Address address = user.getAddress();
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		AddressDTO addressDTO = new AddressDTO();
		BeanUtils.copyProperties(address, addressDTO);
		userDTO.setAddress(addressDTO);
		return userDTO;
	}

	private void getUserObject(UserDTO userDTO, User user) {
		Address address = new Address();
		AddressDTO addressDTO = userDTO.getAddress();
		BeanUtils.copyProperties(addressDTO, address);
		BeanUtils.copyProperties(userDTO, user);
		user.setAddress(address);
	}
}
