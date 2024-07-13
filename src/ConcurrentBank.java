import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConcurrentBank {

    private BigDecimal totalAmount;

    List<BankAccount> bankAccountList = new ArrayList<>();

    public ConcurrentBank() {
        this.totalAmount = new BigDecimal(0);
    }

    public void add(BankAccount account) {
        bankAccountList.add(account);
        this.totalAmount = totalAmount.add(account.getAmount());
    }

    @Override
    public String toString() {
        return "Общий счёт составляет + " + totalAmount;
    }

    public void transfer(BankAccount destination, BankAccount source, String operationType, int amount) {
        BigDecimal transfer = new BigDecimal(amount);
        switch (operationType) {
            case "DEPOSIT" -> {
                source.withdraw(source.getUuid(), transfer);
                destination.deposit(destination.getUuid(), transfer);
            }
            case "WITHDRAW" -> {
                source.deposit(source.getUuid(), transfer);
                destination.withdraw(destination.getUuid(), transfer );
            }
        }
    }

}
