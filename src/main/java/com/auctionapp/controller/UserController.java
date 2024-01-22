package com.auctionapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.auctionapp.model.Users;

@Controller
public class UserController {

	@GetMapping(value = "/")
	public String displayHomePage() {
		System.out.println("---------------------------------DISPLAYING HOME PAGE");
		return "home";
	}

	@PostMapping(value = "/login")
	public ModelAndView postMsg(@ModelAttribute("loginForm") Users usr, Model model) {
		HttpStatus postStatus = HttpStatus.BAD_REQUEST;
		Map<String, String> usrMap = new HashMap<>();
		System.out.println("---------------------------------INSIDE LOGIN ");

		usrMap.put("usrId", usr.getUsrId());
		usrMap.put("usrRole", usr.getUsrRole());

		System.out.println(usr.getUsrId() +  usr.getUsrRole() );
		if (usr.getUsrRole().equals("seller")) {
			return new ModelAndView("seller", usrMap);
		} else if (usr.getUsrRole().equals("buyer")) {
			return new ModelAndView("auction", usrMap);
		} else {
			return new ModelAndView("error");
		}
	}

}
