package com.demo.DrFlight.JPA.Service;

import com.demo.DrFlight.JPA.Repository.ContactUsRepo;
import com.demo.DrFlight.JPA.DTO.Contactus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactUsService {
    @Autowired
    ContactUsRepo contactusRepo;

    /**
     * @return all contactus entries from postgress Contactus table
     */
    public List<Contactus> getAllContactuss() {
        List<Contactus> Contactuss = new ArrayList<>();
        contactusRepo.findAll().forEach(Contactuss::add);
        return Contactuss;
    }

    /**
     * @param id
     * @return contactus entry from postgress Contactus table by id
     */
    public Contactus getContactus(int id) {
        return contactusRepo.findById(id).orElse(null);
    }

    /**
     * Adding new contactus entry to postgress Contactus table
     * @param contactus
     */
    public void addContactus(Contactus contactus) {
        contactusRepo.save(contactus);
    }

    /**
     * Removing the contactus entry from postgress Contactus table
     * @param contactus
     */
    public void deleteContactus(Contactus contactus) {
        contactusRepo.delete(contactus);
    }
}
