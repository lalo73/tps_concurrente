package concu_refactor;

import ar.edu.unq.tpi.pconc.Channel;

public class Way extends Place {

	public Way(Channel<String> controlChannel, int id, Game game) {
		super(controlChannel, id, game);

	}

	@Override
	public void receive(Soldier soldier) {
		this.getGame().putSoldier(soldier,
				this.getNextPlace(soldier.getCurrentPlace()), 0.5);
		super.receive(soldier);
	}

	@Override
	public boolean accept(Soldier soldier) {
		return this.getSoldiers().isEmpty()
				|| this.getSoldiers().get(0).getTeam()
						.equals(soldier.getTeam());
	}

	@Override
	public void conqueredBy(Soldier soldier) {
		this.getSoldiers().add(soldier);
	}

	@Override
	public Place getNextPlace(Place previous_place) {
		if (this.getRoads().get(1).equals(previous_place)) {
			return this.getRoads().get(0);
		} else {
			return this.getRoads().get(1);
		}
	}
}
