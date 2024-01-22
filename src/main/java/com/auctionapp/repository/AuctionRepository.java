package com.auctionapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auctionapp.model.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String> {

}
