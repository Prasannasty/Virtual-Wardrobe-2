package com.example.bitnbuild.Controller;

import com.example.bitnbuild.entity.ClothingItem;
import com.example.bitnbuild.entity.User;
import com.example.bitnbuild.service.S3Service;
import com.example.bitnbuild.service.imple.ClothingItemService;
import com.example.bitnbuild.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/clothing")
public class ClothingItemController {

    @Autowired
    S3Service s3Service;

    @Autowired
    UserService userService;

    @Autowired
    private ClothingItemService clothingItemService;

    @PostMapping("/add")
    public ResponseEntity<ClothingItem> addClothingItem(
            @RequestParam("image") MultipartFile image,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("size") String size,
            @RequestParam("condition") String condition,
            @RequestParam("price") Double price,
            @RequestParam("material") String material,
            @RequestParam("location") String location,
            @AuthenticationPrincipal UserDetails currentUser) throws IOException {

        String fileName = title + "_" + System.currentTimeMillis() + ".png";
        String imageUrl = s3Service.uploadImageToS3(image,fileName);
        ClothingItem clothingItem = new ClothingItem();
        clothingItem.setTitle(title);
        clothingItem.setDescription(description);
        clothingItem.setImageUrl(imageUrl);
        clothingItem.setCategory(category);
        clothingItem.setSize(size);
        clothingItem.setClothcondition(condition);
        clothingItem.setPrice(price);
        clothingItem.setMaterial(material);
        clothingItem.setLocation(location);
        clothingItem.setCreateddate(LocalDate.now());

        User user = userService.findByEmail(currentUser.getUsername());
        clothingItem.setUser(user);

        ClothingItem savedItem = clothingItemService.saveClothingItem(clothingItem);
        return ResponseEntity.ok(savedItem);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClothingItem>> getAllItems() {
        return ResponseEntity.ok(clothingItemService.getAllItems());
    }
}
