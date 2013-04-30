package concubattles;

import java.util.*;
import concubattles.Channel;
import concubattles.Place;
import concubattles.Soldier;
import concubattles.Way;


public class Castle extends City {
  int team;
  ArrayList<Soldier> soldiers = new ArrayList<Soldier>();
  ArrayList<Way> roads = new ArrayList<>();  
  Channel<String> recieveNotification = new Channel(11);  // CANALES PROVISORIOS
  // SE DEBEN AGREGAR CANALES POR LOS QUE VAN A RECIBIRSE LOS SOLDADOS..
  
  
  /** Creacion de soldados del equipo del castillo
   */
  public void createSoldier(){
	  Soldier x = new Soldier(this.team);
	  soldiers.add(x);
	}

/**
 * Si el soldado enemigo llega y no hay ningun soldado para defender el castillo o
 * les gano a todos los soldados, este se agrega a la lista de soldados ocupantes del castillo
 * se le pregunta si su equipo es distinto al equipo que tenia originalmente el castillo
 * de ser asi se termina el juego...
 */
@Override
public void soldierArrive() {
	Soldier soldierEnemy = recieveSoldier.receive(); // CREACION DEL CANAL DONDE SE RECIVE EL SOLDADO
	this.startBattle(this.soldiers, soldierEnemy);
	if (! (this.soldiers.get(0).team == this.team)){
		// AQUI SE DEBERIA PONER EL END GAME!!
	}
	
}
 
}
