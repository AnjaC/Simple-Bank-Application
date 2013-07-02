package gui;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InterestPanel extends JPanel{

	private JButton btnExit;
	public InterestPanel(){
		JButton btnAddInterest = new JButton ("Add Interest");
		btnExit = new JButton ("Exit");
		btnExit.addActionListener(new ExitHandler());
		btnAddInterest.addActionListener(new AddInterestHandler());

		JPanel pnlButtons = new JPanel ();
		pnlButtons.add(btnAddInterest);
		pnlButtons.add(btnExit);

		this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
		this.add(pnlButtons);

	}
	private class ExitHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}		
	}
	
	private class AddInterestHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			BankGUI.bank.addMonthlyInterest();
			JOptionPane.showMessageDialog(null, "Interest Rates were successfully applied.");
		}		
	}
}