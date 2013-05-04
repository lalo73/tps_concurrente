package concu_refactor;

public class Utils {

	public static int fibonacci(int e) {
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
	
	public static int getRandom(int max){
		return (int) (Math.random() * (max));
	}
}
