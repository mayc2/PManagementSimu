import java.util.Queue;
import java.util.PriorityQueue;

//with preemption via configurable time slice TSLICE=100MS
class RoundRobin extends Algorithm{

	public int t_slice;

	public RoundRobin(Process[] p_, int m_, int t_sl){
		super(p_,m_);
		super.readyQueue = new PriorityQueue<>(super.processes.length, super.burstComparator);
		t_slice = t_sl;
	}
}