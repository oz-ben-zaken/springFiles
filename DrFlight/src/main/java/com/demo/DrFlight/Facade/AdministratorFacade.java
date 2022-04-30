package com.demo.DrFlight.Facade;

import com.demo.DrFlight.DAO.AdministratorDao;
import com.demo.DrFlight.Misc.LoginToken;
import com.demo.DrFlight.Poco.Administrator;
import com.demo.DrFlight.Poco.AirlineCompany;
import com.demo.DrFlight.Poco.Customer;
import com.demo.DrFlight.Poco.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorFacade extends AnonymousFacade {
    @Autowired
    protected AdministratorDao administratorDao;
    @Autowired
    private LoginToken loginToken;

    public AdministratorFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    /**
     * Set the facade loginToken to token
     * @param token
     */
    public void setToken(LoginToken token){this.loginToken = token;}

    /**
     * Gets token to authenticate admin.
     * @param loginToken
     * @return List of all customers.
     */
    public List<Customer> get_all_customers(LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return null;
        }
        return this.customerDao.getAll();
    }

    /**
     * Gets token to authenticate admin.
     * Try to add specified airline to database by calling airlineCompanyDao.add.
     * @param airline
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean add_airline(AirlineCompany airline, User user, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        if (airline.countryId <= 0) {
            System.out.println("country id must be a positive number");
            return false;
        }
        user.userRole = 2;
        this.create_new_user(user);
        user = this.userDao.getUserByUsername(user.username);
        airline.userId = user.id;
        return this.airlineCompanyDao.add(airline);
    }

    /**
     * Gets token to authenticate admin.
     * Try to add specified customer to database by calling customerDao.add.
     * @param customer
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean add_customer(Customer customer, User user, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        if (isNotNumeric(customer.phoneNumber) || isNotNumeric(customer.creditCardNumber)) {
            System.out.println("The fields 'phoneNumber' and/or 'creditCardNumber' must be numerical");
            return false;
        }
        user.userRole = 1;
        this.create_new_user(user);
        user = this.userDao.getUserByUsername(user.username);
        customer.userId = user.id;
        return this.customerDao.add(customer);
    }

    /**
     * Gets token to authenticate admin.
     * Try to add specified administrator to database by calling administratorDao.add.
     * @param administrator
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean add_administrator(Administrator administrator, User user, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        user.userRole = 3;
        this.create_new_user(user);
        user = this.userDao.getUserByUsername(user.username);
        administrator.userId = user.id;
        return this.administratorDao.add(administrator);
    }

    /**
     * Gets token to authenticate admin.
     * Try to remove specified airline from database by calling airlineCompanyDao.remove.
     * @param airline
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean remove_airline(AirlineCompany airline, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        if (airline.countryId <= 0) {
            System.out.println("country id must be a positive number");
            return false;
        }
        if(this.airlineCompanyDao.remove(airline)){
            this.userDao.remove(this.userDao.get(airline.userId));
            return true;
        }
        return false;
    }

    /**
     * Gets token to authenticate admin.
     * Try to remove specified customer from database by calling customerDao.remove.
     * @param customer
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean remove_customer(Customer customer, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        if (isNotNumeric(customer.phoneNumber) || isNotNumeric(customer.creditCardNumber)) {
            System.out.println("The fields 'phoneNumber' and/or 'creditCardNumber' must be numerical");
            return false;
        }
        if(this.customerDao.remove(customer)){
            this.userDao.remove(this.userDao.get(customer.userId));
            return true;
        }
        return false;
    }

    /**
     * Gets token to authenticate admin.
     * Try to remove specified administrator from database by calling administratorDao.remove.
     * @param administrator
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean remove_administrator(Administrator administrator, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        if(this.administratorDao.remove(administrator)){
            this.userDao.remove(this.userDao.get(administrator.userId));
            return true;
        }
        return false;
    }

    public Administrator getAdministratorById(int id, LoginToken loginToken){
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return new Administrator(0,null,null,0);
        }
        return this.administratorDao.get(id);
    }

}
