import java.util.Random;
import java.lang.String;

public class Process {
	public String pType;
	public int arrivalTime;
	public int processID;	// 1 through 12 by default <n = 12>
	public int turnaroundTime;
	public int waitTime;
	public int cpuUtil;
	public int totalBurstTime;
	public int totalTurnaroundTime;
	public int totalWaitTime;
	public int numWaitTimes;
	public int remBursts;
	public int burstTime;   // amount of CPU time to complete its CPU burst
							//     20 - 200 for Interactive
							//     200 - 3000 for CPU

	public Process(int pid) {
		//System.out.println("A new process has been created");
		processID = pid;
		turnaroundTime = 0;
		waitTime = 0;
		cpuUtil = 0;
		pType = "";
		arrivalTime = 0;
		totalTurnaroundTime = 0;
		numWaitTimes = 0;
		totalWaitTime = 0;
	}

	public void refresh(int et) { // Process Timings must vary each time
		//Override
	}

	public int randInt(int min, int max){
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public int getWaitTime(int et){
		int time = ((et - arrivalTime) - burstTime);
		totalWaitTime += time;
		numWaitTimes++;
		return time;		
	}

	public int getAvgWaitTime(){
		return (int) (totalWaitTime/numWaitTimes);
	}

	public int getTurnaroundTime(int et){
		return (et - arrivalTime);
	}

	public int getAvgTurnaroundTime(){
		//Override
		return 0;
	}

	public void calcCpuUtil(){
		float x,y;
		x = totalBurstTime;
		y = totalTurnaroundTime;
		x = x / y;
		cpuUtil = (int)(x*100);
	}
}