package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import account.*;

public class UpdatePanel extends JPanel {
	//Attributes
	private JTextField txtAccountID;
	private JTextField txtAccountType;
	private JTextField txtAnnualRate;
	private JTextField txtBalance;
	private JButton btnDeposit;
	private JButton btnWithdraw;
	private JButton btnPayCheck;
	private JButton btnMakePayment;
	private JButton btnCharge;
	private JButton btnExit;

	//Constructor
	public UpdatePanel() {
		//Create the components
		JLabel lblAccountID = new JLabel("Account ID", JLabel.TRAILING);
		txtAccountID = new JTextField(5);
		txtAccountID.addActionListener(new AccountIDHandler());
		JLabel lblAccountType = new JLabel("Account Type", JLabel.TRAILING);
		txtAccountType = new JTextField();
		JLabel lblAnnualRate = new JLabel("Annual Rate", JLabel.TRAILING);
		txtAnnualRate = new JTextField();
		JLabel lblBalance = new JLabel("Balance", JLabel.TRAILING);
		txtBalance = new JTextField();

		btnDeposit = new JButton("Deposit");
		btnWithdraw = new JButton("Withdraw");
		btnPayCheck = new JButton("Pay Check");
		btnMakePayment = new JButton("Make Payment");
		btnCharge = new JButton("Charge");
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ExitHandler());
		btnDeposit.addActionListener(new DepositHandler());
		btnWithdraw.addActionListener(new WithdrawHandler());
		btnPayCheck.addActionListener(new PayCheckHandler());
		btnCharge.addActionListener(new ChargeHandler());
		btnMakePayment.addActionListener(new MakePaymentHandler());

		//Add the components to panel
		JPanel pnlType = new JPanel(new GridLayout(4,2));
		pnlType.add(lblAccountID);
		pnlType.add(txtAccountID);
		pnlType.add(lblAccountType);
		pnlType.add(txtAccountType);
		pnlType.add(lblAnnualRate);
		pnlType.add(txtAnnualRate);
		pnlType.add(lblBalance);
		pnlType.add(txtBalance);

		JPanel pnlButtons = new JPanel(new GridLayout(2,3));
		pnlButtons.add(btnDeposit);
		pnlButtons.add(btnWithdraw);
		pnlButtons.add(btnPayCheck);
		pnlButtons.add(btnMakePayment);
		pnlButtons.add(btnCharge);
		pnlButtons.add(btnExit);

		//Adding panels to OpenPanel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(pnlType);
		this.add(pnlButtons);
	}

	//Behavior
	private Account find(){
		int accountID = Integer.parseInt(txtAccountID.getText());
		return BankGUI.bank.findAccount(accountID);
	}

	//Action Listeners

	private class ExitHandler implements ActionListener{



		@Override

		public void actionPerformed(ActionEvent e) {

			System.exit(0);

		}

	}

	private class AccountIDHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			if (find() == null){
				JOptionPane.showMessageDialog(null, "Account doesn't exist.");
				//Disable any option.
				btnDeposit.setEnabled(false);
				btnWithdraw.setEnabled(false);
				btnMakePayment.setEnabled(false);
				btnCharge.setEnabled(false);
				btnPayCheck.setEnabled(false);
				//Clear fields
				txtAccountID.setText("");
				txtAccountType.setText("");
				txtAnnualRate.setText("");
				txtBalance.setText("");
			}
			else{
				txtAccountType.setText(find().getType());
				txtAccountType.setEditable(false);
				txtAnnualRate.setText(Double.toString(find().getAnnualRate()) + "%");
				txtAnnualRate.setEditable(false);
				txtBalance.setText("$" + Double.toString(find().getBalance()));
				txtBalance.setEditable(false);
			}
			if (find() instanceof CheckingAccount){
				btnDeposit.setEnabled(true);
				btnWithdraw.setEnabled(true);
				btnMakePayment.setEnabled(false);
				btnCharge.setEnabled(false);
				btnPayCheck.setEnabled(true);
			}
			else if (find() instanceof SavingsAccount) {
				btnDeposit.setEnabled(true);
				btnWithdraw.setEnabled(true);
				btnMakePayment.setEnabled(false);
				btnCharge.setEnabled(false);
				btnPayCheck.setEnabled(false);
			}
			else if (find() instanceof CreditCardAccount){
				btnDeposit.setEnabled(false);
				btnWithdraw.setEnabled(true);
				btnMakePayment.setEnabled(true);
				btnCharge.setEnabled(true);
				btnPayCheck.setEnabled(false);
			}
		}
	}

	private class DepositHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String depositInput = JOptionPane.showInputDialog("Deposit Value");
			double depositValue = Double.parseDouble(depositInput);
			try {
				find().deposit(depositValue);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(btnDeposit, e1.getMessage());
			}
			txtBalance.setText("$" + Double.toString(find().getBalance()));
		}
	}

	private class WithdrawHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String withdrawInput = JOptionPane.showInputDialog("Withdrawn Value");
			double withdrawtValue = Double.parseDouble(withdrawInput);
			try {
				find().withdraw(withdrawtValue);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(btnWithdraw, e1.getMessage());
			}
			txtBalance.setText("$" + Double.toString(find().getBalance()));
		}
	}

	private class PayCheckHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Account a = find();
			String payCheckInput = JOptionPane.showInputDialog("Pay Check Value");
			double payCheckValue = Double.parseDouble(payCheckInput);
			if (a instanceof CheckingAccount){
				try {
					((CheckingAccount)a).payCheck(payCheckValue);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(btnPayCheck, e1.getMessage());

				}
				txtBalance.setText("$" + Double.toString(find().getBalance()));
			}
		}
	}

	private class MakePaymentHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Account a = find();
			String makePaymInput = JOptionPane.showInputDialog("Payment Value");
			double makePaymValue = Double.parseDouble(makePaymInput);
			if (a instanceof CreditCardAccount){
				try {
					((CreditCardAccount)a).deposit(makePaymValue);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(btnMakePayment, e1.getMessage());
				}
				txtBalance.setText("$" + Double.toString(find().getBalance()));
			}
		}
	}

	private class ChargeHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Account a = find();
			String chargeInput = JOptionPane.showInputDialog("Charge Value");
			double chargeValue = Double.parseDouble(chargeInput);
			if (a instanceof CreditCardAccount){
				try {
					((CreditCardAccount)a).charge(chargeValue);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(btnCharge, e1.getMessage());
				}
				txtBalance.setText("$" + Double.toString(find().getBalance()));
			}
		}
	}
}