package concubattles;

public class Castle extends Place {
	
	private boolean live;
	private boolean winner;
   
	public Castle(Channel<String> controlChannel) {
		super(controlChannel);
		this.live = true;
		this.winner = false;
		// TODO Auto-generated constructor stub
	}
      //  NO ESTOY SEGURO DE ESTO, PERO EN ESTE CASO
	 // SU EQUIPO (O SEA EL CASTILLO) COINCIDE CON EL PRIMER LUGAR DEL SOLDADITO
	public void createSoldier() {
		Soldier x = new Soldier(this,this);
		this.getSoldiers().add(x);
		System.out.println(x); //syso!!!!
	}
	@Override
	public void receive(Soldier soldier) {
		soldier.setPrevious_place(soldier.getMy_place());
		soldier.setMy_place(this);
		if (!(this == soldier.getTeam())){
			this.startBattle(soldier);
		}else {
			this.getSoldiers().add(soldier);
		}
		
	}
	
	public void win(){
		this.live = false;
		this.winner = true;
	}
	@Override
	public void conqueredBy(Soldier soldier) {
		this.live = false;		
		soldier.getTeam().win();
	}

}
