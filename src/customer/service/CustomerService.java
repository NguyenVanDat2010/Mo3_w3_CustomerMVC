package customer.service;

import customer.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService implements ICustomerService{
    private  static Map<Integer, Customer> customerMap;

    static {
        customerMap=new HashMap<>();
        customerMap.put(1,new Customer(1,"Nguyễn Tuyền","tuyennguyen@gmail.com","Hà Tĩnh"));
        customerMap.put(2,new Customer(2,"Nguyễn Long","longnguyen@gmail.com","Hà Nôi"));
        customerMap.put(3,new Customer(3,"Nguyễn Văn Toản","toannguyen@gmail.com","Hà Nam"));
        customerMap.put(4,new Customer(4,"Nguyễn Thị Mai","mai@gmail.com","Hà Giang"));

    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public void save(Customer customer) {
        customerMap.put(customer.getId(),customer);
    }

    @Override
    public Customer findById(int id) {
        return customerMap.get(id);
    }

    @Override
    public void update(int id, Customer customer) {
        customerMap.put(id,customer);
    }

    @Override
    public void delete(int id) {
        customerMap.remove(id);
    }
}
