package lalo_package;

import java.util.ArrayList;

import ar.edu.unq.tpi.pconc.Channel;

public abstract class Place extends Thread {

	private Channel<PlaceProtocol> controlChannel;
	private Channel<String> confirmationChannel;
	private Channel<String> releaseChannel;
	private ArrayList<Soldier> soldiers;
	private Castle team;
	private Integer placeID;
	private Soldier invader;
	private boolean live;

	public Place(Integer id, Channel<PlaceProtocol> controChannel,
			Channel<String> confirmationChannel, Channel<String> releaseChannel) {
		this.setControlChannel(controChannel);
		this.setConfirmationChannel(confirmationChannel);
		this.setPlaceID(id);
		this.setReleaseChannel(releaseChannel);		
	}
	
	public void checkStatus() {
		if (this.getInvader().getTeam().equals(this.getTeam())) {
			this.getSoldiers().add(this.getInvader());
		} else {
			this.startBattle(this.getInvader());
		}
	}
	
	public void startBattle(Soldier soldierEnemy) {
		while (soldierEnemy.isLive() && !this.getSoldiers().isEmpty()) {
			Soldier soldier = this.getSoldiers().get(0);
			Soldier winner = this.fight(soldier, soldierEnemy);
			Soldier dead;
			if (winner.equals(soldierEnemy)) {
				dead = soldier;
			} else {
				dead = soldierEnemy;
			}
			dead.setLive(false);
			if (this.getSoldiers().contains(dead)) {
				this.getSoldiers().remove(dead);
			}
			winner.experienceUp();
			winner.notifyCreateSoldier();
			if (dead.getLevel() > 1) {
				dead.notifyCreateSoldier();
			}
		}
		if (soldierEnemy.isLive()) {
			this.conqueredBy(soldierEnemy);
		}
	}

	public abstract void conqueredBy(Soldier soldierEnemy);

	public Soldier fight(Soldier soldier, Soldier soldierEnemy) {
		int x = (int) (Math.random() * soldier.getLevel());
		int y = (int) (Math.random() * soldierEnemy.getLevel());
		if (x > y) {
			return soldier;
		} else {
			return soldierEnemy;
		}
	}
	
	// Getters and Setters

	public ArrayList<Soldier> getSoldiers() {
		return soldiers;
	}

	public void setSoldiers(ArrayList<Soldier> soldiers) {
		this.soldiers = soldiers;
	}

	public Castle getTeam() {
		return team;
	}

	public void setTeam(Castle team) {
		this.team = team;
	}

	public Integer getPlaceID() {
		return placeID;
	}

	public void setPlaceID(Integer placeID) {
		this.placeID = placeID;
	}

	public Soldier getInvader() {
		return invader;
	}

	public void setInvader(Soldier invader) {
		this.invader = invader;
	}

	public Channel<PlaceProtocol> getControlChannel() {
		return controlChannel;
	}

	public void setControlChannel(Channel<PlaceProtocol> controChannel) {
		this.controlChannel = controChannel;
	}

	public Channel<String> getConfirmationChannel() {
		return confirmationChannel;
	}

	public void setConfirmationChannel(Channel<String> confirmationChannel) {
		this.confirmationChannel = confirmationChannel;
	}

	public Channel<String> getReleaseChannel() {
		return releaseChannel;
	}

	public void setReleaseChannel(Channel<String> releaseChannel) {
		this.releaseChannel = releaseChannel;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

}
