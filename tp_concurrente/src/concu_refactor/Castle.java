package concu_refactor;

import ar.edu.unq.tpi.pconc.Channel;

public class Castle extends Place {

	boolean live;
	boolean winner;
	int count;

	public Castle(Channel<String> controlChannel, int castleID, Game game) {
		super(controlChannel, castleID, game);
		this.live = true;
		this.winner = false;
		this.count = 0;
	}

	public int getNextSoldierID() {
		int next = this.count;
		this.count++;
		return next;
	}

	public void createSoldier() {
		if (this.live) {
			Soldier x = new Soldier(this, this, this.getNextSoldierID());
			this.getSoldiers().add(x);
			x.start();
			this.game.addSoldier(x);
			System.out.println(x.toString() + " Created");
		}
	}

	@Override
	public void receive(Soldier soldier) {
		this.game.putSoldier(soldier, this);
		super.receive(soldier);
	}
	
	@Override 
	public boolean accept(Soldier soldier){
		return this == soldier.getTeam();
	}

	public void win() {		
		System.out.println(this.getGame().getColorSoldier(this) + " win!");
		this.live = false;
		this.winner = true;
	}

	@Override
	public void conqueredBy(Soldier soldier) {
		System.out.println(this.getGame().getColorSoldier(this) + " lose!");
		this.getSoldiers().add(soldier);		
		this.live = false;
		soldier.getTeam().win();
	}

}
