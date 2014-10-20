public class IntProcess extends Process { // 80% are Interactive
	public IntProcess(int pid) {
		super(pid);
		//System.out.println("I'm Interactive!");
		super.burstTime = super.randInt(20, 200);
		responseTime = super.randInt(1000, 4500);
	}

	public int responseTime;  // 1000 - 4500 until reenters ready queue

	@Override
	public void refresh() {
		super.burstTime = super.randInt(20, 200);
		responseTime = super.randInt(1000, 4500);
	}
}