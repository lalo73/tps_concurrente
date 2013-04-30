package lalo_package;

import ar.edu.unq.tpi.pconc.Channel;

public class Castle extends Place {

	public Castle(Channel<String> controlChannel) {
		super(controlChannel);
	}

	@Override
	public Place getNextPlace(Place previous_place) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SoldierState getSoldierState(Soldier soldier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void receive(Soldier soldier) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Soldier soldier) {
		// TODO Auto-generated method stub

	}

}
