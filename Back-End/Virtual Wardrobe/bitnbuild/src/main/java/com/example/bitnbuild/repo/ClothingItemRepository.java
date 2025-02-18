package com.example.bitnbuild.repo;

import com.example.bitnbuild.entity.ClothingItem;
import com.example.bitnbuild.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothingItemRepository extends JpaRepository<ClothingItem,Long> {
    List<ClothingItem> findAll();
    List<ClothingItem> findByUser(User user);
}
