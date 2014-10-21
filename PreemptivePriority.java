import java.util.*;

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
	public List<Process> p1List;
	public List<Process> p2List;
	public List<Process> p3List;
	public List<Process> p4List;
	public int priority;

	public PreemptivePriority(Process[] p_, int m_){
		super(p_,m_);
		p1List = new LinkedList<Process>();
		p2List = new LinkedList<Process>();
		p3List = new LinkedList<Process>();
		p4List = new LinkedList<Process>();
		priority=randInt(1,4);
	}

	public void execute(){
		//adds all original processes in array to the ready queue at time 0ms
		for(int i = 0; i < super.processes.length; ++i){
			
			//add process to proper list
			if(priority==1){
				p1List.add(processes[i]);
			}
			else if(priority == 2){
				p2List.add(processes[i]);
			}
			else if(priority == 3){
				p3List.add(processes[i]);
			}
			else if(priority == 4){
				p4List.add(processes[i]);
			}
			else{

			}

			System.out.println("[time 0ms] " + super.processes[i].pType + " ID " + super.processes[i].processID + " entered ready queue (requires " + super.processes[i].burstTime + "ms CPU time; priority "+ priority +")");
			if(super.processes[i].pType == "CPU-bound process"){
				super.cpu_in_queue+=1;
			}
		}
	}

}
