package concubattles;

import java.util.concurrent.ExecutionException;

/**
 * This class is only to test the right clone of github project
 * @author leandro
 *
 */
public class Test_Project_Class {
	public static void main(String[] args) {
		System.out.println("Hello Fede again!");
		
		if (foo() || bar()){}
	}
	static boolean foo(){
		return true;
	}
	static boolean bar(){
		throw new RuntimeException("none");
	}
	
}
