package com.jtspringproject.JtSpringProject.controller;

import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final UserService userService;

	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/index")
	public String index(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("username", username);
		return "index";
	}

	@GetMapping("login")
	public ModelAndView adminlogin(@RequestParam(required = false) String error) {
		ModelAndView mv = new ModelAndView("adminlogin");
		if ("true".equals(error)) {
			mv.addObject("msg", "Invalid username or password. Please try again.");
		}
		return mv;
	}

	@GetMapping(value = { "/", "Dashboard" })
	public ModelAndView adminHome(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mv = new ModelAndView("adminHome");
		mv.addObject("admin", authentication.getName());
		return mv;
	}

	@GetMapping("customers")
	public ModelAndView getCustomerDetail() {
		ModelAndView mView = new ModelAndView("displayCustomers");
		List<User> users = this.userService.getUsers();
		mView.addObject("customers", users);
		return mView;
	}

	@GetMapping("profileDisplay")
	public String profileDisplay(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getUserByUsername(username);
		if (user != null) {
			model.addAttribute("userid", user.getId());
			model.addAttribute("username", user.getUsername());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("password", user.getPassword());
			model.addAttribute("address", user.getAddress());
		} else {
			model.addAttribute("msg", "User not found");
		}
		return "updateProfile";
	}

	@PostMapping("updateuser")
	public String updateUserProfile(@RequestParam int userid, @RequestParam String username, @RequestParam String email,
			@RequestParam String password, @RequestParam String address) {
		User user = userService.getUser(userid);
		if (user != null) {
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setAddress(address);
			userService.updateUser(user);
			Authentication newAuthentication = new UsernamePasswordAuthenticationToken(username, password,
				SecurityContextHolder.getContext().getAuthentication().getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(newAuthentication);
		}
		return "redirect:index";
	}

	@GetMapping("customers/add")
	public String showAddCustomerForm() {
		return "customerAdd";
	}

	@PostMapping("customers/add")
	public String addCustomer(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String address) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setAddress(address);
		userService.addUser(user);
		return "redirect:/admin/customers";
	}

	@GetMapping("customers/edit/{id}")
	public ModelAndView showEditCustomerForm(@org.springframework.web.bind.annotation.PathVariable int id) {
		User customer = userService.getUser(id);
		ModelAndView mView = new ModelAndView("customerEdit");
		mView.addObject("customer", customer);
		return mView;
	}

	@PostMapping("customers/edit/{id}")
	public String editCustomer(@org.springframework.web.bind.annotation.PathVariable int id, @RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String address) {
		User user = userService.getUser(id);
		if (user != null) {
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setAddress(address);
			userService.updateUser(user);
		}
		return "redirect:/admin/customers";
	}

}