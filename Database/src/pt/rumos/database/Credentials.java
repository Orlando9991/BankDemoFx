package pt.rumos.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Credentials implements Serializable {

	private static final long serialVersionUID = 1L;
	private String url;
	private String username;
	private String password;

	static Credentials credentials;

	private Credentials() {
		config();
	}

	public static Credentials getInstance() {
		if (credentials == null) {
			credentials = new Credentials();
		}
		return credentials;
	}

	private void config() {
		String configfolderLoc = "/Rumos_Project_Database/config/fconfig.rumos";
		String location = new File(System.getProperty("user.dir")).getParent() + configfolderLoc;
		File file = new File(location);
		if (file.exists()) {
			fileLoad(file);
		} else {
			fileSave(file);
		}
	}

	public void fileSave(File file) {
		try {

			setUrl("jdbc:mysql://localhost:3306/rumos_digital_bank");
			setPassword("");
			setUsername("root");

			FileOutputStream fileOutput = new FileOutputStream(file);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(this);
			objectOutput.close();

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void fileLoad(File file) {
		try {
			FileInputStream fileInput = new FileInputStream(file);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			Credentials c = (Credentials) objectInput.readObject();
			setUrl(c.getUrl());
			setPassword(c.getPassword());
			setUsername(c.getUsername());
			objectInput.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	private void setPassword(String password) {
		this.password = password;
	}
}
