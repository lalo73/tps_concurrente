package concubattles;

import java.util.ArrayList;

import concubattles.Soldier;

public abstract class Battle {

	/** 
	 *  Toma 2 soldados les hace un random a cada uno de acuerdo al nivel y retorna el que tiene el numero 
	 *  mas grande
	 * @param soldier
	 * @param soldierEnemy
	 * @return soldado ganador
	 */
	public Soldier fight (Soldier soldier, Soldier soldierEnemy){
		int x = (int) (Math.random() * soldier.level);
		int y = (int) (Math.random() * soldierEnemy.level);
		 if (x < y){
			 return soldier;
		 }else {
			 return soldierEnemy;
		 }
	}
	 /**
	   * Llega un soldado enemigo.. Si este le gana a todos los soldados enemigos, se agrega el soldado 
	   * a la lista de soldados del castillo o ciudad ya que posee un nuevo ocupante
	   * sino el soldado pierde y se interrumpe la iteracion. (ver este tema de la batalla)
	   * se debe ver el tema de la concurrencia.
	   */
	 public void startBattle (ArrayList<Soldier> soldiers ,Soldier soldierEnemy){
		  Soldier x = soldierEnemy;
		    if (soldiers.isEmpty()){
			   soldiers.add(x);
		  }else {
		  for (Soldier s : soldiers) {
			  if (s.equals(this.fight(s,x))){
				  s.experienceUp();
				  break;
			  }else 
				  soldiers.remove(0);
		  }
		 }
	  }
	 public abstract void soldierArrive();
	
}
