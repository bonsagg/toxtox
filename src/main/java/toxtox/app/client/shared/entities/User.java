package toxtox.app.client.shared.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;


@Entity
@NamedQueries({ @NamedQuery(name = User.FIND_USER_BY_USERNAME, query = "SELECT u FROM User u where u.userName = :userName") })
public class User extends AbstractEntity {

	private static final long serialVersionUID = 9171365352622034806L;

	public static final String FIND_USER_BY_USERNAME = "findUserByUserName";

	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@Column(unique = true)
	@NotNull
	private String userName;
	@NotNull
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}