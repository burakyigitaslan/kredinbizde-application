package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.repository.CampaignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private CampaignService campaignService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCampaign_Success() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);
        campaign.setTitle("Test Campaign");
        campaign.setContent("Test Content");
        campaign.setDueDate(LocalDate.now().plusDays(7));

        when(campaignRepository.save(campaign)).thenReturn(campaign);

        Campaign savedCampaign = campaignService.createCampaign(campaign);

        assertNotNull(savedCampaign);
        assertEquals(1L, savedCampaign.getId());

        verify(campaignRepository, times(1)).save(campaign);
    }

    @Test
    void getAllCampaigns_Success() {
        Campaign campaign1 = new Campaign();
        campaign1.setId(1L);
        campaign1.setTitle("Test Campaign 1");

        Campaign campaign2 = new Campaign();
        campaign2.setId(2L);
        campaign2.setTitle("Test Campaign 2");

        when(campaignRepository.findAll()).thenReturn(Arrays.asList(campaign1, campaign2));

        List<Campaign> campaigns = campaignService.getAllCampaigns();

        assertNotNull(campaigns);
        assertEquals(2, campaigns.size());

        verify(campaignRepository, times(1)).findAll();
    }

    @Test
    void getCampaignById_Success() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);
        campaign.setTitle("Test Campaign");

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));

        Optional<Campaign> foundCampaign = campaignService.getCampaignById(1L);

        assertNotNull(foundCampaign);
        assertEquals(campaign.getId(), foundCampaign.get().getId());

        verify(campaignRepository, times(1)).findById(1L);
    }

    @Test
    void getAllCampaignsOrderByCreatedDateDesc_Success() {
        Campaign campaign1 = new Campaign();
        campaign1.setId(1L);
        campaign1.setTitle("Test Campaign 1");
        campaign1.setCreatedDate(LocalDate.now());

        Campaign campaign2 = new Campaign();
        campaign2.setId(2L);
        campaign2.setTitle("Test Campaign 2");
        campaign2.setCreatedDate(LocalDate.now().minusDays(1));

        when(campaignRepository.findAllByOrderByCreatedDateDesc()).thenReturn(Arrays.asList(campaign1, campaign2));

        List<Campaign> campaigns = campaignService.getAllCampaignsOrderByCreatedDateDesc();

        assertNotNull(campaigns);
        assertEquals(2, campaigns.size());

        assertEquals(campaign1.getId(), campaigns.get(0).getId());
        assertEquals(campaign2.getId(), campaigns.get(1).getId());

        verify(campaignRepository, times(1)).findAllByOrderByCreatedDateDesc();
    }

    @Test
    void getCampaignsByCreditCardName_Success() {
        Campaign campaign1 = new Campaign();
        campaign1.setId(1L);
        campaign1.setTitle("Test Campaign 1");

        Campaign campaign2 = new Campaign();
        campaign2.setId(2L);
        campaign2.setTitle("Test Campaign 2");

        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(campaign1);
        campaigns.add(campaign2);

        when(campaignRepository.findAllByCreditCard_Name("Test Credit Card")).thenReturn(Optional.of(campaigns));

        List<Campaign> retrievedCampaigns = campaignService.getCampaignsByCreditCardName("Test Credit Card");

        assertEquals(2, retrievedCampaigns.size());
        assertEquals("Test Campaign 1", retrievedCampaigns.get(0).getTitle());
        assertEquals("Test Campaign 2", retrievedCampaigns.get(1).getTitle());

        verify(campaignRepository, times(1)).findAllByCreditCard_Name("Test Credit Card");
    }

    @Test
    void updateCampaign_Success() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);
        campaign.setTitle("Test Campaign");

        when(campaignRepository.save(campaign)).thenReturn(campaign);

        Campaign updatedCampaign = campaignService.updateCampaign(campaign);

        assertNotNull(updatedCampaign);
        assertEquals(campaign.getId(), updatedCampaign.getId());

        verify(campaignRepository, times(1)).save(campaign);
    }

    @Test
    void deleteCampaign_Success() {
        Long campaignId = 1L;

        campaignService.deleteCampaign(campaignId);

        verify(campaignRepository, times(1)).deleteById(campaignId);
    }
}

