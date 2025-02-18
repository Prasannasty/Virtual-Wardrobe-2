package com.example.bitnbuild.service.imple;

import com.example.bitnbuild.entity.ClothingItem;
import com.example.bitnbuild.entity.User;
import com.example.bitnbuild.repo.ClothingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothingItemService {
    @Autowired
    private ClothingItemRepository clothingItemRepository;

    public List<ClothingItem> getAllItems() {
        return clothingItemRepository.findAll();
    }

    public List<ClothingItem> getItemsByUser(User user) {
        return clothingItemRepository.findByUser(user);
    }

    public ClothingItem saveClothingItem(ClothingItem item) {
        return clothingItemRepository.save(item);
    }

    public ClothingItem findById(Long id) {
        return clothingItemRepository.findById(id).orElse(null);
    }
}
