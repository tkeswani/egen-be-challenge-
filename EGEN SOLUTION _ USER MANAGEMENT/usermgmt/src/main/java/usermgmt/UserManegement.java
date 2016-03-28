package usermgmt;

public class UserManegement {

	// running main would start a Jetty Server that runs on port 4567
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// calling the test class with UserService instance passed to the test class.
		new test(new UserService());
	}

}
