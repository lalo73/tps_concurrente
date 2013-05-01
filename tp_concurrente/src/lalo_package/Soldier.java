package lalo_package;

public class Soldier extends Thread{

	private Castle team;
	private int level;
	private int experience;
	private int experienceToNextLevel;
	private Place currentPlace;
	private Place previousPlace;
	private boolean live;

	public Soldier(Castle team) {
		this.currentPlace = team;
		this.team = team;
		this.level = 1;
		this.experience = 0;
		this.experienceToNextLevel = 1;
	}

	public void notifyCreateSoldier() {
		this.getTeam().getPermission();
		this.getTeam().createSoldier();
		this.getTeam().returnPermission();
	}

	public Castle getTeam() {
		return this.team;
	}

	public Place getCurrentPlace() {
		return this.currentPlace;
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

	public void levelUp() {
		this.level = this.level + 1;
	}

	public boolean isLive() {
		return this.live;
	}
	
	public boolean teamGaming(){
		this.getTeam().getPermission();
		boolean gaming = this.getTeam().isLive();
		this.getTeam().returnPermission();
		return gaming;
	}

	public void run() {
		while (this.isLive()) {
			Place place = this.getCurrentPlace();
			Gate gate = place.getAGate(this.getPreviousPlace());
			gate.getPermission();
			if (this.isLive() && this.teamGaming()) {
				gate.takeAwayFrom(this, place);
				gate.returnPermission();
			} else {
				gate.returnPermission();
				break;
			}
		}
	}

	public Place getPreviousPlace() {
		return this.previousPlace;
	}

	/**
	 * funcion Fibonacci para la experiencia cada soldado gana 1 de experiencia
	 * por matar a un soldado enemigo..
	 */
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

	public void setTeam(Castle team) {
		this.team = team;
	}

	public void setCurrentPlace(Place currentPlace) {
		this.currentPlace = currentPlace;
	}

	public void setPreviousPlace(Place previousPlace) {
		this.previousPlace = previousPlace;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

}
