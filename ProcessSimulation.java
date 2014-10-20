import java.util.Random;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

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

class ElapsedTime implements Runnable{
	int n;

	public ElapsedTime(){
		this.n=0;
	}

	public void run(){
		while(true){
			this.n += 1;
		}
	}

	public void time(){
		System.out.println("Elapsed Time: " + this.n);
	}

}

public class ProcessSimulation {

	public static Boolean weightedBinary(double weight) { //returns true <weight> % of the time
		Random rand = new Random();
		double randDouble = rand.nextDouble();
		if (randDouble < weight) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void main(String[] args) {

		/*	GLOBAL VARIABLES	*/
		int n=12;	//12 by default
		int m=4;	//4 by default

		/*	HANDLES CLI ARGUMENTS	*/
		//Usage: java ProcessSimulation.java processes(n) cpu's(m)
		if(args.length>2){
			System.out.println("Usage: program_name processes(n) cpu's(m)");
			return;
		}
		else if(args.length==1){
			try{
				n=Integer.parseInt(args[0]);
			}
			catch (NumberFormatException e){
				System.out.println("Usage: program_name processes(n) cpu's(m)");
				return;
			}
		}
		else if(args.length==2){
			try{
				m=Integer.parseInt(args[1]);
			}
			catch (NumberFormatException e1){
				System.out.println("Usage: program_name processes(n) cpu's(m)");
				return;
			}
		}

	/*	


		ElapsedTime t1 = new ElapsedTime();
		System.out.println("Test: First Println");
		Thread th1 = new Thread(t1);
		th1.start();
		t1.time();
		t1.time();
		t1.time();
		t1.time();
		t1.time();
		int i=0;
		while(i!=30000000) ++i;
		System.out.println("past while");
		t1.time();
		t1.time();
		t1.time();

		try{
		    while ( Thread.activeCount() > 1 )
		    {
		      if ( th1.isAlive() )
		      {
		        th1.join();     // BLOCKS
		      }
		      System.out.println( "t1 thread terminated" );
		    }
		}
		catch ( InterruptedException ex )
		{
		}
	*/

		/////////// Above here just a test, also argument parsing not the cleanest but works
		Boolean cpuAdded = false;
		Process[] processes = new Process[n];
		for (int i = 0; i < n; i++) {
			if (i == (n - 1) && !cpuAdded) { // If only one process left and none are cpu
				processes[i] = new CpuProcess(i, 8);
			}
			else {
				if (weightedBinary(0.2)) { // Returns true 20% of time for cpu processes
					cpuAdded = true;
					processes[i] = new CpuProcess(i, 8);
				}
				else { // 80% of time interactive process
					processes[i] = new IntProcess(i);
				}
			}
		}
		/*for (int i = 0; i < n; i++) {
			System.out.println(processes[i].getClass().getName());
		}*/
	}
}