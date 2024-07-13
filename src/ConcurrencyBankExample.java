public class ConcurrencyBankExample {

    private static Long idGenerator = 0L;


    public static void main(String[] args) throws InterruptedException {
        ConcurrentBank concurrentBank = new ConcurrentBank();
        BankAccount account = new BankAccount(3000, ++idGenerator);
        BankAccount account2 = new BankAccount(3000, ++idGenerator);
        concurrentBank.add(account);
        concurrentBank.add(account2);
        System.out.println(concurrentBank);
        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                concurrentBank.transfer(account, account2, "DEPOSIT", 500 );
            }
        });
        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                concurrentBank.transfer(account2, account, "WITHDRAW", 500);
            }
        });

        depositThread.start();
        withdrawThread.start();

        depositThread.join();
        withdrawThread.join();

        System.out.println(concurrentBank);

    }


}
