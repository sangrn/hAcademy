package mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthentication extends Authenticator {
	PasswordAuthentication passAuth;

	public GoogleAuthentication() {
		passAuth = new PasswordAuthentication("snman016@gmail.com", "tkdals99!@");
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return passAuth;
	}
}
