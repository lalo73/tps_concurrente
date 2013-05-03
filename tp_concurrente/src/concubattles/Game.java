package concubattles;

import ar.edu.unq.tpi.pconc.Channel;

public class Game {
	
	private int count;
	private Channel<String> input;
	private Channel<String> output;
	
	public int getNextIn(){
		int next = this.count;
		this.count++;
		return next;
	}
	
	public Game(Channel<String> input, Channel<String> output){
		this.count = 5;
		this.input = input;
		this.output = output;
	}
	
	public Castle createCastle(){
		return new Castle(new Channel<String>(this.getNextIn()), this.getNextIn());
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
	
	public void receive(){
		System.out.println(this.input.receive());
	}
	
	public void moveSilver(int i){
		this.output.send("silver1 1");
	}
	
	public static void main(String[] args) {
		
		Game game = new Game(new Channel<String>(1002), new Channel<String>(1001));
		
		game.receive();
		game.moveSilver(0);
//		Castle team1 = game.createCastle();
//		Castle team2 = game.createCastle();
//		City city1 = game.createCity();
//		City city2 = game.createCity();
//		game.connect(team1, city1);
//		game.connect(city1, city2);
//		game.connect(city2, team2);
//		team1.createSoldier();
//		team2.createSoldier();
	}

}
