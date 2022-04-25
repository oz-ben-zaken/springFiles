package com.demo.DrFlight.JPA.Controller;

import com.demo.DrFlight.JPA.DTO.Contactus;
import com.demo.DrFlight.JPA.Service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactus")
public class ContactUsController {

    @Autowired
    ContactUsService contactusService;

    @GetMapping("")
    public List<Contactus> getAllContactuss(){
        return contactusService.getAllContactuss();
    }

    @GetMapping("/{id}")
    public Contactus getContactus(@PathVariable int id){
        return contactusService.getContactus(id);
    }

    @RequestMapping(method=RequestMethod.POST,value = "/")
    public void addContactus(@RequestBody Contactus contactus){
        contactusService.addContactus(contactus);
    }

    @DeleteMapping("/{id}")
    public void deleteContactus(@PathVariable Contactus contactus){
        contactusService.deleteContactus(contactus);
    }
}
