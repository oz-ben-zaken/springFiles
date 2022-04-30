package com.demo.DrFlight;

import com.demo.DrFlight.DAO.CustomerDao;
import com.demo.DrFlight.Poco.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class WithMockUserTests {


    //todo:: learn how to mock user to fool Spring Security cuz this code wont do it

    @Test
    @WithMockUser(username = "oz", password = "oz123", authorities = {"administrator"})
    public void getCustomersFromAdministratorControllerTest() {
        //SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("oz", "oz123"));
        CustomerDao customerDao = new CustomerDao();
        var current = customerDao.getAll();
        System.out.println(current);
        String url = "http://localhost:8080/admin/customer";
        var expected = HttpTestManager.getResponse(url, Customer[].class);
        Assert.assertEquals(current.get(0).toString(), expected);
    }
}
