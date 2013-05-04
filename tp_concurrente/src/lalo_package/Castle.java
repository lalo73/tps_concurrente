package lalo_package;

import java.util.ArrayList;

import ar.edu.unq.tpi.pconc.Channel;

public class Castle extends Place {

	private Integer soldiersIDCount;
	private Castle team;
	private boolean gaming;
	private boolean win;

	public Castle(Integer id, Channel<PlaceProtocol> controChannel,
			Channel<String> confirmationChannel, Channel<String> releaseChannel) {
		super(id, controChannel, confirmationChannel, releaseChannel);
		this.setTeam(this);
		this.setWin(false);
	}

	@Override
	public void run() {
		while (this.isLive() && this.getGaming()) {
			PlaceProtocol command = this.getControlChannel().receive();
			switch (command) {
			case createSoldier:
				this.createSoldier();
				break;
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

	public void createSoldier() {
		Soldier newSoldier = new Soldier(this.getNewSoldierID(), this);
		this.getSoldiers().add(newSoldier);
		newSoldier.start();
	}

	protected Integer getNewSoldierID() {
		Integer id = this.getSoldiersIDCount();
		this.setSoldiersIdCount(this.getSoldiersIDCount() + 1);
		return id;
	}

	public void conqueredBy(Soldier soldier) {
		this.setLive(false);
		this.setGaming(false);
	}

	// Getters and Setters

	public void setGaming(boolean gaming) {
		this.gaming = gaming;
	}

	public boolean getGaming() {
		return this.gaming;
	}

	public Integer getSoldiersIDCount() {
		return this.soldiersIDCount;
	}

	public void setSoldiersIdCount(Integer count) {
		this.soldiersIDCount = count;
	}

	public Castle getTeam() {
		return team;
	}

	public void setTeam(Castle team) {
		this.team = team;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

}
