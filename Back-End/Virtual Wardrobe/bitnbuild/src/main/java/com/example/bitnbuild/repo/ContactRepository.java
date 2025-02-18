package com.example.bitnbuild.repo;

import com.example.bitnbuild.entity.Contact;
import com.example.bitnbuild.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findByUser(User user);
}
