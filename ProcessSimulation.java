import java.util.Random;

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
////////////////////////////////////////////////////
		/*	GLOBAL VARIABLES	*/
		int n=12;	//12 by default
		int m=1;	//4 by default

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
////////////////////////////////////////////////////
		Boolean cpuAdded = false;
		Process[] processes = new Process[n];
		for (int i = 0; i < n; i++) {
			if (i == (n - 1) && !cpuAdded) { // If only one process left and none are cpu
				processes[i] = new CpuProcess(i+1, 8);
			}
			else {
				if (weightedBinary(0.2)) { // Returns true 20% of time for cpu processes
					cpuAdded = true;
					processes[i] = new CpuProcess(i+1, 8);
				}
				else { // 80% of time interactive process
					processes[i] = new IntProcess(i+1);
				}
			}
		}

		// Shortest Job First
		ShortestJobFirst sjf = new ShortestJobFirst(processes,m);
		sjf.execute();
		sjf.statistics();
	}
}