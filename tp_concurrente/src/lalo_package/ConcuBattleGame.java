package lalo_package;

import ar.edu.unq.tpi.pconc.Channel;

public class ConcuBattleGame {

	private int count;

	public ConcuBattleGame() {
		this.count = 0;
	}

	public int nextChannelInt() {
		int next = this.count;
		this.count++;
		return next;
	}

	private void makeConnectionAll(Place place1, Place place2) {

		Way way = new Way(new Channel<String>(this.nextChannelInt()));
		Gate gate1 = new Gate(place1, way, new Channel<String>(
				this.nextChannelInt()));
		Gate gate2 = new Gate(place2, way, new Channel<String>(
				this.nextChannelInt()));
		way.saveConnection(place1, gate1);
		way.saveConnection(place2, gate2);
		place1.saveConnection(way, gate1);
		place2.saveConnection(way, gate2);
	}

	public void makeConnection(City city1, City city2) {
		this.makeConnectionAll(city1, city2);
	}

	public void makeConnection(Castle castle, City city) {
		this.makeConnectionAll(castle, city);
	}

	public static void main(String[] args) {
		ConcuBattleGame game = new ConcuBattleGame();

		Castle team1 = new Castle(new Channel<String>(game.nextChannelInt()));
		Castle team2 = new Castle(new Channel<String>(game.nextChannelInt()));
		City city1 = new City(new Channel<String>(game.nextChannelInt()));
		City city2 = new City(new Channel<String>(game.nextChannelInt()));
		game.makeConnection(team1, city1);
		game.makeConnection(city1, city2);
		game.makeConnection(team2, city2);

		team1.createSoldier();
		team2.createSoldier();

	}

}
