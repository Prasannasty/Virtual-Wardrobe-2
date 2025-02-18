package com.example.bitnbuild.repo;

import com.example.bitnbuild.entity.Item;
import com.example.bitnbuild.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findByUserId(Long userId);
    List<Item> findByCategory(String category);
    List<Item> findByOccasion(String occasion);
    List<Item> findByUser(User user);
    List<Item> findByCategoryAndUser_Email(String category, String email);
    List<Item> findByOccasionAndUser_Email(String occasion, String email);

}
