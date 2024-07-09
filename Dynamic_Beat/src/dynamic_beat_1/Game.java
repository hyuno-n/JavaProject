package dynamic_beat_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Game extends Thread{
	
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image purpleFlareImage;
	private Image judgeImage; 
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpaceImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image background;
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	private Music resultMusic = new Music("resultMusic.mp3", true);
	
	private int score;
	private String strScore = "00000";
	private String uname;
	private DBConnection DB = new DBConnection();
	
	private boolean isGameScreen = true;
	private boolean isResultScreen = false;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}
	
	public void screenDraw(Graphics2D g) {
		if(isGameScreen) {
			g.drawImage(noteRouteDImage, 117, 30, null);
			g.drawImage(noteRouteFImage, 220, 30, null);
			g.drawImage(noteRouteSpaceImage, 323, 30, null);
			g.drawImage(noteRouteJImage, 426, 30, null);
			g.drawImage(noteRouteKImage, 529, 30, null);
			g.drawImage(noteRouteLineImage, 114, 30, null);
			g.drawImage(noteRouteLineImage, 217, 30, null);
			g.drawImage(noteRouteLineImage, 320, 30, null);
			g.drawImage(noteRouteLineImage, 423, 30, null);
			g.drawImage(noteRouteLineImage, 526, 30, null);
			g.drawImage(noteRouteLineImage, 629, 30, null);
			g.drawImage(gameInfoImage, 0, 663, null);
			g.drawImage(judgementLineImage, 117, 580, null);
			
			for(int i = 0; i < noteList.size(); i++)
			{
				Note note = noteList.get(i);
				if(note.getY() > 620) {
					judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
	
				}
				if(!note.isProceeded()) {
					noteList.remove(i);
					i--;
				}
				else {
					note.screenDraw(g);
				}
			}
			g.setColor(Color.white);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setFont(new Font("돋음체", Font.BOLD, 30));
			g.drawString(titleName, 20, 704);
			g.drawString(difficulty, 1190, 704);
			g.setFont(new Font("Arial", Font.PLAIN, 26));
			g.setColor(Color.DARK_GRAY);
			g.drawString("D", 159, 612);
			g.drawString("F", 262, 612);
			g.drawString("Space", 336, 609);
			g.drawString("J", 468, 612);
			g.drawString("K", 571, 612);
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("돋음체", Font.BOLD, 40));
			g.drawString(strScore , 565, 708);
			g.drawImage(purpleFlareImage, 180, 360, null);
			g.drawImage(judgeImage, 295, 420, null);
			g.drawImage(keyPadDImage, 117, 580, null);
			g.drawImage(keyPadFImage, 220, 580, null);
			g.drawImage(keyPadSpaceImage, 323, 580, null);
			g.drawImage(keyPadJImage, 426, 580, null);
			g.drawImage(keyPadKImage, 529, 580, null);
		}
		if(isResultScreen) {
			background = new ImageIcon(Main.class.getResource("../images/resultBackground.png")).getImage();
			g.drawImage(background, 0, 0, null);
			g.setColor(Color.white);
			g.setFont(new Font("돋음체", Font.BOLD, 120));
			g.drawString("Score  " + strScore, 350, 360);
			g.setFont(new Font("돋음체", Font.PLAIN, 100));
			g.drawString("결과", 300, 550);
		}
	}
	
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
	}
	
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();

	}
	
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	
	public void pressSpace() {
		judge("Space");
		noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpaceImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
	}
	
	public void releaseSpace() {
		noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpaceImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
	}
	
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
	}
	
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(this.titleName);
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
		if(isResultScreen) {
			resultMusic.close();
		}
	}
	
	public void dropNotes(String titleName) {
		Beat[] beats = null;
		if(titleName.equals("Blueming - IU") && difficulty.equals("Easy")) {
			int startTime = 2400 - Main.REACH_TIME * 1000;
			int gap = 250;
			beats = new Beat[] {
		               new Beat(startTime, "D"),
		               new Beat(startTime + gap * 4, "F"),
		               new Beat(startTime + gap * 9, "J"),
		               new Beat(startTime + gap * 13, "K"),
		               new Beat(startTime + gap * 18, "D"),
		               new Beat(startTime + gap * 22, "F"),
		               new Beat(startTime + gap * 27, "Space"),
		               new Beat(startTime + gap * 34, "Space"),
		               new Beat(startTime + gap * 36, "J"),
		               new Beat(startTime + gap * 38, "D"),
		               new Beat(startTime + gap * 40, "J"),
		               new Beat(startTime + gap * 44, "K"),
		               new Beat(startTime + gap * 46, "D"),
		               new Beat(startTime + gap * 49, "Space"),
		               new Beat(startTime + gap * 53, "J"),
		               new Beat(startTime + gap * 56, "F"),
		               new Beat(startTime + gap * 58, "J"),
		               new Beat(startTime + gap * 61 , "K"),
		               new Beat(startTime + gap * 70 , "Space"),
		               new Beat(startTime + gap * 72 , "K"),
		               new Beat(startTime + gap * 74 , "D"),
		               new Beat(startTime + gap * 80 , "J"),
		               new Beat(startTime + gap * 82 , "F"),
		               new Beat(startTime + gap * 84 , "K"),
		               new Beat(startTime + gap * 86 , "D"),
		               new Beat(startTime + gap * 88 , "F"),
		               new Beat(startTime + gap * 92 , "J"),
		               new Beat(startTime + gap * 96 , "Space"),
		               new Beat(startTime + gap * 104 , "Space"),
		               new Beat(startTime + gap * 106 , "J"),
		               new Beat(startTime + gap * 108 , "D"),
		               new Beat(startTime + gap * 110 , "F"),
		               new Beat(startTime + gap * 112 , "K"),
		               new Beat(startTime + gap * 116 , "K"),
		               new Beat(startTime + gap * 120 , "D"),            
		               new Beat(startTime + gap * 121 , "F"),         
		               new Beat(startTime + gap * 123 , "Space"),         
		               new Beat(startTime + gap * 125 , "K"),         
		               new Beat(startTime + gap * 127 , "D"),         
		               new Beat(startTime + gap * 129 , "F"),         
		               new Beat(startTime + gap * 135 , "J"),         
		               new Beat(startTime + gap * 143 , "Space"), // ����
		               new Beat(startTime + gap * 146 , "Space"), // �ٸ�               
		               new Beat(startTime + gap * 148 , "J"), // ���         
		               new Beat(startTime + gap * 152 , "K"), // �Բ�����      
		               new Beat(startTime + gap * 155 , "F"), // �ʾ�      
		               new Beat(startTime + gap * 161 , "J"), // ����      
		               new Beat(startTime + gap * 163 , "D"), // ����      
		               new Beat(startTime + gap * 165 , "K"), // ����      
		               new Beat(startTime + gap * 169 , "J"), // ~��      
		               new Beat(startTime + gap * 177 , "F"), // �츮��      
		               new Beat(startTime + gap * 181 , "Space"), // �׸�      
		               new Beat(startTime + gap * 183 , "Space"), // �׸�      
		               new Beat(startTime + gap * 185 , "Space"), // �׸�      
		               new Beat(startTime + gap * 188 , "J"), // �׸�      
		               new Beat(startTime + gap * 194 , "K"),   
		               new Beat(startTime + gap * 196 , "D"),   
		               new Beat(startTime + gap * 198 , "F"),   
		               new Beat(startTime + gap * 200 , "J"),   
		               new Beat(startTime + gap * 202 , "F"),   
		               new Beat(startTime + gap * 208 , "J"),   
		               new Beat(startTime + gap * 212 , "K"),   
		               new Beat(startTime + gap * 214 , "D"),   
		               new Beat(startTime + gap * 218 , "F"),   
		               new Beat(startTime + gap * 222 , "J"),   
		               new Beat(startTime + gap * 230 , "K"),//��   
		               new Beat(startTime + gap * 231 , "F"),//��
		               new Beat(startTime + gap * 232 , "D"),//��
		               new Beat(startTime + gap * 233 , "J"),//��
		               new Beat(startTime + gap * 237 , "K"),//��
		               new Beat(startTime + gap * 239 , "J"),//��
		               new Beat(startTime + gap * 240 , "K"),//��
		               new Beat(startTime + gap * 247 , "D"),// i feel
		               new Beat(startTime + gap * 253 , "F"),// bloom
		               new Beat(startTime + gap * 255 , "Space"),// 
		               new Beat(startTime + gap * 257 , "J"),// 
		               new Beat(startTime + gap * 259 , "D"),// 
		               new Beat(startTime + gap * 261 , "F"),// 
		               new Beat(startTime + gap * 264 , "D"),// 
		               new Beat(startTime + gap * 272 , "K"),// i feel
		               new Beat(startTime + gap * 274 , "J"),// bloom
		               new Beat(startTime + gap * 276 , "F"),// 
		               new Beat(startTime + gap * 278 , "Space"),// 
		               new Beat(startTime + gap * 280 , "J"),// 
		               new Beat(startTime + gap * 282 , "D"),// 
		               new Beat(startTime + gap * 285 , "F"),// 
		               new Beat(startTime + gap * 287 , "J"),// i feel
		               new Beat(startTime + gap * 291 , "K"),// bloom
		               new Beat(startTime + gap * 293 , "J"),// 
		               new Beat(startTime + gap * 295 , "D"),// 
		               new Beat(startTime + gap * 297 , "F"),// 
		               new Beat(startTime + gap * 299 , "D"),// 
		               new Beat(startTime + gap * 303 , "F"),// 
		               new Beat(startTime + gap * 308 , "J"),//��
		               new Beat(startTime + gap * 311 , "K"),//��
		               new Beat(startTime + gap * 317 , "D"),//��
		               new Beat(startTime + gap * 324 , "Space"),//�� 
		               new Beat(startTime + gap * 325 , "Space"),//�� 
					
			};
		}
		else if(titleName.equals("Blueming - IU") && difficulty.equals("Hard")) {
			int startTime = 2400 - Main.REACH_TIME * 1000;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime, "D"),
					new Beat(startTime + gap * 2, "F"),
					new Beat(startTime + gap * 3, "D"),
					new Beat(startTime + gap * 7, "K"),
					new Beat(startTime + gap * 9, "J"),
					new Beat(startTime + gap * 10, "K"),
					new Beat(startTime + gap * 18, "F"),
					new Beat(startTime + gap * 20, "D"),
					new Beat(startTime + gap * 21, "F"),
					new Beat(startTime + gap * 25, "J"),
					new Beat(startTime + gap * 27, "K"),
					new Beat(startTime + gap * 28, "J"),
					new Beat(startTime + gap * 36, "J"),
					new Beat(startTime + gap * 38, "J"),
					new Beat(startTime + gap * 39, "J"),
					new Beat(startTime + gap * 43, "J"),
					new Beat(startTime + gap * 45, "J"),
					new Beat(startTime + gap * 46, "J"),
					new Beat(startTime + gap * 54, "J"),
					new Beat(startTime + gap * 56, "J"),
					new Beat(startTime + gap * 57, "J"),
					new Beat(startTime + gap * 67, "D"), // ��
					new Beat(startTime + gap * 67, "K"), // ��
					new Beat(startTime + gap * 69, "F"), // ��
					new Beat(startTime + gap * 69, "J"), // ��
					new Beat(startTime + gap * 71, "F"), // ��
					new Beat(startTime + gap * 73, "D"), // ��
					new Beat(startTime + gap * 74, "J"), // ��
					new Beat(startTime + gap * 76, "F"), // ��
					new Beat(startTime + gap * 77, "J"), // ��
					new Beat(startTime + gap * 79, "K"), // ��
					

			};
		}
		else if(titleName.equals("Eight - IU") && difficulty.equals("Easy"))
		{
			int startTime = 2400 - Main.REACH_TIME * 1000;
			int gap = 250;
			beats = new Beat[] {
					new Beat(startTime, "D"),
			};
		}
		else if(titleName.equals("Eight - IU") && difficulty.equals("Hard"))
		{
			int startTime = 2400 - Main.REACH_TIME * 1000;
			int gap = 250;
			beats = new Beat[] {
					new Beat(startTime, "D"),
			};
		}
		else if(titleName.equals("Good Day - IU") && difficulty.equals("Easy"))
		{
			int startTime = 2400 - Main.REACH_TIME * 1000;
			int gap = 250;
			beats = new Beat[] {
					new Beat(startTime, "D"),
			};
		}
		else if(titleName.equals("Good Day - IU") && difficulty.equals("Hard"))
		{
			int startTime = 2400 - Main.REACH_TIME * 1000;
			int gap = 250;
			beats = new Beat[] {
					new Beat(startTime, "D"),
			};
		}
		int i = 0;
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
		
			if(!dropped) {
				try {
					Thread.sleep(5);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			Thread.sleep(7000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		enterResult();
	}
	
	public void judge(String input) {
		for(int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				scorePlus(note.judge());
				break;
			}
		}
	}
	
	public void judgeEvent(String judge) {
		if(!judge.equals("None")) {
			purpleFlareImage = new ImageIcon(Main.class.getResource("../images/purpleFlare.png")).getImage();
		}
		if(judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
		}
		else if(judge.equals("Bad")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeBad.png")).getImage();
		}
		else if(judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
		}
		else if(judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
		}
		else if(judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
		}	
	}
	
	public void scorePlus(String judge) {	
		if(judge.equals("Perfect"))
		{
			score += 777;
		}
		else if(judge.equals("Great"))
		{
			score += 555;
		}
		else if(judge.equals("Good"))
		{
			score += 333;
		}
		else if(judge.equals("Bad"))
		{
			score += 111;
		}
		strScore = Integer.toString(score);
		strScore = String.format("%5s", strScore).replace(' ', '0');
	}
	
	public void enterResult() {
		isGameScreen = false;
		close();
		isResultScreen = true;
		resultMusic.start();
		Scanner in = new Scanner(System.in);
		System.out.print("이름: ");
		uname = in.next();
		DB.insertData(uname, this.score);
	}
}
