package th.ac.ku.atm.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.atm.model.BankAccount;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BankAccountService {
    private RestTemplate restTemplate;

    public BankAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public  List<BankAccount> getCustomerBankAccounts(int customerId){
        String url = "http://localhost:8091/api/bankaccount/customer/" + customerId;

        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url, BankAccount[].class);
        BankAccount[] accounts = response.getBody();
        return Arrays.asList(accounts);
    }


    public void createAccount(BankAccount bankAccount) {
        String url = "http://localhost:8091/api/bankaccount/";
        restTemplate.postForObject(url, bankAccount, BankAccount.class);
    }

    public List<BankAccount> getAccounts() {
        String url = "http://localhost:8091/api/bankaccount/";
        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url, BankAccount[].class);
        BankAccount[] accounts = response.getBody();
        return Arrays.asList(accounts);
    }

    public BankAccount getBankAccount(int id){
        String url = "http://localhost:8091/api/bankaccount/" + id;
        ResponseEntity<BankAccount> response =
                restTemplate.getForEntity(url, BankAccount.class);
        return response.getBody();
    }

    public void editBankAccount(BankAccount bankAccount){
        String url = "http://localhost:8091/api/bankaccount/" + bankAccount.getId();
        restTemplate.put(url, bankAccount);
    }

    public void depositBankAccount(BankAccount bankAccount){
        String url = "http://localhost:8091/api/bankaccount/" + bankAccount.getId();
        double old_balance = getBankAccount(bankAccount.getId()).getBalance();
        double new_balance = old_balance + bankAccount.getBalance();
        bankAccount.setBalance(new_balance);
        restTemplate.put(url, bankAccount);
    }

    public void withdrawBankAccount(BankAccount bankAccount){
        String url = "http://localhost:8091/api/bankaccount/" + bankAccount.getId();
        double old_balance = getBankAccount(bankAccount.getId()).getBalance();
        double new_balance = old_balance - bankAccount.getBalance();
        if (new_balance >= 0) {
            bankAccount.setBalance(new_balance);
        }else{
            bankAccount.setBalance(old_balance);
        }
        restTemplate.put(url, bankAccount);
    }

    public void deleteBankAccount(BankAccount bankAccount){
        String url = "http://localhost:8091/api/bankaccount/" + bankAccount.getId();
        restTemplate.delete(url, bankAccount);
    }
}
