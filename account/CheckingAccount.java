package account;

public final class CheckingAccount extends SavingsAccount{
	//-------------------------------
	//		Data
	//-------------------------------
	public static final double DEFAULT_ANNUAL_RATE = 0.015;
	private static final double OVERDRAFT_FEE = 30;

	private double overdraftLimit;

	//-------------------------------
	//		Methods
	//-------------------------------
	public CheckingAccount (double annualRate, double overdraftLimit) {
		super(annualRate);
		this.overdraftLimit = -Math.abs(overdraftLimit);
	}

	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = -Math.abs(overdraftLimit);
	}

	public void payCheck (double amount) throws Exception {		
		if (amount < 0){
			throw new Exception("Please enter a positive amount.");
		}
		else if(amount <= balance){
			balance -= amount;
		}
		else {
			if (amount > balance && ((balance - amount) >= (overdraftLimit))){
				balance -= amount + OVERDRAFT_FEE;
			}
			else {
				throw new Exception("You cannot exceed your current Overdraft Limit of: $" + Math.abs(getOverdraftLimit()));
			}
		}
	}

	@Override
	public String getType() {
		return "Checking";
	}
}
