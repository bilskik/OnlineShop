package com.bilskik.onlineshop.repositories;

import com.bilskik.onlineshop.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.channels.SelectableChannel;

@Repository
public interface ServiceRepository extends JpaRepository<Seller,Integer> {
}
