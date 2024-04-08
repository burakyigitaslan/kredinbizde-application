package com.patika.kredinbizdeservice.service.interfaces;

import com.patika.kredinbizdeservice.model.Campaign;

import java.util.List;
import java.util.Optional;

public interface ICampaignService {
    Campaign createCampaign(Campaign campaign);
    List<Campaign> getAllCampaigns();
    Optional<Campaign> getCampaignById(Long id);
    List<Campaign> getAllCampaignsOrderByCreatedDateDesc();
    List<Campaign> getCampaignsByCreditCardName(String creditCardName);
    Campaign updateCampaign(Campaign campaign);
    void deleteCampaign(Long id);
}
