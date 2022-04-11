package com.demo.DrFlight.JPA.Service;

import com.demo.DrFlight.JPA.DTO.Contactus;
import com.demo.DrFlight.JPA.Repository.ContactUsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactUsService {
    @Autowired
    ContactUsRepo contactusRepo;

    public List<Contactus> getAllContactuss() {
        List<Contactus> Contactuss = new ArrayList<>();
        contactusRepo.findAll().forEach(Contactuss::add);
        return Contactuss;
    }

    public Contactus getContactus(int id) {
        return contactusRepo.findById(id).orElse(null);
    }

    public void addContactus(Contactus contactus) {
        contactusRepo.save(contactus);
    }

    public void deleteContactus(Contactus contactus) {
        contactusRepo.delete(contactus);
    }
}
