package com.epam.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.spring.common.MessageProperties;
import com.epam.spring.dto.UserDto;
import com.epam.spring.exception.CustomGenericException;
import com.epam.spring.model.User;
import com.epam.spring.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController extends GlobalExceptionController{
	
	@Autowired
	private MessageProperties messageProperties;
	
	public void setMessageProperties(MessageProperties messageProperties) {
		this.messageProperties = messageProperties;
	}
	
	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String getUserList(Model model) throws Exception {
		List<User> userlist = userService.getAllUsers();
		model.addAttribute("userlist", userlist);
		return "userlist";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable("id") Integer userId, Model model) throws Exception {
		User user = userService.getUserById(userId);
		if (user != null) {
			model.addAttribute("user", user);
			return "userpage";
		} else {
					throw new CustomGenericException(messageProperties.getMessage("error.code.User.GetUser"), 
																					   messageProperties.getMessage("error.User.LinkIsNull"));
		}
	}

	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String addUser(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "signup";
	}

	@RequestMapping(value ="/reg", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public String saveUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("bindingResult", bindingResult);
			return "signup";
		}
		User user = new User(userDto);
		if (userService.getUserByName(user.getUserName())!=null) {
			throw new CustomGenericException(messageProperties.getMessage("error.code.User.SaveUser"), 
					   messageProperties.getMessage("error.User.ObjectAlreadyExist"));
		}
		
		user.setUserRole("ROLE_USER");
		userService.saveOrUpdateUser(user);
		model.addAttribute("user", user);
		return "redirect:/users/" + user.getUserId();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String editUser(@PathVariable("id") Integer userId, Model model) {
		User user = userService.getUserById(userId);
		if (user == null) {
			throw new CustomGenericException(messageProperties.getMessage("error.code.User.EditUser"), 
					   messageProperties.getMessage("error.User.LinkIsNull"));
		}
		UserDto userDto = new UserDto(userService.getUserById(userId));
		model.addAttribute("user", userDto);
		return "signup";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public String updateUser(@PathVariable("id") Integer userId, @ModelAttribute("user") @Valid UserDto userDto,
			BindingResult bindingResult, Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute("bindingResult", bindingResult);
			return "signup";
		}
		User user = userService.getUserById(userId);
		
		user.setUserName(userDto.getUserName());
		user.setUserPassword(userDto.getUserPassword());
		user.setUserMail(userDto.getUserMail());
		try {	
			userService.saveOrUpdateUser(user);
		} catch (Exception e) {
			throw new CustomGenericException(messageProperties.getMessage("error.code.User.UpdateUser"), 
				   messageProperties.getMessage("error.User.ObjectAlreadyExist"));
		}
			
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User authUser = userService.getUserByName(auth.getName());
		if((user.getUserRole().equals("ROLE_USER")) && (authUser.getUserRole().equals("ROLE_ADMIN"))) {
			model.addAttribute("user", user);
			return "redirect:/users/" + userId;
		}
		return "redirect:/logout";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteUser(@PathVariable("id") Integer userId, Model model) {
		User user = userService.getUserById(userId);
		if (user != null) {
			userService.deleteUser(user);
			model.addAttribute("userlist", userService.getAllUsers());
			return "redirect:/users/";
		} else {
			throw new CustomGenericException(messageProperties.getMessage("error.code.User.DeleteUser"), 
					   messageProperties.getMessage("error.User.LinkIsNull"));
		}
	}
}
