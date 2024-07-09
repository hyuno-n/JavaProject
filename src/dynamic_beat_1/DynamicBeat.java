package dynamic_beat_1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/leftButtonBasic.png"));

	private ImageIcon easyButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/backButtonBasic.png"));
	private ImageIcon rankingButtonImage = new ImageIcon(
			Main.class.getResource("../images/rankingButton.png"));
	private ImageIcon rankingButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/rankingButtonEntered.png"));

	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	private JButton backIntroButton = new JButton(backButtonBasicImage);
	private JButton rankingButton = new JButton(rankingButtonImage);
	
	private int mouseX, mouseY;
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	private boolean isRankingScreen = false;
	
	private DBConnection DB = new DBConnection();
	private String[] Ranking;

	ArrayList<Track> trackList = new ArrayList<Track>();
	
	private Image titleImage; 
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	private Music rankingMusic = new Music("rankingMusic.mp3", true);
	private int nowSelected = 0;
	
	public static Game game;
	
	public DynamicBeat() {
		
		trackList.add(new Track("Blueming Title Image.png", "Blueming Start Image.png",
				"Blueming Game Image.jpg", "Blueming Selected.mp3", "Blueming.mp3", "Blueming - IU"));
		trackList.add(new Track("Eight Title Image.png", "Eight Start Image.png",
				"Blueming Game Image.jpg", "eight Selected.mp3", "eight.mp3", "Eight - IU"));
		trackList.add(new Track("Good Day Title Image.png", "Good Day Start Image.png",
				"Blueming Game Image.jpg", "Good Day Selected.mp3", "Good Day.mp3", "Good Day - IU"));

		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		addKeyListener(new KeyListener());
		
		introMusic.start();
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

		startButton.setBounds(40, 300, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterMain();
			}
		});
		add(startButton);

		quitButton.setBounds(40, 450, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectLeft();
			}
		});
		add(leftButton);
		
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectRight();
			}
		});
		add(rightButton);
		
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 210, 65);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);
		
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 210, 65);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);
		
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				backMain();
			}
		});
		add(backButton);
		
		backIntroButton.setVisible(false);
		backIntroButton.setBounds(20, 50, 60, 60);
		backIntroButton.setBorderPainted(false);
		backIntroButton.setContentAreaFilled(false);
		backIntroButton.setFocusPainted(false);
		backIntroButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backIntroButton.setIcon(backButtonEnteredImage);
				backIntroButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				backIntroButton.setIcon(backButtonBasicImage);
				backIntroButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				backIntro();
			}
		});
		add(backIntroButton);
		
		rankingButton.setVisible(false);
		rankingButton.setBounds(1200, 50, 60, 60);
		rankingButton.setBorderPainted(false);
		rankingButton.setContentAreaFilled(false);
		rankingButton.setFocusPainted(false);
		rankingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rankingButton.setIcon(rankingButtonEnteredImage);
				rankingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				rankingButton.setIcon(rankingButtonImage);
				rankingButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterRanking();
			}
		});
		add(rankingButton);
		
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); //
		if(isMainScreen)
		{
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 120, null);
		}
		if(isGameScreen)
		{	
			game.screenDraw(g);
		}
		if(isRankingScreen)
		{
			g.setColor(Color.DARK_GRAY);
			g.setFont(new Font("돋음체", Font.PLAIN, 50));
			g.drawString(Ranking[0],190, 200);
			g.drawString(Ranking[1],190, 300);
			g.drawString(Ranking[2],190, 400);
			g.drawString(Ranking[3],190, 500);
			g.drawString(Ranking[4],190, 600);
			g.drawString(Ranking[5],680, 200);
			g.drawString(Ranking[6],680, 300);
			g.drawString(Ranking[7],680, 400);
			g.drawString(Ranking[8],680, 500);
			g.drawString(Ranking[9],680, 600);
		}
		
		paintComponents(g); //add()
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	public void selectTrack(int nowSelected) {
		if(selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();	
	}
	
	public void selectLeft() {
		if(nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}
	
	public void selectRight() {
		if(nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		rankingButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);
	}
	
	public void backIntro() {
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		rankingButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
		startButton.setVisible(true);
		quitButton.setVisible(true);
		backIntroButton.setVisible(false);
		selectedMusic.close();
		introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
	}
	
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		hardButton.setVisible(true);
		easyButton.setVisible(true);
		rankingButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		if(isGameScreen) {
			isGameScreen = false;
			game.close();
		}
		if(isRankingScreen) {
			isRankingScreen = false;
			rankingMusic.close();
		}
	}
	
	public void enterMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		backIntroButton.setVisible(true);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		rankingButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}
	
	public void enterRanking() {
		selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		rankingButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/rankingBackground.png")).getImage();
		backButton.setVisible(true);
		Ranking = DB.selectData();
		isRankingScreen = true;
		rankingMusic.start();
	}
	
}
