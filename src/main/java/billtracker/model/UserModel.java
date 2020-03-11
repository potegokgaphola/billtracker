package billtracker.model;

import lombok.Data;

@Data
public class UserModel {
	private static int id;
	private static String firstname;
	private static String lastname;
	private static String password;
	private static String username;

	public UserModel(int id, String username, String firstname, String lastname, String password){
		UserModel.id = id;
		UserModel.username = username;
		UserModel.firstname = firstname;
		UserModel.lastname = lastname;
		UserModel.password = password;
	}

	public UserModel() {}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		UserModel.username = username;
	}

	public static int getId() {

		return id;
	}

	public static void setId(int id) {

		UserModel.id = id;
	}

	public static String getFirstname() {

		return firstname;
	}

	public static void setFirstname(String firstname) {

		UserModel.firstname = firstname;
	}

	public static String getLastname() {
		return lastname;
	}

	public static void setLastname(String lastname) {
		UserModel.lastname = lastname;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		UserModel.password = password;
	}
}
