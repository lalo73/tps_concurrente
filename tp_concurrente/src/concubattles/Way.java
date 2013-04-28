package concubattles;


import java.io.Serializable;
import java.util.ArrayList;
import concubattles.Channel;
import concubattles.Place;
import concubattles.Soldier;

public class Way  extends Place implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/** Discutir si el numero de los canales se hacen con constantes ya que un camino siempre 
	 * conecta 2 ciudades y las ciudades deberan crear los mismos canales respecto a los 
	 * caminos..
	 */
   ArrayList<Soldier> soldiers = new ArrayList<Soldier>();	
   Channel<String> permissionChannel = new Channel<String>(1);
   Channel<Soldier> cityChannelAB = new Channel<Soldier>(2);        //Canal por el que recibira los soldados de las ciudades que 
                                                          // conecta
   
@Override
public void soldierArrive() {
	/**
	 * Con el canal de permisos se arregla el problema de posibles "interlivings"
	 */
    Soldier soldierArrive = this.cityChannelAB.receive();
	if (this.soldiers.isEmpty() || this.soldiers.get(0).team == soldierArrive.team){    // preguntar si hay "OR" de circuito corto
		this.soldiers.add(soldierArrive);
		this.permissionChannel.send("ok");     // envia un permiso para recibir otro soldado
	} else {
		this.startBattle(this.soldiers,soldierArrive);
		this.permissionChannel.send("ok");
		// ENVIO DEL MENSAJE AL CASTILLO DEL SOLDADO GANADOR PARA 
		// CREAR UN NUEVO SOLDADO Y AVISARLE AL CASTILLO DEL PERDEDOR
		// QUE LO BORRE DE LA INTERFAZ.. LO MISMO PARA EL IF DE ARRIBA
	}
	
}
   
	
    
}
