package lalo_package;

public class Soldier {	

	public Castle team;
	int level;
	int experience;
	int experienceToNextLevel;
	Place currentPlace;
	Place previousPlace;
	boolean live;

	public Soldier(Place myPlace, Castle team) {
		this.currentPlace = myPlace;
		this.team = team;
		this.level = 1;
		this.experience = 0;
		this.experienceToNextLevel = 1;
	}
	
	public Place getCurrentPlace(){
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

	public void run() {
		while (this.live) {
			Place place = this.getCurrentPlace();			
			Gate gate = place.getAGate();
			gate.getPermission();
			SoldierState state = place.getSoldierState(this);
			switch (state) {
			case dead:
				this.live = false;
				gate.returnPermission();
				break;
			case live:				
				gate.takeAwayFrom(this, place);
				gate.returnPermission();
				break;
			}
		}
	}
	
	public Place getPreviousPlace(){
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
}
