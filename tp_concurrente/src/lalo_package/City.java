package lalo_package;

import ar.edu.unq.tpi.pconc.Channel;

public class City extends Place {
	private Castle team;

	public City(Channel<String> controlChannel) {
		super(controlChannel);
	}
	
	public void conqueredBy(Soldier soldier){
		this.setTeam(soldier.getTeam());
		soldier.notifyCreateSoldier();
		this.getSoldiers().add(soldier);
	}
	
	@Override
	public void receive(Soldier soldier){
		System.out.println("Llego soldado");
		if (this.getTeam() == soldier.getTeam()) {
			this.getSoldiers().add(soldier);
		} else {
			this.startBattle(soldier);
		}
	}
	
	public Castle getTeam() {
		return team;
	}

	public void setTeam(Castle team) {
		this.team = team;
	}

}
