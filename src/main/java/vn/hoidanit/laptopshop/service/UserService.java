package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

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
		return this.userRepository.save(user);
	}

	public List<User> fetchAllUsers() {
		return this.userRepository.findAll();
	}

	public List<User> fetchAllUsersByEmail(String email) {
		return this.userRepository.findAllByEmail(email);
	}

	public User fetchUserById(long id) {
		Optional<User> user = userRepository.findById(id);
		System.out.println(user);
		return user.get();
	}
}
