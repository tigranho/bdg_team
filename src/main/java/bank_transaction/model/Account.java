package bank_transaction.model;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String cardNumber;
    private String currency;
    private String bankName;
    private String cardType;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Account() {

    }

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        Pattern VALID_ACCOUNT_NUMBER_REGEX = Pattern.compile("[0-9]");
        if(accountNumber.length() == 32 && VALID_ACCOUNT_NUMBER_REGEX.matcher(accountNumber).find()) {
            this.accountNumber = accountNumber;
        }
        else {
            System.out.println("Invalid account number.");
        }
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        Pattern VALID_CARD_NUMBER_REGEX = Pattern.compile("[0-9]");
        if(cardNumber.length() == 16 && VALID_CARD_NUMBER_REGEX.matcher(cardNumber).find()) {
            this.cardNumber = cardNumber;
        }
        else {
            System.out.println("Invalid card number.");
        }
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        if(!bankName.equals("")) {
            this.bankName = bankName;
        }
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        if(cardType.equals("master") || cardType.equals("visa") || cardType.equals("gold")) {
            this.cardType = cardType;
        }
        else {
            System.out.println("Invalid card type.");
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
