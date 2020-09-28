package th.ac.ku.atm.controller;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")

public class BankAccountController {
    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("allAccounts", bankAccountService.getAccounts());
        return "bankaccount";
    }
    @PostMapping //Create data
    public String  registerAccount(@ModelAttribute BankAccount account,
                                    Model model) {
        bankAccountService.createAccount(account);
        model.addAttribute("allAccounts", bankAccountService.getAccounts());
        return "redirect:bankaccount";

    }

    @GetMapping("/edit/{id}")
    public String getEditBankAccountPage(@PathVariable int id, Model model){
        BankAccount bankAccount = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "bankaccount-edit";

    }

    @PostMapping("/edit/{id}")
    public String editAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model){
        bankAccountService.editBankAccount(bankAccount);
        model.addAttribute("bankaccounts", bankAccountService.getAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model){
        bankAccountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankaccounts", bankAccountService.getAccounts());
        return "redirect:/bankaccount";
    }

}
