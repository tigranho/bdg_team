package bank_transaction.controller;

import bank_transaction.DTO.TransactionDTO;
import bank_transaction.model.Transaction;
import bank_transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/{id}")
    public TransactionDTO getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @PostMapping(value = "/account/{id}")
    public void addTransaction(@RequestBody Transaction transaction, @PathVariable Long id) {
        transactionService.addTransaction(transaction, id);
    }

    @PatchMapping(value = "/{transactionId}/admin/{adminId}/status")
    public void acceptTransaction(@PathVariable Long transactionId, @PathVariable Long adminId) {
        transactionService.acceptTransaction(transactionId, adminId);
    }

    @PatchMapping(value = "/{id}/status")
    public void cancelTransaction(@PathVariable Long id) {
        transactionService.cancelTransaction(id);
    }
}
