package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.repository.CampaignRepository;
import com.patika.kredinbizdeservice.service.interfaces.ICampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService implements ICampaignService {

    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    @Override
    public Optional<Campaign> getCampaignById(Long id) {
        return campaignRepository.findById(id);
    }

    @Override
    public List<Campaign> getAllCampaignsOrderByCreatedDateDesc() {
        return campaignRepository.findAllByOrderByCreatedDateDesc();
    }

    @Override
    public List<Campaign> getCampaignsByCreditCardName(String creditCardName){
        Optional<List<Campaign>> foundCampaigns = campaignRepository.findAllByCreditCard_Name(creditCardName);

        List<Campaign> campaigns = null;

        if (foundCampaigns.isPresent()) {
            campaigns = foundCampaigns.get();
        }

        return campaigns;
    }

    @Override
    public Campaign updateCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }


    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}
