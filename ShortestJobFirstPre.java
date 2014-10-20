import java.util.Queue;
import java.util.PriorityQueue;

//with preemption
class ShortestJobFirstPre extends Algorithm{
	public ShortestJobFirstPre(Process[] p_, int m_){
		super(p_,m_);
	}
/*
	public void execute(){
		for(int i = 0; i < super.processes.length; ++i){
			super.readyQueue.add(super.processes[i]);
			System.out.println("[time 0ms] " + super.processes[i].pType + " ID " + super.processes[i].processID + " entered ready queue (requires " + super.processes[i].burstTime + "ms CPU time");
			if(super.processes[i].pType == "CPU-bound process"){
				super.cpu_in_queue+=1;
			}
		}
		
		//while queue has CPU-bound processes run...
		while(cpu_in_queue>0){

			//handles additions to ready queue 
			int check=0;
			Process temp;
			while(check != 1){
				temp = waitingTimeQueue.poll();
				if(temp.pType == "CPU-bound process"){
					if((temp.blockTime+temp.arrivalTime) < elapsed_time)){
						temp.arrivalTime=elapsed_time;
						readyQueue.add(temp);
						System.out.println("[time " + elapsed_time + "ms] " + temp.pType + " ID " + temp.processID + " entered ready queue (requires " + temp.burstTime + "ms CPU time");
					}
					else{
						check = 1;
						waitingTimeQueue.add(temp);
					}
				}
				else{
					if((temp.responseTime+temp.arrivalTime) < elapsed_time)){
						temp.arrivalTime=elapsed_time;
						readyQueue.add(temp);
						System.out.println("[time " + elapsed_time + "ms] " + temp.pType + " ID " + temp.processID + " entered ready queue (requires " + temp.burstTime + "ms CPU time");
					}	
					else{
						check = 1;
						waitingTimeQueue.add(temp);
					}
				}
			}


			//polls shortest job from the queue
			Process currentProcess = super.readyQueue.poll();

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
					waitingTimeQueue.add(currentProcess);
				}
			}
			//Interactive Process
			else{
				waitingTimeQueue.add(currentProcess);
			}

			//context switch!

		}

	}
*/
}