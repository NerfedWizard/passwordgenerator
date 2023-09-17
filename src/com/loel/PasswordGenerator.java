package com.loel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.filechooser.FileSystemView;

public class PasswordGenerator {
	private StringBuilder password;
	private static final int PASSWORD_LENGTH = 18;
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String GET_URL = "https://random-word-api.herokuapp.com/word?length=";
	private static final String TITLE = "Password Generator";

	public PasswordGenerator() {
		this.password = new StringBuilder("");
	}

	public static void main(String[] args) {
		PasswordGenerator pc = new PasswordGenerator();
		PassGUI pg = new PassGUI();
		pg.run();
		PassGUI.getGeneratePassword().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Password Button");
					pc.makeNewPassword();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		PassGUI.getSaveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Save Button");
					pc.writeToFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public static String getNameForTitle() {
		return TITLE;
	}

	public static String getWords(int wordLength) throws IOException {
		String word = "";
		URL first_try = new URL(GET_URL + wordLength);
		HttpURLConnection first_con = (HttpURLConnection) first_try.openConnection();
		first_con.setRequestMethod("GET");
		first_con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = first_con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(first_con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			int indexFirst = response.toString().indexOf('"') + 1;
			int indexFirstEnd = response.toString().lastIndexOf('"');
			word = response.toString().substring(indexFirst, indexFirstEnd);
		} else {
			System.out.println("GET request not worked");
		}
		return word;
	}

	public void makeNewPassword() throws IOException {
		password = new StringBuilder("");
		password.append(getRandomNumber(0, 9));
		int wrdLen1 = getRandomNumber(2, PASSWORD_LENGTH - 2);
		int wordLen1 = wrdLen1;
		int wordLen2 = PASSWORD_LENGTH - wordLen1;
		String firstWord = capFirstLetter(getWords(wordLen1));
		String secWord = capFirstLetter(getWords(wordLen2));
		password.append(firstWord);
		password.append(getSymbol());
		password.append(secWord);
		setPassword(password.toString());
		PassGUI.getPassTA().setText(password.toString());
	}

	public String capFirstLetter(String cap) {
		return cap.substring(0, 1).toUpperCase() + cap.substring(1);
	}

	public void writeToFile() throws IOException {
		String who = PassGUI.getNewEntity().getText();
		String passGen = getPassword().toString();
		String userName = PassGUI.getNewUserNameTF().getText();
		File home = FileSystemView.getFileSystemView().getHomeDirectory();

		try (FileWriter fw = new FileWriter(home.getAbsolutePath() + "\\password.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println("\n---> " + who + " <---");
			out.println("---> " + userName + " <---");
			out.println("--> " + passGen + " <--");
		} catch (IOException e) {
			System.err.println("file not written");
		}
		PassGUI.getPassTA().setText("Password:\n\t" + passGen + "\nhas been saved to: " + home.getAbsolutePath());
	}

	public static String getSymbol() {
		String[] symbol = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "?", "/", ">", ".",
				"<", ",", "~", "`", ":", ";", "{", "}", "[", "]", "|", "}", ";" };
		int number = (int) (Math.random() * (symbol.length - 0) + 0);
		return symbol[number];
	}

	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public StringBuilder getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		StringBuilder temp = new StringBuilder(password);
		this.password = temp;
	}
}
