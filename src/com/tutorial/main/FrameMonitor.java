package com.tutorial.main;

public class FrameMonitor {
	// Configurable from here
	int frameHistorySize = 30;
	
	// Configured in caller
	int gameFPS;
	int updatesPerSecond;
	
	// Derived values set in constructor
	int averageFrames;
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
			frameHistoryIndex++;
			if(frameHistoryIndex == frameHistorySize){
				frameHistoryIndex = 0;
				frameHistoryFull = true;
			}
			averageFrames = frameHistorySum / frameHistoryIndex;
			//return frameHistorySum / frameHistoryIndex;
		}else{
			frameHistorySum -= frameHistory[frameHistoryIndex];
			frameHistorySum += frames;
			frameHistory[frameHistoryIndex] = frames;
			frameHistoryIndex++;
			averageFrames = frameHistorySum / frameHistorySize;
			//return frameHistorySum / frameHistoryNum;
		}
	}
	
	public void updateRenderInterval(){
		int averageFramesPerSecond = averageFrames * updatesPerSecond;
		double percentOffBy = (1.0 - ((double)averageFramesPerSecond / (double)gameFPS));
		// Add half the difference
		int amountToAdd = (int) (.5 * percentOffBy * renderInterval);
		renderInterval += amountToAdd;
	}
}
