package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        return new ResponseEntity<>(campaigns, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id) {
        Campaign campaign = campaignService.getCampaignById(id)
                .orElse(null);
        return campaign != null ? ResponseEntity.ok(campaign) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        Campaign createdCampaign = campaignService.createCampaign(campaign);
        return new ResponseEntity<>(createdCampaign, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable Long id, @RequestBody Campaign campaign) {
        campaign.setId(id);
        Campaign updatedCampaign = campaignService.updateCampaign(campaign);
        return new ResponseEntity<>(updatedCampaign, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orderByDateDesc")
    public ResponseEntity<List<Campaign>> getAllCampaignsOrderByCreatedDateDesc() {
        List<Campaign> campaigns = campaignService.getAllCampaignsOrderByCreatedDateDesc();
        return new ResponseEntity<>(campaigns, HttpStatus.OK);
    }

    @GetMapping("/byCreditCardName/{creditCardName}")
    public ResponseEntity<List<Campaign>> getCampaignsByCreditCardName(@PathVariable String creditCardName) {
        List<Campaign> campaigns = campaignService.getCampaignsByCreditCardName(creditCardName);
        return campaigns.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(campaigns);
    }
}