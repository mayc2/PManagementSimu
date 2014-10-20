public class CpuProcess extends Process { // 20% are CPU

	public CpuProcess(int pid, int b) {
		super(pid);
		super.pType="CPU-bound process";
		super.burstTime = super.randInt(200, 3000);
		super.blockTime = super.randInt(1200, 3200);
		remBurstTime=burstTime;
		super.numBursts = b;
		super.remBursts = b;
	}

	@Override
	public void refresh(int et) {
		super.arrivalTime = et;
		totalTurnaroundTime+=turnaroundTime;
		turnaroundTime=0;
		totalWaitTime+=waitTime;
		waitTime=0;
		super.burstTime = super.randInt(200, 3000);
		super.blockTime = super.randInt(1200, 3200);
		remBurstTime=burstTime;
	}

	@Override
	public int getAvgTurnaroundTime(){
		return (int) (totalTurnaroundTime / super.numBursts);	
	}
}