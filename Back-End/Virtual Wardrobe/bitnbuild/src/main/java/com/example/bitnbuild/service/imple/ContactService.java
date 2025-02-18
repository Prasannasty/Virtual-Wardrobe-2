package com.example.bitnbuild.service.imple;

import com.example.bitnbuild.entity.Contact;
import com.example.bitnbuild.entity.User;
import com.example.bitnbuild.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> getContactsForUser(User user) {
        return contactRepository.findByUser(user);
    }
}
