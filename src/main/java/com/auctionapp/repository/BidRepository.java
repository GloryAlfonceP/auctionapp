package com.auctionapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auctionapp.model.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, String> {
	List<Bid> findAllByOrderByBidPriceDesc();

	List<Bid> findByOrderByBidPriceDesc();
}
