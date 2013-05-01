package concubattles;

public class Castle extends City {
   
	public Castle(Channel<String> controlChannel) {
		super(controlChannel);
		// TODO Auto-generated constructor stub
	}
      //  NO ESTOY SEGURO DE ESTO, PERO EN ESTE CASO
	 // SU EQUIPO (O SEA EL CASTILLO) COINCIDE CON EL PRIMER LUGAR DEL SOLDADITO
	public void createSoldier() {
		Soldier x = new Soldier(this,this);
		this.getSoldiers().add(x);
		System.out.println(x); //syso!!!!
		
		
	}

}
