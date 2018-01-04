import javax.sound.sampled.*;

public class Sound {
	private Clip clip;
	long clipTime;
	
    public static Sound sound1 = new Sound("/MarioMusic.wav");
    public static Sound sound2 = new Sound("/GameOver.wav");
    public static Sound sound3 = new Sound("/Point.wav");
	
	public Sound(String fileName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			System.out.println("ERROR");
		}
	}
	
	public void play() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			System.out.println("ERROR");
		}
	}
	
	
	public void stop() {
		if (clip == null) {
			return;
		} else {
			clip.stop();
		}
	}
	
	public void loop() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized(clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						}
					}
				}.start();
			}
		} catch (Exception e) {
			System.out.println("ERROR");
		}
	} 
	
	public void pause() {
		clipTime = clip.getMicrosecondPosition();
		clip.stop();
	}
	
	public void startAfterPause() {
		clip.setMicrosecondPosition(clipTime);
		clip.start();
	}
	
	public boolean isActive() {
		return clip.isActive();
	}
	
}
