import java.util.Random;
import java.lang.String;

public class Process {
	// Base Variables
	public int processID;	// 1 through 12 by default <n = 12>
	public String pType;
	public int burstTime;   // amount of CPU time to complete its CPU burst
							//     20 - 200 for Interactive
							//     200 - 3000 for CPU
	// CPU Only
	public int blockTime;   // time between bursts 1200 - 3200
	public int numBursts;   // number of bursts before terminating
							// by default <b = 8>
	public int remBursts;   // remaining number of bursts
	// Interactive Only
	public int responseTime;  // 1000 - 4500 until reenters ready queue
	// Statistics
	public int arrivalTime;
	public int endTime;
	public int turnaroundTime;
	public int waitTime;
	public int cpuUtil;
	public int totalBurstTime;
	public int totalTurnaroundTime;
	public int totalWaitTime;
	public int numWaitTimes;
	public int remBurstTime;

	public Process(int pid) {
		processID = pid;
		turnaroundTime = 0;
		waitTime = 0;
		cpuUtil = 0;
		pType = "";
		arrivalTime = -1;
		endTime=-1;
		totalTurnaroundTime = 0;
		totalBurstTime=0;
		numWaitTimes = 1;
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

	public int getAvgWaitTime(){
		return (int) (totalWaitTime/numWaitTimes);
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