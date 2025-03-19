package personal.contacts.program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

//Holds different classes that deal with creating an error window pop-up
public class ErrorDialog 
{
	public ErrorDialog() { }
	
	//This dialog triggers if the home/cell number fields are not empty and do not have 10 digits
	//Empty fields are allowed in case the contact doesn't have a home/cell number
	public static class NumberLengthError extends JDialog implements ActionListener
	{
		private static JPanel errorPanel;
		private static JButton confirmButton;
		private Font defaultTextFont = new Font("SansSerif", Font.PLAIN, 12);
		private Font smallTextFont = new Font("SansSerif", Font.PLAIN, 11);
		
		public NumberLengthError()
		{
			Dimension dialogDimension = new Dimension(300, 190);
			
			this.setTitle("Error");
			this.setPreferredSize(dialogDimension);
			this.setMinimumSize(dialogDimension);
			this.setMaximumSize(dialogDimension);
			this.setResizable(false);
			this.setLayout(new BorderLayout());
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			this.setLocationRelativeTo(null);
			
			createErrorPanel();
			
			this.pack();
			this.setVisible(true);
		}
		
		private void createErrorPanel()
		{
			errorPanel = new JPanel(new GridLayout(3, 1, 0, 2));
			errorPanel.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new EtchedBorder(EtchedBorder.LOWERED)));
			
			String errorMsg = "<html><p style=\"text-align:center;\">"
					           + "ERROR:</p>"
					           + "<p style=\"text-align:center;\">"
					           + "The fields of Home/Cell numbers only accept numerical values and must be 10 digits."
					           + "</p></html>";
			String extraMsg = "<html><p style=\"text-align:center;\">"
							   + "If contact does not have a number, enter 0 into the corresponding field."
							   + "</p></html>";
					
			JLabel errorLabel = new JLabel(errorMsg, JLabel.CENTER);
			errorLabel.setFont(defaultTextFont);
			errorLabel.setForeground(Color.red);
			errorPanel.add(errorLabel);
			
			JLabel extraLabel = new JLabel(extraMsg, JLabel.CENTER);
			extraLabel.setFont(smallTextFont);
			errorPanel.add(extraLabel);
			
			JPanel buttonPanel = new JPanel();
			confirmButton = new JButton("I understand");
			confirmButton.setFont(smallTextFont);
			confirmButton.addActionListener(this);
			buttonPanel.add(confirmButton);
			errorPanel.add(buttonPanel);
			
			this.add(errorPanel);
				
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//Once the user confirms they read the error message, remove the pop-up
			if(e.getSource() == confirmButton)
				dispose();		
		}
		
	}
}
