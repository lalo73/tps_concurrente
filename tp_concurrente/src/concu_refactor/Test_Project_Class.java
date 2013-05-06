package concu_refactor;

import java.util.concurrent.ExecutionException;

/**
 * This class is only to test the right clone of github project
 * @author leandro
 *
 */
public class Test_Project_Class {
	public static void main(String[] args) {
		double num = 3.5786;
		System.out.println(num*1);
		System.out.println(redondear(num));
	}

	
	public static double redondear(double numero)
	{
	      return Math.rint(numero);
	}
}
