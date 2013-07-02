package account;

public class SavingsAccount extends Account{
	//----------------------------------------------
	//		Data
	//----------------------------------------------
	public static final double DEFAULT_ANNUAL_RATE = 0.025;
	
	//----------------------------------------------
	//		Constructor
	//----------------------------------------------
	public SavingsAccount (double annualRate) {
		super(annualRate);
	}
	
	@Override
	public void deposit (double amount) throws Exception {
		if (amount > 0) {
			balance += amount;
		} else {
			throw new Exception("You cannot deposit a negative amount. Use a positive one.");
		}
	}
	
	@Override
	public void withdraw (double amount) throws Exception {
		if (amount > 0) {
			if (amount <= balance){
				balance -= amount;
			} else {
				throw new Exception("Insuficient Funds.");
			}
		} else {
			throw new Exception("You cannot withdraw a negative amount. Use a positive one.");
		}
	}
	
	@Override
	public String getType(){
		return "Savings";
	}
}
