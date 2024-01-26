package com.auctionapp.service;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.auctionapp.model.Bid;
import com.auctionapp.model.Item;
import com.auctionapp.model.Users;
import com.auctionapp.repository.ItemRepository;
import com.auctionapp.repository.UsersRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class UserService {
	@Autowired
	UsersRepository repo;

	@Autowired
	ItemRepository itmRepository;

	@PersistenceContext
	private EntityManager entityManager;

	private static final Logger LOGGER = LogManager.getLogger(AuctionService.class);

	public String verifyUser(Users usr) {
		// StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		// encryptor.setPassword(seed);

		// String decrypted = encryptor.decrypt(encrypted);F
		return null;

	}

	public void postItmToSale(Item itm) {
		try {

			if (itmRepository.existsById(itm.itmId)) {
				itmRepository.save(itm);
				LOGGER.info("*********************************UPDATE ITEM to SELL SUCCESS");
			} else {
				itmRepository.save(itm);
			}
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

	public void closeBid(@ModelAttribute("closeForm") Item itm) {
		Query closeBidQry = entityManager.createQuery("update Item set auctionStatus = 'closed' where itmId = (:Id)");

		closeBidQry.setParameter("Id", itm.getItmId());
		closeBidQry.executeUpdate();
		System.out.println("---------------------------------INSIDE closeBid Success");

	}

}
