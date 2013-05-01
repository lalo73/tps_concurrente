package concubattles;

public class Way extends Place {

	public Way(Channel<String> controlChannel) {
		super(controlChannel);
		
	}

	// SI EL SOLDADO VINO DE LA CIUDAD QUE OCUPA EL PRIMER LUGAR EN LA LISTA DE LUGARES DEL CAMINO
	// ENTONCES EL PROXIMO LUGAR ES LA CIUDAD QUE OCUPA EL SEGUNDA LUGAR EN LA LISTA
	// O VICEVERSA
	// EL CAMINO SOLO TIENE 2 ELEMENTOS EN SU LISTA DE "ROADS"
	@Override
	public Place getNextPlace(Place previous_place) {
		if (this.getRoads().get(0) == previous_place){
			return this.getRoads().get(1);
		} else {
			return this.getRoads().get(0);
		}
	}

	@Override
	public void receive(Soldier soldier) {
		this.startBattle(soldier);		
	}

	@Override
	public void conqueredBy(Soldier soldier) {
		this.getSoldiers().add(soldier);
	}
}
