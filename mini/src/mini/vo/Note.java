package mini.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Serializable{
	/**
	 * @author 최보원
	 */
	private String sender; // User's id
	private String receiver; // User's id
	private String content; // 쪽지내용
	private Date sendTime; // 발송시간
	
	public Note() {}

	public Note(String sender, String receiver, String content, Date sendTime) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.sendTime = sendTime;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String toString() {
		return "보낸 사람 = " + sender + 
				", 받는 사람 = " + receiver + "\n" +
				"   내용 = " + content + "\n" +
				"   보낸 시간 = " + new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss").format(sendTime);
	}
}
