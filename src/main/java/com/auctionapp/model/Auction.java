package com.auctionapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Auction")
public class Auction {
	@Id
	@Column
	public String auctionId;
	@Column
	public String auctionItm;

	@Column
	public String seller;
	@Column
	public String bidId; // [fk]
	@Column
	public String winningBid;
	@Column
	public String buyer; // [fk]
	@Column
	public String payment;

	public Auction(String auctionId, String auctionItm, Integer minPrice, String seller, String bidId,
			String winningBid, String buyer, String payment) {
		super();
		this.auctionId = auctionId;
		this.auctionItm = auctionItm;

		this.seller = seller;
		this.bidId = bidId;
		this.winningBid = winningBid;
		this.buyer = buyer;
		this.payment = payment;
	}

	public String getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}

	public String getAuctionItm() {
		return auctionItm;
	}

	public void setAuctionItm(String auctionItm) {
		this.auctionItm = auctionItm;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public String getWinningBid() {
		return winningBid;
	}

	public void setWinningBid(String winningBid) {
		this.winningBid = winningBid;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

}
