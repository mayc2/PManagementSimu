import java.util.*;

/*
 	higher-priority processes entering the ready queue may 
 	preempt a running process; aging causes lower-priority 
 	processes to increase their priority by 1 every elapsed 
 	1200 milliseconds
 */
class PreemptivePriority extends Algorithm{
/*	
	public Queue<Process> readyQueue;
	public List<Process> waitingTimeList;
	public List<Process> cpuList;
	public int context_switch_count;
	public int numAdded;
	public int t_cs;
	public int tmp1;
*/
//	cpu_in_queue=0;
//	elapsed_time=0;

	public PreemptivePriority(Process[] p_, int m_){
		super(p_,m_);
		readyQueue = new PriorityQueue<>(p_.length,priorityComparator);
	}

	public void execute(){

		//adds all original processes in array to the ready queue at time 0ms
		for(int i = 0; i < processes.length; ++i){
			readyQueue.add(processes[i]);
			System.out.println("[time 0ms] " + processes[i].pType + " ID " + processes[i].processID + " entered ready queue (requires " + processes[i].burstTime + "ms CPU time; priority "+ processes[i].priority +")");
			if(processes[i].pType == "CPU-bound process"){
				cpu_in_queue+=1;
			}
		}

		//end of execute function
	}

}
