package com.example.bitnbuild.service.imple;

import com.example.bitnbuild.entity.Item;
import com.example.bitnbuild.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getWeatherBasedRecommendations(String weatherCondition,String userEmail) {
        // Sample logic based on weather condition
        return switch (weatherCondition.toLowerCase()) {
            case "summer" -> itemRepository.findByCategoryAndUser_Email("Summer Wear", userEmail);
            case "rainy" -> itemRepository.findByCategoryAndUser_Email("Rain Wear", userEmail);
            case "winter" -> itemRepository.findByCategoryAndUser_Email("Winter Wear", userEmail);
            default -> new ArrayList<>();
        };
    }

    public List<Item> getEventBasedRecommendations(String occasion,String userEmail) {
        return itemRepository.findByOccasionAndUser_Email(occasion, userEmail);
    }
}