import java.util.Queue;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;
import java.util.*;

//with no preemption
public class ShortestJobFirst extends Algorithm{ 
	
	public ShortestJobFirst(Process[] p_, int m_){
		super(p_,m_);
		super.readyQueue = new PriorityQueue<>(super.processes.length, super.burstComparator);
		super.waitingTimeList =  new LinkedList<>();
		super.cpuList =  new LinkedList<>();
		super.context_switch_count = 0;
		super.t_cs = 2;
		tmp1=0;
		numAdded=0;
	}

	public void incrementTime(int amount){
		//increase elapsed time by one
		elapsed_time += amount;
		Process temp;

		//increase wait time for each process in ready queue
		Iterator<Process> it = readyQueue.iterator();
		while(it.hasNext()){
			temp=it.next();
			if(cpuList.indexOf(temp) == -1){
				temp.waitTime++;
				temp.turnaroundTime++;
			}
		}

		//re-add any processes that are ready to go into ready queue
		for(int j=0; j < waitingTimeList.size(); ++j){
			temp=waitingTimeList.get(j);
			if(temp.pType == "CPU-bound process"){
				temp.blockTime--;
				if(temp.blockTime == 0){
					waitingTimeList.remove(temp);
					waitCompletion(temp);	
				}
			}
			else{
				temp.responseTime--;
				if(temp.responseTime == 0){
					waitingTimeList.remove(temp);
					waitCompletion(temp);
				}
			}
		}

		//edit remaining time in current burst and handle completions if any occur
		for(int i = 0; i < cpuList.size(); ++i){
			temp = cpuList.get(i);
			temp.remBurstTime--;
			temp.turnaroundTime++;
			if(temp.remBurstTime == 0){
				// System.out.println("Removing "+temp.processID+" from cpuList");
				temp.endTime=elapsed_time;
				burstCompletion(temp);
				cpuList.remove(temp);
			}
		}

	}

	public void waitCompletion(Process temp){
		temp.arrivalTime=elapsed_time;
		readyQueue.add(temp);
		System.out.println("[time " + elapsed_time + "ms] " + temp.pType + " ID " + temp.processID + " entered ready queue (requires " + temp.burstTime + "ms CPU time)");
	}

	public void burstCompletion(Process currentProcess){
		currentProcess.totalBurstTime+=currentProcess.burstTime;

		//print process status
		System.out.println("[time " + elapsed_time + "ms] " + currentProcess.pType + " ID " + currentProcess.processID + " CPU burst done (turnaround time " + currentProcess.turnaroundTime + "ms, total wait time " + currentProcess.waitTime + "ms)");

		//updates the current process with new times
		currentProcess.refresh(elapsed_time);

		//Identify type of process and implement
		if(currentProcess.pType == "CPU-bound process"){
			if(currentProcess.remBursts==1){
				cpu_in_queue--;
				currentProcess.remBursts--;

				//print process status
				System.out.println("[time " + elapsed_time + "ms] " + currentProcess.pType + " ID " + currentProcess.processID + " terminated (avg turnaround time " + currentProcess.getAvgTurnaroundTime() + "ms, avg total wait time " + currentProcess.getAvgWaitTime() + "ms)");

			}
			else{
				currentProcess.remBursts--;
				waitingTimeList.add(currentProcess);
			}
		}
		//Interactive Process
		else{
			waitingTimeList.add(currentProcess);
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

		//while queue has CPU-bound processes run...
		while(super.cpu_in_queue>0) {

			//fills cpu's with processes
			while(cpuList.size() < m){

				//polls shortest job from the queue
				Process xProcess = readyQueue.poll();	
				
				if(xProcess == null){
					incrementTime(1);
					continue;
				}
				cpuList.add(xProcess);
			}
			incrementTime(1);
		}
	}
}