package concubattles;

import java.util.ArrayList;

import ar.edu.unq.tpi.pconc.Channel;

public class Game {
	
	private int count;
	private Channel<String> input;
	private Channel<String> output;
	public ArrayList<Place> places; 
	
	public int minID;
	
	public int getNextChannelID(){
		int next = this.count;
		this.increaseCount();
		return next;
	}
	public void increaseCount(){
		this.count++;
	}
	public void increaseID(){
		this.minID++;
	}
	public int getNextID(){
		int next = this.minID;
		this.increaseID();
		return next;
	}
	
	
	public Game(Channel<String> input, Channel<String> output){
		this.count = 5;
		this.input = input;
		this.output = output;
		this.places = new ArrayList<Place>();
		this.minID = 10;
	}
	
	public void ensureID(int id){
		while(this.minID <= id){
			this.minID++;
		}
	}
	
	public Castle createCastle(int castleID){
		System.out.println("Castillo creado con id: "+castleID);
		this.ensureID(castleID);
		Castle castle =  new Castle(new Channel<String>(this.getNextChannelID()), castleID);
		this.places.add(castle);
		return castle;
		
	}
	
	public City createCity(int ciryID){
		System.out.println("Ciudad creado con id: "+ciryID);
		this.ensureID(ciryID);
		City city = new City(new Channel<String>(this.getNextChannelID()), ciryID);
		this.places.add(city);
		return city;
	
	}
	
	private Way createway(int id){
		this.ensureID(id);
		Way way = new Way(new Channel<String>(this.getNextChannelID()), id);
		this.places.add(way);
		return way;
	}
	
	public void connect(Place place1, Place place2){
		Way way = this.createway(this.getNextID());
		place1.getRoads().add(way);
		place2.getRoads().add(way);
		way.getRoads().add(place1);
		way.getRoads().add(place2);
	}
	
	public void createMap(){
		String map = this.input.receive();
		String[] cities = map.split("\n");
		int numCities = cities.length - 2;
		for (int i = 1; i <= numCities; i++) {
			this.createCity(i);
		}
		for (int i = numCities +1; i <= cities.length; i++){
			this.createCastle(i);
		}
		/// Unir ciudades y castillos!!!!!!
		
	}
	
	public void moveSilver(int i){
		this.output.send("silver1 "+i);
	}
	
	public static void main(String[] args) {
		
		Game game = new Game(new Channel<String>(1002), new Channel<String>(1001));
		
		game.createMap();
		game.moveSilver(1);
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
