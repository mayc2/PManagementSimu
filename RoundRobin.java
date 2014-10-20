import java.util.Queue;
import java.util.PriorityQueue;

//with preemption via configurable time slice TSLICE=100MS
class RoundRobin extends Algorithm{
	public RoundRobin(Process[] p_){
		super(p_);
	}
}