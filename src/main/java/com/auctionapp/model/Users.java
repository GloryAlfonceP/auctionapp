package com.auctionapp.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String usrToken;
	@Column(unique=true)
	@Nonnull
	public String usrId;
	@Column
	public String usrName;
	@Column
	public String usrRole;
	@Column
	public Double usrBalance;

	public Users(String usrToken, String usrId, String usrName, String usrRole, Double usrBalance) {
		super();
		this.usrToken = usrToken;
		this.usrId = usrId;
		this.usrName = usrName;
		this.usrRole = usrRole;
		this.usrBalance = usrBalance;
	}

	public String getUsrToken() {
		return usrToken;
	}

	public void setUsrToken(String usrToken) {
		this.usrToken = usrToken;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrRole() {
		return usrRole;
	}

	public void setUsrRole(String usrRole) {
		this.usrRole = usrRole;
	}

	public Double getUsrBalance() {
		return usrBalance;
	}

	public void setUsrBalance(Double usrBalance) {
		this.usrBalance = usrBalance;
	}

}
