package leetcode;

public class BowlingScore {
	
	private int counterInFrame = 0;
	private int frameCount=0;
	private int prevPins = 0;
	private int prevFrameStat = -1;
	private int prevPrevFrameStat = -1;
	private int score = 0;
	
	public void scoreBoard(int pins) {
				
		
		
		addToPrevFrame(pins);	
		if(counterInFrame == 0) {//frame start
			score += pins;
			if(pins == 10) {//strike
				frameCount++;
				prevPrevFrameStat = prevFrameStat;
				prevFrameStat = 0; // 0 for strike, 1 for spare, 2 for open frame
			} else {
				counterInFrame++;
				prevPins = pins;
			}
			
			
		} else if(counterInFrame==1){//second try
			score += pins;
			frameCount++;
			counterInFrame = 0;
			prevPrevFrameStat = prevFrameStat;
			if(prevPins + pins == 10) {//spare
				prevFrameStat = 1;
			} else {
				prevFrameStat = 2;
			}
		}
		
		System.out.println("=============");
		System.out.println("pins is:"+pins);
		System.out.println("score is:"+score);
		System.out.println("Frame count is:"+frameCount);
		System.out.println("counterInframe:"+counterInFrame);
	}
	
	private void addToPrevFrame(int pins) {
		if(prevFrameStat == 0) {
			score += pins;
		}
		if(prevFrameStat == 1 && counterInFrame==0) {
			score += pins;
		}
		if(prevFrameStat == 0 && prevPrevFrameStat == 0) {
			score += pins;
		}
	}
	
	public int getFrameCount() {
		return frameCount;
	}
	
	public int getPrevPins() {
		return prevPins;
	}
	public int getCounterInframe() {
		return counterInFrame;
	}
	
	public static void main(String[] args) {
		BowlingScore bs = new BowlingScore();
		while(bs.getFrameCount() < 10) {
			int pins;
			if(bs.getCounterInframe()==1) {
				pins = (int)((10-bs.getPrevPins()+1) * Math.random());
			} else {
				 pins = (int)(Math.random() * 11);
			}
			bs.scoreBoard(pins);
			
		}
			
		
	}

}
