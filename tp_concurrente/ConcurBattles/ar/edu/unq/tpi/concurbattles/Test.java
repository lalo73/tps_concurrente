package ar.edu.unq.tpi.concurbattles;

import ar.edu.unq.tpi.pconc.Channel;
import static ar.edu.unq.tpi.pconc.Utils.*;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		final Channel<String> c1 = new Channel<String>(1);
		
		final Channel<String> c2 = new Channel<String>(2);
		new Thread(new Runnable() { public void run() { while (true) System.out.println(c2.receive()); } }).start();
		
//		while (true) {
//			c1.send(readLine(System.in));
//		}
		
		while (true) {
			String line = readLine(System.in);
			if (!line.contains("*")) {
				c1.send(line);
			} else {
				for (float i = 0.1f; i <= 1.0f; i += 0.1) {
					c1.send(line.replaceAll("[*]", "") + " " + i);
					Thread.sleep(25);
				}
			}
		}
		
	}

}
