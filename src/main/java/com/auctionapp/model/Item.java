package com.auctionapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Item")
public class Item {
	@Id
	@Column
	public String itmId;
	@Column
	public String itmName;
	@Column
	public String itmDesc;
	@Column
	public String itmImage;
	@Column
	public String itmOwner;
	@Column
	public Integer minPrice;
	@Column
	public String auctionStatus;

	public Item(String itmId, String itmName, String itmDesc, String itmImage, String itmOwner, Integer minPrice,
			String auctionStatus) {
		super();
		this.itmId = itmId;
		this.itmName = itmName;
		this.itmDesc = itmDesc;
		this.itmImage = itmImage;
		this.itmOwner = itmOwner;
		this.minPrice = minPrice;
		this.auctionStatus = auctionStatus;
	}

	public Item() {

	}

	public String getAuctionStatus() {
		return auctionStatus;
	}

	public void setAuctionStatus(String auctionStatus) {
		this.auctionStatus = auctionStatus;
	}

	public Item(String itmId, String itmName, String itmDesc) {
		super();
		this.itmId = itmId;
		this.itmName = itmName;
		this.itmDesc = itmDesc;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public String getItmId() {
		return itmId;
	}

	public void setItmId(String itmId) {
		this.itmId = itmId;
	}

	public String getItmName() {
		return itmName;
	}

	public void setItmName(String itmName) {
		this.itmName = itmName;
	}

	public String getItmDesc() {
		return itmDesc;
	}

	public void setItmDesc(String itmDesc) {
		this.itmDesc = itmDesc;
	}

	public String getItmImage() {
		return itmImage;
	}

	public void setItmImage(String itmImage) {
		this.itmImage = itmImage;
	}

	public String getItmOwner() {
		return itmOwner;
	}

	public void setItmOwner(String itmOwner) {
		this.itmOwner = itmOwner;
	}

}
