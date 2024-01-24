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
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

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
	public String WinningBidName = "";
	public Integer WinningPrice = null;
	Map<String, String> winDtlMap = new HashMap<>();

	@PersistenceContext
	private EntityManager entityManager;
	private static final Logger LOGGER = LogManager.getLogger(AuctionService.class);

	public ArrayList<Item> displayAuctionitems() {
		Query subQry = entityManager.createQuery("Select auctionItm from Auction where auctionStatus!='closed'");
		List<String> subQryitmLst = subQry.getResultList();
		Query displayAuctionitemsQry = entityManager
				.createQuery("SELECT itmId,itmName,itmDesc FROM Item t1 where itmId not in (:itmIds)")
				.setParameter("itmIds", subQryitmLst);
		ArrayList<Item> dsplyItmLst = (ArrayList<Item>) displayAuctionitemsQry.getResultList();
		ArrayList<Item> dsplyItmLst2 = (ArrayList<Item>) itmRepository.findAll();
		ArrayList<Item> newLst = (ArrayList<Item>) displayAuctionitemsQry.getResultList();
		System.out.println("---------------------------------" + dsplyItmLst.size());

		ArrayList<Item> result = (ArrayList<Item>) displayAuctionitemsQry.getResultList();
		Iterator itr = result.iterator();

		while (itr.hasNext()) {
			Item tmpitm;
			Object[] obj = (Object[]) itr.next();
			String tmpItmid = (String) obj[0];
			String tmpItmid1 = (String) obj[1];
			String tmpItmid2 = (String) obj[2];
			tmpitm = new Item(tmpItmid, tmpItmid1, tmpItmid2);
			newLst.add(tmpitm);
			

		}
		System.out.println("---------------------------------1 " + newLst.get(0).getItmId());
		return dsplyItmLst2;
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

			if (finalistsBids.get(i).getBidPrice() > itmMinPrice) {
				String tmp1 = finalistsBids.get(i).getBidId();
				Integer tmp2 = finalistsBids.get(i).getBidPrice();
				Timestamp tmp3 = finalistsBids.get(i).getBidTs();
				Bid newBid = new Bid(tmp1, tmp2, tmp3);
				sortedBids.add(newBid);
			}
		}
		Collections.sort(sortedBids, new Comparator<Bid>() {
			public int compare(Bid o1, Bid o2) {
				int c = o1.getBidTs().compareTo(o2.getBidTs());
				if (c != 0)
					c = o1.getBidPrice().compareTo(o2.getBidPrice());
				return o1.getBidPrice().compareTo(o2.getBidPrice());
			}
		});
		for (int i = 0; i < sortedBids.size(); i++) {
			LOGGER.info("********************************* sorted ID " + sortedBids.get(i).getBidId() + " "
					+ sortedBids.get(i).getBidPrice() + " " + " " + sortedBids.get(i).getBidTs());
		}

		int sortedBidsSize = sortedBids.size();
		LOGGER.info("********************************* size  " + sortedBidsSize);
		Bid firstBid = sortedBids.get(sortedBidsSize - 1);
		Bid secondBid = sortedBids.get(sortedBidsSize - 2);
		LOGGER.info("********************************* winningbidssssss " + firstBid.getBidPrice() + " "
				+ secondBid.getBidPrice());
		LOGGER.info("********************************* winningbidssssss " + firstBid.getBidTs() + " "
				+ secondBid.getBidTs());
		if (firstBid.getBidPrice() == secondBid.getBidPrice()) {
			if (firstBid.getBidTs().before(secondBid.getBidTs())) {
				WinningBidId = firstBid.getBidId();
			} else if (secondBid.getBidTs().before(firstBid.getBidTs())) {
				WinningBidId = secondBid.getBidId();
			}
		} else {
			WinningBidId = firstBid.getBidId();
		}
		LOGGER.info("********************************* winningbid " + WinningBidId);
		Bid bid1 = getWinnerDetails(WinningBidId, model);
		return bid1;

	}

	private Bid getWinnerDetails(String WinningBid, Model model) {

		Query getWinnerDtlsQry = entityManager
				.createQuery("SELECT bidId,bidPrice,bidItm FROM Bid t1 where bidId in (:bidId)")
				.setParameter("bidId", WinningBid);
		List winnerLst = getWinnerDtlsQry.getResultList();

		System.out.println(winnerLst.size());
		Bid bid = null;
		for (Object record : winnerLst) {
			Object[] fields = (Object[]) record;

			String id = (String) fields[0];
			Integer name = (Integer) fields[1];
			System.out.printf("id: %s, name: %s%n", id, name);
			bid = new Bid((String) fields[0], (Integer) fields[1], (String) fields[0]);
		}

		return bid;

	}

}
