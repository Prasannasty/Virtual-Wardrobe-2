package com.example.bitnbuild.service.imple;

import com.example.bitnbuild.entity.Item;
import com.example.bitnbuild.entity.User;
import com.example.bitnbuild.repo.ItemRepository;
import com.example.bitnbuild.repo.UserRepository;
import com.example.bitnbuild.service.S3Service;
import com.example.bitnbuild.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private S3Service s3Service;
    @Autowired
    private ItemRepository itemRepository;

    public Item addItem(MultipartFile image, String name,String color,String occasion,String category,int wearCount) throws IOException {
        // Generate a unique file name for the S3 upload
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        String fileName = name + "_" + System.currentTimeMillis() + ".png"; // Example file name

        // Upload the image to S3 and get the URL
        String imageUrl = s3Service.uploadImageToS3(image, fileName);

        // Save the item to the database with the S3 image URL
        Item item = new Item();
        item.setName(name);            // Set the name
        item.setColor(color);          // Set the color
        item.setOccasion(occasion);    // Set the occasion
        item.setCategory(category);     // Set the category
        item.setImageUrl(imageUrl);
        item.setWearCount(wearCount);
        item.setUser(user);// Set the image URL
        return itemRepository.save(item);
    }

    public List<Item> getAllItemsByUser(Long userId) {
        return itemRepository.findByUserId(userId);
    }

    public List<Item> getItemsForCurrentUser() {
        User currentUser = userService.getCurrentUser(); // Implement this method to get the logged-in user
        return itemRepository.findByUser(currentUser);
    }



    public Item wearItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        item.wearItem(); // Increment wear count and add to wear history
        return itemRepository.save(item); // Save changes
    }

    public List<LocalDate> getWearHistory(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        return item.getWearHistory();
    }
}
