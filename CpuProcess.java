public class CpuProcess extends Process { // 20% are CPU

	public CpuProcess(int pid, int b) {
		super(pid);
		super.pType="CPU-bound process";
		//System.out.println("I'm CPU Bound!");
		super.numBursts = b;
		super.remBursts = b;
		super.burstTime = super.randInt(200, 3000);
		super.blockTime = super.randInt(1200, 3200);
	}

	@Override
	public void refresh(int et) {
		super.arrivalTime = et;
		super.burstTime = super.randInt(200, 3000);
		super.blockTime = super.randInt(1200, 3200);
	}

	@Override
	public int getAvgTurnaroundTime(){
		return (int) (super.totalTurnaroundTime / super.numBursts);	
	}
}