package account;

public class CreditCardAccount extends Account{
	//-------------------------------
	//		Data
	//-------------------------------
	public static final double DEFAULT_ANNUAL_RATE = 0.125;
	private static final double CASH_ADVANCE_FEE = 15;
	private double creditLimit;

	//-------------------------------
	//		Methods
	//-------------------------------
	public CreditCardAccount (double annualRate, double creditLimit) {
		super(annualRate);
		this.creditLimit = creditLimit;
	}



	public double getCreditLimit() {
		return creditLimit;
	}



	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public double getRemainCredit() {
		return creditLimit - balance;
	}

	public void charge(double amount) throws Exception {
		if(amount > 0){
			if(amount <= getRemainCredit()){
				balance += amount;
			}
			else {
				throw new Exception("You cannot exceed you remaining credit of: $" + getRemainCredit());
			}
		}
		else {
			throw new Exception("Please enter a positive amount.");
		}
	}

	@Override
	public void deposit(double amount) throws Exception {
		if (amount > 0){
			balance -= amount;
		}
		else {
			throw new Exception("Please enter a positive amount.");
		}		
	}

	@Override
	public void withdraw(double amount) throws Exception{
		if (amount > 0){
			if (amount <= getRemainCredit()){
				balance += amount + CASH_ADVANCE_FEE;	
			}
			else{
				throw new Exception("You cannot exceed you remaining credit of: $" + getRemainCredit() + ". Remember a Cash Advance Fee of $" + CASH_ADVANCE_FEE + " may also apply.");
			}
		}
		else {
			throw new Exception("Please enter a positive amount.");
		}
	}

		@Override
		public String getType() {
			return "Credit Card";
		}
	}
