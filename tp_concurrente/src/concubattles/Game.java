package concubattles;

public class Game {
	
	private int count;
	
	public int getNextIn(){
		int next = this.count;
		this.count++;
		return next;
	}
	
	public Castle createCastle(){
		return new Castle(new Channel<String>(this.getNextIn()));
	}
	
	public City createCity(){
		return new City(new Channel<String>(this.getNextIn()));
	}
	
	private Way createway(){
		return new Way(new Channel<String>(this.getNextIn()));
	}
	
	public void connect(Place place1, Place place2){
		Way way = this.createway();
		place1.getRoads().add(way);
		place2.getRoads().add(way);
		way.getRoads().add(place1);
		way.getRoads().add(place2);
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		Castle team1 = game.createCastle();
		Castle team2 = game.createCastle();
		City city1 = game.createCity();
		City city2 = game.createCity();
		game.connect(team1, city1);
		game.connect(city1, city2);
		game.connect(city2, team2);
		team1.createSoldier();
		team2.createSoldier();
	}

}
