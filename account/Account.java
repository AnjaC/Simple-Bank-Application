package account;

import java.util.Date;

public abstract class Account {
	//-------------------------------------------------------
	//	Data
	//-------------------------------------------------------
		private static int nextId = 1;
		private int id;
		private Date dateOpened;
		private double annualRate;
		
		protected double balance;
		
		//-------------------------------------------------------
		//	Constructor
		//-------------------------------------------------------
		protected Account (double annualRate) {			
			this.id = nextId++;
			this.annualRate = annualRate;
			dateOpened = new Date();
			balance = 0;
		}
		
		//-------------------------------------------------------
		//	Methods
		//-------------------------------------------------------
		public int getId() {
			return id;
		}

		public Date getDateOpened() {
			return dateOpened;
		}
		
		public double getBalance() {
			return balance;
		}

		public double getAnnualRate() {
			return annualRate;
		}

		public void setAnnualRate(double annualRate) {
			this.annualRate = annualRate;
		}
		
		public void addMonthlyInterest() {
			double b = balance*(annualRate/1200.0);
			this.balance += Math.rint(b*100.0)/100.0;
		}
		
		public abstract void deposit (double amount) throws Exception;					
		public abstract void withdraw (double amount) throws Exception;		
		public abstract String getType ();
		
		public String toString() {
			return "Account ID: " + this.id + " | Type: " + this.getType() + " | Date Opened: " + this.dateOpened + " | Annual Interest Rate: "+ this.annualRate + " | Balance: " + this.balance;
		}		
}
