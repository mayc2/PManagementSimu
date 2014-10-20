import java.util.Queue;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;
import java.util.*;

//with no preemption
public class ShortestJobFirst extends Algorithm{ 
	
	public ShortestJobFirst(Process[] p_, int m_){
		super(p_,m_);
		super.readyQueue = new PriorityQueue<>(super.processes.length, super.burstComparator);
		super.waitingTimeQueue =  new PriorityQueue<>(super.processes.length, super.waitComparator);
		super.cpuList =  new LinkedList<>();
		super.context_switch_count = 0;
		super.t_cs = 2;
		tmp1=0;
		numAdded=0;
	}

	//finds time to increment elapsed time by after completion of a process
	public int findElapsedTime(int lastEndTime, int startTime, int endTime){
		int temp;
		if(lastEndTime>startTime){
			temp = lastEndTime - startTime;
			return endTime - temp;
		}
		else if (lastEndTime < startTime){
			temp = startTime-lastEndTime;
			return temp + (endTime - startTime);
		}
		else{
			return endTime - startTime;
		}
	}

	public void incrementTime(int amount){
		elapsed_time += amount;
		Process temp;
		for(int i = 0; i < cpuList.size(); ++i){
			temp = cpuList.get(i);
			temp.remBurstTime--;
			if(temp.remBurstTime == 0){
				System.out.println("Removing "+temp.processID+" from cpuList");
				temp.endTime=elapsed_time;
				burstCompletion(temp);
				cpuList.remove(temp);
			}
		}
	}

	public void burstCompletion(Process currentProcess){
		currentProcess.totalBurstTime+=currentProcess.burstTime;

		//print process status
		System.out.println("[time " + elapsed_time + "] " + currentProcess.pType + " ID " + currentProcess.processID + " CPU burst done (turnaround time " + (currentProcess.getTurnaroundTime(elapsed_time)-1) + "ms, total wait time " + (currentProcess.getWaitTime(elapsed_time,context_switch_count,t_cs)-1) + "ms)");

		//updates the current process with new times
		currentProcess.refresh(elapsed_time);
		
		//Identify type of process and implement
		if(currentProcess.pType == "CPU-bound process"){
			if(currentProcess.remBursts==1){
				cpu_in_queue--;
				currentProcess.totalBurstTime+=currentProcess.burstTime;

				//print process status
				System.out.println("[time " + elapsed_time + "] " + currentProcess.pType + " ID " + currentProcess.processID + " terminated (avg turnaround time " + (currentProcess.getAvgTurnaroundTime()-1) + "ms, avg total wait time " + (currentProcess.getAvgWaitTime()-1) + "ms)");

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

	public void execute(){

		//adds all original processes in array to the ready queue at time 0ms
		for(int i = 0; i < super.processes.length; ++i){
			super.readyQueue.add(super.processes[i]);
			System.out.println("[time 0ms] " + super.processes[i].pType + " ID " + super.processes[i].processID + " entered ready queue (requires " + super.processes[i].burstTime + "ms CPU time)");
			if(super.processes[i].pType == "CPU-bound process"){
				super.cpu_in_queue+=1;
			}
		}

		//time of completion of previous process
		int lastAdded=0;

		//while queue has CPU-bound processes run...
		while(super.cpu_in_queue>0){

			//fills cpu's with processes
			while(cpuList.size() < m){
				//numAdded++;

				//handles additions to ready queue 
				int check=0;
				Process temp;
				while(check != 1){
					temp = super.waitingTimeQueue.poll();
					if(temp == null){
						break;
					}
					if(temp.pType == "CPU-bound process"){
						if((temp.blockTime+temp.arrivalTime) < super.elapsed_time){
							temp.arrivalTime=super.elapsed_time;
							super.readyQueue.add(temp);
							System.out.println("[time " + super.elapsed_time + "ms] " + temp.pType + " ID " + temp.processID + " entered ready queue (requires " + temp.burstTime + "ms CPU time)");
						}
						else{
							check = 1;
							super.waitingTimeQueue.add(temp);
						}
					}
					else{
						if((temp.responseTime+temp.arrivalTime) < super.elapsed_time){
							temp.arrivalTime=super.elapsed_time;
							super.readyQueue.add(temp);
							System.out.println("[time " + super.elapsed_time + "ms] " + temp.pType + " ID " + temp.processID + " entered ready queue (requires " + temp.burstTime + "ms CPU time)");
						}	
						else{
							check = 1;
							super.waitingTimeQueue.add(temp);
						}
					}
				}

				//polls shortest job from the queue
				Process xProcess = readyQueue.poll();	
				
				if(xProcess == null){
					incrementTime(1);
					continue;
				}

/*				if(numAdded > 4){
					//context switch!
					context_switch_count++;
					incrementTime(t_cs);
				}*/
				System.out.println("adding "+xProcess.processID+" to cpuList");
				cpuList.add(xProcess);
			}
			
			incrementTime(1);

			/*//polls first process running on a cpu to be completed
			Process currentProcess = cpuList.poll();
			
			//add burst time to elapsed time + total burst time for process
			elapsed_time += findElapsedTime(lastAdded,currentProcess.arrivalTime,(currentProcess.arrivalTime + currentProcess.burstTime));
			lastAdded=elapsed_time-tracker;
*/
		}
	}
				
}