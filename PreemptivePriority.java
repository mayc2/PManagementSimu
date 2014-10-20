import java.util.Queue;
import java.util.PriorityQueue;

/*
 	with random priority levels 0-4 assigned to processes at 
 	the onset (low numbers indicate high priority); processes 
 	with the same priority are processed via the FCFS algorithm; 
 	higher-priority processes entering the ready queue may 
 	preempt a running process; aging causes lower-priority 
 	processes to increase their priority by 1 every elapsed 
 	1200 milliseconds
 */
class PreemptivePriority extends Algorithm{
	public PreemptivePriority(Process[] p_, int m_){
		super(p_,m_);
	}
}
