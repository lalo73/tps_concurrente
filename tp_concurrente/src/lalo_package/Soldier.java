package lalo_package;

public class Soldier extends Thread {

	private boolean live;
	private int level;
	private int experience;
	private int experienceToNextLevel;
	private Castle team;
	private Integer soldierID;
	private Place currentPlace;
	private Place previousPlace;

	public Soldier(Integer id, Castle team) {
		this.setSoldierID(id);
		this.setTeam(team);
		this.setCurrentPlace(team);
		this.setLevel(1);
		this.setLive(true);
		this.setExperience(0);
		this.setExperienceToNextLevel(2);
		this.setPreviousPlace(null);
	}

	@Override
	public void run() {
//		while(this.isLive()){
//			Place currentPlace = this.getCurrentPlace();
//			Place nextPlace = currentPlace.getNextPlace();
//			
//		}
	}

	public void notifyCreateSoldier() {
		this.getTeam().getControlChannel().send(PlaceProtocol.createSoldier);
	}

	public int fibonacci(int e) {

		int f1 = 1;
		int f2 = 0;
		int f = 0;

		for (int i = 1; i < e; i++) {

			f = f1 + f2;
			f2 = f1;
			f1 = f;

		}
		return f;

	}

	public void experienceUp() {

		this.experience = this.experience + 1;
		if (this.experience == this.experienceToNextLevel) {
			this.levelUp();
			this.experienceToNextLevel = this.fibonacci(this.level);
			this.experience = 0;
		}

	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getExperienceToNextLevel() {
		return experienceToNextLevel;
	}

	public void setExperienceToNextLevel(int experienceToNextLevel) {
		this.experienceToNextLevel = experienceToNextLevel;
	}

	public void levelUp() {
		this.level = this.level + 1;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public Castle getTeam() {
		return team;
	}

	public void setTeam(Castle team) {
		this.team = team;
	}

	public Integer getSoldierID() {
		return soldierID;
	}

	public void setSoldierID(Integer soldierID) {
		this.soldierID = soldierID;
	}

	public Place getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(Place currentPlace) {
		this.currentPlace = currentPlace;
	}

	public Place getPreviousPlace() {
		return previousPlace;
	}

	public void setPreviousPlace(Place previousPlace) {
		this.previousPlace = previousPlace;
	}

}
