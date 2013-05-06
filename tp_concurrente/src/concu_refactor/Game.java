package concu_refactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import ar.edu.unq.tpi.pconc.Channel;
import ar.edu.unq.tpi.pconc.Utils;

public class Game {

	private int count;
	private Channel<String> input;
	private Channel<String> output;
	public ArrayList<Place> places;
	public HashMap<Place, ArrayList<Place>> connected;
	public ArrayList<Castle> castles;
	public int minID;

	public Game(Channel<String> input, Channel<String> output) {
		this.setCount(10);
		this.setInput(input);
		this.setOutput(output);
		this.setPlaces(new ArrayList<Place>());
		this.setMinID(1);
		this.setConnected(new HashMap<Place, ArrayList<Place>>());
		this.setCastles(new ArrayList<Castle>());
	}

	public Castle createCastle(int castleID) {		
		this.ensureMinID(castleID);
		Castle castle = new Castle(
				new Channel<String>(this.getNextChannelID()), castleID, this);
		this.getPlaces().add(castle);
		this.getCastles().add(castle);
		return castle;

	}

	public String getColorSoldier(Castle castle) {
		if (castle.getId() == 1) {
			return "gold";
		}
		return "silver";
	}

	public void putSoldier(Soldier soldier, Place place) {
		this.send(this.getColorSoldier(soldier.getTeam()) + soldier.getSoldier_id()
				+ " " + place.getId());
	}

	public void putSoldier(Soldier soldier, Place place, double dou) {
		this.send(this.getColorSoldier(soldier.getTeam()) + soldier.getSoldier_id()
				+ " " + place.getId() + " " + dou);
	}

	public void removeSoldier(Soldier soldier) {
		this.send(this.getColorSoldier(soldier.getTeam()) + soldier.getSoldier_id());
	}

	public void addSoldier(Soldier soldier) {
		this.send(this.getColorSoldier(soldier.getTeam()) + soldier.getSoldier_id()
				+ " " + soldier.getTeam().getId());
	}

	public void send(String message) {
		this.output.send(message);
	}

	public City createCity(int ciryID) {		
		this.ensureMinID(ciryID);
		City city = new City(new Channel<String>(this.getNextChannelID()), ciryID, this);
		this.getPlaces().add(city);
		return city;

	}

	private Way createWay(int id) {
		this.ensureMinID(id);
		Way way = new Way(new Channel<String>(this.getNextChannelID()), id, this);
		this.getPlaces().add(way);
		return way;
	}

	public void addOrCreate(Place key, Place value) {
		if (!this.getConnected().containsKey(key)) {
			this.getConnected().put(key, new ArrayList<Place>());
		}
		this.getConnected().get(key).add(value);
	}

	public void connect(Place place1, Place place2) {
		Way way = this.createWay(this.getNextID());		
		place1.getRoads().add(way);
		place2.getRoads().add(way);
		way.getRoads().add(place1);
		way.getRoads().add(place2);
		this.addOrCreate(place1, place2);
		this.addOrCreate(place2, place1);
	}

	public void connectByID(int id1, int id2) {
		Place place1 = this.getPlaceById(id1);
		Place place2 = this.getPlaceById(id2);
		if (isConnectedTo(place1, place2)) {
			return;
		} else {
			this.connect(place1, place2);
		}
	}

	public Place getPlaceById(int id) {
		for (Place place : this.getPlaces()) {
			if (place.getId() == id) {
				return place;
			}
		}
		return null;
	}

	public boolean isConnectedTo(Place place1, Place place2) {
		if (this.getConnected().containsKey(place1)) {
			return this.getConnected().get(place1).contains(place2);
		}
		return false;
	}

	public void createMap() {
		String map = this.getInput().receive();
		String[] cities = map.split("\n");
		int numCities = cities.length - 1;
		for (int i = 2; i <= numCities; i++) {
			this.createCity(i);
		}
		this.createCastle(numCities + 1);
		this.createCastle(1);

		for (String city : cities) {
			String[] citiesID = city.split(" ");
			String id1 = citiesID[1];
			for (String id : Arrays.copyOfRange(citiesID, 2, citiesID.length)) {
				this.connectByID(Utils.parseInt(id1), Utils.parseInt(id));
			}

		}

	}

	public void startGame() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		for (Castle c : this.getCastles()) {			
			c.createSoldier();
		}
	}
	
	public void increaseMinID() {
		this.minID++;
	}
	
	public void ensureMinID(int id) {
		while (this.getMinID() <= id) {
			this.increaseMinID();
		}
	}
	
	public int getNextID() {
		int next = this.minID;
		this.increaseMinID();
		return next;
	}
	
	public int getNextChannelID() {
		int next = this.getCount();
		this.increaseCount();
		return next;
	}

	public void increaseCount() {
		this.count++;
	}

	public static void main(String[] args) {
		Game game = new Game(new Channel<String>(1002), new Channel<String>(1001));
		game.createMap();
		game.startGame();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Channel<String> getInput() {
		return input;
	}

	public void setInput(Channel<String> input) {
		this.input = input;
	}

	public Channel<String> getOutput() {
		return output;
	}

	public void setOutput(Channel<String> output) {
		this.output = output;
	}

	public ArrayList<Place> getPlaces() {
		return places;
	}

	public void setPlaces(ArrayList<Place> places) {
		this.places = places;
	}

	public HashMap<Place, ArrayList<Place>> getConnected() {
		return connected;
	}

	public void setConnected(HashMap<Place, ArrayList<Place>> connected) {
		this.connected = connected;
	}

	public ArrayList<Castle> getCastles() {
		return castles;
	}

	public void setCastles(ArrayList<Castle> castles) {
		this.castles = castles;
	}

	public int getMinID() {
		return minID;
	}

	public void setMinID(int minID) {
		this.minID = minID;
	}
	
	

}
