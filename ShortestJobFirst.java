import java.util.Queue;
import java.util.PriorityQueue;

//with no preemption
public class ShortestJobFirst extends Algorithm{ 

	public ShortestJobFirst(Process[] p_){
		super(p_);
		super.processQueue = new PriorityQueue<>(super.processes.length, super.burstComparator);
	}

	public void execute(){
		for(int i = 0; i < super.processes.length; ++i){
			super.processQueue.add(super.processes[i]);
			System.out.println("[time 0ms] " + super.processes[i].pType + " ID " + super.processes[i].processID + " entered ready queue (requires " + super.processes[i].burstTime + "ms CPU time");
			if(super.processes[i].pType == "CPU-bound process"){
				super.cpu_in_queue+=1;
			}
		}

		//while queue has CPU-bound processes run...
		while(cpu_in_queue>0){
			//ADD WAITING QUEUE

			//polls shortest job from the queue
			Process currentProcess = super.processQueue.poll();

			//add burst time to elapsed time
			super.elapsed_time += currentProcess.burstTime;

			//print process status
			System.out.println("[time " + elapsed_time + "] " + currentProcess.pType + " ID " + currentProcess.processID + "CPU burst done (turnaround time " + currentProcess.getTurnaroundTime(elapsed_time) + "ms, total wait time " + currentProcess.getWaitTime(elapsed_time));

			//updates the current process with new times
			currentProcess.refresh(elapsed_time);

			//Identify type of process and implement
			if(currentProcess.pType == "CPU-bound process"){
				if(currentProcess.remBursts==1){
					cpu_in_queue--;
					
					//print process status
					System.out.println("[time " + elapsed_time + "] " + currentProcess.pType + " ID " + currentProcess.processID + "terminated (avg turnaround time " + currentProcess.getAvgTurnaroundTime() + "ms, avg total wait time " + currentProcess.getAvgWaitTime());

				}
				else{
					currentProcess.remBursts--;
					//put back on the queue after block time
				}
			}
			//Interactive Process
			else{
				//put in queue after response time
			}

			

			//context switch!

		}

	}

	
}