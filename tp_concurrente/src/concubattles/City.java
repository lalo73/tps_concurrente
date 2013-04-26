package concubattles;

import java.util.ArrayList;
import concubattles.Channel;
import concubattles.Battle;
import concubattles.Soldier;


public class City extends Battle {
         ArrayList<Soldier>soldiers = new ArrayList<Soldier>();
         ArrayList<Way>roads = new ArrayList<Way>();
        
         /**
          * se debe agregar como se crearia un soldado cuando se libere una batalla y 
          * haya un ganador..
          * Lo mismo para castillo..
          * Se debe ver la forma de saber cuantos canales crear dependiendo de la cantidad de 
          * caminos que tenga
          */
   public void soldierArrive(){
	   Soldier soldier = recieveSoldier.recieve(); // CREACION DEL CANAL DONDE SE RECIBE EL SOLDADO
	   if (this.soldiers.isEmpty()){
		   this.soldiers.add(soldier);
	   }else {
		   this.startBattle(this.soldiers, soldier);
	   }
   }
   
   
}
