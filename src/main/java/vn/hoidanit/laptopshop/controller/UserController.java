package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
	
	final private UserService userService;

	private UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping("/")
	public String getHomePage(Model model) {
		String test = userService.handleHello();
		model.addAttribute("test", test);
		model.addAttribute("giang", "test thu view");
		return "test";
	}

	@RequestMapping("/admin/user")
	public String getUserPage(Model model) {
		model.addAttribute("newUser", new User());
		return "admin/user/create";
	}

	/*
	 * 
	 * @param model : lưu trữ dữ liệu sẽ được truyền đến view (trang web)
	 * @param user : liên kết với một đối tượng User có tên "newUser" trong mô hình (model)
	 * Việc sử dụng @ModelAttribute cho phép Spring tự động tạo ra đối tượng User nếu nó chưa tồn tại trong mô hình.
	 * @return
	 */
	@RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
	public String createUserPage(Model model, @ModelAttribute("newUser") User user) {
		System.out.println("test ket noi trang" + user);
		return "test";
	}

}
