import java.util.Queue;
import java.util.PriorityQueue;

//with no preemption
public class ShortestJobFirst extends Algorithm{ 

	public ShortestJobFirst(Process[] p_){
		super(p_);
		super.processQueue = new PriorityQueue<>(super.processes.length, super.burstComparator);
	}

	public static void main(){
		for(int i = 0; i < super.processes.length; ++i){
			processQueue.add(super.processes[i]);
			System.out.println("[time 0ms] " + super.processes[i].pType + " ID " + super.processes[i].processID + " entered ready queue (requires " + super.processes[i].burstTime + "ms CPU time");
			if(super.processes[i].pType == "CPU-bound process"){
				cpu_in_queue+=1;
			}
		}

		//while queue has CPU-bound processes run...
		while(cpu_in_queue>0){
			
			//polls shortest job from the queue
			Process currentProcess = processQueue.poll();

			
			//updates the current process with new times
			currentProcess.refresh();
		}

	}

	
}