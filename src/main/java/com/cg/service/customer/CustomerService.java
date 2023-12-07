package com.cg.service.customer;

import com.cg.entity.Customer;
import com.cg.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<Customer> findByName(String name) {
        return customerRepository.findCustomerByName(name);
    }
}
