package concubattles;

public class Game {
	
	private int count;
	
	public int getNextIn(){
		int next = this.count;
		this.count++;
		return next;
	}
	
	public void connect(Place place1, Place place2){
		Way way = new Way(new Channel<String>(this.getNextIn()));
		place1.getRoads().add(way);
		place2.getRoads().add(way);
		way.getRoads().add(place1);
		way.getRoads().add(place2);
	}
	
	public static void main(String[] args) {
		
		
	}

}
