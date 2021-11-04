package mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

	/**
	 * 
	 * @author 김상민 <나중에 비밀번호를 발송하도록 바꿀것>
	 *
	 */

public class MailSend {
	public static void sendMail(String pw, String name) {		
		// SMTP 설정
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		Properties props = System.getProperties();
//		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");

		// 세션 및 메세지 객체 생성
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				//변수를 받아와서 새팅하는 방법 확인 
				String name = "구글이메일 주소";
				String pw = "구글비밀번호";
				return new PasswordAuthentication(name, pw);
			}

		});
		MimeMessage msg = new MimeMessage(session);

		try {
			//이메일 정규식
			// boolean err = false; 
			String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; 
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(name); 
			if(!m.matches()) {
				 throw new Exception("이메일 확인 불가");
			//	err = true; 
			}

			msg.setFrom(new InternetAddress("구글 이메일 주소", "TESTER"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(name));
			msg.setSubject("비밀번호 확인");
			msg.setText(pw, "utf-8");

			Transport.send(msg);
			System.out.println("이메일 발송 완료");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("이메일 확인 불가");
		}
	}

//	public static void main(String[] args) throws Exception{
//		sendMail( "tkdals99!@","snman016@gmail.com"); // 비밀번호 받이메일 , 제목, 내용
//		
//	}

}