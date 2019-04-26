package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.Log.LogDAO;
import com.techelevator.model.User.User;
import com.techelevator.model.User.UserDAO;

@Controller
public class UserController {


	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private LogDAO logDao;
	
	@RequestMapping(path="/userView", method=RequestMethod.GET)
	public String displayUserView(ModelMap map, HttpSession session) {
		
		if(((User) session.getAttribute("currentUser")).getRole().equals("Admin")) {
			
			List<User> userList = userDAO.getAllUsers();
			
			map.addAttribute("users", userList);
			
			return "userView";
		}
		
		return "redirect:/login";
	}
	
	@RequestMapping(path="/userView", method=RequestMethod.POST)
	public String addUser(@RequestParam String userName, @RequestParam String password, @RequestParam String role, HttpSession session) {
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.insertLog(user.getUserName(), "Admin Added New User");
		
		userDAO.saveUser(userName, password, role);
		
		return "redirect:/userView";
	}
	
	@RequestMapping(path="/deleteUser", method=RequestMethod.POST)
	public String addUser(@RequestParam long id, HttpSession session) {
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.insertLog(user.getUserName(), "Admin Deleted User");
		
		userDAO.deleteUser(id);
		
		return "redirect:/userView";
	}
	
	@RequestMapping(path="/editUser", method=RequestMethod.POST)
	public String editUser(@RequestParam long id, @RequestParam String role, HttpSession session) {
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.insertLog(user.getUserName(), "Admin Changed User Role");
		
		if(role.equals("Admin")) {
			userDAO.updateRole("User", id);
		} else {
			userDAO.updateRole("Admin", id);
		}
		
		return "redirect:/userView";
	}
	
	
}
