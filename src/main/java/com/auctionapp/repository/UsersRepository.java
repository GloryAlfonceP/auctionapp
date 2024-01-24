package com.auctionapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auctionapp.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

}
