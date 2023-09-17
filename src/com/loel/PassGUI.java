package com.loel;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.GroupLayout.Alignment.TRAILING;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextArea;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class PassGUI implements Runnable {
	// Text Fields
	private static JTextField newEntity = new JTextField();
	private static JTextField newUserNameTF = new JTextField();
	// private static JOptionPane passOp = new JOptionPane();
	private static TextArea pasTA = new TextArea(2, 50);
	// Buttons
	private static Button saveButton = new Button("Save Password");
	private static Button generatePassword = new Button("Generate Password");
	// Labels
	private static Label newEntityLabel = new Label("Enter Entity");
	private static Label newUserNameLabel = new Label("Enter Username");
	private static Label passLabel = new Label("Password");

	public PassGUI() {
		/**
		 * Creating the frame
		 */
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new PassGUI());
	}

	public void run() {
		JFrame frame = new JFrame("Password Generator");
		frame.setSize(550, 400);

		GroupLayout layout = new GroupLayout(frame.getContentPane());
		frame.getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(LEADING).addComponent(newEntityLabel)
						.addComponent(newUserNameLabel).addComponent(passLabel).addComponent(generatePassword))
				.addGroup(layout.createParallelGroup(LEADING).addComponent(newEntity).addComponent(newUserNameTF)
						.addComponent(pasTA).addComponent(saveButton)));

		layout.linkSize(SwingConstants.HORIZONTAL, generatePassword, saveButton);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(BASELINE).addComponent(newEntityLabel).addComponent(newEntity))
				.addGroup(
						layout.createParallelGroup(BASELINE).addComponent(newUserNameLabel).addComponent(newUserNameTF))
				.addGroup(layout.createParallelGroup(LEADING).addComponent(passLabel).addComponent(pasTA)).addGroup(
						layout.createParallelGroup(TRAILING).addComponent(generatePassword).addComponent(saveButton)));
		frame.setTitle("PasswordGenerator");
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true); // making the frame visible

	}

	public static JTextField getNewEntity() {
		return newEntity;
	}

	public static void setNewEntity(JTextField newEntity) {
		PassGUI.newEntity = newEntity;
	}

	public static JTextField getNewUserNameTF() {
		return newUserNameTF;
	}

	public static void setNewUserNameTF(JTextField newUserNameTF) {
		PassGUI.newUserNameTF = newUserNameTF;
	}

	public static TextArea getPassTA() {
		return pasTA;
	}

	public static void setPassTA(TextArea passTA) {
		PassGUI.pasTA = passTA;
	}

	public static Button getSaveButton() {
		return saveButton;
	}

	public static void setSaveButton(Button saveButton) {
		PassGUI.saveButton = saveButton;
	}

	public static Button getGeneratePassword() {
		return generatePassword;
	}

	public static void setGeneratePassword(Button generatePassword) {
		PassGUI.generatePassword = generatePassword;
	}

//	public static void main(String args[]) {
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//					// "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//					// UIManager.getCrossPlatformLookAndFeelClassName());
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//				new PassGUI().setVisible(true);
//			}
//		});
//
//	}
}
