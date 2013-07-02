package gui;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import account.Account;
import account.Bank;
import account.CheckingAccount;

public class ClosePanel extends JPanel{

	private JButton btnExit;
	private JTextField txtAccountID;
	private JTextField txtAccountType;
	private JTextField txtAnnualRate;
	private JTextField txtBalance;
	private JButton btnClose;

	public ClosePanel(){
		//Create the components
		JLabel lblAccountID = new JLabel("Account ID", JLabel.TRAILING);
		txtAccountID = new JTextField(5);
		txtAccountID.addActionListener(new AccountIdHandler());
		JLabel lblAccountType = new JLabel("Account Type", JLabel.TRAILING);
		txtAccountType = new JTextField();
		JLabel lblAnnualRate = new JLabel("Annual Rate", JLabel.TRAILING);
		txtAnnualRate = new JTextField();
		JLabel lblBalance = new JLabel("Balance", JLabel.TRAILING);
		txtBalance = new JTextField();

		btnClose = new JButton("Close");
		btnClose.addActionListener(new CloseHandler());
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new Exit());

		JPanel pnlType = new JPanel(new GridLayout(4,2));
		pnlType.add(lblAccountID);
		pnlType.add(txtAccountID);
		pnlType.add(lblAccountType);
		pnlType.add(txtAccountType);
		pnlType.add(lblAnnualRate);
		pnlType.add(txtAnnualRate);
		pnlType.add(lblBalance);
		pnlType.add(txtBalance);

		JPanel pnlButtons = new JPanel();
		pnlButtons.add(btnClose);
		pnlButtons.add(btnExit);


		this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
		this.add(pnlType);
		this.add(pnlButtons);
	}


	//Behavior
	private Account find(){
		int accountID = Integer.parseInt(txtAccountID.getText());
		return BankGUI.bank.findAccount(accountID);
	}


	private class Exit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private class AccountIdHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			if (find() == null){
				JOptionPane.showMessageDialog(null, "Account doesn't exist.");
				//Clear fields
				btnClose.setEnabled(false);
				txtAccountID.setText("");
				txtAccountType.setText("");
				txtAnnualRate.setText("");
				txtBalance.setText("");
			}
			else{	
				txtAccountType.setText(find().getType());
				txtAccountType.setEditable(false);
				txtAnnualRate.setText(Double.toString(find().getAnnualRate()) + " %");	
				txtAnnualRate.setEditable(false);
				txtBalance.setText("$" + Double.toString(find().getBalance()));
				txtBalance.setEditable(false);
				btnClose.setEnabled(true);

			}

		}
	}


	private class CloseHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
		Account acct = find();
		BankGUI.bank.removeAccount(acct);
		JOptionPane.showMessageDialog(null, acct.getType() + " Account was succesfully closed. ID # " + acct.getId());

		
		//Clear fields
		txtAccountID.setText("");
		txtAccountType.setText("");
		txtAnnualRate.setText("");
		txtBalance.setText("");
		
		
		}
	}

}
