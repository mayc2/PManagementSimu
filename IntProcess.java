public class IntProcess extends Process { // 80% are Interactive
	public IntProcess(int pid) {
		super(pid);
		super.pType="Interactive process";
		//System.out.println("I'm Interactive!");
		super.burstTime = super.randInt(20, 200);
		responseTime = super.randInt(1000, 4500);
		super.totalBurstTime=super.burstTime;
	}

	public int responseTime;  // 1000 - 4500 until reenters ready queue

	@Override
	public void refresh(int et) {
		super.totalTurnaroundTime = et-super.arrivalTime;
		super.arrivalTime = et;
		super.burstTime = super.randInt(20, 200);
		super.totalBurstTime += super.burstTime;
		responseTime = super.randInt(1000, 4500);
	}

	@Override
	public int getAvgTurnaroundTime(){
		return (int) (totalTurnaroundTime / numWaitTimes);		
	}
}