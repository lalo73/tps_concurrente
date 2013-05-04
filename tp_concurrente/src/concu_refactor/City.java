package concu_refactor;

import ar.edu.unq.tpi.pconc.Channel;

public class City extends Place {
	private Castle team;

	public Castle getTeam() {
		return team;
	}

	public City(Channel<String> controlChannel, int cityID, Game game) {
		super(controlChannel, cityID, game);
	}

	@Override
	public void receive(Soldier soldier) {
		this.game.putSoldier(soldier, this);
		super.receive(soldier);
	}

	@Override
	public boolean accept(Soldier soldier) {
		return this.getTeam() == soldier.getTeam();
	}

	@Override
	public void conqueredBy(Soldier soldier) {
		soldier.notifyCreateSoldier();
		this.getSoldiers().add(soldier);		
		this.setTeam(soldier.getTeam());
	}

	public void setTeam(Castle team) {
		this.team = team;
	}
}
