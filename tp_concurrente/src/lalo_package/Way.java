package lalo_package;

import ar.edu.unq.tpi.pconc.Channel;

public class Way extends Place {

	public Way(Channel<String> controlChannel) {
		super(controlChannel);
	}

	@Override
	public void conqueredBy(Soldier soldier) {
		this.getSoldiers().add(soldier);
	}

	@Override
	public void receive(Soldier soldier) {
		if(!this.getSoldiers().isEmpty()){
			Castle team = this.getSoldiers().get(0).getTeam();
			if(!team.equals(soldier)){
				this.startBattle(soldier);	
			}
		} else {
			this.getSoldiers().add(soldier);
		}		
	}

}
