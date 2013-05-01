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

	public static void main(String[] args) {
		ConcuBattleGame game = new ConcuBattleGame();
		
		Castle team1 = new Castle(new Channel<String>(game.nextChannelInt()));
		Castle team2 = new Castle(new Channel<String>(game.nextChannelInt()));
		
		City city1 = new City(new Channel<String>(game.nextChannelInt()));
		City city2 = new City(new Channel<String>(game.nextChannelInt()));
		
		Gate gate = new Gate(team1, city1, new Channel<String>(game.nextChannelInt()));
		Gate gate2 = new Gate(team2, city2, new Channel<String>(game.nextChannelInt()));
		Gate gate3 = new Gate(city1, city2, new Channel<String>(game.nextChannelInt()));	
		team1.saveConnection(city1, gate);
		city1.saveConnection(team1, gate);		
		team2.saveConnection(city2, gate2);
		city2.saveConnection(team2, gate2);		
		city1.saveConnection(city2, gate3);
		city2.saveConnection(city1, gate3);
		
		team1.createSoldier();
		team2.createSoldier();
			
	}

}
