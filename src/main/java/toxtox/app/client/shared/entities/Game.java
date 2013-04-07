package toxtox.app.client.shared.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Game extends AbstractEntity {

	private static final long serialVersionUID = -2572599830552741578L;

	@ManyToMany(mappedBy="playingGames")
	private List<User> players = new ArrayList<User>();

	public List<User> getPlayers() {
		return players;
	}

	public void setPlayers(List<User> players) {
		this.players = players;
	}
}
