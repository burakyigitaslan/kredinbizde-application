package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    public List<Campaign> findAllByOrderByCreatedDateDesc();

    public Optional<List<Campaign>> findAllByCreditCard_Name(String creditCardName);
}
