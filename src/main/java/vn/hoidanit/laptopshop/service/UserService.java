package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public String handleHello() {
		return "Hello Service";
	}
	
	public User handleSaveUser(User user) {
		return userRepository.save(user);
	}

	public List<User> fetchAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public List<User> fetchAllUsersByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findAllByEmail(email);
	}
}
