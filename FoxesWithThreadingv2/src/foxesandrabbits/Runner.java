/*package foxesandrabbits;

public class Runner extends Simulator implements Runnable {
	
	private boolean run;
	
	public void start() {
		new Thread(this).start();
	}
	
	public void stop() {
		run=false;
	}
	
	public void run() {
		run=true;
		while(run) {
			super.simulateOneStep();
			try {
				Thread.sleep(100);
			} catch (Exception e) {} 
		}
	}
}*/
