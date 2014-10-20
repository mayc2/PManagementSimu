import java.util.Comparator;
import java.util.Arrays;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;
import java.util.*;

public class Algorithm{
	public Process[] processes;
	public Queue<Process> readyQueue;
	public List<Process> waitingTimeList;
	public List<Process> cpuList;
	public int cpu_in_queue;
	public int elapsed_time;
	public int context_switch_count;
	public int numAdded;
	public int t_cs;
	public int tmp1;
	public int m;

	public Algorithm(Process[] p_, int m_){
		processes=p_;
		cpu_in_queue=0;
		elapsed_time=0;
		m=m_;
	}

	public static Comparator<Process> endTimeComparator = new Comparator<Process>(){
        @Override
        public int compare(Process p1, Process p2) {
			return (int) ((p1.arrivalTime + p1.burstTime) - (p2.arrivalTime + p2.burstTime));
        }
    };
	
	public static Comparator<Process> burstComparator = new Comparator<Process>(){
        @Override
        public int compare(Process p1, Process p2) {
			return (int) (p1.burstTime - p2.burstTime);
        }
    };

    public static Comparator<Process> turnaroundComp = new Comparator<Process>(){
        @Override
        public int compare(Process p1, Process p2) {
			return (int) (p1.turnaroundTime - p2.turnaroundTime);
        }
    };

    public void TurnaroundSort(){
		Arrays.sort(processes, turnaroundComp);
	}

	public static Comparator<Process> waitComparator = new Comparator<Process>(){
        @Override
        public int compare(Process p1, Process p2) {
			return (int) (p1.waitTime - p2.waitTime);
        }
    };

	public void WaitSort(){
		Arrays.sort(processes, waitComparator);
	}

	public static Comparator<Process> IDComparator = new Comparator<Process>(){
        @Override
        public int compare(Process p1, Process p2) {
			return (int) (p1.processID - p2.processID);
        }
    };

	public void IDSort(){
		Arrays.sort(processes, IDComparator);
	}

	public void statistics(){
		
		//print turnaround time statistic for current algorithm
		TurnaroundSort();
		float avg=0;
		for(int i = 0; i < processes.length; ++i){
			avg += processes[i].turnaroundTime;
		}
		avg = avg/(processes.length);
		System.out.println("Turnaround time: min " + processes[0].turnaroundTime + " ms; avg " + avg + " ms; max " + processes[processes.length-1].turnaroundTime + " ms" );

		//print wait time statistic for current algorithm
		WaitSort();
		avg=0;
		for(int i = 0; i < processes.length; ++i){
			avg += processes[i].waitTime;
		}
		avg = avg/(processes.length);
		System.out.println("Total wait time: min " + processes[0].waitTime + " ms; avg " + avg + " ms; max " + processes[processes.length-1].waitTime + " ms" );

		//print Average CPU utilization
		avg=0;
		for(int i = 0; i<processes.length; ++i){ 
			processes[i].calcCpuUtil();
			avg+=processes[i].cpuUtil;
		}
		avg = (int) (avg/(processes.length));
		System.out.println("Average CPU utilization: " + avg + "%");

		//print Average CPU utilization of each process
		IDSort();
		System.out.println("");
		System.out.println("Average CPU utilization per process:");
		for(int i=0; i < processes.length; ++i){
			System.out.println("totalBurstTime is " + processes[i].totalBurstTime + " and totalTurnaroundTime is " + processes[i].totalTurnaroundTime);
			System.out.println("process " + processes[i].processID + ": " + processes[i].cpuUtil + "%");
		}
	}
}