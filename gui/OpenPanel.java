package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import account.CheckingAccount;
import account.CreditCardAccount;
import account.SavingsAccount;

public class OpenPanel extends JPanel {

	private JRadioButton radSavings;
	private JRadioButton radChecking;
	private JRadioButton radCredCard;
	private JTextField txtAnnualIR;
	private JTextField txtOverdraftLimit;

	public OpenPanel() {
		//Creating Components
		JLabel lblType = new JLabel("Type", JLabel.TRAILING);
		radSavings = new JRadioButton("Savings");
		radChecking = new JRadioButton("Checking");
		radCredCard = new JRadioButton("Credit Card");
		JLabel lblOverdraftLimit = new JLabel("Overdraft Limit", JLabel.TRAILING);		
		JLabel lblAnnualIR = new JLabel("Annual Interest Rate", JLabel.TRAILING);
		txtAnnualIR = new JTextField(6);
		txtOverdraftLimit = new JTextField(6);
		JButton btnOpen = new JButton("Open");
		JButton btnExit = new JButton("Exit");
		btnOpen.addActionListener(new OpenHandler());
		btnExit.addActionListener(new ExitHandler());
		
		//Adding components to Panel
		JPanel pnlType = new JPanel(new GridLayout(5,2));
		pnlType.add(new JLabel());//empty label
		pnlType.add(radSavings);
		pnlType.add(lblType);
		pnlType.add(radChecking);
		pnlType.add(new JLabel());//empty label
		pnlType.add(radCredCard);
		pnlType.add(lblOverdraftLimit);
		pnlType.add(txtOverdraftLimit);
		pnlType.add(lblAnnualIR);
		pnlType.add(txtAnnualIR);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radSavings);
		group.add(radChecking);
		group.add(radCredCard);

		JPanel pnlButtons = new JPanel();
		pnlButtons.add(btnOpen);
		pnlButtons.add(btnExit);

		//Adding the Panels to Openpanel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(pnlType);
		this.add(pnlButtons);
	}
	
	private class ExitHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}		
	}
	
	private class OpenHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//Read the annual rate.
			double ol = Double.parseDouble(txtOverdraftLimit.getText());
			double ar = Double.parseDouble(txtAnnualIR.getText());
			if(radSavings.isSelected()){
				SavingsAccount sa = new SavingsAccount(ar);
				BankGUI.bank.addAccount(sa);
				JOptionPane.showMessageDialog(null, sa.getType() + " Account was successfully Created. ID #" + sa.getId());
				txtOverdraftLimit.setText("");
				txtAnnualIR.setText("");
			}
			else if(radChecking.isSelected()){
				CheckingAccount ca = new CheckingAccount(ar, ol);
				BankGUI.bank.addAccount(ca);
				JOptionPane.showMessageDialog(null, ca.getType() + " Account was successfully Created. ID #" + ca.getId());
				txtOverdraftLimit.setText("");
				txtAnnualIR.setText("");
			}
			else if(radCredCard.isSelected()){
				CreditCardAccount cc = new CreditCardAccount(ar, ol);
				BankGUI.bank.addAccount(cc);
				JOptionPane.showMessageDialog(null, cc.getType() + " Account was successfully Created. ID #" + cc.getId());
				txtOverdraftLimit.setText("");
				txtAnnualIR.setText("");
			}
		}
		
	}
}
