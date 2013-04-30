package lalo_package;

import java.util.ArrayList;
import java.util.HashMap;

import ar.edu.unq.tpi.pconc.Channel;

public abstract class Place {
	private Channel<String> controlChannel;
	private HashMap<Place, Gate> connections;
	private Castle team;

	public void createConnectionTo(Place place, Channel<String> controlChannel) {
		Gate gate = new Gate(this, place, controlChannel);
		this.saveConnection(place, gate);
		place.saveConnection(this, gate);
	}

	public void saveConnection(Place place, Gate gate) {
		this.connections.put(place, gate);
	}
	
	public Gate getAGate(){
		/**
		 * Debe retormnar alguna instancia de Gate al azar.
		 */
	}

	public Place(Channel<String> controlChannel) {
		this.controlChannel = controlChannel;
		this.connections = new HashMap<Place, Gate>();
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
	public abstract Place getNextPlace(Place excludedPlace);

	/**
	 * Debe retornar el estado del soldado, talvez lo mataron pero aun no se
	 * entero :P
	 * 
	 * @param soldier
	 * @return
	 */
	public abstract SoldierState getSoldierState(Soldier soldier);

	/**
	 * La primera version de este metodo debe resolver batallas si las hubiera.
	 * agregar al soldado a si mismo, etc TODO: Que lo haga un thread aparte.
	 * 
	 * @param soldier
	 */
	public abstract void receive(Soldier soldier);

	/**
	 * Talavez simplemente remueve el soldado de la lista de soldados
	 * 
	 * @param soldier
	 */
	public abstract void remove(Soldier soldier);

	/**
	 * Toma 2 soldados les hace un random a cada uno de acuerdo al nivel y
	 * retorna el que tiene el numero mas grande
	 * 
	 * @param soldier
	 * @param soldierEnemy
	 * @return soldado ganador
	 */
	public Soldier fight(Soldier soldier, Soldier soldierEnemy) {
		int x = (int) (Math.random() * soldier.level);
		int y = (int) (Math.random() * soldierEnemy.level);
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
	public void startBattle(ArrayList<Soldier> soldiers, Soldier soldierEnemy) {
		Soldier x = soldierEnemy;
		if (soldiers.isEmpty()) {
			soldiers.add(x);
		} else {
			for (Soldier s : soldiers) {
				if (s.equals(this.fight(s, x))) {
					s.experienceUp();
					break;
				} else {
					x.experienceUp();
					soldiers.remove(s);
				}
			}
		}
	}

}
