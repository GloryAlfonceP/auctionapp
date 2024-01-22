package com.auctionapp.model;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bid")
public class Bid {
	@Id
	@Column
	public String bidId; // [pk]
	@Column
	public String bidItm; // [fk]
	@Column
	public String bidUsr; // [fk]
	@Column
	public Integer bidPrice;
	@Column
	public Timestamp bidTs;
	@Column
	public Boolean isSuccess;

	public Bid(String bidId, String bidItem, String bidUser, Integer bidprice, Timestamp b_ts, Boolean isSuccess) {
		super();
		this.bidId = bidId;
		this.bidItm = bidItem;
		this.bidUsr = bidUser;
		this.bidPrice = bidprice;
		this.bidTs = b_ts;
		this.isSuccess = isSuccess;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public String getBidItem() {
		return bidItm;
	}

	public void setBidItem(String bidItem) {
		this.bidItm = bidItem;
	}

	public String getBidUser() {
		return bidUsr;
	}

	public void setBidUser(String bidUser) {
		this.bidUsr = bidUser;
	}

	public Integer getBidprice() {
		return bidPrice;
	}

	public void setBidprice(Integer bidprice) {
		this.bidPrice = bidprice;
	}

	public Timestamp getB_ts() {
		return bidTs;
	}

	public void setB_ts(Timestamp b_ts) {
		this.bidTs = b_ts;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
