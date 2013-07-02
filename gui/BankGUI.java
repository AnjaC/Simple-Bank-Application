package gui;

import javax.swing.*;

import account.Bank;

public class BankGUI extends JFrame{
	public static Bank bank = new Bank();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					// Set the GUI look and feel to the same as the system.
					String laf = UIManager.getSystemLookAndFeelClassName();
					UIManager.setLookAndFeel(laf);
				}
				catch (Exception ex) { /* Do nothing */ }

				new BankGUI();
			}
		});
	}

public BankGUI() {
	JTabbedPane contentPane = new JTabbedPane();
	contentPane.addTab("Open New" , new OpenPanel());
	contentPane.addTab("Update" , new UpdatePanel());
	contentPane.addTab("Interest" , new InterestPanel());
	contentPane.addTab("Close" , new ClosePanel());

	this.setContentPane(contentPane);
	this.pack();
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
}
}
