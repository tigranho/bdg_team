package bank_transaction.service;

import bank_transaction.DTO.TransactionDTO;
import bank_transaction.model.Account;
import bank_transaction.model.Transaction;
import bank_transaction.model.User;
import bank_transaction.repository.AccountRepository;
import bank_transaction.repository.TransactionRepository;
import bank_transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public TransactionDTO getTransaction(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(TransactionDTO :: new).orElse(null);
    }

    public void addTransaction(Transaction transaction, Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if(account != null) {
            account.getTransactions().add(transaction);
            transaction.setAccount(account);

            transactionRepository.save(transaction);
        }
    }

    public void acceptTransaction(Long transactionId, Long adminId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        User admin = userRepository.findById(adminId).orElse(null);

        if(transaction != null && admin != null) {
            if(admin.getRole().equals("ADMIN") && !transaction.getStatus().equals("canceled")) {
                transaction.setStatus("accepted");
                transactionRepository.save(transaction);
            }
            else if(!admin.getRole().equals("ADMIN")){
                System.out.println("The user is not a admin.");
            }
            else {
                System.out.println("The transaction is canceled");
            }
        }
        else if(transaction == null) {
            System.out.println("Transaction does not exist.");
        }
        else {
            System.out.println("User does not exist.");
        }
    }

    public void cancelTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);

        if(transaction != null && !transaction.getStatus().equals("accepted")) {
            transaction.setStatus("canceled");
            transactionRepository.save(transaction);
        }
        else if(transaction == null) {
            System.out.println("Transaction does not exist.");
        }
        else {
            System.out.println("It's too late. Transaction is accepted.");
        }
    }
}
