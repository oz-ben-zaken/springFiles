package com.demo.DrFlight.JPA.Repository;

import com.demo.DrFlight.JPA.DTO.AddLogger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddLoggerRepo extends MongoRepository<AddLogger,Integer> {
}
