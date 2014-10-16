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

public class ProcessSimulation implements Runnable{

	public static void main(String[] args) {
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
	}


	public void run(){
		System.out.println("Test: First Print in run()");		
	}

}