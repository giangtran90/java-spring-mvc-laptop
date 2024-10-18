package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
	
	private final UserService userService;

	private UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping("/")
	public String getHomePage(Model model) {
		String test = userService.handleHello();
		List<User> listUsers = userService.fetchAllUsers();
		List<User> listUsersByEmail = userService.fetchAllUsersByEmail("2@gmail.com");
		System.out.println(listUsers);
		System.out.println(listUsersByEmail);
		model.addAttribute("test", test);
		model.addAttribute("giang", "test thu view");
		return "test";
	}

	// get all user
	@RequestMapping("/admin/user")
	public String getUserPage(Model model) {
		List<User> users = userService.fetchAllUsers();
		model.addAttribute("listUsers", users);
		return "admin/user/show";
	}

	// get user detail
	@RequestMapping("/admin/user/{id}")
	public String getUserDetailPage(Model model, @PathVariable long id) {
		User user = userService.fetchUserById(id);
		model.addAttribute("id", id);
		model.addAttribute("userDetail", user);
		return "admin/user/detail";
	}
	
	// get page create
	@RequestMapping("/admin/user/create")
	public String getCreateUserPage(Model model) {
		model.addAttribute("newUser", new User());
		return "admin/user/create";
	}

	/*
	 * create new user
	 * @param model : lưu trữ dữ liệu sẽ được truyền đến view (trang web)
	 * @param user : liên kết với một đối tượng User có tên "newUser" trong mô hình (model)
	 * Việc sử dụng @ModelAttribute cho phép Spring tự động tạo ra đối tượng User nếu nó chưa tồn tại trong mô hình.
	 * @return
	 */
	@RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
	public String createUserPage(Model model, @ModelAttribute("newUser") User user) {
		userService.handleSaveUser(user);
		return "redirect:/admin/user";
	}
	
	// get update page
	@RequestMapping("/admin/user/update/{id}")
	public String getUpdateUserPage(Model model, @PathVariable long id) {
		User currentUser = userService.fetchUserById(id);
		model.addAttribute("updateUser", currentUser);
		return "admin/user/update-user";
	}
	
	// update user
	@PostMapping("/admin/user/update")
	public String updateUser(Model model, @ModelAttribute("updateUser") User updateUser) {
		User currentUser = userService.fetchUserById(updateUser.getId());
		if (currentUser != null) {
			currentUser.setFullName(updateUser.getFullName());
			currentUser.setAddress(updateUser.getAddress());
			currentUser.setPhone(updateUser.getPhone());
			this.userService.handleSaveUser(currentUser);
		}
		return "redirect:/admin/user";
	}
	
	// get delete page
	@GetMapping("/admin/user/delete/{id}")
	public String getDeleteUserPage(Model model, @PathVariable long id) {
		User currentUser = userService.fetchUserById(id);
		model.addAttribute("deleteUser", currentUser);
		return "admin/user/delete-user";
	}
	
	// delete user
	@PostMapping("/admin/user/delete")
	public String postDeleteUserPage(Model model, @ModelAttribute("deleteUser") User deleteUser) {
		User currentUser = userService.fetchUserById(deleteUser.getId());
		if (currentUser != null) {
			this.userService.deleteUserById(currentUser.getId());
		}
		return "redirect:/admin/user";
	}	

}