package com.auctionapp.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.auctionapp.dao.AuctionDao;
import com.auctionapp.model.Bid;
import com.auctionapp.model.Item;
import com.auctionapp.model.Users;
import com.auctionapp.repository.BidRepository;
import com.auctionapp.repository.ItemRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuctionService implements AuctionDao {

	@Autowired
	ItemRepository itmRepository;

	@Autowired
	BidRepository bidRepository;

	public String WinningBidId = "";
	Map<String, String> winDtlMap = new HashMap<>();

	@PersistenceContext
	private EntityManager entityManager;
	private static final Logger LOGGER = LogManager.getLogger(AuctionService.class);

	public ArrayList<Item> displayAuctionitems() {

		Query displayAuctionitemsQry = entityManager
				.createQuery("SELECT itmId,itmName,itmDesc FROM Item t1 where auctionStatus not in ('closed')");
		ArrayList<Item> newLst = (ArrayList<Item>) displayAuctionitemsQry.getResultList();
		Iterator itr = newLst.iterator();
		ArrayList<Item> resuldLst = new ArrayList<Item>();
		Item itm;
		while (itr.hasNext()) {

			Object[] obj = (Object[]) itr.next();
			String tmpItmid = (String) obj[0];
			String tmpItmid1 = (String) obj[1];
			String tmpItmid2 = (String) obj[2];
			itm = new Item(tmpItmid, tmpItmid1, tmpItmid2);
			resuldLst.add(itm);
		}
		return resuldLst;
	}

	public void postItmToSale(Item itm) {
		try {
			itmRepository.save(itm);
			LOGGER.info("*********************************INSERT ITEM to SELL SUCCESS");
		} catch (Exception e) {
			LOGGER.error("*********************************INSERT ITEM to SELL FAIL");

		}
	}

	public void postBid(Bid bd, Users usr) {
		int max = 10000;
		int min = 10;
		int range = max - min + 1;
		int randomWithMathRandom = (int) (Math.random() * range) + min;
		Date date = new Date();
		String bidId = "b" + randomWithMathRandom;
		Query insertBidQry = entityManager.createQuery("insert into Bid (bidPrice,bidTs,bidId,bidItm,bidUsr) values "
				+ "(:bidPrice,:ts,:bidId,:bidItm,:bidUsr)");
		insertBidQry.setParameter("bidPrice", bd.getBidPrice());
		insertBidQry.setParameter("ts", new Timestamp(date.getTime()));
		insertBidQry.setParameter("bidId", bidId);
		insertBidQry.setParameter("bidItm", bd.getBidItm());
		insertBidQry.setParameter("bidUsr", usr.getUsrToken());
		try {
			insertBidQry.executeUpdate();
			LOGGER.info("*********************************INSERT BID SUCCESS");
		} catch (Exception e) {

			LOGGER.error("*********************************INSERT BID FAIL");
		}
	}

	public Bid calcWinner(Item itm, Users usr, Bid bd, Model model, HttpSession session) {
		LOGGER.info("********************************* inside CalcWinner");
		Integer itmMinPrice = itmRepository.findById((String) session.getAttribute("bidItm")).get().minPrice;
		List<Bid> finalistsBids = bidRepository.findByOrderByBidPriceDesc();// new ArrayList<Bid>();

		List<Bid> sortedBids = new ArrayList<Bid>();
		for (int i = 0; i < finalistsBids.size(); i++) {
			// check if bid_price > min_price of an item
			if (finalistsBids.get(i).getBidPrice() > itmMinPrice) {
				String tmp1 = finalistsBids.get(i).getBidId();
				Integer tmp2 = finalistsBids.get(i).getBidPrice();
				Timestamp tmp3 = finalistsBids.get(i).getBidTs();
				Bid newBid = new Bid(tmp1, tmp2, tmp3);
				sortedBids.add(newBid);
			}
		}
		// Sort bid prices
		Collections.sort(sortedBids, new Comparator<Bid>() {
			public int compare(Bid o1, Bid o2) {
				int c = o1.getBidTs().compareTo(o2.getBidTs());
				if (c != 0)
					c = o1.getBidPrice().compareTo(o2.getBidPrice());
				return o1.getBidPrice().compareTo(o2.getBidPrice());
			}
		});
		// log data
		for (int i = 0; i < sortedBids.size(); i++) {
			LOGGER.info("********************************* sorted ID " + sortedBids.get(i).getBidId() + " "
					+ sortedBids.get(i).getBidPrice() + " " + " " + sortedBids.get(i).getBidTs());
		}
		int sortedBidsSize = sortedBids.size();
		Bid firstBid = sortedBids.get(sortedBidsSize - 1);
		Bid secondBid = sortedBids.get(sortedBidsSize - 2);
		LOGGER.info("********************************* 1stwinningbids " + firstBid.getBidPrice() + " "
				+ firstBid.getBidTs() + " " + firstBid.getBidId());
		LOGGER.info("********************************* 2ndwinningbids " + secondBid.getBidPrice() + " "
				+ secondBid.getBidTs() + " " + secondBid.getBidId());
		// Compare bid timestamp
		if (firstBid.getBidPrice().equals(secondBid.getBidPrice())) {

			if (secondBid.getBidTs().before(firstBid.getBidTs())) {

				WinningBidId = secondBid.getBidId();
			} else if (secondBid.getBidTs().after(firstBid.getBidTs())) {

				WinningBidId = firstBid.getBidId();
			}
		} else {
			WinningBidId = firstBid.getBidId();
		}
		LOGGER.info("********************************* winningbid " + WinningBidId + session.getAttribute("usrId"));
		markItemSold(itm, WinningBidId);
		Bid bid1 = getWinnerDetails(WinningBidId, model);
		return bid1;

	}

	private Bid getWinnerDetails(String WinningBid, Model model) {

		Query getWinnerDtlsQry = entityManager
				.createQuery("SELECT bidId,bidPrice,bidItm,bidUsr FROM Bid t1 where bidId in (:bidId)")
				.setParameter("bidId", WinningBid);
		List winnerLst = getWinnerDtlsQry.getResultList();
		Bid bid = null;
		for (Object record : winnerLst) {
			Object[] fields = (Object[]) record;
			String id = (String) fields[0];
			Integer name = (Integer) fields[1];
			String id1 = (String) fields[2];
			String id2 = (String) fields[3];
			System.out.println(id + name + id1 + id2);
			bid = new Bid((String) fields[0], (Integer) fields[1], (String) fields[2], (String) fields[3]);
		}
		return bid;
	}

	public void closeBid(@ModelAttribute("closeForm") Item itm) {
		Query closeBidQry = entityManager.createQuery("update Item set auctionStatus = 'closed' where itmId = (:Id)");

		closeBidQry.setParameter("Id", itm.getItmId());
		closeBidQry.executeUpdate();
		System.out.println("---------------------------------INSIDE closeBid Success");

	}

	public void markItemSold(@ModelAttribute("closeForm") Item itm, String winBid) {
		Query markItmQry = entityManager.createQuery("update Item set auctionStatus = 'closed' where itmId = (:Id)");
		markItmQry.setParameter("Id", itm.getItmId());
		markItmQry.executeUpdate();
		Query mrkBidQry = entityManager.createQuery("update Bid set isSuccess = true where bidId = (:bId)");
		mrkBidQry.setParameter("bId", winBid);
		mrkBidQry.executeUpdate();
		System.out.println("---------------------------------INSIDE markItemSold Items Marked Success");

	}
}
