package com.demo.DrFlight.JPA.Repository;

import com.demo.DrFlight.JPA.DTO.Contactus;
import org.springframework.data.repository.CrudRepository;


public interface ContactUsRepo extends CrudRepository<Contactus,Integer> {
}
