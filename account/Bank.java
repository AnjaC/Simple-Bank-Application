package account;

public class Bank {
	//------------------
	//	Attributes
	//----------------
	
	private Account[] accounts;
	private int numberOfAccounts;
	private int lastIndex; //Holds the index of the last account in the accounts array.

	
	//-----------------------
	//	Behavior
	//----------------------

	//************************************************************************************************
	//Methods inherited by java.lang.Object:
	//		clone, equals, finalize, getClass, hashCode, notify, notifyAll, toString, wait, wait, wait
	//************************************************************************************************
	
	public Bank(){
		this.accounts = new Account[10000];
		this.numberOfAccounts = 0;
		this.lastIndex = 0;
		//Creates a bank object by initializing accounts and numberOfAccounts.
	}
	
	public void addAccount(Account account){
		accounts[account.getId()] = account;
		numberOfAccounts++;
		lastIndex++;
		//Creates a bank object by initializing accounts and numberOfAccounts.
	}
	
	public Account findAccount(int id){
		return accounts[id];
		//Returns the account that has the specified id or null if no such account exists.
	}
	
	public void addMonthlyInterest(){
		for (int i = 0; i < accounts.length; i++){
			if (accounts[i] != null){
				accounts[i].addMonthlyInterest();
			}
		}
		// Adds monthly interest to all the accounts in this bank.
	}
	
	public void removeAccount(Account account){
//		if (accounts[account.getId()] != null){
			accounts[account.getId()] = null;
			numberOfAccounts--;
//		}
//		else {
//			throw new Exception("Account doesn't exist.");
//		}
		//Removes an account from this bank.
	}
}
