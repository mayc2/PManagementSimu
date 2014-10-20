public class CpuProcess extends Process { // 20% are CPU

	public CpuProcess(int pid, int b) {
		super(pid);
		super.pType="CPU-bound process";
		//System.out.println("I'm CPU Bound!");
		numBursts = b;
		remBursts = b;
		super.burstTime = super.randInt(200, 3000);
		blockTime = super.randInt(1200, 3200);
		super.totalBurstTime=super.burstTime;
	}

	public int numBursts;    // number of bursts before terminating
							 // by default <b = 8>
	public int blockTime;    // time between bursts 1200 - 3200

	@Override
	public void refresh(int et) {
		super.totalTurnaroundTime = et-super.arrivalTime;
		super.arrivalTime = et;
		super.burstTime = super.randInt(200, 3000);
		super.totalBurstTime += super.burstTime;
		blockTime = super.randInt(1200, 3200);
	}

	@Override
	public int getAvgTurnaroundTime(){
		return (int) (totalTurnaroundTime / numBursts);	
	}
}