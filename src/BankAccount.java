import java.math.BigDecimal;

public class BankAccount {

    private Long uuid;
    private BigDecimal amount;

    public Long getUuid() {
        return uuid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BankAccount(int amount, Long uuid) {
        this.amount = new BigDecimal(amount);
        this.uuid = uuid;
    }

    public synchronized void deposit(Long uuid, BigDecimal amount) {
        if (uuid.equals(this.uuid)) {
            this.amount = this.amount.add(amount);
            System.out.println("Deposit on " + uuid +  " successfully. Remaining balance : " + this.amount);
        } else System.out.println("Wrong uuid");
    }

    public synchronized void withdraw(Long uuid, BigDecimal amount) {
        if (uuid.equals(this.uuid)) {
            if (this.amount.subtract(amount).compareTo(new BigDecimal(0)) < 0) {
                System.out.println("Insufficient balance for this withdrawal");
            } else {
                this.amount = this.amount.subtract(amount);
                System.out.println("Withdrawal successful. Remaining balance on " + uuid + " " + this.amount);
            }
        } else System.out.println("Wrong uuid");
    }

}
