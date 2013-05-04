package lalo_package;

import java.util.ArrayList;

import ar.edu.unq.tpi.pconc.Channel;

public class City extends Place{
	
	public City(Integer id, Channel<PlaceProtocol> controChannel,
			Channel<String> confirmationChannel, Channel<String> releaseChannel) {
		super(id, controChannel, confirmationChannel, releaseChannel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while (this.isLive()) {
			PlaceProtocol command = this.getControlChannel().receive();
			switch (command) {
			case receiveSoldier:
				this.getConfirmationChannel().send("ok");
				this.getReleaseChannel().receive();
				this.checkStatus();
				break;
			default:
				break;
			}

		}
	}
	
	@Override
	public void conqueredBy(Soldier soldierEnemy) {
		this.setTeam(soldierEnemy.getTeam());
		this.getSoldiers().add(soldierEnemy);			
	}
}
