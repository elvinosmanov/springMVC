package guru.springframework.services;

import guru.springframework.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<Integer, Customer> customers;

    public CustomerServiceImpl() {
        loadCustomers();
    }

    @Override
    public List<Customer> listAllCustomers() {

        return new ArrayList<>(customers.values());

    }

    @Override
    public Customer getCustomer(Integer id) {
        return customers.get(id);
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        if(customer!=null){
            if(customer.getId()==null){
                customer.setId(getNextId());
            }
            customers.put(customer.getId(), customer);
            return customer;
        }else{
            throw new RuntimeException("Customer's id is null");
        }
    }

    @Override
    public void deleteCustomer(Integer id) {
        customers.remove(id);
    }

    private int getNextId(){
        if(customers.size()!=0){
            int getNextId = Collections.max(customers.keySet())+1;
            return getNextId;
        }else {
            return 1;
        }
    }

    private void loadCustomers() {
        customers = new HashMap<>();

        Customer customer1 = new Customer();

        customer1.setId(1);
        customer1.setFirstName("Elvin");
        customer1.setLastName("Osmanov");
        customer1.setEmail("youngtronik@gmail.com");
        customer1.setPhoneNumber("0554261998");
        customer1.setAddressLineOne("Nizami street");
        customer1.setAddressLineTwo("Gence prospect");
        customer1.setCity("Baku");
        customer1.setState("Azerbaijan");
        customer1.setZipCode("1104");

        customers.put(1, customer1);

        Customer customer2 = new Customer();

        customer2.setId(2);
        customer2.setFirstName("Nermin");
        customer2.setLastName("Osmanova");
        customer2.setEmail("bakugan192@gmail.com");
        customer2.setPhoneNumber("0554261998");
        customer2.setAddressLineOne("Nizami street");
        customer2.setAddressLineTwo("Gence prospect");
        customer2.setCity("Gence");
        customer2.setState("Azerbaijan");
        customer2.setZipCode("1104");

        customers.put(2, customer2);

        Customer customer3 = new Customer();

        customer3.setId(3);
        customer3.setFirstName("Emin");
        customer3.setLastName("Qaralov");
        customer3.setEmail("youngtronik@gmail.com");
        customer3.setPhoneNumber("0556095542");
        customer3.setAddressLineOne("Nizami street");
        customer3.setAddressLineTwo("Gence prospect");
        customer3.setCity("Baku");
        customer3.setState("Azerbaijan");
        customer3.setZipCode("1104");

        customers.put(3, customer3);
    }


}
