package concu_refactor;

import java.util.ArrayList;
import ar.edu.unq.tpi.pconc.Channel;

public abstract class Place {
	private Channel<String> controlChannel;
	private ArrayList<Soldier> soldiers = new ArrayList<Soldier>();
	private ArrayList<Place> roads = new ArrayList<Place>();
	private int id;
	private Game game;

	public Place(Channel<String> controlChannel, int id, Game game) {
		this.controlChannel = controlChannel;
		this.controlChannel.send("");
		this.setId(id);
		this.setGame(game);
	}

	public void getPermission() {
		this.controlChannel.receive();
	}

	public void returnPermission() {
		this.controlChannel.send("");
	}

	/**
	 * debe retornar el siguiente lugar a donde el soldado puede ir, teniendo en
	 * cuenta el lugar previo (puede ser null)
	 * 
	 * @param previous_place
	 * @return Place subclass instance
	 */
	public Place getNextPlace(Place previous_place) {
		if (this.getRoads().size() == 1) {
			return this.getRoads().get(0);
		}
		this.getRoads().remove(previous_place);		
		int index = Utils.getRandom(this.getRoads().size());
		Place nextPlace = this.getRoads().get(index);
		this.getRoads().add(previous_place);
		return nextPlace;
	}

	/**
	 * La primera version de este metodo debe resolver batallas si las hubiera.
	 * agregar al soldado a si mismo, etc Debe setear el valor de la variable
	 * live de soldado en caso de estar muerto. TODO: Que lo haga un thread
	 * aparte.
	 * 
	 * @param soldier
	 */
	protected void receive(Soldier soldier) {
		soldier.setPrevious_place(soldier.getCurrentPlace());
		soldier.setCurrentPlace(this);
		if (!this.accept(soldier)) {
			this.startBattle(soldier);
		} else {
			this.getSoldiers().add(soldier);
		}
	}

	public abstract boolean accept(Soldier soldier);

	/**
	 * Talavez simplemente remueve el soldado de la lista de soldados
	 * 
	 * @param soldier
	 */
	public void remove(Soldier soldier) {
		this.getSoldiers().remove(soldier);
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
		// int x = (int) (Math.random() * soldier.getLevel());
		// int y = (int) (Math.random() * soldierEnemy.getLevel());
		int x = Utils.getRandom(soldier.getLevel());
		int y = Utils.getRandom(soldierEnemy.getLevel());
		if (x > y) {
			return soldier;
		} else {
			return soldierEnemy;
		}
	}

	abstract public void conqueredBy(Soldier soldier);

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
				Soldier dead;
				if (winner.equals(soldierEnemy)) {
					dead = soldier;
				} else {
					dead = soldierEnemy;
				}
				dead.setLive(false);
				if (this.getSoldiers().contains(dead)) {
					this.getSoldiers().remove(dead);
				}
				winner.experienceUp();
				winner.notifyCreateSoldier();				
				if (dead.getLevel() > 1) {
					dead.notifyCreateSoldier();					
				}
				this.getGame().removeSoldier(dead);
			}
			if (soldierEnemy.isLive()) {
				this.conqueredBy(soldierEnemy);
			}
		}
	}

	public ArrayList<Place> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Place> roads) {
		this.roads = roads;
	}

	public Channel<String> getControlChannel() {
		return controlChannel;
	}

	public void setControlChannel(Channel<String> controlChannel) {
		this.controlChannel = controlChannel;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ArrayList<Soldier> getSoldiers() {
		return this.soldiers;
	}

	public void setSoldiers(ArrayList<Soldier> soldiers) {
		this.soldiers = soldiers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
