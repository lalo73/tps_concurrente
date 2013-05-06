package concu_refactor;

public class Soldier extends Thread {
	private Integer soldier_id;
	private Castle team;
	private int level;
	private int experience;
	private int experienceToNextLevel;
	private Place currentPlace;
	private Place previous_place;
	private boolean live;

	@Override
	public String toString() {
		return "Soldier: " + this.soldier_id + " of team " + "'"
				+ this.getTeam().getGame().getColorSoldier(this.getTeam()) + "'";
	}

	public Soldier(Place currentPlace, Castle team, int soldier_id) {
		this.setCurrentPlace(currentPlace);
		this.setTeam(team);
		this.setLevel(1);
		this.setExperience(0);
		this.setExperienceToNextLevel(2);
		this.setLive(true);
		this.setSoldier_id(soldier_id);
	}

	public void notifyCreateSoldier() {
		if (this.getCurrentPlace().equals(this.getTeam())
				|| this.getPrevious_place().equals(this.getTeam())) {
			this.getTeam().createSoldier();
		} else {
			this.getTeam().getPermission();
			this.getTeam().createSoldier();
			this.getTeam().returnPermission();
		}
	}

	public void run() {
		while (this.isLive()) {
			Place place = this.getCurrentPlace();
			Place next_place = place.getNextPlace(this.getPrevious_place());

			if (!(place instanceof Way)) {
				next_place.getPermission();
				place.getPermission();
			} else {
				place.getPermission();
				next_place.getPermission();
				
			}

			if (this.isLive() && this.getTeam().isLive()) {
				place.remove(this);
				next_place.receive(this);
				place.returnPermission();
				next_place.returnPermission();
			} else {
				place.returnPermission();
				next_place.returnPermission();
				break;
			}
			
			try {
				sleep(3000); //TODO: pass the time by main args
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * funcion Fibonacci para la experiencia cada soldado gana 1 de experiencia
	 * por matar a un soldado enemigo..
	 */
	public void experienceUp() {

		this.setExperience(this.getExperience() + 1);
		if (this.getExperience()== this.getExperienceToNextLevel()) {
			this.levelUp();
			this.setExperienceToNextLevel(Utils.fibonacci(this.level));
			this.setExperience(0); // PORQUE??!!!!
		}

	}
	
	public void levelUp() {
		this.setLevel(this.getLevel() + 1);
	}

	public Castle getTeam() {
		return team;
	}

	public void setTeam(Castle team) {
		this.team = team;
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

	public Place getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(Place currentPlace) {
		this.currentPlace = currentPlace;
	}

	public Place getPrevious_place() {
		return previous_place;
	}

	public void setPrevious_place(Place previous_place) {
		this.previous_place = previous_place;
	}

	public boolean isLive() {
		return this.live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public Integer getSoldier_id() {
		return soldier_id;
	}

	public void setSoldier_id(Integer soldier_id) {
		this.soldier_id = soldier_id;
	}

}
