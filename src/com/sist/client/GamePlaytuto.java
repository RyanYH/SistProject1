package com.sist.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.sist.user.UserDTO;

public class GamePlaytuto extends JPanel implements ActionListener {
	JButton[] b = new JButton[11];
	JButton[] ct = new JButton[16];

	ImageIcon[] j = new ImageIcon[16];
	ImageIcon[] k = new ImageIcon[16];

	JButton btn4, btn5;
	JButton p2item1, p2item2, p2item3, p2item4;
	JTextPane pane;
	JTextField tf;
	JComboBox box;
	JButton ct0;
	Image img;

	String[] arrImg1 = new String[16];
	String[] arrImg3 = new String[16];

	int[] numArr1 = new int[16];
	int[] numArr3 = new int[16];

	Border thickBorder = new LineBorder(Color.RED, 5); // 버튼에 에메랄드색 굵기 표시
	Border thinBorder = new LineBorder(null);
	Border panelBorder = new LineBorder(Color.white, 3);

	Timer timer;
	Timer itemTimer;
	Timer imageTimer;

	char level;
	static int count = 0;

	String okSound = "resources/sounds/ok.wav";
	String failSound = "resources/sounds/fail.wav";

	ItemClass ic = new ItemClass();
	Check ch = new Check();
	ItemCheck ich = new ItemCheck();

	String nick = "";
	String avatar = "";

	JLabel p2id;
	ImageIcon cardBack2;
	JTextField p2tf;
	JLabel p2card;
	JPanel p2;
	
	public void initPanel(String nick, String avatar) {	
		ImageIcon r = new ImageIcon(avatar);
		Image i = r.getImage();
		Image newimg = i.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);

		p2tf.setText(nick);
		p2card.setIcon(new ImageIcon(newimg));
	}
	
	public static void Sound(String file, boolean Loop) {
		Clip clip;

		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));

			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();

			if (Loop) {
				clip.loop(-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTimer(JButton jb1) throws Exception {
	      timer = new Timer();
	      TimerTask timerTask = new TimerTask() {
	         @Override
	         public void run() {
	            Image tmp = new ImageIcon("resources/images/" + level + ".jpg").getImage();
	            Image newimg = tmp.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);
	            ImageIcon ctI = new ImageIcon(newimg);
	            
	            jb1.setIcon(ctI);

	            for (int n = 0; n < ct.length; n++) {
	               ct[n].setEnabled(true);
	            }
	            this.cancel();
	            timer.cancel();
	         }
	      };
	      timer.schedule(timerTask, 1000, 1000);
	   }

	public void setItemTimer() throws Exception {
		itemTimer = new Timer();
		TimerTask itemTask = new TimerTask() {
			@Override
			public void run() {
				ich.setVisible(false);
				this.cancel();
				itemTimer.cancel();
			}
		};

		itemTimer.schedule(itemTask, 1000, 3500);
	}

	public void setImageTimer() throws Exception {
		imageTimer = new Timer();
		TimerTask imageTask = new TimerTask() {
			@Override
			public void run() {
				ch.setVisible(false);
				this.cancel();
				imageTimer.cancel();
			}
		};

		imageTimer.schedule(imageTask, 1000, 4000);
	}

	public void setImageCard(char level) {
		this.level = level;

		// 랜덤 인덱스 (내 카드)
		for (int i = 0; i < numArr1.length; i++) {
			numArr1[i] = (int) (Math.random() * 16);
		}

		// 랜덤 인덱스 (가운데 카드)
		boolean check3;
		int su3 = 0;
		for (int i = 0; i < numArr3.length; i++) {
			check3 = true;
			while (check3) {
				su3 = (int) (Math.random() * 16);
				check3 = false;
				for (int j = 0; j < i; j++) {
					if (numArr3[j] == su3) {
						check3 = true;
						break;
					}
				}
			}
			numArr3[i] = su3;
		}

		img = Toolkit.getDefaultToolkit().getImage("resources/images/" + level + ".png");

		// 내카드 str
		for (int i = 0; i < arrImg1.length; i++) {
			arrImg1[i] = "resources/images/170/" + level + i + ".png";
		}

		// 센터카드 str
		for (int i = 0; i < arrImg3.length; i++) {
			arrImg3[i] = "resources/images/170/" + level + i + ".png";
		}

		// 내카드 이미지 아이콘
		for (int n = 0; n < j.length; n++) {
			j[n] = new ImageIcon(arrImg1[numArr1[n]]);
		}

		// 센터카드 이미지 아이콘
		for (int n = 0; n < k.length; n++) {
			k[n] = new ImageIcon(arrImg3[numArr3[n]]);
		}

		// 액션리스너 추가
		for (int n = 0; n < ct.length; n++) {
			ct[n].addActionListener(this);
		}

		// 이미지 아이콘 변경

		for (int n = 0; n < b.length; n++) {
			Image image = j[n].getImage();
			Image newimg = image.getScaledInstance(b[0].getWidth(), b[0].getHeight(), java.awt.Image.SCALE_SMOOTH);
			ImageIcon cI = new ImageIcon(newimg);
			b[n].setIcon(cI);
		}

		for (int n = 0; n < ct.length; n++) {
			Image tmp = new ImageIcon("resources/images/" + level + ".jpg").getImage();
			Image newimg = tmp.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon ctI = new ImageIcon(newimg);
			
			ct[n].setIcon(ctI);
		}

		// 보더 제거
		for (int i = 0; i < b.length; i++) {
			b[i].setBorder(thinBorder);
		}

		// 보더 생성
		b[0].setBorder(thickBorder);
		p2item1.addActionListener(this);
		p2item2.addActionListener(this);
		p2item3.addActionListener(this);
		p2item4.addActionListener(this);
	}

	public GamePlaytuto() {
		img = Toolkit.getDefaultToolkit().getImage("resources/images/back1.jpg");

		// =====================================================
		// 튜토리얼 로고

		Image image = new ImageIcon("resources/images/tuto3.gif").getImage();
		ImageIcon tt = new ImageIcon(image);
		JLabel label = new JLabel("", tt, JLabel.CENTER);
		JPanel p0 = new JPanel(new BorderLayout());
		p0.add(label);
		add(p0);
		p0.setBounds(30, 15, 400, 100);

		// 내 정보 보여주는곳
		p2 = new JPanel();
		p2.setBorder(panelBorder);
		p2id = new JLabel("닉네임");
		p2id.setForeground(Color.white);

		cardBack2 = new ImageIcon("resources/images/userbackcard33.png");
		p2card = new JLabel(cardBack2);
		p2tf = new JTextField();
		p2tf.setEnabled(false);
		p2item1 = new RoundButton("item1");
		p2item1.setIcon(new ImageIcon("resources/images/test11.png"));
		p2item2 = new RoundButton("item2");
		p2item2.setIcon(new ImageIcon("resources/images/test22.png"));
		p2item3 = new RoundButton("item3");
		p2item3.setIcon(new ImageIcon("resources/images/test33.png"));
		p2item4 = new RoundButton("item4");
		p2item4.setIcon(new ImageIcon("resources/images/test44.png"));
		p2.setBackground(Color.BLACK);
		p2.setLayout(null);
		p2id.setBounds(20, 15, 50, 17);
		p2card.setBounds(100, 10, 120, 140);
		p2.add(p2id);
		p2tf.setBounds(15, 30, 90, 20);
		p2item1.setBounds(15, 55, 40, 40);
		p2item2.setBounds(60, 55, 40, 40);
		p2item3.setBounds(15, 100, 40, 40);
		p2item4.setBounds(60, 100, 40, 40);
		add(p2);
		p2.add(p2card);
		p2.add(p2tf);
		p2.add(p2item1);
		p2.add(p2item2);
		p2.add(p2item3);
		p2.add(p2item4);
		p2.setBounds(850, 680, 210, 150);


		// ======================================================
		// 채팅창 입력, 크기 조정, 버튼만들기

		pane = new JTextPane();
		pane.setEditable(false);
		JScrollPane js = new JScrollPane(pane);
		
		// 게임 시작 메세지
		initStyle();
		String color="red";
		append("연습모드가 시작 되었습니다", color, "System");
		append("연습모드 중 아이템 사용은 불가능 합니다", color, "System");	
		
		tf = new JTextField(); // 채팅창 입력부분
		tf.setEditable(false);
		tf.setText("사용할 수 없습니다.");
		box = new JComboBox(); // 글씨색바꾸는부분
		box.addItem("검정");
		box.addItem("파랑");
		box.addItem("분홍");
		box.addItem("노랑");
		box.addItem("초록");
		
		box.setEnabled(false);
		
		btn4 = new JButton("연습모드 나가기");
		btn5 = new JButton("다시 하기");				

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1, 3, 3));
		p.add(btn4);
		p.add(btn5);
		p.setOpaque(false);

		add(js);
		add(tf);
		add(box);
		add(p);

		setLayout(null);

		js.setBounds(850, 15, 210, 500);
		tf.setBounds(850, 520, 150, 25);
		box.setBounds(1005, 520, 55, 25);
		p.setBounds(850, 550, 210, 100);
		
		// 액션리스너 추가
		tf.addActionListener(this);
		// ======================================================

		// 내 카드 버튼으로 추가
		for (int n = 0; n < b.length; n++) {
			b[n] = new JButton();
		}

		// 센터카드 버튼 추가
		for (int n = 0; n < ct.length; n++) {
			ct[n] = new JButton();
		}

		setLayout(null);

		// 내카드 위치조정
		int bX = 15;

		for (int n = 0; n < b.length; n++) {
			b[n].setBounds(bX, 730, 70, 100);
			bX = bX + 75;
		}

		// 센터 카드 위치조정
		ct[0].setBounds(100, 170, 130, 130);
		ct[1].setBounds(255, 170, 130, 130);
		ct[2].setBounds(410, 170, 130, 130);
		ct[3].setBounds(565, 170, 130, 130);

		ct[4].setBounds(100, 305, 130, 130);
		ct[5].setBounds(255, 305, 130, 130);
		ct[6].setBounds(410, 305, 130, 130);
		ct[7].setBounds(565, 305, 130, 130);

		ct[8].setBounds(100, 440, 130, 130);
		ct[9].setBounds(255, 440, 130, 130);
		ct[10].setBounds(410, 440, 130, 130);
		ct[11].setBounds(565, 440, 130, 130);

		ct[12].setBounds(100, 575, 130, 130);
		ct[13].setBounds(255, 575, 130, 130);
		ct[14].setBounds(410, 575, 130, 130);
		ct[15].setBounds(565, 575, 130, 130);

		for (int n = 0; n < b.length; n++) {
			add(b[n]);
		}

		for (int n = 0; n < ct.length; n++) {
			add(ct[n]);
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int p = 0; p < ct.length; p++) {
			if (e.getSource() == ct[p]) {
				Image image = k[p].getImage();
				Image newimg = image.getScaledInstance(ct[0].getWidth(), ct[0].getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				ImageIcon cI = new ImageIcon(newimg);

				ct[p].setIcon(cI);
				for (int n = 0; n < b.length; n++) {
					if (b[n].getBorder() == thickBorder) {
						if (j[n].toString().equals(k[p].toString())) {
							if (n == 10) {
								ch.setVisible(true);
								try {
									setImageTimer();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								// 게임 종료 메세지
								initStyle();
								String color="red";
								append("연습모드가 종료 되었습니다", color, "System");
								append("연습모드가 다시 시작됩니다", color, "System");
								
								b[0].setBorder(thickBorder);
								b[10].setBorder(thinBorder);
								break;
							}

							b[n].setBorder(thinBorder);
							b[n + 1].setBorder(thickBorder);

							Sound(okSound, false);
							break;
						} else {
							if (count == 1) {
								count = 0;
								return;
							}

							// 턴넘기기
							Sound(failSound, false);
						}
					}
				}

				try {
					for (int n = 0; n < ct.length; n++) {
						if (n == p) {
							continue;
						} else {
							ct[n].setEnabled(false);
						}
					}

					setTimer(ct[p]);

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		}

		if (e.getSource() == tf) {
			String data = tf.getText();
			if (data.length() < 1) {
				return;
			}
			initStyle();
			String color = box.getSelectedItem().toString();
			append(data, color, nick);
			tf.setText("");
			tf.requestFocus();
		}
		repaint();
	}

	public void initStyle(){
		//default 색상
		Style def=StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		Style green=pane.addStyle("초록", def);
		StyleConstants.setForeground(green, Color.green);
		Style pink=pane.addStyle("분홍", def);
		StyleConstants.setForeground(pink, Color.pink);
		Style yellow=pane.addStyle("노랑", def);
		StyleConstants.setForeground(yellow, Color.yellow);
		Style blue=pane.addStyle("파랑", def);
		StyleConstants.setForeground(blue, Color.blue);
		Style red=pane.addStyle("red", def);
		StyleConstants.setForeground(red, Color.red);
	}

	public void append(String msg, String color, String id) {
		Document doc = pane.getDocument();
		try {
			doc.insertString(doc.getLength(), "[" + id + "] " + msg + "\n", pane.getStyle(color));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
