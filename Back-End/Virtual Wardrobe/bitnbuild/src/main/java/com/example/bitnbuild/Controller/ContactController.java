package com.example.bitnbuild.Controller;

import com.example.bitnbuild.dto.ContactRequest;
import com.example.bitnbuild.entity.ClothingItem;
import com.example.bitnbuild.entity.Contact;
import com.example.bitnbuild.entity.User;
import com.example.bitnbuild.service.imple.ClothingItemService;
import com.example.bitnbuild.service.imple.ContactService;
import com.example.bitnbuild.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ClothingItemService clothingItemService;

    @PostMapping("/send")
    public ResponseEntity<Contact> sendContactDetails(@RequestBody ContactRequest request, @AuthenticationPrincipal UserDetails currentUser) {
        User uploader = userService.findById(request.getUploaderId());
        ClothingItem clothingItem = clothingItemService.findById(request.getClothingItemId());

        Contact contact = new Contact();
        contact.setContactName(request.getContactName());
        contact.setContactEmail(request.getContactEmail());
        contact.setMessage(request.getMessage());
        contact.setClothingItem(clothingItem);
        contact.setUser(uploader);

        Contact savedContact = contactService.saveContact(contact);
        return ResponseEntity.ok(savedContact);
    }

    @GetMapping("/mycontacts")
    public ResponseEntity<List<Contact>> getMyContacts(@AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByEmail(currentUser.getUsername());
        List<Contact> contacts = contactService.getContactsForUser(user);

        return ResponseEntity.ok(contacts);
    }
}
