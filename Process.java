public class Process implements Runnable{

	public void run(){
		
	}

	public static int ranInt(int min, int max){
		Random rand = new Random();
		int random=rand.nextInt((max-min)+1)+min;
		return random;
	}
}