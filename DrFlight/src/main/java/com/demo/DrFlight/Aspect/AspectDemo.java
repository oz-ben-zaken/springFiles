package com.demo.DrFlight.Aspect;

import com.demo.DrFlight.JPA.DTO.AddLogger;
import com.demo.DrFlight.JPA.Service.AddLoggerService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Aspect
public class AspectDemo {

    @Autowired
    AddLoggerService addLoggerService;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/uuuu");

    /**
     * Aspect that execute on each call to an add* func in the dao package.
     * we log the time of function call and adding id to the entry.
     */
    @Before("execution(* com.demo.DrFlight.DAO.*.add*(..))")
    public void beforeAdd() {
        long id = addLoggerService.getLength();
        AddLogger addLogger= new AddLogger(id,dtf.format(LocalDateTime.now()));
        addLoggerService.add(addLogger);
        System.out.println("Add func logged to mongodb from aspect.\nlog is: "+addLogger);
    }
}
