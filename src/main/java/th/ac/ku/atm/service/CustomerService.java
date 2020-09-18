package th.ac.ku.atm.service;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> customerList;

    @PostConstruct
    public void postConstruct(){
        this.customerList = new ArrayList<>();
    }

    public void createCustomer(Customer customer) {
        int hashedPin = hash(customer.getPin());
        customer.setPin(hashedPin);
        customerList.add(customer);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(this.customerList);
    }

    public int hash(int value){
        return 0;
    }
}
