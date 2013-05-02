package concubattles;

import ar.edu.unq.tpi.pconc.Channel;

public class Castle extends Place {

	boolean live;
	boolean winner;
	int count;
	int castleID;

	@Override
	public String toString() {
		return "Castle of team: " + this.castleID;
	}

	public Castle(Channel<String> controlChannel, int castleID) {
		super(controlChannel);
		this.live = true;
		this.winner = false;
		this.count = 0;
		this.castleID = castleID;
	}

	public int getNextSoldierID() {
		int next = this.count;
		this.count++;
		return next;
	}

	public void createSoldier() {
		Soldier x = new Soldier(this, this, this.getNextSoldierID());
		this.getSoldiers().add(x);
		x.start();
		System.out.println(x.toString() + " creado");
	}

	@Override
	public void receive(Soldier soldier) {
		System.out.println(soldier.toString() + " en castle");
		soldier.setPrevious_place(soldier.getMy_place());
		soldier.setMy_place(this);
		if (!(this == soldier.getTeam())) {
			this.startBattle(soldier);
		} else {
			this.getSoldiers().add(soldier);
		}

	}

	public void win() {
		System.out.println(this.toString() + " win!");
		this.live = false;
		this.winner = true;
	}

	@Override
	public void conqueredBy(Soldier soldier) {
		System.out.println(this.toString() + " conquered");
		this.live = false;
		soldier.getTeam().win();
	}

}
