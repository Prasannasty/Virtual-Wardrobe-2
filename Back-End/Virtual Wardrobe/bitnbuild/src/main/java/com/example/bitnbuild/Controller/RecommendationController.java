package com.example.bitnbuild.Controller;

import com.example.bitnbuild.entity.Item;
import com.example.bitnbuild.service.imple.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {


    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/weather")
    public ResponseEntity<List<Item>> getWeatherRecommendations(@RequestParam String weather, @AuthenticationPrincipal UserDetails currentUser) {
        List<Item> recommendations = recommendationService.getWeatherBasedRecommendations(weather,currentUser.getUsername());
        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/event")
    public ResponseEntity<List<Item>> getEventRecommendations(@RequestParam String occasion,@AuthenticationPrincipal UserDetails currentUser) {
        List<Item> recommendations = recommendationService.getEventBasedRecommendations(occasion,currentUser.getUsername());
        return ResponseEntity.ok(recommendations);
    }
}