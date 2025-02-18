package com.example.bitnbuild.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;  // E.g., "Everyday Wear", "Special Occasion"
    private String color;
    private String occasion;
    private int wearCount;

    private String imageUrl;  // URL for the image stored (e.g., AWS S3 or local storage)

    private List<LocalDate>wearHistory=new ArrayList<>();

    public void wearItem() {
        this.wearCount++;
        this.wearHistory.add(LocalDate.now());
    }
    public List<LocalDate> getWearHistory() {
        return wearHistory;
    }

    public void setWearHistory(List<LocalDate> wearHistory) {
        this.wearHistory = wearHistory;
    }

    @ManyToOne
    private User user;  // Assuming you have a User entity for authentication

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public int getWearCount() {
        return wearCount;
    }

    public void setWearCount(int wearCount) {
        this.wearCount = wearCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
// Getters and Setters
}

