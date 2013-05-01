package concubattles;

public class City extends Place {
      private Castle team;
      
      
	
	public Castle getTeam() {
		return team;
	}

	public void setTeam(Castle team) {
		this.team = team;
	}

	// NO SE SI ESTA COPADO QUE CASTILLO HEREDE DE CITY.. YA QUE LA CIUDAD TENDRIA QUE TENER
	// UN EQUIPO OCUPANTE (PARA PODER IR CAMBIANDOLO. EL CUAL SI CASTILLO LO HEREDA SE HEREDA A SI MISMO
	public City(Channel<String> controlChannel) {
		super(controlChannel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receive(Soldier soldier) {
		if ( ! (this.getTeam().equals(soldier.getTeam()))){
			this.startBattle(soldier);
			if (soldier.getLive()){
				this.setTeam(soldier.getTeam());
			}
		}else {
			this.getSoldiers().add(soldier);
		}

	}

	@Override
	public void remove(Soldier soldier) {
		// TODO Auto-generated method stub

	}

}
