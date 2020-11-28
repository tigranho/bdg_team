package bank_transaction.DTO;

import bank_transaction.model.Transaction;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    private int deposit;
    private int withdrawal;
    private String status;
    private LocalDate date;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.deposit = transaction.getDeposit();
        this.withdrawal = transaction.getWithdrawal();
        this.status = transaction.getStatus();
        this.date = transaction.getDate();
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
        this.deposit = deposit;
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
