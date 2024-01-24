package com.auctionapp.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Bid")
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

	public Bid() {
		super();
	}

	public Bid(String bidId, String bidItem, String bidUser, Integer bidprice, Timestamp b_ts, Boolean isSuccess) {
		super();
		this.bidId = bidId;
		this.bidItm = bidItem;
		this.bidUsr = bidUser;
		this.bidPrice = bidprice;
		this.bidTs = b_ts;
		this.isSuccess = isSuccess;
	}

	public Bid(String bidId, Integer bidPrice, Timestamp bidTs) {
		this.bidId = bidId;

		this.bidPrice = bidPrice;
		this.bidTs = bidTs;
		// TODO Auto-generated constructor stub
	}

	public Bid(String bidId, Integer bidPrice, String bidItm) {
		this.bidId = bidId;

		this.bidPrice = bidPrice;
		this.bidItm = bidItm;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public String getBidItm() {
		return bidItm;
	}

	public void setBidItm(String bidItm) {
		this.bidItm = bidItm;
	}

	public String getBidUsr() {
		return bidUsr;
	}

	public void setBidUsr(String bidUsr) {
		this.bidUsr = bidUsr;
	}

	public Integer getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Integer bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Timestamp getBidTs() {
		return bidTs;
	}

	public void setBidTs(Timestamp bidTs) {
		this.bidTs = bidTs;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
