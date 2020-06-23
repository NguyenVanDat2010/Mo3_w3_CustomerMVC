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

    /**
     * Tìm kiếm và lấy ra tất cả các bản ghi chứa giá trị cần tìm trong vòng for
     */
    @Override
    public List<Customer> search(String value) {
        ArrayList<Customer> customerArrayList = (ArrayList<Customer>) findAll();

        List<Customer> customerList = new ArrayList<>();

        /** Xóa bỏ tất cả các khoảng trắng đầu và cuối khi tìm kiếm một giá trị bất kỳ*/
        int firstIndex = 0;
        int lastIndex = value.length() - 1;
        if (value.charAt(0) == ' ' || value.charAt(value.length() - 1) == ' ') {
            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) != ' ') {
                    firstIndex = i;
                    for (int j = value.length() - 1; j >= 0; j--) {
                        if (value.charAt(j) != ' ') {
                            lastIndex = j;
                            break;
                        }
                    }
                    break;
                }
            }
            value = value.substring(firstIndex, lastIndex);
        }

        for (Customer customer : customerArrayList) {
            if (Integer.toString(customer.getId()).equals(value) ||
                    customer.getName().toLowerCase().contains(value.toLowerCase()) ||
                    customer.getEmail().toLowerCase().contains(value.toLowerCase()) ||
                    customer.getAddress().toLowerCase().contains(value.toLowerCase())
            ) {
                customerList.add(customer);
            }
        }
        return customerList;
    }

    /** Tìm kiếm chỉ lấy ra 1 bản ghi đầu tiên tìm thấy trong vòng for*/
//    @Override
//    public Customer search(String value) {
//        ArrayList<Customer> customerArrayList = (ArrayList<Customer>) findAll();
//
//        for (Customer customer : customerArrayList) {
//            if (Integer.toString(customer.getId()).equals(value) ||
//                    customer.getName().toLowerCase().contains(value.toLowerCase()) ||
//                    customer.getEmail().toLowerCase().contains(value.toLowerCase()) ||
//                    customer.getAddress().toLowerCase().contains(value.toLowerCase())
//            ) {
//                return customer;
//            }
//        }
//        return null;
//    }
}
