package dynamic_beat_1;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	
	public Note(String noteType) {
		if(noteType.equals("D")) {
			x = 117;
		}
		else if(noteType.equals("F")) {
			x = 220;
		}
		else if(noteType.equals("Space")) {
			x = 323;
		}
		else if(noteType.equals("J")) {
			x = 426;
		}
		else if(noteType.equals("K")) {
			x = 529;
		}
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null);
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y > 620) {
			close();
		}
	}

	
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}
				else {
					interrupt();
					break;
				}
			}
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String judge() {
		if(y >= 613) {
			close();
			return "Bad";
		}
		else if(y >= 600) {
			close();
			return "Good";
		}
		else if(y >= 587) {
			close();
			return "Great";
		}
		else if(y >= 573) {
			close();
			return "Perfect";
		}
		else if(y >= 565) {
			close();
			return "Great";
		}
		else if(y >= 550) {
			close();
			return "Good";
		}
		else if(y >= 535) {
			close();
			return "Bad";
		}
		return "None";
	}
	

	public int getY() {
		return y;
	}
}
