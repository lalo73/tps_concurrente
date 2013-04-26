package concubattles;

import java.util.ArrayList;

import concubattles.Battle;
import concubattles.Soldier;


public class City extends Battle {
         ArrayList<Soldier>soldiers = new ArrayList<Soldier>();
         ArrayList<Way>roads = new ArrayList<Way>();
        
         /**
          * se debe agregar como se crearia un soldado cuando se libere una batalla y 
          * haya un ganador..
          * Lo mismo para castillo..
          */
   public void soldierArrive(Soldier soldier){
	   if (this.soldiers.isEmpty()){
		   this.soldiers.add(soldier);
	   }else {
		   this.startBattle(this.soldiers, soldier);
	   }
   }
   
   
}
