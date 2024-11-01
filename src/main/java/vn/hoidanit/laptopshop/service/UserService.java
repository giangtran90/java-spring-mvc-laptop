package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
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

	public void deleteUserById(long id) {
		this.userRepository.deleteById(id);
	}
	
	public Role fetchRoleByName(String name) {
		return roleRepository.findByName(name);
	}
	
	public User RegisterDTOtoUser(RegisterDTO registerDTO) {
		User user = new User();
		user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(registerDTO.getPassword());
		return user;
	}
	
	public User fetchUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
