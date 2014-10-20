public class IntProcess extends Process { // 80% are Interactive
	public IntProcess(int pid) {
		super(pid);
		super.pType="Interactive process";
		//System.out.println("I'm Interactive!");
		super.burstTime = super.randInt(20, 200);
		super.responseTime = super.randInt(1000, 4500);
		remBurstTime=burstTime;
	}

	@Override
	public void refresh(int et) {
		super.arrivalTime = et;
		super.burstTime = super.randInt(20, 200);
		remBurstTime=burstTime;
		super.responseTime = super.randInt(1000, 4500);
	}

	@Override
	public int getAvgTurnaroundTime(){
		return (int) (super.totalTurnaroundTime / super.numWaitTimes);		
	}
}