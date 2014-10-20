public class CpuProcess extends Process { // 20% are CPU
	public CpuProcess(int pid, int b) {
		super(pid);
		super.pType="CPU-bound process";
		//System.out.println("I'm CPU Bound!");
		numBursts = b;
		super.burstTime = super.randInt(200, 3000);
		blockTime = super.randInt(1200, 3200);
	}

	public int numBursts;    // number of bursts before terminating
							 // by default <b = 8>
	public int blockTime;    // time between bursts 1200 - 3200

	@Override
	public void refresh() {
		super.burstTime = super.randInt(200, 3000);
		blockTime = super.randInt(1200, 3200);
	}
}