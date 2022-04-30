package com.demo.DrFlight.JPA.Service;

import com.demo.DrFlight.JPA.Repository.AddLoggerRepo;
import com.demo.DrFlight.JPA.DTO.AddLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddLoggerService {
    @Autowired
    AddLoggerRepo addLoggerRepo;

    /**
     * Adding new addLogger entry to mongodb AddLogger Document
     * @param addLogger
     */
    public void add(AddLogger addLogger) {
        addLoggerRepo.save(addLogger);
    }

    /**
     * @return long length of mongodb AddLogger Document
     */
    public long getLength() {
        return addLoggerRepo.findAll().size();
    }
}
