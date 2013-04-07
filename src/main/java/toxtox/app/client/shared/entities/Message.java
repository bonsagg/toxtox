package toxtox.app.client.shared.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Message extends AbstractEntity{

	private static final long serialVersionUID = -889034547193412676L;
	
	@NotNull
	private Date sendDate;
	@NotNull
	private String message;
	@NotNull
	private String regard;
	@ManyToOne
	private User receiver;
	@ManyToOne
	private User sender;
	
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRegard() {
		return regard;
	}
	public void setRegard(String regard) {
		this.regard = regard;
	}
}
