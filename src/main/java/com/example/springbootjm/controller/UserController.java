package com.example.springbootjm.controller;

import com.example.springbootjm.model.Role;
import com.example.springbootjm.model.User;
import com.example.springbootjm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

	//Success +
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String showAdmin(ModelMap model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user_authentication) {
		List<User> allUsers = userService.listUsers();
		User newUser = new User();
		User user = userService.getUserByName(user_authentication.getUsername());
		List<Role> roles = userService.allRoles();
		Set<Role> rolesString = userService.allRolesString();
		model.addAttribute("allRoles", roles);
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("user_authentication", user);
		model.addAttribute("newUser", newUser);
		model.addAttribute("allRolesString", rolesString);
		return "admin";
	}

	//Success +
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String showUser(ModelMap model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user_authentication){
		User findUserByUsername = userService.getUserByName(user_authentication.getUsername());
		User user = userService.getUserByName(user_authentication.getUsername());
		List<Role> roles = userService.allRoles();
		model.addAttribute("allRoles", roles);
		model.addAttribute("findUserByUsername", findUserByUsername);
		model.addAttribute("user_authentication", user);
		return "user";
	}

	//Success +
//	@RequestMapping(value = "admin/new", method = RequestMethod.GET)
//	public String showFormNewUser(Model model) {
//		model.addAttribute("user", new User());
//		return "new";
//	}

	//Success +
	@PostMapping(value = "/add")
	public String create(@ModelAttribute("user") User user) {
		userService.add(user);
		return "redirect:/admin/";
	}

	Role role;

	@PostMapping(value = "/addNew")
	public String addNew (@RequestParam("firstName") String firstName,
						  @RequestParam("lastName") String lastName,
						  @RequestParam("department") String department,
						  @RequestParam("email") String email,
						  @RequestParam("username") String username,
						  @RequestParam("password") String password,
						  @RequestParam("roles") String[] roles) {
		userService.add(saveUserAsUser(firstName, lastName, department, email, username, password, String.join(" ", roles)));
	return "redirect:/admin/";
	}

	//Success +
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

	//Success +
	@PostMapping("admin/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/admin/";
	}

	//Success +
	@PostMapping("admin/edit/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		User user = userService.showUser(id);
		model.addAttribute("userUpdate", user);
		return "redirect:/admin/";
//		return "new";
	}

	private User saveUserAsUser(String firstName,
								String lastName,
								String department,
								String email,
								String username,
								String password,
								String roles) {
//		Set<Role> rolesSet = Arrays.stream(roles.split(" "))
//				.map(Role::new)
//				.collect(Collectors.toSet());
		Set<Role> rolesSet = new HashSet<>();
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDepartment(department);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		user.setRoles(rolesSet);
		return user;
	}
}