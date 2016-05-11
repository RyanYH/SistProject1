package com.sist.client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
//import javafx.scene.shape.MoveTo;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.sist.user.UserDTO;


public class ReadyBeforeGame55 extends JPanel{
	JButton a0,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15; //상대편카드 나열
	JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15;// 내카드 나열
	JButton ct0,ct1,ct2,ct3,ct4,ct5,ct6,ct7,ct8,ct9,ct10,ct11,ct12,ct13,ct14,ct15,ct16,ct17,ct18,ct19,ct20,ct21,ct22,ct23,ct24,ct25;
	JButton btn1, btn2, btn3, btn4,btn5, btn6;
	JButton p2item1, p2item2, p2item3, p2item4;
	
	boolean[] btnChk = new boolean[16];
	
	JTextPane pane;
	JTextField tf;
	JComboBox box;
	
	Timer timer = new Timer();
	Image img;
	
	Border panelBorder = new LineBorder(Color.white, 3); 
	
	String nick = "";
	String avatar = "";
	
	JLabel p1id, p2id;
	ImageIcon cardBack, cardBack2;
	JTextField p1tf, p2tf;
	JLabel p1card, p2card;
	JPanel p1, p2;
	JScrollBar bar;
	static String p1ava, p2ava;
	
	public void initPanel(String nick, String avatar) {	
		p2ava = avatar;
		
		ImageIcon r = new ImageIcon(avatar);
		Image i = r.getImage();
		Image newimg = i.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);

		p2tf.setText(nick);
		p2card.setIcon(new ImageIcon(newimg));
	}
	
	public void initPanel1(String nick, String avatar) {	
		p1ava = avatar;
		
		ImageIcon r = new ImageIcon(avatar);
		Image i = r.getImage();
		Image newimg = i.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);

		p1tf.setText(nick);
		p1card.setIcon(new ImageIcon(newimg));
	}
	
	public ReadyBeforeGame55(){
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
		// 카드 배열(앞)
		String[] arrImg = new String[25];
		for(int i = 0; i < arrImg.length; i++){
			arrImg[i] = "resources/images/centerbackcard33.png";
		}

		// 사용자 뒤
		String[] arrImg1 = new String[25];
		for(int i = 0; i < arrImg1.length; i++){
			arrImg1[i] = "resources/images/userbackcard33.png";
		}
				
		// 랜덤 인덱스 (내 카드)
		int[] numArr1 = new int[11];
		for(int i = 0; i < numArr1.length; i++){
			numArr1[i] = (int)(Math.random()*9);
		}

		// 랜덤 인덱스 (상대 카드)
		int[] numArr2 = new int[11];
		for(int i = 0; i < numArr2.length; i++){
			numArr2[i] = (int)(Math.random()*9);
		}
		
		// 랜덤 인덱스 (가운데 카드)
		int[] numArr3 = new int[25];
		boolean check3;
		int su3 = 0;
		
		for(int i = 0; i < numArr3.length; i++){
			check3 = true;
			while(check3){
				su3 = (int)(Math.random()*25);
				
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
		
		// 이미지 생성 (내카드)
		ImageIcon i0 = new ImageIcon(arrImg1[numArr1[0]]);
		ImageIcon i1 = new ImageIcon(arrImg1[numArr1[1]]);
		ImageIcon i2 = new ImageIcon(arrImg1[numArr1[2]]);
		ImageIcon i3 = new ImageIcon(arrImg1[numArr1[3]]);
		ImageIcon i4 = new ImageIcon(arrImg1[numArr1[4]]);
		ImageIcon i5 = new ImageIcon(arrImg1[numArr1[5]]);
		ImageIcon i6 = new ImageIcon(arrImg1[numArr1[6]]);
		ImageIcon i7 = new ImageIcon(arrImg1[numArr1[7]]);
		ImageIcon i8 = new ImageIcon(arrImg1[numArr1[8]]);
		ImageIcon i9 = new ImageIcon(arrImg1[numArr1[9]]);
		ImageIcon i10 = new ImageIcon(arrImg1[numArr1[10]]);
		
		// 이미지 생성 (상대카드)
		ImageIcon j0 = new ImageIcon(arrImg1[numArr2[0]]);
		ImageIcon j1 = new ImageIcon(arrImg1[numArr2[1]]);
		ImageIcon j2 = new ImageIcon(arrImg1[numArr2[2]]);
		ImageIcon j3 = new ImageIcon(arrImg1[numArr2[3]]);
		ImageIcon j4 = new ImageIcon(arrImg1[numArr2[4]]);
		ImageIcon j5 = new ImageIcon(arrImg1[numArr2[5]]);
		ImageIcon j6 = new ImageIcon(arrImg1[numArr2[6]]);
		ImageIcon j7 = new ImageIcon(arrImg1[numArr2[7]]);
		ImageIcon j8 = new ImageIcon(arrImg1[numArr2[8]]);
		ImageIcon j9 = new ImageIcon(arrImg1[numArr2[9]]);
		ImageIcon j10 = new ImageIcon(arrImg1[numArr2[10]]);
		
		// 이미지 생성 (가운데 카드)
		Image image = new ImageIcon("resources\\images\\centerbackcard33.png").getImage();
		Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
		
		ImageIcon k0 = new ImageIcon(newimg);
		ImageIcon k1 = new ImageIcon(newimg);
		ImageIcon k2 = new ImageIcon(newimg);
		ImageIcon k3 = new ImageIcon(newimg);
		ImageIcon k4 = new ImageIcon(newimg);
		ImageIcon k5 = new ImageIcon(newimg);
		ImageIcon k6 = new ImageIcon(newimg);
		ImageIcon k7 = new ImageIcon(newimg);
		ImageIcon k8 = new ImageIcon(newimg);
		ImageIcon k9 = new ImageIcon(newimg);
		ImageIcon k10 = new ImageIcon(newimg);
		ImageIcon k11 = new ImageIcon(newimg);
		ImageIcon k12 = new ImageIcon(newimg);
		ImageIcon k13 = new ImageIcon(newimg);
		ImageIcon k14 = new ImageIcon(newimg);
		ImageIcon k15 = new ImageIcon(newimg);
		ImageIcon k16 = new ImageIcon(newimg);
		ImageIcon k17 = new ImageIcon(newimg);
		ImageIcon k18 = new ImageIcon(newimg);
		ImageIcon k19 = new ImageIcon(newimg);
		ImageIcon k20 = new ImageIcon(newimg);
		ImageIcon k21 = new ImageIcon(newimg);
		ImageIcon k22 = new ImageIcon(newimg);
		ImageIcon k23 = new ImageIcon(newimg);
		ImageIcon k24 = new ImageIcon(newimg);
		
		setLayout(null);
		Border thickBorder = new LineBorder(Color.CYAN, 5); // 버튼에 빨간색 굵기 표시
		

		
//======================================================
		//상대방 카드 버튼으로 추가
		a0 = new JButton(j0);
		a1 = new JButton(j1);
		a2 = new JButton(j2);
		a3 = new JButton(j3);
		a4 = new JButton(j4);
		a5 = new JButton(j5);
		a6 = new JButton(j6);
		a7 = new JButton(j7);
		a8 = new JButton(j8);
		a9 = new JButton(j9);
		a10 = new JButton(j10);
		a0.setBorder(thickBorder); // 시작지점 빨간색
//======================================================
		//내 카드 버튼으로 추가
		b0= new JButton(i0);
		b1 = new JButton(i1);
		b2 = new JButton(i2);
		b3 = new JButton(i3);
		b4 = new JButton(i4);
		b5 = new JButton(i5);
		b6 = new JButton(i6);
		b7 = new JButton(i7);
		b8 = new JButton(i8);
		b9 = new JButton(i9);
		b10 = new JButton(i10);
		
//======================================================
		//중앙 9개 뿌려주기
		ct0 = new JButton(k0);
		ct1 = new JButton(k1);
		ct2 = new JButton(k2);
		ct3 = new JButton(k3);
		ct4 = new JButton(k4);
		ct5 = new JButton(k5);
		ct6 = new JButton(k6);
		ct7 = new JButton(k7);
		ct8 = new JButton(k8);
		ct9 = new JButton(k9);
		ct10 = new JButton(k10);
		ct11 = new JButton(k11);
		ct12 = new JButton(k12);
		ct13 = new JButton(k13);
		ct14 = new JButton(k14);
		ct15 = new JButton(k15);
		ct16 = new JButton(k16);
		ct17 = new JButton(k17);
		ct18 = new JButton(k18);
		ct19 = new JButton(k19);
		ct20 = new JButton(k20);
		ct21 = new JButton(k21);
		ct22 = new JButton(k22);
		ct23 = new JButton(k23);
		ct24 = new JButton(k24);
		//ct25 = new JButton(k25);
		
//======================================================
		//카드추가
		add(a10);
		add(a9);
		add(a8);
		add(a7);
		add(a6);
		add(a5);
		add(a4);
		add(a3);
		add(a2);
		add(a1);
		add(a0);
	
		add(b10);
		add(b9);
		add(b8);
		add(b7);
		add(b6);
		add(b5);
		add(b4);
		add(b3);
		add(b2);
		add(b1);
		add(b0);
		
		add(ct0);
		add(ct1);
		add(ct2);
		add(ct3);
		add(ct4);
		add(ct5);
		add(ct6);
		add(ct7);
		add(ct8);
		add(ct9);
		add(ct10);
		add(ct11);
		add(ct12);
		add(ct13);
		add(ct14);
		add(ct15);
		add(ct16);
		add(ct17);
		add(ct18);
		add(ct19);
		add(ct20);
		add(ct21);
		add(ct22);
		add(ct23);
		add(ct24);
		//add(ct25);
//======================================================
		//상대 카드 위치 조정
		a0.setBounds(15,40,70,100);
		a1.setBounds(90, 40, 70, 100);
		a2.setBounds(165,40,70,100);
		a3.setBounds(240,40,70,100);
		a4.setBounds(315,40,70,100);
		a5.setBounds(390,40,70,100);
		a6.setBounds(465,40,70,100);
		a7.setBounds(540,40,70,100);
		a8.setBounds(615,40,70,100);
		a9.setBounds(690,40,70,100);
		a10.setBounds(765,40,70,100);
		
//======================================================
		//내카드 위치조정
		b0.setBounds(15,730,70,100);
		b1.setBounds(90, 730, 70, 100);
		b2.setBounds(165,730,70,100);
		b3.setBounds(240,730,70,100);
		b4.setBounds(315,730,70,100);
		b5.setBounds(390,730,70,100);
		b6.setBounds(465,730,70,100);
		b7.setBounds(540,730,70,100);
		b8.setBounds(615,730,70,100);
		b9.setBounds(690,730,70,100);
		b10.setBounds(765,730,70,100);
//======================================================
		//센터 카드 위치조정
		ct0.setBounds(165,175,100,100);
		ct1.setBounds(270,175,100,100);
		ct2.setBounds(375,175,100,100);
		ct3.setBounds(480,175,100,100);
		ct4.setBounds(585,175,100,100);
		ct5.setBounds(165,280,100,100);
		ct6.setBounds(270,280,100,100);
		ct7.setBounds(375,280,100,100);
		ct8.setBounds(480,280,100,100);
		ct9.setBounds(585,280,100,100);
		ct10.setBounds(165,385,100,100);
		ct11.setBounds(270,385,100,100);
		ct12.setBounds(375,385,100,100);
		ct13.setBounds(480,385,100,100);
		ct14.setBounds(585,385,100,100);
		ct15.setBounds(165,490,100,100);
		ct16.setBounds(270,490,100,100);
		ct17.setBounds(375,490,100,100);
		ct18.setBounds(480,490,100,100);
		ct19.setBounds(585,490,100,100);
		ct20.setBounds(165,595,100,100);
		ct21.setBounds(270,595,100,100);
		ct22.setBounds(375,595,100,100);
		ct23.setBounds(480,595,100,100);
		ct24.setBounds(585,595,100,100);
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

		