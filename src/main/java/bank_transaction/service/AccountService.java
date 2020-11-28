package bank_transaction.service;

import bank_transaction.DTO.AccountDTO;
import bank_transaction.DTO.TransactionDTO;
import bank_transaction.model.Account;
import bank_transaction.model.Transaction;
import bank_transaction.model.User;
import bank_transaction.repository.AccountRepository;
import bank_transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired UserRepository userRepository;

    public AccountDTO getAccount(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(AccountDTO::new).orElse(null);
    }

    public List<TransactionDTO> getTransactions(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        if(account != null) {
            for(Transaction transaction : account.getTransactions()) {
                transactionDTOList.add(new TransactionDTO(transaction));
            }

            return transactionDTOList;
        }
        return null;
    }



    public List<TransactionDTO> getTransactionsByDate(Long id, LocalDate date) {
        Account account = accountRepository.findById(id).orElse(null);
        List<TransactionDTO> transactionsByDate = new ArrayList<>();

        if(account != null) {
            for (Transaction transaction : account.getTransactions()) {
                if (transaction.getDate().compareTo(date) == 0) {
                    transactionsByDate.add(new TransactionDTO(transaction));
                }
            }

            return transactionsByDate;
        }
        else {
            return null;
        }
    }

    public void addAccount(Account account, Long userId, Long adminId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElse(null);
        Optional<User> adminOptional = userRepository.findById(adminId);
        User admin = userOptional.orElse(null);
        if(admin != null) {
            if (admin.getRole().equals("ADMIN")) {
                user.getAccounts().add(account);
                account.setUser(user);
                accountRepository.save(account);
            } else {
                System.out.println("Not a admin.");
            }
        }
    }

}
