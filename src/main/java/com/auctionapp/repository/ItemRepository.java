package com.auctionapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auctionapp.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

}
