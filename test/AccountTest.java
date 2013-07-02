package test;

import static org.junit.Assert.*;
import java.util.Date;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import account.CheckingAccount;
import account.CreditCardAccount;
import account.SavingsAccount;

public class AccountTest extends TestCase {

	public static void main(String[] args){
		TestSuite suite = new TestSuite();
		suite.addTest(new AccountTest("testSavings"));
		suite.addTest(new AccountTest("testChecking"));
		suite.addTest(new AccountTest("testCreditCard"));
		suite.addTest(new AccountTest("testMonthlyInterest"));
		junit.textui.TestRunner.run(suite);
	}

	public AccountTest(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}
	//
	//@Test
	//public void testAccountIds(){
	//
	////	 Verifies that account identification numbers are assigned starting with 1, sequentially in the order that the accounts were created.
	//}
	//
	//@Test
	//public void testDateOpened(){
	//
	//}
	//
	//@Test
	//public void testGetType(){
	//
	//}
	//
	@Test
	public void testToString(){

		final double ar = 0.015; //0.015
		final double odl = 100;
		final double cl = 100;

		SavingsAccount sa = new SavingsAccount(ar);
		CheckingAccount ca = new CheckingAccount(ar, odl);
		CreditCardAccount cca = new CreditCardAccount(ar, cl);
		System.out.println(sa.toString());
		System.out.println(ca.toString());
		System.out.println(cca.toString());

		//	 Tests the toString method in each child class to ensure the String that it returns contains, in this order, the account's
		//	 identification number,
		//	 type,
		//	 date opened,
		//	 annual interest rate, and
		//	 balance.
	}

	@Test
	public void testSavings(){
		final double ar = 0.015; //0.015
		SavingsAccount sa = new SavingsAccount(ar);
		CheckingAccount ca = new CheckingAccount(ar, 1000);
		//assertEquals(1, sa.getId());
		assertEquals(ar, sa.getAnnualRate(), 0.00001);
		assertEquals(0, sa.getBalance(), 0.0001);

		Date now = new Date();
		assertEquals(now.getTime(), sa.getDateOpened().getTime(), 100);
		assertEquals("Checking", ca.getType());
		assertEquals("Savings", sa.getType());
		

		sa.deposit(100);
		assertEquals(100, sa.getBalance(), 0.0001);

		sa.withdraw(40);
		assertEquals(60, sa.getBalance(), 0.0001);
		try{sa.withdraw(70);}
		catch(Exception ex){}
		assertEquals(60, sa.getBalance(), 0.0001);
		sa.withdraw(60);
		assertEquals(0, sa.getBalance(), 0.0001);

		sa.deposit(100);
		sa.addMonthlyInterest();
		assertEquals(100.12, sa.getBalance(), 0.0001);

		//	 Verifies that deposit, withdraw, and getBalance work correctly for savings accounts. Testing steps:
		//	 Create a new SavingsAccount named sa.
		//	 Ensure balance of sa is zero.
		//	 Deposit $50 in sa; ensure balance of sa is $50.
		//	 Attempt to deposit -10 in sa; ensure balance of sa is $50.
		//	 Attempt to withdraw -10 from sa; ensure balance of sa is $50.
		//	 Withdraw $25 from sa; ensure balance of sa is $25.
		//	 Attempt to withdraw $25.01 from sa; ensure balance of sa is $25.
		//	 Withdraw $25 from sa; ensure balance of sa is 0.
	}

	@Test
	public void testChecking(){
		final double ar = 0.015;
		final double odl = 100;
		CheckingAccount ca = new CheckingAccount(ar, odl);
		//assertEquals(2, ca.getId());
		assertEquals(ar, ca.getAnnualRate(), 0.00001);
		assertEquals(0, ca.getBalance(), 0.0001);

		Date now = new Date();
		assertEquals(now.getTime(), ca.getDateOpened().getTime(), 100);

		assertEquals("Checking", ca.getType());

		ca.deposit(50);
		assertEquals(50, ca.getBalance(), 0.0001);
		ca.deposit(-10);
		assertEquals(50, ca.getBalance(), 0.0001);	

		ca.withdraw(-10);
		assertEquals(50, ca.getBalance(), 0.0001);

		ca.payCheck(-10);
		assertEquals(50, ca.getBalance(), 0.0001);

		ca.withdraw(25);
		assertEquals(25, ca.getBalance(), 0.0001);
		ca.withdraw(25.01);
		assertEquals(25, ca.getBalance(), 0.0001);

		ca.payCheck(20);
		assertEquals(5, ca.getBalance(), 0.0001);
		ca.payCheck(5.01);
		assertEquals(-30.01, ca.getBalance(), 0.0001);
		ca.payCheck(70);
		assertEquals(-30.01, ca.getBalance(), 0.0001);

		//	 try{ca.withdraw(70);}
		//	 catch(Exception ex){}
		//	 assertEquals(60, ca.getBalance(), 0.0001);
		//	 ca.withdraw(60);
		//	 assertEquals(0, ca.getBalance(), 0.0001);
		//	
		//	 ca.deposit(100);
		//	 ca.addMonthlyInterest();
		//	 assertEquals(100.12, ca.getBalance(), 0.0001);


		//	 Verifies that deposit, withdraw, payCheck, and getBalance work correctly for checking accounts. Testing steps:
		//	 Create a new CheckingAccount named ca with an overdraft limit of $100.
		//	 Ensure balance of ca is zero.
		//	 Deposit $50 in ca; ensure balance of ca is $50.
		//	 Attempt to deposit -10 in ca; ensure balance of ca is $50.
		//	 Attempt to withdraw -10 from ca; ensure balance of ca is $50.
		//	 Attempt to pay a check of -10 from ca; ensure balance of ca is $50.
		//	 Withdraw $25 from ca; ensure balance of ca is $25.
		//	 Attempt to withdraw $25.01 from ca; ensure balance of ca is $25.
		//	 Pay a check of $20 from ca; ensure balance of ca is $5.
		//	 Pay a check of $5.01 from ca; ensure balance of ca is -30.01 (What a scam!)
		//	 Attempt to pay a check of $70 from ca; ensure balance of ca is -30.01
	}

	@Test
	public void testCreditCard(){
		final double ar = 0.015;
		final double cl = 100;

		CreditCardAccount ca = new CreditCardAccount(ar, cl);
		//assertEquals(3, ca.getId());
		assertEquals(ar, ca.getAnnualRate(), 0.00001);
		assertEquals(0, ca.getBalance(), 0.0001);

		Date now = new Date();
		assertEquals(now.getTime(), ca.getDateOpened().getTime(), 100);

		assertEquals("Credit Card", ca.getType());

		ca.charge(50);
		assertEquals(50, ca.getBalance(), 0.0001);

		ca.deposit(25);
		assertEquals(25, ca.getBalance(), 0.0001);
		ca.deposit(-10);
		assertEquals(25, ca.getBalance(), 0.0001);

		ca.charge(-10);
		assertEquals(25, ca.getBalance(), 0.0001);

		ca.withdraw(-10);
		assertEquals(25, ca.getBalance(), 0.0001);

		assertEquals(75, ca.getRemainCredit(), 0.0001);

		ca.withdraw(75);
		assertEquals(115, ca.getBalance(), 0.0001);

		ca.charge(10);
		assertEquals(115, ca.getBalance(), 0.0001);

		ca.withdraw(15);
		assertEquals(115, ca.getBalance(), 0.0001);

		//	 try{ca.withdraw(70);}
		//	 catch(Exception ex){}
		//	 assertEquals(60, ca.getBalance(), 0.0001);
		//	 ca.withdraw(60);
		//	 assertEquals(0, ca.getBalance(), 0.0001);
		//	
		//	 ca.deposit(100);
		//	 ca.addMonthlyInterest();
		//	 assertEquals(100.12, ca.getBalance(), 0.0001);
		//	
		//	 Verifies that deposit, withdraw, charge, and getBalance work correctly for credit card accounts. Testing steps:
		//	 Create a new CreditCardAccount named ca with an credit limit of $100.
		//	 Ensure balance of ca is zero.
		//	 Charge $50 to ca; ensure balance of ca is $50.
		//	 Make a payment (deposit) of $25; ensure balance of ca is $25.
		//	 Attempt a payment (deposit) of -10; ensure balance of ca is $25.
		//	 Attempt a charge of -10; ensure balance is $25.
		//	 Attempt a cash advance (withdrawal) of -10; ensure balance of ca is $25.
		//	 Ensure remaining credit of ca is $75.
		//	 Get a cash advance (withdrawal) of $75 from ca; ensure balance of ca is $115
		//	 Attempt a charge of $10; ensure balance is $115.
		//	 Attempt a cash advance of $10; ensure balance is $115.
	}
	
	@Test
	public void testMonthlyInterest(){
		
		SavingsAccount sa = new SavingsAccount(0.028);
		assertEquals(0.028, sa.getAnnualRate(), 0.0001);
		sa.setAnnualRate(0.025);
		assertEquals(0.025, sa.getAnnualRate(), 0.0001);
		sa.deposit(204);
		sa.addMonthlyInterest();
		assertEquals(204.42, sa.getBalance(), 0.0001);
		
		CheckingAccount ca = new CheckingAccount(0.028, 200.0);
		assertEquals(0.028, ca.getAnnualRate(), 0.0001);
		ca.setAnnualRate(0.025);
		assertEquals(0.025, ca.getAnnualRate(), 0.0001);
		ca.deposit(204.0);
		ca.addMonthlyInterest();
		assertEquals(204.42, ca.getBalance(), 0.0001);
		
		CreditCardAccount cca = new CreditCardAccount(0.028, 1000.0);
		assertEquals(0.028, cca.getAnnualRate(), 0.0001);
		cca.setAnnualRate(0.025);
		assertEquals(0.025, cca.getAnnualRate(), 0.0001);
		cca.charge(204.0);
		cca.addMonthlyInterest();
		assertEquals(204.42, cca.getBalance(), 0.0001);
		
		//	 Verifies that monthly interest is correctly computed and added to accounts. Testing steps:
		//	 Create a SavingsAccount with an annual rate of 2.8%
		//	 Verify the annual rate is 2.8%
		//	 Change the annual rate to 2.5%; verify the annual rate is 2.5%
		//	 Deposit $204 and add monthly interest; verify the balance is $204.42
		//	 Repeat for CheckingAccount and CreditCardAccount except use charge instead of deposit for CreditCardAccount
	}
}