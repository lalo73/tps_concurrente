package concubattles;

import ar.edu.unq.tpi.pconc.Channel;

public class Castle extends Place {

	boolean live;
	boolean winner;

	public Castle(Channel<String> controlChannel) {
		super(controlChannel);
		this.live = true;
		this.winner = false;
	}

	public void createSoldier() {		
		Soldier x = new Soldier(this, this);
		this.getSoldiers().add(x);
		System.out.println("Soldado creado");
	}

	@Override
	public void receive(Soldier soldier) {
		soldier.setPrevious_place(soldier.getMy_place());
		soldier.setMy_place(this);
		if (!(this == soldier.getTeam())) {
			this.startBattle(soldier);
		} else {
			this.getSoldiers().add(soldier);
		}

	}

	public void win() {
		this.live = false;
		this.winner = true;
	}

	@Override
	public void conqueredBy(Soldier soldier) {
		this.live = false;
		soldier.getTeam().win();
	}

}
