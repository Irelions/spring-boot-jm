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
	@PostMapping(value = "/add")
	public String create(@ModelAttribute("user") User user, @RequestParam ("roles") String[] roles) {
		for(String role : roles) {
			if(role.toLowerCase().contains("admin")){
				user.setRoles(Set.of(new Role(1L, role)));
			}
			if(role.toLowerCase().contains("user")){
				user.setRoles(Set.of(new Role(2L, role)));
			}
		}
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
		model.addAttribute("userUpdate", user);
		return "redirect:/admin/";
	}
}