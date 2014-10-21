// Paul Chiappetta and Chris May

public class IntProcess extends Process { // 80% are Interactive
	public IntProcess(int pid) {
		super(pid);
		super.pType="Interactive process";
		super.burstTime = super.randInt(20, 200);
		super.responseTime = super.randInt(4000, 4500);
		remBurstTime=burstTime;
	}

	@Override
	public void refresh(int et) {
		totalTurnaroundTime+=turnaroundTime;
		turnaroundTime=0;
		totalWaitTime+=waitTime;
		waitTime=0;
		numWaitTimes+=1;
		super.arrivalTime = et;
		super.burstTime = super.randInt(20, 200);
		remBurstTime=burstTime;
		super.responseTime = super.randInt(1000, 4500);
	}

	@Override
	public int getAvgTurnaroundTime(){
		return (int) (totalTurnaroundTime / numWaitTimes);		
	}

	@Override
	public int getAvgWaitTime(){
		return (int) ((totalTurnaroundTime - totalBurstTime)/numWaitTimes);
	}
}