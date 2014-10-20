import java.util.Comparator;
import java.util.Arrays;

public class Algorithm{
	public Process[] processes;
	private Queue<Process> processQueue;
	private int cpu_in_queue;
	private int elapsed_time;

	public Algorithm(Process[] p_){
		processes=p_;
		cpu_in_queue=0;
		elapsed_time=0;
	}
	
	public static Comparator<Process> turnaroundComp = new Comparator<Process>(){
        @Override
        public int compare(Process p1, Process p2) {
			return (int) (p1.turnaroundTime - p2.turnaroundTime);
        }
    };

    public static Comparator<Process> burstComparator = new Comparator<Process>(){
        @Override
        public int compare(Process p1, Process p2) {
			return (int) (p1.burstTime - p2.burstTime);
        }
    };

    public void TurnaroundSort(){
		Arrays.sort(processes, turnaroundComp);
	}

	public void WaitSort(){
		Arrays.sort(processes, new Comparator<Process>(){
			public int compare(Process p1, Process p2){
		    	return (int) (p1.waitTime - p2.waitTime);
		  	}
		});
	}
	public void IDSort(){
		Arrays.sort(processes, new Comparator<Process>(){
			public int compare(Process p1, Process p2){
		    	return (int) (p1.processID - p2.processID);
		  	}
		});
	}

	public void Statistics(){
		
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
			avg+=processes[i].cpuUtil;
		}
		avg = avg/(processes.length);
		System.out.println("Average CPU utilization: " + avg + "%");

		//print Average CPU utilization of each process
		IDSort();
		System.out.println("");
		System.out.println("Average CPU utilization per process:");
		for(int i=0; i < processes.length; ++i){
			System.out.println("process " + processes[i].processID + ": " + processes[i].cpuUtil + "%");
		}
	}
}