package com.example.bitnbuild.Controller;

import com.example.bitnbuild.entity.Item;
import com.example.bitnbuild.service.imple.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {


    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity<Item> uploadItem(@RequestParam("image") MultipartFile image,
                                           @RequestParam("name") String name,@RequestParam("color") String color,
                                           @RequestParam("occasion") String occasion,
                                           @RequestParam("category") String category,
                                           @RequestParam("wearCount") int wearCount)throws IOException {

        Item item = itemService.addItem(image,name,color,occasion,category,wearCount);
        return ResponseEntity.ok(item);
    }
    @GetMapping("/display")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getItemsForCurrentUser();
        return ResponseEntity.ok(items);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Item>> getUserItems(@PathVariable Long userId) {
        return new ResponseEntity<>(itemService.getAllItemsByUser(userId), HttpStatus.OK);
    }

    @PostMapping("/wear/{itemId}") // Wear item endpoint
    public ResponseEntity<Item> wearItem(@PathVariable Long itemId) {
        Item updatedItem = itemService.wearItem(itemId); // Wear the item and update its history
        return ResponseEntity.ok(updatedItem);
    }
    @GetMapping("/{id}/wear-history")
    public ResponseEntity<List<LocalDate>> getWearHistory(@PathVariable Long id) {
        List<LocalDate> wearHistory = itemService.getWearHistory(id);
        return ResponseEntity.ok(wearHistory);
    }
}
