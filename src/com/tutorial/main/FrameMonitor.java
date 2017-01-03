package com.tutorial.main;

public class FrameMonitor {
	// Configurable from here
	int frameHistorySize = 30;
	
	// Configured in caller
	int gameFPS;
	int updatesPerSecond;
	
	// Derived values set in constructor
	double averageFrames;
	int quarterFrames;
	int renderInterval;
	
	// Other
	int[] frameHistory = new int[frameHistorySize];
	boolean frameHistoryFull = false;
	int frameHistoryIndex = 0;
	int frameHistorySum = 0;
	
	public FrameMonitor(int gameFPS, int updatesPerSecond){
		this.gameFPS = gameFPS;
		this.updatesPerSecond = updatesPerSecond;
		
		this.averageFrames = gameFPS;
		this.quarterFrames = gameFPS / updatesPerSecond;
		this.renderInterval = 1000000000 / gameFPS;
	}
	
	public void updateAverageFrames(int frames){
		if(!frameHistoryFull){
			frameHistory[frameHistoryIndex] = frames;
			frameHistorySum += frames;
			averageFrames = (double)frameHistorySum / (double)(frameHistoryIndex + 1);
			if(frameHistoryIndex == (frameHistorySize - 1)){
				frameHistoryIndex = 0;
				frameHistoryFull = true;
			}
			frameHistoryIndex++;
			//return frameHistorySum / frameHistoryIndex;
		}else{
			frameHistorySum -= frameHistory[frameHistoryIndex];
			frameHistorySum += frames;
			frameHistory[frameHistoryIndex] = frames;
			if(frameHistoryIndex < (frameHistorySize - 1)){
				frameHistoryIndex++;
			}else{
				frameHistoryIndex = 0;
			}
			averageFrames = (double)frameHistorySum / (double)frameHistorySize;
			//return frameHistorySum / frameHistoryNum;
		}
	}
	
	public void updateRenderInterval(){
		double averageFramesPerSecond = averageFrames * updatesPerSecond;
		double percentOffBy = (1.0 - ((double)averageFramesPerSecond / (double)gameFPS));
		
		if(Math.abs(percentOffBy) < .02){
			System.out.println("Under sleep threshold");
			return;
		}
		
		// Maybe add only a fraction of the difference
		int amountToAdd = -1 * (int) ((1.0) * percentOffBy * (double)renderInterval);
		renderInterval += amountToAdd;
		
		System.out.print("AverageFramesPerSecond: " + averageFramesPerSecond + ", ");
		System.out.print("percentOffBy: " + percentOffBy + ", ");
		System.out.print("renderInterval: " + renderInterval + ", ");
		System.out.println("amountToAdd: " + amountToAdd);
	}
}
