package shoppingcart.sk.fmph.uniba.service;

import shoppingcart.sk.fmph.uniba.repository.CustomerRepository;
import shoppingcart.sk.fmph.uniba.entities.Customer;
import shoppingcart.sk.fmph.uniba.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService
{
    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findOne(id);
    }

    public List<Order> getCustomerOrders(String email) {
        return customerRepository.getCustomerOrders(email);
    }

}
