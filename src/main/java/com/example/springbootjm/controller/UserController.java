package com.example.springbootjm.controller;

import com.example.springbootjm.model.User;
import com.example.springbootjm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

	//Success +
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String showAdmin(ModelMap model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user_authentication) {
		List<User> allUsers = userService.listUsers();
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("user_authentication", user_authentication.getUsername());
		return "admin";
	}

	//Success +
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String showUser(ModelMap model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user_authentication){
		User findUserByUsername = userService.getUserByName(user_authentication.getUsername());
		model.addAttribute("findUserByUsername", findUserByUsername);
		model.addAttribute("user_authentication", user_authentication.getUsername());
		return "user";
	}

	//Success +
	@RequestMapping(value = "admin/new", method = RequestMethod.GET)
	public String showFormNewUser(Model model) {
		model.addAttribute("user", new User());
		return "new";
	}

	//Success +
	@PostMapping(value = "/add")
	public String create(@ModelAttribute("user") User user) {
		userService.add(user);
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
		model.addAttribute("user", user);
		return "new";
	}
}