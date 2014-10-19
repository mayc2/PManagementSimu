import java.util.Random;

class Process {
	public Process(int pid) {
		System.out.println("A new process has been created");
		processID = pid;
	}

	public int processID;	// 1 through 12 by default <n = 12>
	public int burstTime;   // amount of CPU time to complete its CPU burst
							//     20 - 200 for Interactive
							//     200 - 3000 for CPU

	public void refresh() { // Process Timings must vary each time
		//Override
	}

	public static int randInt(int min, int max){
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}