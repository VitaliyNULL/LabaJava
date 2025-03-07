package com.laba.labajava.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laba.labajava.model.AdDetails;
import com.laba.labajava.model.City;
import com.laba.labajava.model.State;
import com.laba.labajava.service.RiaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final RiaService riaService;

    public ApiController(RiaService riaService) {
        this.riaService = riaService;
    }

    @PostMapping("/search")
    public List<AdDetails> getSearch(@RequestParam("stateId") Integer stateId,
                                     @RequestParam("cityId") Integer cityId,
                                     @RequestParam("priceFrom") Integer priceFrom,
                                     @RequestParam(value = "priceTo", required = false) Integer priceTo) {
        List<AdDetails> ads = new ArrayList<>();
        try {
            // Викликаємо API Ria для пошуку оголошень
            List<Integer> searchResult = riaService.searchAds(stateId, cityId, priceFrom);
            for (Integer id : searchResult) {
                ads.add(riaService.getAdDetails(id));
            }
        } catch (HttpClientErrorException.TooManyRequests e) {
            // Якщо перевищено ліміт запитів, завантажуємо дані з fallback JSON
            System.err.println("Too many requests, loading fallback data.");
            ObjectMapper mapper = new ObjectMapper();
            try (InputStream is = getClass().getResourceAsStream("/data/fallback_data.json")) {
                ads = mapper.readValue(is, new TypeReference<List<AdDetails>>() {
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            try (InputStream is = getClass().getResourceAsStream("/data/fallback_data.json")) {
                ads = mapper.readValue(is, new TypeReference<List<AdDetails>>() {
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return ads;
    }

    @GetMapping("/oblasts")
    public List<State> getOblasts() {
        return riaService.getStates();
    }

    @GetMapping("/cities")
    public List<City> getCities(@RequestParam("stateId") Integer stateId) {
        return riaService.getCities(stateId);
    }
}
