package th.ac.ku.atm.service;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.BankAccount;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {
    private List<BankAccount> accountList;

    @PostConstruct
    public void postConstruct(){
        this.accountList = new ArrayList<>();
    }

    public void createAccount(BankAccount account) {
        accountList.add(account);
    }

    public List<BankAccount> getAccounts() {
        return new ArrayList<>(this.accountList);
    }
}
