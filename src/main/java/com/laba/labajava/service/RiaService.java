package com.laba.labajava.service;

import com.laba.labajava.model.City;
import com.laba.labajava.model.SearchResponse;
import com.laba.labajava.model.State;
import com.laba.labajava.model.AdDetails;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RiaService {

    private final RestTemplate restTemplate;
    private final String API_KEY = "c31ytO7KgJCzCKH0bKRPtEe9DsKkXJHlBZtFTLci";
    private final String BASE_URL = "https://developers.ria.com/dom";

    public RiaService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<State> getStates() {
        String url = BASE_URL + "/states?api_key=" + API_KEY;
        State[] states = restTemplate.getForObject(url, State[].class);
        return Arrays.asList(states);
    }

    public List<City> getCities(Integer stateId) {
        String url = BASE_URL + "/cities/" + stateId + "?api_key=" + API_KEY;
        City[] cities = restTemplate.getForObject(url, City[].class);
        return Arrays.asList(cities);
    }

    public List<Integer> searchAds(Integer stateId, Integer cityId, Integer priceFrom) {
        String url = BASE_URL + "/search?category=1&realty_type=2&operation=3"
                + "&state_id=" + stateId + "&city_id=" + cityId
                + "&price_from=" + priceFrom + "&price_cur=3"
                + "&api_key=" + API_KEY;
        System.out.println("Запит до Ria API: " + url);
        try {
            SearchResponse response = restTemplate.getForObject(url, SearchResponse.class);
            if (response == null || response.getItems() == null) {
                throw new RuntimeException("Відповідь від Ria API порожня");
            }
            return response.getItems();
        } catch (HttpClientErrorException.TooManyRequests e) {
            // Обробка помилки 429
            System.err.println("Перевищено ліміт запитів до Ria API: " + e.getMessage());
            throw e; // або поверніть порожній список / спеціальне повідомлення
        }
    }

    public AdDetails getAdDetails(Integer adId) {
        String url = BASE_URL + "/info/" + adId + "?api_key=" + API_KEY;
        return restTemplate.getForObject(url, AdDetails.class);
    }

}
