package lalo_package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ar.edu.unq.tpi.pconc.Channel;

public abstract class Place {
	private Channel<String> controlChannel;
	private HashMap<Place, Gate> connections;
	private ArrayList<Place> knownPlaces;
	private ArrayList<Soldier> soldiers;

	public void createConnectionTo(Place place, Channel<String> controlChannel) {
		Gate gate = new Gate(this, place, controlChannel);
		this.saveConnection(place, gate);
		place.saveConnection(this, gate);
	}

	public void saveConnection(Place place, Gate gate) {
		this.connections.put(place, gate);
		this.knownPlaces.add(place);
	}

	public Gate getAGate(Place previousPlace) {
		int index = 0;
		if (this.knownPlaces.size() != 1) {
			index = this.getRandomInt();
		}
		Place place = this.knownPlaces.get(index);
		if (place.equals(previousPlace)) {
			place = this.getNextTo(index);
		}
		Gate gate = this.connections.get(place);
		return gate;
	}

	private Place getNextTo(int index) {
		int nextIndex = index + 1;
		if (nextIndex == this.connections.size()) {
			nextIndex = 0;
		}
		return this.knownPlaces.get(nextIndex);
	}

	private Integer getRandomInt() {
		return new Random().nextInt(this.knownPlaces.size());
	}

	public Place(Channel<String> controlChannel) {
		this.controlChannel = controlChannel;
		this.connections = new HashMap<Place, Gate>();
		this.knownPlaces = new ArrayList<Place>();
		this.soldiers = new ArrayList<Soldier>();
		this.controlChannel.send("");
	}

	public void getPermission() {
		this.controlChannel.receive();
	}

	public void returnPermission() {
		this.controlChannel.send("");
	}

	/**
	 * La primera version de este metodo debe resolver batallas si las hubiera.
	 * agregar al soldado a si mismo, etc TODO: Que lo haga un thread aparte.
	 * 
	 * @param soldier
	 */
	abstract public void receive(Soldier soldier);

	/**
	 * Talavez simplemente remueve el soldado de la lista de soldados
	 * 
	 * @param soldier
	 */
	public void remove(Soldier soldier) {
		this.soldiers.remove(soldier);
	}

	/**
	 * Toma 2 soldados les hace un random a cada uno de acuerdo al nivel y
	 * retorna el que tiene el numero mas grande
	 * 
	 * @param soldier
	 * @param soldierEnemy
	 * @return soldado ganador
	 */
	public Soldier fight(Soldier soldier, Soldier soldierEnemy) {
		int x = (int) (Math.random() * soldier.getLevel());
		int y = (int) (Math.random() * soldierEnemy.getLevel());
		if (x < y) {
			return soldier;
		} else {
			return soldierEnemy;
		}
	}

	/**
	 * Llega un soldado enemigo.. Si este le gana a todos los soldados enemigos,
	 * se agrega el soldado a la lista de soldados del castillo o ciudad ya que
	 * posee un nuevo ocupante sino el soldado pierde y se interrumpe la
	 * iteracion. (ver este tema de la batalla) se debe ver el tema de la
	 * concurrencia.
	 */
	public void startBattle(Soldier soldierEnemy) {
		if (this.getSoldiers().isEmpty()) {
			this.conqueredBy(soldierEnemy);
		} else {
			while (soldierEnemy.isLive() && !this.getSoldiers().isEmpty()) {
				Soldier soldier = this.getSoldiers().get(0);
				Soldier winner = this.fight(soldier, soldierEnemy);
				if (winner.equals(soldierEnemy)) {
					this.getSoldiers().remove(soldier);
				} else {
					soldierEnemy.setLive(false);
					System.out.println("Soldier killed");
				}
				winner.experienceUp();
				winner.notifyCreateSoldier();
				Soldier killed;
				if(winner.equals(soldierEnemy)){
					killed = soldier;
				} else {
					killed = winner; 
				}
				if(killed.getLevel() > 1){
					killed.notifyCreateSoldier();
				}
			}
			if (soldierEnemy.isLive()) {
				this.conqueredBy(soldierEnemy);
			}
		}
	}

	abstract public void conqueredBy(Soldier soldier);

	public Channel<String> getControlChannel() {
		return controlChannel;
	}

	public void setControlChannel(Channel<String> controlChannel) {
		this.controlChannel = controlChannel;
	}

	public HashMap<Place, Gate> getConnections() {
		return connections;
	}

	public void setConnections(HashMap<Place, Gate> connections) {
		this.connections = connections;
	}

	public ArrayList<Place> getKnownPlaces() {
		return knownPlaces;
	}

	public void setKnownPlaces(ArrayList<Place> knownPlaces) {
		this.knownPlaces = knownPlaces;
	}

	public ArrayList<Soldier> getSoldiers() {
		return soldiers;
	}

	public void setSoldiers(ArrayList<Soldier> soldiers) {
		this.soldiers = soldiers;
	}

}
