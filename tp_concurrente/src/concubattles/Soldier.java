package concubattles;

import java.io.Serializable;

public class Soldier implements Serializable {

	private static final long serialVersionUID = 1L;

	int team;
	int level;
	int experience;
	int experienceToNextLevel;
	Place my_place;
	Place previous_place;
	boolean live;

	public Soldier(Place my_place, int team) {
		this.my_place = my_place;
		this.team = team;
		this.level = 1;
		this.experience = 0;
		this.experienceToNextLevel = 1;
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

	public Place getMyPlace() {
		return this.my_place;
	}

	public boolean isLive() {
		return this.live;
	}

	public void run() {
		while (this.live) {
			Place place = this.getMyPlace();
			Place next_place = place.getNextPlace(this.previous_place);
			if (place instanceof City) {
				place.getPermission();
				next_place.getPermission();
			} else {
				next_place.getPermission();
				place.getPermission();
			}
			if (this.isLive()) {
				next_place.send(this);
				place.remove(this);
				next_place.returnPermission();
				place.returnPermission();
				break;
			} else {
				place.returnPermission();
				break;
			}

		}
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

}
