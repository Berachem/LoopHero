package fr.iut.zen.game;

public class TimeData {
	private static long tick = System.currentTimeMillis();
	private  long elapsedTotal = 0; 	// elapsed time since creation
	private long elapsedBob = 0; 	// elapsed time since last Bob reset()
	private boolean stopped;
	public final static double DAY_MILLISECONDS = 24_000;
	public static int BOB_DELAY = 850;
	public static int previousBOB_DELAY = BOB_DELAY;
	private static int daysCount = 0;

	private void tickTock() {
		long tock = System.currentTimeMillis();
		elapsedTotal += tock - tick;
		elapsedBob += tock - tick;
		tick = tock;
		
	}

	public double timeFraction() {
		if (!stopped) {
			tickTock();
		}
		return (elapsedTotal % DAY_MILLISECONDS) / (double) DAY_MILLISECONDS;
	}
	
	public static void addTime(long value) {
		tick -= value;
	}

	public long elapsedBob() {
		if (stopped) {
			return 0;
		}
		tickTock();
		
		return elapsedBob;
	}

	public void resetElapsedBob() {
		elapsedBob = 0;
		
	}

	public boolean stopped() {
		return stopped;
	}

	public void stop() {
		if(stopped) {
			return;
		}
		tickTock();
		BOB_DELAY = 850;
		stopped = true;
	}

	public void start() {
		stopped = false;
		BOB_DELAY = previousBOB_DELAY;
		tick = System.currentTimeMillis();
	}


	public static void setTick(long tick) {
		TimeData.tick = tick;
	}
	
	public void resetElapsedTotal() {
		if (elapsedTotal >= DAY_MILLISECONDS) {
			daysCount +=1;
			elapsedTotal = 0;
		}
	}
	
	public static void decreaseSpeed() {
		int speedVariation = 200;
		
		BOB_DELAY+=speedVariation;
		previousBOB_DELAY = BOB_DELAY;
	}
	
	public static void increaseSpeed() {
		int speedVariation = 200;
		
		if (BOB_DELAY-speedVariation<0) {
			BOB_DELAY=0;
		}else {
			BOB_DELAY-=speedVariation;
		}
		previousBOB_DELAY = BOB_DELAY;
	}
	
	
	public static int getDay() {
		return daysCount;
	}

	
	
	
	
	
}
