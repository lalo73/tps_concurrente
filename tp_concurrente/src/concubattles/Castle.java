package concubattles;

import java.util.*;

import concubattles.Battle;
import concubattles.Soldier;


public class Castle extends Battle {
  int team;
  ArrayList<Soldier> soldiers = new ArrayList<Soldier>();
  ArrayList<Way> roads = new ArrayList<Way>();
  
  
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
public void soldierArrive(Soldier soldierEnemy) {
	this.startBattle(this.soldiers, soldierEnemy);
	if (! (this.soldiers.get(0).team == this.team)){
		// AQUI SE DEBERIA PONER EL END GAME!!
	}
	
}
 
}
