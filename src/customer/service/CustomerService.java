package customer.service;

import customer.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService implements ICustomerService {
    private static Map<Integer, Customer> customerMap;

    static {
        customerMap = new HashMap<>();
        customerMap.put(1, new Customer(1, "Nguyễn Tuyền", "tuyennguyen@gmail.com", "Hà Tĩnh"));
        customerMap.put(2, new Customer(2, "Nguyễn Long", "longnguyen@gmail.com", "Hà Nôi"));
        customerMap.put(3, new Customer(3, "Nguyễn Văn Toản", "toannguyen@gmail.com", "Hà Nam"));
        customerMap.put(4, new Customer(4, "Nguyễn Thị Mai", "mai@gmail.com", "Hà Giang"));

    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public void save(Customer customer) {
        customerMap.put(customer.getId(), customer);
    }

    @Override
    public Customer findById(int id) {
        return customerMap.get(id);
    }

    @Override
    public void update(int id, Customer customer) {
        customerMap.put(id, customer);
    }

    @Override
    public void delete(int id) {
        customerMap.remove(id);
    }

//    @Override
//    public List<Customer> search(String value) {
//        ArrayList<Customer> customerArrayList = (ArrayList<Customer>) findAll();
//        List<Customer> customerList = new ArrayList<>();
//        for (Customer customer : customerArrayList) {
//            if (Integer.toString(customer.getId()).equals(value) ||
//                    customer.getName().toLowerCase().contains(value.toLowerCase()) ||
//                    customer.getEmail().toLowerCase().contains(value.toLowerCase()) ||
//                    customer.getAddress().toLowerCase().contains(value.toLowerCase())
//            ) {
//                customerList.add(customer);
//            }
//        }
//        return customerList;
//    }

    @Override
    public Customer search(String value) {
        ArrayList<Customer> customerArrayList = (ArrayList<Customer>) findAll();

        for (Customer customer : customerArrayList) {
            if (Integer.toString(customer.getId()).equals(value) ||
                    customer.getName().toLowerCase().contains(value.toLowerCase()) ||
                    customer.getEmail().toLowerCase().contains(value.toLowerCase()) ||
                    customer.getAddress().toLowerCase().contains(value.toLowerCase())
            ) {
                return customer;
            }
        }
        return null;


//        int index = 0;
//        for (int i = 0; i < customerMap.size(); i++) {
//            if (
//                    customerMap.get(i).getId() == Integer.parseInt(value) ||
//                            customerMap.get(i).getName().toLowerCase().contains(value) ||
//                            customerMap.get(i).getEmail().toLowerCase().contains(value) ||
//                            customerMap.get(i).getAddress().toLowerCase().contains(value)
//            ) {
//                index = i;
//                return customerMap.get(index);
//            }
//        }
//        return null;
    }
}
