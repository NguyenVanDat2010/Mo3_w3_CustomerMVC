package customer.service;

import customer.model.Customer;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(int id);

    void update(int id, Customer customer);

    void delete(int id);

    Customer search(String value);

//    List<Customer> search(String value);
}
