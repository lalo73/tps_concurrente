package lalo_package;

import ar.edu.unq.tpi.pconc.Channel;

public class Castle extends Place {
	private boolean live;

	public Castle(Channel<String> controlChannel) {
		super(controlChannel);
		this.live = true;
	}

	public void conquered() {
		this.live = false;
		System.out.println("Castle conquered " + this.toString());
	}

	public void createSoldier() {
		Soldier soldier = new Soldier(this);
		this.getSoldiers().add(soldier);
		soldier.start();
	}

	public boolean isLive() {
		return this.live;
	}

	@Override
	public void conqueredBy(Soldier soldier) {
		this.conquered();
	}

	@Override
	public void receive(Soldier soldier){
		if (this.equals(soldier.getTeam())) {
			this.getSoldiers().add(soldier);
		} else {
			this.startBattle(soldier);
		}
	}

}
