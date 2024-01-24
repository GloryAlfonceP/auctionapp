package com.auctionapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.auctionapp.model.Bid;
import com.auctionapp.model.Item;
import com.auctionapp.model.Users;
import com.auctionapp.service.AuctionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuctionController {

	@Autowired
	AuctionService auctionService;
	ArrayList<Item> dsplyItmLst = new ArrayList<>();

	@GetMapping(value = "/")
	public String displayHomePage() {
		System.out.println("---------------------------------DISPLAYING HOME PAGE");
		return "home";
	}

	@PostMapping(value = "/login")
	public String postMsg(@ModelAttribute("loginForm") Users usr, Model model) {

		HttpStatus postStatus = HttpStatus.BAD_REQUEST;
		Map<String, String> usrMap = new HashMap<>();

		System.out.println("---------------------------------INSIDE USERLOGIN Method");
		dsplyItmLst = auctionService.displayAuctionitems();
	
		model.addAttribute("usrRole", usr.getUsrRole());
		model.addAttribute("itmLst", dsplyItmLst);
		usrMap.put("usrId", usr.getUsrId());
		usrMap.put("usrRole", usr.getUsrRole());

		if (usr.getUsrRole().equalsIgnoreCase("seller")) {
			return "seller";
		} else if (usr.getUsrRole().equalsIgnoreCase("buyer")) {
			return "auction";
		} else {
			return "error";
		}
	}

	@PostMapping(value = "/postItm")
	public String postItmToSale(@ModelAttribute("sellerForm") Item itm, Model model, HttpSession session) {
		System.out.println("---------------------------------INSIDE postItmToSale Method");
		auctionService.postItmToSale(itm);
		return "seller";
	}

	@PostMapping(value = "/postBid")
	public String postbid(@ModelAttribute("bidform") Bid bd, Users usr, Model model, HttpSession session) {
		System.out.println("---------------------------------INSIDE postbid Method");
		auctionService.postBid(bd, usr);
		model.addAttribute("bidItm", bd.getBidItm());
		model.addAttribute("itmLst", dsplyItmLst);
		return "auction";
	}

	@PostMapping(value = "/removeItmToSell")
	public String removeItmToSale(@ModelAttribute("sellerForm") Item itm, Users usr, Model model, HttpSession session) {
		auctionService.postItmToSale(itm);
		model.addAttribute("bidItm", itm.getItmId());
		System.out.println("---------------------------------INSIDE removeItmToSale Method");
		return "auction";
	}

	@PostMapping(value = "/closeBid")
	public String closeBid(@ModelAttribute("sellerForm") Item itm, Users usr, Model model, HttpSession session) {
		// auctionService.postItmToSale(itm);
		model.addAttribute("bidItm", itm.getItmId());
		System.out.println("---------------------------------INSIDE removeItmToSale Method");
		return "auction";
	}

	@PostMapping(value = "/seeWinner")
	public ModelAndView calcWinner(@ModelAttribute("sellerForm") Item itm, Users usr, Bid bd, Model model,
			HttpSession session) {
		System.out.println("---------------------------------INSIDE calcWinner Method");
		model.addAttribute("bidItm", bd.getBidItm());
		Bid b = auctionService.calcWinner(itm, usr, bd, model, session);
		System.out.println("---------------------------------INSIDE calcWinner Method " + b.getBidId());

		Map<String, String> winnerMap = new HashMap<>();

		winnerMap.put("bidId", b.getBidId());
		String tmp = b.getBidPrice() + "";
		winnerMap.put("bidPrice", tmp);
		winnerMap.put("bidUsr", b.getBidUsr());
		return new ModelAndView("display", winnerMap);

	}
}
