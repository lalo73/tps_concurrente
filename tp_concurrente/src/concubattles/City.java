package concubattles;

public class City extends Place {

	
	// NO SE SI ESTA COPADO QUE CASTILLO HEREDE DE CITY.. YA QUE LA CIUDAD TENDRIA QUE TENER
	// UN EQUIPO OCUPANTE (PARA PODER IR CAMBIANDOLO. EL CUAL SI CASTILLO LO HEREDA SE HEREDA A SI MISMO
	public City(Channel<String> controlChannel) {
		super(controlChannel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receive(Soldier soldier) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Soldier soldier) {
		// TODO Auto-generated method stub

	}

}
