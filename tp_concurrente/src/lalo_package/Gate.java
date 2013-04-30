package lalo_package;

import ar.edu.unq.tpi.pconc.Channel;

public class Gate {
	
	private Channel<String> controlChannel;
	private Place placeOne;
	private Place placeTwo;
	
	public Gate(Place placeOne, Place placeTwo, Channel<String> controlChannel){
		this.placeOne = placeOne;
		this.placeTwo = placeTwo;
		this.controlChannel = controlChannel;
	}
	
	public Place getAnotherPlace(Place excludedPlace){
		if(!this.placeOne.equals(excludedPlace)){
			return this.placeOne; 
		} else {
			return this.placeTwo;
		}
	}
	
	public void takeAwayFrom(Soldier soldier, Place place){
		Place anotherPlace = this.getAnotherPlace(place);
		place.remove(soldier);
		anotherPlace.receive(soldier);
	}
	
	public void getPermission(){
		this.controlChannel.receive();
		this.placeOne.getPermission();
		this.placeTwo.getPermission();		
	}
	
	public void returnPermission(){
		this.controlChannel.send("");
		this.placeOne.returnPermission();
		this.placeTwo.returnPermission();
	}

}
