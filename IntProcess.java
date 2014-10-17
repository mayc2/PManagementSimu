import java.util.Random;

public class IntProcess implements Runnable{
	public int processID;	//1 through 12 by default
	public int type;		//interactive or cpu bound
	public int burstTime;
	



	public void run(){

	}

	public static int ranInt(int min, int max){
		Random rand = new Random();
		int random=rand.nextInt((max-min)+1)+min;
		return random;
	}
}