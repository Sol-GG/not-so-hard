package com.notsohard.framework;

public interface Game {
	public Input getInput();
	
	public FileIO getFileIO();
	
	public Graphics getGraphics();
	
	public Audio getAudio();
	
	public void setScreen(Screen screen);
	
	public Screen getCurentScreen();
	
	public Screen getStartScreen();
}
