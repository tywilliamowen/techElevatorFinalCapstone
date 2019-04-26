package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.Log.Log;
import com.techelevator.model.Log.LogDAO;
import com.techelevator.model.User.User;
import com.techelevator.model.User.UserDAO;

@Controller
public class LoginController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private LogDAO logDao;
	
	@RequestMapping(value = {"/", "/login"}, method=RequestMethod.GET)
	public String displayLoginForm(HttpSession session) {
		if(session.getAttribute("currentUser") != null) {
			return "redirect:/survey";
		}
		return "login";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST) 
	public String login(@RequestParam String userName, 
						@RequestParam String password, 
						HttpSession session) {
		
		if(userDAO.searchForUsernameAndPassword(userName, password)) {
			session.setAttribute("currentUser", userDAO.getUserByUserName(userName));
			User user = ((User) session.getAttribute("currentUser"));
			
			if(userDAO.isTemporaryPassword(user.getUserNameId())) {
				return "redirect:/changeOneTimePassword";
			}
			
			logDao.insertLog(user.getUserName(), "Successful Login");
			return "redirect:/survey";
		} else {
			logDao.insertLog("Unknown", "Failed Login");
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path="/changeOneTimePassword", method=RequestMethod.GET)
	public String displayChangeOneTimePasswordView(ModelMap map, HttpSession session) {
		
		if(((User) session.getAttribute("currentUser")) != null) {
			return "changeOneTimePassword";
		}
		return "redirect:/login";
	}
	
	@RequestMapping(path="/changeOneTimePassword", method=RequestMethod.POST) 
	public String changeOneTimePassword(@RequestParam String userName, @RequestParam String password, HttpSession session, ModelMap model) {
		
		userDAO.updatePassword(userName, password);
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.insertLog(user.getUserName(), "User Changed One-Time Password");
		
		
		return "redirect:/survey";
		
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.insertLog(user.getUserName(), "User Logged Out");
		
		model.remove("currentUser");
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping(path="/changePassword", method=RequestMethod.POST) 
	public String changePassword(@RequestParam String userName, @RequestParam String password, HttpSession session, ModelMap model) {
		
		userDAO.updatePassword(userName, password);
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.insertLog(user.getUserName(), "User Changed Password");
		
		model.remove("currentUser");
		session.invalidate();
		
		return "redirect:/login";
		
	}
	
	@RequestMapping(path="/setOneTimePassword", method=RequestMethod.POST) 
	public String setOneTimePassword(@RequestParam long userNameId, @RequestParam String password, HttpSession session) {
		
		userDAO.updatePasswordTemporary(userNameId, password);
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.insertLog(user.getUserName(), "Admin Set One-Time Password");
		
		
		return "redirect:/userView";
		
	}
	
	@RequestMapping(path="/log", method=RequestMethod.GET)
	public String displayLogView(ModelMap map, HttpSession session) {
		
		User user = (User) session.getAttribute("currentUser");
		
		if(((User) session.getAttribute("currentUser")).getRole().equals("Admin")) {
			
			List<Log> logList = logDao.getAllLogs();
		
			map.addAttribute("logs", logList);
			
			return "log";
		}
		
		return "redirect:/login";
	}
}
