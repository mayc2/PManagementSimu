import java.util.Queue;
import java.util.PriorityQueue;

//with no preemption
public class ShortestJobFirst extends Algorithm{ 

	public ShortestJobFirst(Process[] p_){
		super(p_);
		super.readyQueue = new PriorityQueue<>(super.processes.length, super.burstComparator);
		super.waitingTimeQueue =  new PriorityQueue<>(super.processes.length, super.waitComparator);
		super.context_switch_count = 0;
		super.t_cs = 2;
	}

	public void execute(){
		for(int i = 0; i < super.processes.length; ++i){
			super.readyQueue.add(super.processes[i]);
			System.out.println("[time 0ms] " + super.processes[i].pType + " ID " + super.processes[i].processID + " entered ready queue (requires " + super.processes[i].burstTime + "ms CPU time)");
			if(super.processes[i].pType == "CPU-bound process"){
				super.cpu_in_queue+=1;
			}
		}

		int tmp1=0;
		
		//while queue has CPU-bound processes run...
		while(cpu_in_queue>0){

			//handles additions to ready queue 
			int check=0;
			Process temp;
			while(check != 1){
				temp = waitingTimeQueue.poll();
				if(temp == null){
					break;
				}
				if(temp.pType == "CPU-bound process"){
					if((temp.blockTime+temp.arrivalTime) < elapsed_time){
						temp.arrivalTime=elapsed_time;
						readyQueue.add(temp);
						System.out.println("[time " + elapsed_time + "ms] " + temp.pType + " ID " + temp.processID + " entered ready queue (requires " + temp.burstTime + "ms CPU time)");
					}
					else{
						check = 1;
						waitingTimeQueue.add(temp);
					}
				}
				else{
					if((temp.responseTime+temp.arrivalTime) < elapsed_time){
						temp.arrivalTime=elapsed_time;
						readyQueue.add(temp);
						System.out.println("[time " + elapsed_time + "ms] " + temp.pType + " ID " + temp.processID + " entered ready queue (requires " + temp.burstTime + "ms CPU time)");
					}	
					else{
						check = 1;
						waitingTimeQueue.add(temp);
					}
				}
			}


			//polls shortest job from the queue
			Process currentProcess = super.readyQueue.poll();
			if(currentProcess == null){
				// System.out.println("ERROR: ready queue is null");
				elapsed_time++;
				continue;
				// break;
			}

			//add burst time to elapsed time + total burst time for process
			super.elapsed_time += currentProcess.burstTime;
			currentProcess.totalBurstTime+=currentProcess.burstTime;

			//print process status
			System.out.println("[time " + elapsed_time + "] " + currentProcess.pType + " ID " + currentProcess.processID + " CPU burst done (turnaround time " + currentProcess.getTurnaroundTime(elapsed_time,tmp1) + "ms, total wait time " + currentProcess.getWaitTime(elapsed_time,context_switch_count,t_cs) + "ms)");

			//context switch!
			super.context_switch_count++;
			elapsed_time+=t_cs;
			tmp1=1;

			//updates the current process with new times
			currentProcess.refresh(elapsed_time);
/*
			if(currentProcess.pType == "CPU-bound process"){
				System.out.println("Block time is " + currentProcess.blockTime);
			}
			else{
				System.out.println("responseTime is " + currentProcess.responseTime);
			}*/

			//Identify type of process and implement
			if(currentProcess.pType == "CPU-bound process"){
				if(currentProcess.remBursts==1){
					cpu_in_queue--;
					currentProcess.totalBurstTime+=currentProcess.burstTime;

					//print process status
					System.out.println("[time " + elapsed_time + "] " + currentProcess.pType + " ID " + currentProcess.processID + " terminated (avg turnaround time " + currentProcess.getAvgTurnaroundTime() + "ms, avg total wait time " + currentProcess.getAvgWaitTime() + "ms)");

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

		}
	}

	
}