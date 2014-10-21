// Paul Chiappetta and Chris May

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
		cpuList = new LinkedList<>();
		waitingTimeList =  new LinkedList<>();
		super.context_switch_count = 0;
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
				temp.timeWithCpu++;
				if(temp.timeWithCpu == 1200){
					temp.timeWithCpu=0;
					if(temp.priority!=0){
						temp.priority--;
						System.out.println("[time "+elapsed_time+"ms] Increased priority of "+temp.pType+" ID "+temp.processID+" to "+temp.priority+" due to aging");
					}
				}
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
		Process currentProcess;
		for(int i = 0; i < cpuList.size(); ++i){
			currentProcess = cpuList.get(i);
			if(temp.priority < currentProcess.priority){
				cpuList.remove(currentProcess);
				readyQueue.add(currentProcess);
				cpuList.add(temp);
				System.out.println("[time " + elapsed_time + "ms] Context switch (swapping out process "+ currentProcess.processID +" for process ID "+ temp.processID+")");
			}
		}
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
		for(int i = 0; i < processes.length; ++i){
			readyQueue.add(processes[i]);
			System.out.println("[time 0ms] " + processes[i].pType + " ID " + processes[i].processID + " entered ready queue (requires " + processes[i].burstTime + "ms CPU time; priority "+ processes[i].priority +")");
			if(processes[i].pType == "CPU-bound process"){
				cpu_in_queue+=1;
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

		//end of execute function
	}

}
