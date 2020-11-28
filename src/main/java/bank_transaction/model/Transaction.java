package bank_transaction.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int deposit;
    private int withdrawal;
    private String status = "pending";
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        if(deposit > 0) {
            this.deposit = deposit;
        }
        else {
            System.out.println("Invalid deposit.");
        }
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        if(withdrawal > 0) {
            this.withdrawal = withdrawal;
        }
        else {
            System.out.println("Invalid withdrawal.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status.equals("pending") || status.equals("accepted") || status.equals("canceled")) {
            this.status = status;
        }
        else {
            System.out.println("Invalid status");
        }
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
