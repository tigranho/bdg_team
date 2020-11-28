package bank_transaction.controller;

import bank_transaction.DTO.AccountDTO;
import bank_transaction.DTO.TransactionDTO;
import bank_transaction.model.Account;
import bank_transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @GetMapping(value = "/{id}/transaction")
    public List<TransactionDTO> getTransactions(@PathVariable Long id) {
        return accountService.getTransactions(id);
    }

    @GetMapping(value = "/{id}/transaction/filter")
    public List<TransactionDTO> getTransactionsByDate(@PathVariable Long id, @RequestParam(value = "date") String dateStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, dtf);
        return accountService.getTransactionsByDate(id, date);
    }


    @PostMapping(value = "/user/{userId}/admin/{adminId}")
    public void addAccount(@RequestBody Account account, @PathVariable Long userId, @PathVariable Long adminId) {
        accountService.addAccount(account, userId, adminId);
    }
}
