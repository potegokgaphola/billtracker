package billtracker.model;

public class UserModel {
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

	private static int id;
	private static String firstname;
}
