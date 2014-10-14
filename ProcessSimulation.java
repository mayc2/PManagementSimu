//with no preemption
class ShortestJobFirst{

} 

//with preemption
class ShortestJobFirstPre{

}

//with preemption via configurable time slice TSLICE=100MS
class RoundRobin{

}

/*
 	with random priority levels 0-4 assigned to processes at 
 	the onset (low numbers indicate high priority); processes 
 	with the same priority are processed via the FCFS algorithm; 
 	higher-priority processes entering the ready queue may 
 	preempt a running process; aging causes lower-priority 
 	processes to increase their priority by 1 every elapsed 
 	1200 milliseconds
 */
class PreemptivePriority{

}

class Timer{
	int n;
	public Timer (int n){
		this.n=n;
	}

}

public class ProcessSimulation implements Runnable{

	public static void main(String[] args) {
			System.out.println("Test: First Print");
	}

	public void run(){
			System.out.println("Test: First Print in run()");		
	}

}