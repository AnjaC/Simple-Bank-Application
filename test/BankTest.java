package test;

import static org.junit.Assert.*;
import org.junit.Test;
import account.SavingsAccount;
import account.CheckingAccount;
import account.CreditCardAccount;
import account.Bank;

public class BankTest{

	//	//Attributes
	//	private final Account[] accounts;
	//	private Bank bank;
	//	private java.util.Random rand;
	//	private static final double[] RATES;
	//	private static final int[] TYPES;
	//	
	//	//Constructor
	//	public BankTest() {
	//		Bank bank = new Bank();
	//
	//		// Add two accounts to the bank.
	//		Account sa = new SavingsAccount(ANNUAL_RATE);
	//		Account ca = new CheckingAccount(ANNUAL_RATE, OVERDRAFT_LIMIT);
	//		bank.addAccount(sa);
	//		bank.addAccount(ca);
	//
	//		// Find the first the account that was just added.
	//		Account f = bank.findAccount(sa.getId());
	//		assertSame(f, sa);
	//
	//		// Attempt to find an account that doesn't exist.
	//		Account f = bank.findAccount(ca.getId() + 1);
	//		assertNull(f);
	//	}

	//Behavior
	@Test
	public void testAddAccount() {
		Bank bank1 = new Bank();

		bank1.addAccount(new SavingsAccount(0.025));
		bank1.addAccount(new CheckingAccount(0.015, 100));
		bank1.addAccount(new CreditCardAccount(0.125, 1000));

		assertEquals(1, bank1.findAccount(1).getId());
		//Tests Bank.addAccount() by adding many random accounts to the bank.
	}

	@Test
	public void testFindAccount() {
		Bank bank2 = new Bank();
		
		SavingsAccount sa1 = new SavingsAccount(0.025);
		CheckingAccount ca1 = new CheckingAccount(0.015, 100);
		CreditCardAccount cc1 = new CreditCardAccount(0.125, 1000);
		
		bank2.addAccount(sa1);
		bank2.addAccount(ca1);
		bank2.addAccount(cc1);

		assertEquals(sa1.getId(), bank2.findAccount(sa1.getId()).getId());
		assertEquals(ca1.getId(), bank2.findAccount(ca1.getId()).getId());
		assertEquals(cc1.getId(), bank2.findAccount(cc1.getId()).getId());
		//Tests Bank.findAccount() by finding all existing accounts and attempting to find some non-existing accounts.
	}

	@Test
	public void testAddMonthlyInterest() {
		Bank bank3 = new Bank();

		SavingsAccount sa1 = new SavingsAccount(0.025);
		SavingsAccount sa2 = new SavingsAccount(0.05);
		SavingsAccount sa3 = new SavingsAccount(0.015);

		CheckingAccount ca1 = new CheckingAccount(0.015, 100);
		CheckingAccount ca2 = new CheckingAccount(0.025, 100);
		CheckingAccount ca3 = new CheckingAccount(0.05, 100);

		CreditCardAccount cc1 = new CreditCardAccount(0.125, 1000);
		CreditCardAccount cc2 = new CreditCardAccount(0.15, 1000);
		CreditCardAccount cc3 = new CreditCardAccount(0.25, 1000);

		bank3.addAccount(sa1);
		bank3.addAccount(sa2);
		bank3.addAccount(sa3);
		bank3.addAccount(ca1);
		bank3.addAccount(ca2);
		bank3.addAccount(ca3);
		bank3.addAccount(cc1);
		bank3.addAccount(cc2);
		bank3.addAccount(cc3);

		sa1.deposit(150);
		sa1.withdraw(50);
		ca3.deposit(1300);
		ca3.withdraw(300);
		cc2.charge(100);
		cc2.charge(50);
		cc2.deposit(50);
	
		//assertEquals(1000, ca3.toString());
		
		bank3.addMonthlyInterest();

		assertEquals(100.21, sa1.getBalance(), 0.00001);
		assertEquals(1004.17, ca3.getBalance(), 0.00001);
		assertEquals(101.25, cc2.getBalance(), 0.00001);
		assertEquals(100.21, bank3.findAccount(sa1.getId()).getBalance(), 0.00001);
		assertEquals(1004.17, bank3.findAccount(ca3.getId()).getBalance(), 0.00001);
		assertEquals(101.25, bank3.findAccount(cc2.getId()).getBalance(), 0.00001);
		//Tests Bank.addMonthlyInterest() which should add the correct monthly interest amount to all existing accounts.
		//1. Create savings accounts, checking accounts, and credit card accounts with many different interest rates.
		//2. Add all accounts to a bank.
		//3. Deposit and charge different amounts to the accounts.
		//4. Call bank.addMonthlyInterest() (Use a bank object, not the Bank class to call the method.)
		//5. Check the balance of each account.
	}

	@Test
	public void testRemoveAccount() {
		Bank bank4 = new Bank();

		SavingsAccount sa1 = new SavingsAccount(0.025);
		SavingsAccount sa2 = new SavingsAccount(0.05);
		SavingsAccount sa3 = new SavingsAccount(0.015);

		CheckingAccount ca1 = new CheckingAccount(0.015, 100);
		CheckingAccount ca2 = new CheckingAccount(0.025, 100);
		CheckingAccount ca3 = new CheckingAccount(0.05, 100);

		CreditCardAccount cc1 = new CreditCardAccount(0.125, 1000);
		CreditCardAccount cc2 = new CreditCardAccount(0.15, 1000);
		CreditCardAccount cc3 = new CreditCardAccount(0.25, 1000);

		bank4.addAccount(sa1);
		bank4.addAccount(sa2);
		bank4.addAccount(sa3);
		bank4.addAccount(ca1);
		bank4.addAccount(ca2);
		bank4.addAccount(ca3);
		bank4.addAccount(cc1);
		bank4.addAccount(cc2);
		bank4.addAccount(cc3);
		
		bank4.removeAccount(sa1);
		bank4.removeAccount(cc3);
		bank4.removeAccount(ca2);
		bank4.removeAccount(cc1);
		
		assertEquals(sa2, bank4.findAccount(sa2.getId()));
		assertEquals(cc2, bank4.findAccount(cc2.getId()));
		assertNull(bank4.findAccount(sa1.getId()));
		assertNull(bank4.findAccount(cc3.getId()));
		assertNull(bank4.findAccount(ca2.getId()));
		assertNull(bank4.findAccount(cc1.getId()));
		//Tests Bank.removeAccount() by removing the first and last accounts and several random accounts in the middle.
	}
	
}
