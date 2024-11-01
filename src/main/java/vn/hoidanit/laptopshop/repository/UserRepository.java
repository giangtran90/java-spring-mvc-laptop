package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User save(User user);
	// Goi tuong minh tham khao tai lieu bai #61(udemy)
	List<User> findAllByEmail(String email);
	User findByEmail(String email);
}
