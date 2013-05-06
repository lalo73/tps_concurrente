package concu_refactor;

import ar.edu.unq.tpi.pconc.Channel;

public class Castle extends Place {

	private boolean live;
	private boolean winner;
	private int count;

	public Castle(Channel<String> controlChannel, int castleID, Game game) {
		super(controlChannel, castleID, game);
		this.setLive(true);
		this.setWinner(false);
		this.setCount(0);
	}

	public void createSoldier() {
		if (this.isLive()) {
			Soldier newSoldier = new Soldier(this, this, this.getNextSoldierID());
			this.getSoldiers().add(newSoldier);
			newSoldier.start();
			this.getGame().addSoldier(newSoldier);			
		}
	}

	@Override
	public void receive(Soldier soldier) {
		this.getGame().putSoldier(soldier, this);
		super.receive(soldier);
	}
	
	@Override 
	public boolean accept(Soldier soldier){
		return this.equals(soldier.getTeam());
	}

	public void win() {		
		Utils.print(this.getGame().getColorSoldier(this) + " team  win!");
		this.setLive(false);
		this.setWinner(true);
	}

	@Override
	public void conqueredBy(Soldier soldier) {
		Utils.print(this.getGame().getColorSoldier(this) + " team  lose!");			
		this.setLive(false);
		soldier.getTeam().win();
	}
	
	public int getNextSoldierID() {
		int next = this.getCount();
		this.increaseSoldierCoun();
		return next;
	}
	
	public void increaseSoldierCoun(){
		this.count++;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
