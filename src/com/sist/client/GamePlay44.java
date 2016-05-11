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

public class GamePlay44 extends JPanel{
	JButton[] a = new JButton[11];
	JButton[] b = new JButton[11];
	JButton[] ct = new JButton[16];
	
	ImageIcon[] i = new ImageIcon[16];
	ImageIcon[] j = new ImageIcon[16];
	ImageIcon[] k = new ImageIcon[16];
	
	JButton btn1, btn2, btn3, btn4, btn5, btn6;
	JButton p2item1, p2item2, p2item3, p2item4;
	JTextPane pane;
	JTextField tf;
	JComboBox box;
	JButton ct0;
	Image img;
	
	String[] arrImg1 = new String[16];
	String[] arrImg2 = new String[16];
	String[] arrImg3 = new String[16];
	
	int[] numArr1 = new int[16];
	int[] numArr2 = new int[16];
	int[] numArr3 = new int[16];
	 
	Border thickBorder = new LineBorder(Color.RED, 5); // 버튼에 에메랄드색 굵기 표시
	Border thinBorder=new LineBorder(null);
	Border panelBorder = new LineBorder(Color.white, 3); 
	
	Timer timer;
	Timer itemTimer;
	Timer imageTimer;
	JScrollBar bar;
	
	char level;
	
	static int count = 0;
	
	String okSound = "resources/sounds/01.wav";
	String failSound = "resources/sounds/02.wav";
	String itemSound = "resources/sounds/itemSound.wav";
	
	ItemClass ic = new ItemClass();
	Check ch = new Check();
	Check ch1 = new Check();
	ItemCheck ich=new ItemCheck();
	ItemCheck2 ich2=new ItemCheck2();
	ItemCheck3 ich3=new ItemCheck3();
	ItemCheck4  ich4=new ItemCheck4();
	
	String nick = "";
	String avatar = "";
	
	JLabel p1id, p2id;
	ImageIcon cardBack, cardBack2;
	JTextField p1tf, p2tf;
	JLabel p1card, p2card;
	JPanel p1, p2;
	
	static int countBorder = 0;
	
	public void initPanel(String nick, String avatar) {	
		ImageIcon r = new ImageIcon(avatar);
		Image i = r.getImage();
		Image newimg = i.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);

		p2tf.setText(nick);
		p2card.setIcon(new ImageIcon(newimg));
	}
	
	public void initPanel1(String nick, String avatar) {	
		ImageIcon r = new ImageIcon(avatar);
		Image i = r.getImage();
		Image newimg = i.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);

		p1tf.setText(nick);
		p1card.setIcon(new ImageIcon(newimg));
	}
	
	public static void Sound(String file, boolean Loop){
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
		    	Image image = new ImageIcon("resources/images/"+level+".jpg").getImage();
				Image newimg = image.getScaledInstance(ct[0].getWidth(), ct[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
				ImageIcon cI = new ImageIcon(newimg);
				
		    	jb1.setIcon(cI);
				
				for(int n = 0; n < ct.length; n++){
					ct[n].setEnabled(true);
				}
				
				this.cancel();
				timer.cancel();
		    }
		};
		
		timer.schedule(timerTask, 1000, 1000);
	}
	
	public void setItemTimer(int a)throws Exception{
		itemTimer = new Timer();
		TimerTask itemTask = new TimerTask() {
			@Override
		    public void run() {
				System.out.println(a);
				switch(a){
					case 1:
						ich.setVisible(false);
						this.cancel();
						itemTimer.cancel();
						break;
					case 2:
						ich2.setVisible(false);
						this.cancel();
						itemTimer.cancel();
						break;
					case 3:
						ich3.setVisible(false);
						this.cancel();
						itemTimer.cancel();
						break;
					case 4:
						ich4.setVisible(false);
						this.cancel();
						itemTimer.cancel();
						break;
				}
			}
		};
		
		itemTimer.schedule(itemTask, 4500, 500);
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
		
		imageTimer.schedule(imageTask, 1500, 500);
	}
	
	public void setImageTimer1() throws Exception {
		imageTimer = new Timer();
		TimerTask imageTask = new TimerTask() {
			@Override
		    public void run() {
				ch1.setVisible(false);
				this.cancel();
				imageTimer.cancel();
			}
		};
		
		imageTimer.schedule(imageTask, 1500, 500);
	}
	
	public void setImageCard(char level){
		this.level = level;
		
		// 랜덤 인덱스 (내 카드)
		for(int i = 0; i < numArr1.length; i++){
			numArr1[i] = (int)(Math.random()*16);
		}
		
		// 랜덤 인덱스 (상대 카드)
		for(int i = 0; i < numArr2.length; i++){
			numArr2[i] = (int)(Math.random()*16);
		}
		
		// 랜덤 인덱스 (가운데 카드)
		boolean check3;
		int su3 = 0;
		for(int i = 0; i < numArr3.length; i++){
			check3 = true;
			while(check3){
				su3 = (int)(Math.random()*16);
				check3 = false;
				for(int j = 0; j < i; j++){
					if (numArr3[j] == su3){
						check3 = true;
						break;
					}
				}
			}
			numArr3[i] = su3;
		}
		
		img=Toolkit.getDefaultToolkit().getImage("resources/images/"+level+".png");
		
		// 내카드 str
		for(int i = 0; i < arrImg1.length; i++){
			arrImg1[i] = "resources/images/170/"+level+i+".png";
		}
		
		// 상대카드 str
		for(int i = 0; i < arrImg2.length; i++){
			arrImg2[i] = "resources/images/170/"+level+i+".png";
		}
		
		// 센터카드 str
		for(int i = 0; i < arrImg3.length; i++){
			arrImg3[i] = "resources/images/170/"+level+i+".png";
		}
		
		// 내카드 이미지 아이콘
		for(int n = 0; n < i.length; n++){
			i[n] = new ImageIcon(arrImg1[numArr1[n]]);
		}
		
		// 상대카드 이미지 아이콘
		for(int n = 0; n < j.length; n++){
			j[n] = new ImageIcon(arrImg2[numArr2[n]]);
		}
		
		// 센터카드 이미지 아이콘
		for(int n = 0; n < k.length; n++){		
			k[n] =  new ImageIcon(arrImg3[numArr3[n]]);
		}

		// 이미지 아이콘 변경
		for(int n = 0; n < a.length; n++){
			ImageIcon tmp = new ImageIcon("resources/images/"+level+".jpg");
			
			Image image = tmp.getImage();
			Image newimg = image.getScaledInstance(a[0].getWidth(), a[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
			ImageIcon cI = new ImageIcon(newimg);
			
			a[n].setIcon(cI);
		}
		
		for(int n = 0; n < b.length; n++){
			Image image = j[n].getImage();
			Image newimg = image.getScaledInstance(b[0].getWidth(), b[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
			ImageIcon cI = new ImageIcon(newimg);
			
			b[n].setIcon(cI);
		}
		
		for(int n = 0; n < ct.length; n++){
			ImageIcon tmp = new ImageIcon("resources/images/"+level+".jpg");
			
			Image image = tmp.getImage();
			Image newimg = image.getScaledInstance(ct[0].getWidth(), ct[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
			ImageIcon cI = new ImageIcon(newimg);
			
			ct[n].setIcon(cI);
		}
		
		// 보더 제거
		for(int i = 0; i < a.length; i++){
			a[i].setBorder(thinBorder);
		}
		
		for(int i = 0; i < b.length; i++){
			b[i].setBorder(thinBorder);
		}
		
		// 보더 생성
		//a[0].setBorder(thickBorder);
		a[0].setBorder(thickBorder);
		b[0].setBorder(thickBorder);
		// 아이템 이미지 리셋
		p2item1.setIcon(new ImageIcon("resources/images/test1.png"));
		p2item2.setIcon(new ImageIcon("resources/images/test2.png"));
		p2item3.setIcon(new ImageIcon("resources/images/test3.png"));
		p2item4.setIcon(new ImageIcon("resources/images/test4.png"));
		
	}
	
	public GamePlay44(){
		img=Toolkit.getDefaultToolkit().getImage("resources/images/back1.jpg");
		
		p1 = new JPanel();
		p1.setBorder(panelBorder);
		p1id = new JLabel("닉네임");
		p1id.setForeground(Color.white);
		
		cardBack = new ImageIcon("resources/images/userbackcard33.png");
		p1card = new JLabel(cardBack);
		
		JLabel p1item1 = new JLabel(new ImageIcon("resources/images/item1.png"));
		JLabel p1item2 = new JLabel(new ImageIcon("resources/images/item2.png"));
		JLabel p1item3 = new JLabel(new ImageIcon("resources/images/item3.png"));
		JLabel p1item4 = new JLabel(new ImageIcon("resources/images/item4.png"));
		
		p1tf = new JTextField();
		p1tf.setEnabled(false);
		
		p1.setBackground(Color.BLACK);
		
		p1.setLayout(null);
		p1id.setBounds(20,15,50,15);
		p1card.setBounds(100,10,120,140);
		p1tf.setBounds(15, 30, 90, 20);
		p1item1.setBounds(15, 55, 40, 40);
		p1item2.setBounds(60, 55, 40, 40);
		p1item3.setBounds(15, 100, 40, 40);
		p1item4.setBounds(60, 100, 40, 40);
		
		p1.add(p1id);
		p1.add(p1card);
		p1.add(p1tf);
		p1.add(p1item1);
		p1.add(p1item2);
		p1.add(p1item3);
		p1.add(p1item4);
		add(p1);
		
		p1.setBounds(850, 40, 210, 150);
	
//=====================================================
		//내 정보 보여주는곳
		p2 = new JPanel();
		p2.setBorder(panelBorder);
		p2id = new JLabel("닉네임");
		p2id.setForeground(Color.white);
		
		cardBack2 = new ImageIcon("resources/images/userbackcard33.png");
		p2card = new JLabel(cardBack2);
		p2tf = new JTextField();
		p2tf.setEnabled(false);
		
		p2item1 = new RoundButton("item1");
		p2item1.setIcon(new ImageIcon("resources/images/test1.png"));
		p2item2 = new RoundButton("item2");
		p2item2.setIcon(new ImageIcon("resources/images/test2.png"));
		p2item3 = new RoundButton("item3");
		p2item3.setIcon(new ImageIcon("resources/images/test3.png"));
		p2item4 = new RoundButton("item4");
		p2item4.setIcon(new ImageIcon("resources/images/test4.png"));
		
		p2.setBackground(Color.BLACK);
		
		p2.setLayout(null);
		p2id.setBounds(20,15,50,17);
		p2card.setBounds(100,10,120,140);
		p2tf.setBounds(15, 30, 90, 20);
		p2item1.setBounds(15, 55, 40, 40);
		p2item2.setBounds(60, 55, 40, 40);
		p2item3.setBounds(15, 100, 40, 40);
		p2item4.setBounds(60, 100, 40, 40);
		
		add(p2);
		p2.add(p2id);
		p2.add(p2card);
		p2.add(p2tf);
		p2.add(p2item1);
		p2.add(p2item2);
		p2.add(p2item3);
		p2.add(p2item4);
		
		p2.setBounds(850, 680, 210, 150);
		
//======================================================
		//채팅창 입력, 크기 조정, 버튼만들기
		
		pane=new JTextPane();
		pane.setEditable(false);
		JScrollPane js=new JScrollPane(pane);
		bar = js.getVerticalScrollBar();
		
		tf=new JTextField(); //채팅창 입력부분
		box=new JComboBox(); // 글씨색바꾸는부분
		box.addItem("검정");
		box.addItem("파랑");
		box.addItem("분홍");
		box.addItem("노랑");
		box.addItem("초록");
		
		btn1 = new JButton("게임 준비");
		btn1.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,15));
		btn3 = new JButton("게임 초대");
		btn3.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,15));
		btn2 = new JButton("쪽지보내기");
		btn2.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,15));
		btn4 = new JButton("나가기");
		btn4.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,15));
		btn5 = new JButton("게임 시작");
		btn5.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,15));
		btn6 = new JButton("아이템 설명");
		btn6.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,15));
		
		btn1.setEnabled(false);
		btn3.setEnabled(false);
		btn5.setEnabled(false);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2, 3 ,3));
		p.add(btn1);p.add(btn4);//p.add(btn2);p.add(btn3);
		p.setOpaque(false);
		
		add(js);
		add(tf);
		add(box);
		add(p);
		add(btn5);
		add(btn6);
		
		setLayout(null);
		
		js.setBounds(850,195,210,350);
		tf.setBounds(850, 550, 150, 25);
		box.setBounds(1005,550,55,25);
		p.setBounds(850,625,210,50);
		btn5.setBounds(850,580,210,40);
		btn6.setBounds(735,680,100,40);
		
//======================================================
		
		//상대방 카드 버튼으로 추가
		for(int n = 0; n < a.length; n++){
			a[n] = new JButton();
		}
		
		//내 카드 버튼으로 추가
		for(int n = 0; n < b.length; n++){
			b[n] = new JButton();
		}
		
		//센터카드 버튼 추가
		for(int n = 0; n < ct.length; n++){
			ct[n] = new JButton();
		}
		
		setLayout(null);
				
		//상대 카드 위치 조정
		int aX = 15;
		
		for(int n = 0; n < a.length; n++){
			a[n].setBounds(aX,40,70,100);
			aX = aX + 75;
		}

		//내카드 위치조정
		int bX = 15;
		
		for(int n = 0; n < b.length; n++){
			b[n].setBounds(bX,730,70,100);
			bX = bX + 75;
		}
		
		
		//센터 카드 위치조정
		ct[0].setBounds(100,170,130,130);
		ct[1].setBounds(255,170,130,130);
		ct[2].setBounds(410,170,130,130);
		ct[3].setBounds(565,170,130,130);
		
		ct[4].setBounds(100,305,130,130);
		ct[5].setBounds(255,305,130,130);
		ct[6].setBounds(410,305,130,130);
		ct[7].setBounds(565,305,130,130);
		
		ct[8].setBounds(100,440,130,130);
		ct[9].setBounds(255,440,130,130);
		ct[10].setBounds(410,440,130,130);
		ct[11].setBounds(565,440,130,130);
		
		ct[12].setBounds(100,575,130,130);
		ct[13].setBounds(255,575,130,130);
		ct[14].setBounds(410,575,130,130);
		ct[15].setBounds(565,575,130,130);
				
		for(int n = 0; n < a.length; n++){
			add(a[n]);
		}
		
		for(int n = 0; n < b.length; n++){
			add(b[n]);
		}
		
		for(int n = 0; n < ct.length; n++){
			add(ct[n]);
		}		
	}	
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);	
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
	
	public void append(String msg,String color, String nick){
		Document doc=pane.getDocument();
		try {
			doc.insertString(doc.getLength(), "["+nick+"] "+msg+"\n", pane.getStyle(color));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
