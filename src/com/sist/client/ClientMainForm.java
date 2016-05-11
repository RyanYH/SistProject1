package com.sist.client;

import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

import com.sist.common.Function;
import com.sist.server.Room;
import com.sist.server.Server.Client;
import com.sist.user.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

public class ClientMainForm  extends JFrame implements ActionListener, Runnable, MouseListener{
	int cate = 0;
	static String roomNum = "1";
	static boolean tCheck;
	
	Login login=new Login();
	WaitRoom wr=new WaitRoom();
	signup sg = new signup();
	Tutorial t = new Tutorial();
	Roomselect rs=new Roomselect();
	Itemtutorial it = new Itemtutorial();
	ReadyBeforeGame33 rb33= new ReadyBeforeGame33();
	ReadyBeforeGame44 rb44= new ReadyBeforeGame44();
	ReadyBeforeGame55 rb55= new ReadyBeforeGame55();
	GamePlay33 gp33 = new GamePlay33();
	GamePlay44 gp44 = new GamePlay44();
	GamePlay55 gp55 = new GamePlay55();
	Check ch = new Check();
	Orderchoice oc = new Orderchoice();
	Made made = new Made();
	ReadyBeforetuto rbtt = new ReadyBeforetuto();
	GamePlaytuto gptt = new GamePlaytuto();
	
	static CardLayout card = new CardLayout();
	
	static String BGMStr = "";
	
	static char level = 0;
	static boolean checkFirst;
	static boolean logCheck;
	static String id, pwd;
	
	static String getId = "";
	static String getUsernick = "";
	static String getUserava = "";
	static String getGender = "";
	
	static int[] numArr3;
	
	static String myRoomNum;
	
	Socket s;
	BufferedReader in;
	OutputStream out;
	boolean bTurn;
	
	static String myRoom;
	Timer imageTimer;
	static int i = 0;
	static Clip clip;
	
	public ClientMainForm(){
		setLocationRelativeTo(null);
		setLayout(card);
		
		add("LOG",login);
		add("WR",wr);
		add("SG", sg);
		add("RS", rs);
		add("T",t);
		add("RB33",rb33);
		add("RB44",rb44);
		add("RB55",rb55);
		add("GP33",gp33);
		add("GP44",gp44);
		add("GP55",gp55);
		add("RBTT", rbtt);
		add("GPTT", gptt);
		
		setTitle("같은 그림 찾기");
		setSize(800, 600);
		setVisible(true);
		
		setLocationRelativeTo(null); 

		login.b1.addActionListener(this); 
		login.b2.addActionListener(this);
		login.b3.addActionListener(this);
		login.pf.addActionListener(this);
		
		sg.b1.addActionListener(this);
		sg.b2.addActionListener(this);
		sg.b3.addActionListener(this);
		sg.b4.addActionListener(this);
		
		rs.rb1.addActionListener(this);
		rs.rb2.addActionListener(this);
		rs.rb3.addActionListener(this);
		rs.rb4.addActionListener(this);
		rs.rb5.addActionListener(this);
		rs.rb6.addActionListener(this);
		rs.rb7.addActionListener(this);
		rs.rb8.addActionListener(this);
		rs.rb9.addActionListener(this);
		rs.rb10.addActionListener(this);
		rs.rb11.addActionListener(this);
		
		rs.b1.addActionListener(this);
		rs.b2.addActionListener(this);
		
		wr.b1.addActionListener(this);
		wr.b2.addActionListener(this);
		wr.b3.addActionListener(this);
		wr.b4.addActionListener(this);
		wr.b5.addActionListener(this);
		wr.b6.addActionListener(this);
		wr.b7.addActionListener(this);
		wr.tf.addActionListener(this);
		
		t.b1.addActionListener(this);
		
		gp33.tf.addActionListener(this);
		gp44.tf.addActionListener(this);
		gp55.tf.addActionListener(this);
		
		rb33.btn4.addActionListener(this);
		rb44.btn4.addActionListener(this);
		rb55.btn4.addActionListener(this);
		
		rb33.btn1.addActionListener(this);
		rb44.btn1.addActionListener(this);
		rb55.btn1.addActionListener(this);
		
		rb33.btn5.addActionListener(this);
		rb44.btn5.addActionListener(this);
		rb55.btn5.addActionListener(this);
		
		gp33.btn4.addActionListener(this);
		gp44.btn4.addActionListener(this);
		gp55.btn4.addActionListener(this);
		
		it.b1.addActionListener(this);
		
		rb33.btn6.addActionListener(this);
		rb44.btn6.addActionListener(this);
		rb55.btn6.addActionListener(this);
		
		gp33.btn6.addActionListener(this);
		gp44.btn6.addActionListener(this);
		gp55.btn6.addActionListener(this);
		
		gptt.btn4.addActionListener(this);
		gptt.btn5.addActionListener(this);
		
		rbtt.btn5.addActionListener(this);
		rbtt.btn6.addActionListener(this);
		
		made.b1.addActionListener(this);
		
		wr.table1.addMouseListener(this);
		
		rb33.tf.addActionListener(this);
		rb44.tf.addActionListener(this);
		rb55.tf.addActionListener(this);
		
		gp33.p2item1.addActionListener(this);
		gp33.p2item2.addActionListener(this);
		gp33.p2item3.addActionListener(this);
		gp33.p2item4.addActionListener(this);
		
		gp44.p2item1.addActionListener(this);
		gp44.p2item2.addActionListener(this);
		gp44.p2item3.addActionListener(this);
		gp44.p2item4.addActionListener(this);
		
		gp55.p2item1.addActionListener(this);
		gp55.p2item2.addActionListener(this);
		gp55.p2item3.addActionListener(this);
		gp55.p2item4.addActionListener(this);
		
		// 액션리스너 추가
		for(int n = 0; n < gp33.ct.length; n++){
			gp33.ct[n].addActionListener(this);
		}
			
		// 액션리스너 추가
		for(int n = 0; n < gp44.ct.length; n++){
			gp44.ct[n].addActionListener(this);
		}
		
		// 액션리스너 추가
		for(int n = 0; n < gp55.ct.length; n++){
			gp55.ct[n].addActionListener(this);
		}
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
	}
	
	public static void Sound(String file, boolean Loop){
	    try {
	      	AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));

	      	clip = AudioSystem.getClip();
	      	clip.open(ais);
	      	
	      	FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	      	gainControl.setValue(-10.0f);
	      		
	      	clip.start();

	      	if (Loop) {
	      		clip.loop(-1);
	      	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		try
		{
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
			
		new ClientMainForm();
		BGMStr = "resources/sounds/test1.wav";
		Sound(BGMStr, true);
	}
	
	public void setImageTimer() throws Exception {
		imageTimer = new Timer();
		TimerTask imageTask = new TimerTask() {
			@Override
		    public void run() {
				oc.setVisible(false);
				this.cancel();
				imageTimer.cancel();
			}
		};
		
		imageTimer.schedule(imageTask, 5000, 100);
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				String msg = in.readLine();		
				StringTokenizer st = new StringTokenizer(msg, "|");
				System.out.println("client : " + msg);
				
				int protocol = Integer.parseInt(st.nextToken());			
				String id = st.nextToken();
				
				switch(protocol){
				case Function.CLICKCENTER : {
					String card = st.nextToken();
					int p = Integer.parseInt(st.nextToken());
					
					ImageIcon temp = new ImageIcon(card);
					if (rs.rb3.isSelected()){
						Image image = temp.getImage();
						Image newimg = image.getScaledInstance(gp33.ct[0].getWidth(), gp33.ct[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
						ImageIcon cI = new ImageIcon(newimg);
						
						gp33.ct[p].setIcon(cI);
						gp33.setTimer(gp33.ct[p]);
					} else if (rs.rb4.isSelected()){
						Image image = temp.getImage();
						Image newimg = image.getScaledInstance(gp44.ct[0].getWidth(), gp44.ct[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
						ImageIcon cI = new ImageIcon(newimg);
						
						gp44.ct[p].setIcon(cI);
						gp44.setTimer(gp44.ct[p]);
					} else if (rs.rb5.isSelected()){
						Image image = temp.getImage();
						Image newimg = image.getScaledInstance(gp55.ct[0].getWidth(), gp55.ct[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
						ImageIcon cI = new ImageIcon(newimg);
						
						gp55.ct[p].setIcon(cI);
						gp55.setTimer(gp55.ct[p]);
					}
				}
				break;
				
				case Function.ENDGAME : {
					String mid = st.nextToken();
					String yid = st.nextToken();
					
					JOptionPane.showMessageDialog(this, "게임이 종료되어 대기실로 이동합니다");
					setSize(1000,750);
					setLocationRelativeTo(null);
					card.show(getContentPane(), "WR");
					wr.model1.removeRow(0);
					this.setEnabled(true);
					this.setVisible(true);
					clip.close();
					clip.open();
					BGMStr = "resources/sounds/test1.wav";
					Sound(BGMStr, true);
					
					

				}
				break;
				
				case Function.MOVEBORDER : {
					int n = Integer.parseInt(st.nextToken());
					
					if (rs.rb3.isSelected()){
						gp33.b[n].setBorder(gp33.thinBorder);
						gp33.b[n+1].setBorder(gp33.thickBorder);
					} else if (rs.rb4.isSelected()){
						gp44.b[n].setBorder(gp44.thinBorder);
						gp44.b[n+1].setBorder(gp44.thickBorder);
					} else if (rs.rb5.isSelected()){
						gp55.b[n].setBorder(gp55.thinBorder);
						gp55.b[n+1].setBorder(gp55.thickBorder);
					}
				}
				break;
				
				case Function.MYMOVEBORDER : {
					int n = Integer.parseInt(st.nextToken());
					
					if (rs.rb3.isSelected()){
						gp33.a[n].setBorder(gp33.thinBorder);
						gp33.a[n+1].setBorder(gp33.thickBorder);
					} else if (rs.rb4.isSelected()){
						gp44.a[n].setBorder(gp44.thinBorder);
						gp44.a[n+1].setBorder(gp44.thickBorder);
					} else if (rs.rb5.isSelected()){
						gp55.a[n].setBorder(gp55.thinBorder);
						gp55.a[n+1].setBorder(gp55.thickBorder);
					}
				}
				break;
				
				case Function.GAMEITEM1 : {
					if (rs.rb3.isSelected()){
						gp33.Sound(gp33.itemSound, false);
						gp33.p2item1.setIcon(new ImageIcon("resources/images/test11.png"));
						gp33.ich4.setVisible(true);
						try {
							gp33.setItemTimer(4);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp33.p2item1.removeActionListener(this);
						
						for(int i = 0; i < gp33.b.length; i++){
							ImageIcon tmp = new ImageIcon("resources/images/"+level+".jpg");
							
							Image image = tmp.getImage();
							Image newimg = image.getScaledInstance(gp33.a[0].getWidth(), gp33.a[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
							ImageIcon cI = new ImageIcon(newimg);
							
							gp33.a[i].setIcon(cI);
						}
					} else if (rs.rb4.isSelected()){
						gp44.Sound(gp44.itemSound, false);
						gp44.p2item1.setIcon(new ImageIcon("resources/images/test11.png"));
						gp44.ich4.setVisible(true);
						try {
							gp44.setItemTimer(4);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp44.p2item1.removeActionListener(this);
						
						for(int i = 0; i < gp44.b.length; i++){
							ImageIcon tmp = new ImageIcon("resources/images/"+level+".jpg");
							
							Image image = tmp.getImage();
							Image newimg = image.getScaledInstance(gp44.a[0].getWidth(), gp44.a[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
							ImageIcon cI = new ImageIcon(newimg);
							
							gp44.a[i].setIcon(cI);
						}
					} else if (rs.rb5.isSelected()){
						gp55.Sound(gp55.itemSound, false);
						gp55.p2item1.setIcon(new ImageIcon("resources/images/test11.png"));
						gp55.ich4.setVisible(true);
						try {
							gp55.setItemTimer(4);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp55.p2item1.removeActionListener(this);
						
						for(int i = 0; i < gp55.b.length; i++){
							ImageIcon tmp = new ImageIcon("resources/images/"+level+".jpg");
							
							Image image = tmp.getImage();
							Image newimg = image.getScaledInstance(gp55.a[0].getWidth(), gp55.a[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
							ImageIcon cI = new ImageIcon(newimg);
							
							gp55.a[i].setIcon(cI);
						}
					}
				}
				break;
				
				case Function.MYGAMEITEM1 : {
					if (rs.rb3.isSelected()){
						gp33.Sound(gp33.itemSound, false);
						ImageIcon[] r = gp33.ic.setItem1(gp33.numArr2, gp33.arrImg2, gp33.j, gp33.b, gp33.level, 9);
						gp33.j = r;
						gp33.ich4.setVisible(true);
						try {
							gp33.setItemTimer(4);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						for(int i = 0; i < gp33.b.length; i++){
							ImageIcon tmp = new ImageIcon("resources/images/"+level+".jpg");
							
							Image image = tmp.getImage();
							Image newimg = image.getScaledInstance(gp33.a[0].getWidth(), gp33.a[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
							ImageIcon cI = new ImageIcon(newimg);
							
							gp33.a[i].setIcon(cI);
						}
					} else if (rs.rb4.isSelected()){
						gp44.Sound(gp44.itemSound, false);
						ImageIcon[] r = gp44.ic.setItem1(gp44.numArr2, gp44.arrImg2, gp44.j, gp44.b, gp44.level, 16);
						gp44.j = r;
						gp44.ich4.setVisible(true);
						try {
							gp44.setItemTimer(4);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						for(int i = 0; i < gp44.b.length; i++){
							ImageIcon tmp = new ImageIcon("resources/images/"+level+".jpg");
							
							Image image = tmp.getImage();
							Image newimg = image.getScaledInstance(gp44.a[0].getWidth(), gp44.a[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
							ImageIcon cI = new ImageIcon(newimg);
							
							gp44.a[i].setIcon(cI);
						}
					} else if (rs.rb5.isSelected()){
						gp55.Sound(gp55.itemSound, false);
						ImageIcon[] r = gp55.ic.setItem1(gp55.numArr2, gp55.arrImg2, gp55.j, gp55.b, gp55.level, 25);
						gp55.j = r;
						gp55.ich4.setVisible(true);
						try {
							gp55.setItemTimer(4);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						for(int i = 0; i < gp55.b.length; i++){
							ImageIcon tmp = new ImageIcon("resources/images/"+level+".jpg");
							
							Image image = tmp.getImage();
							Image newimg = image.getScaledInstance(gp55.a[0].getWidth(), gp55.a[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
							ImageIcon cI = new ImageIcon(newimg);
							
							gp55.a[i].setIcon(cI);
						}
					}
				}
				break;
				
				case Function.GAMEITEM2 : {
					if (rs.rb3.isSelected()) {
						gp33.Sound(gp33.itemSound, false);
						gp33.ich3.setVisible(true);
						gp33.setItemTimer(3);
					} else if (rs.rb4.isSelected()) {
						gp44.Sound(gp44.itemSound, false);
						gp44.ich3.setVisible(true);
						gp44.setItemTimer(3);
					} else if (rs.rb5.isSelected()) {
						gp55.Sound(gp55.itemSound, false);
						gp55.ich3.setVisible(true);
						gp55.setItemTimer(3);
					}
				}
				break;
				
				case Function.MYGAMEITEM2 : {
					if (rs.rb3.isSelected()) {
						gp33.Sound(gp33.itemSound, false);
						gp33.count = 1;
						gp33.p2item2.setIcon(new ImageIcon("resources/images/test22.png"));
						gp33.ich3.setVisible(true);
						try {
							gp33.setItemTimer(3);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp33.p2item2.removeActionListener(this);
					} else if (rs.rb4.isSelected()){
						gp44.Sound(gp44.itemSound, false);
						gp44.count = 1;
						gp44.p2item2.setIcon(new ImageIcon("resources/images/test22.png"));
						gp44.ich3.setVisible(true);
						try {
							gp44.setItemTimer(3);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp44.p2item2.removeActionListener(this);
					} else if (rs.rb5.isSelected()){
						gp44.Sound(gp44.itemSound, false);
						gp44.count = 1;
						gp44.p2item2.setIcon(new ImageIcon("resources/images/test22.png"));
						gp44.ich3.setVisible(true);
						try {
							gp44.setItemTimer(3);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp44.p2item2.removeActionListener(this);
					}
				}
				break;
				
				case Function.GAMEITEM3 : {
					if (rs.rb3.isSelected()){
						gp33.Sound(gp33.itemSound, false);
						for (int i=0; i<gp33.b.length; i++) {
							gp33.b[i].setBorder(gp33.thinBorder);
						}
						gp33.b[0].setBorder(gp33.thickBorder);
						
						gp33.countBorder = 0;
						
						gp33.ich2.setVisible(true);
						try {
							gp33.setItemTimer(2);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					
					} else if (rs.rb4.isSelected()){
						gp44.Sound(gp44.itemSound, false);
						for (int i=0; i<gp44.b.length; i++) {
							gp44.b[i].setBorder(gp44.thinBorder);
						}
						gp44.b[0].setBorder(gp44.thickBorder);
						
						gp44.countBorder = 0;
						gp44.ich2.setVisible(true);
						try {
							gp44.setItemTimer(2);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else if (rs.rb5.isSelected()){
						gp55.Sound(gp55.itemSound, false);
						for (int i=0; i<gp55.b.length; i++) {
							gp55.b[i].setBorder(gp55.thinBorder);
						}
						gp55.b[0].setBorder(gp55.thickBorder);
						
						gp55.countBorder = 0;
						gp55.ich2.setVisible(true);
						try {
							gp55.setItemTimer(2);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				break;
				
				case Function.MYGAMEITEM3 : {
					if (rs.rb3.isSelected()){
						gp33.Sound(gp33.itemSound, false);
						for (int i=0; i<gp33.a.length; i++) {
							gp33.a[i].setBorder(gp33.thinBorder);
						}
						gp33.a[0].setBorder(gp33.thickBorder);
						
						gp33.p2item3.setIcon(new ImageIcon("resources/images/test33.png"));
						gp33.ich2.setVisible(true);
						try {
							gp33.setItemTimer(2);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp33.p2item3.removeActionListener(this);
					} else if (rs.rb4.isSelected()){
						gp44.Sound(gp44.itemSound, false);
						for (int i=0; i<gp44.a.length; i++) {
							gp44.a[i].setBorder(gp44.thinBorder);
						}
						gp44.a[0].setBorder(gp44.thickBorder);
						
						gp44.p2item3.setIcon(new ImageIcon("resources/images/test33.png"));
						gp44.ich2.setVisible(true);
						try {
							gp44.setItemTimer(2);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp44.p2item3.removeActionListener(this);
					} else if (rs.rb5.isSelected()){
						gp55.Sound(gp55.itemSound, false);
						for (int i=0; i<gp55.a.length; i++) {
							gp55.a[i].setBorder(gp55.thinBorder);
						}
						gp55.a[0].setBorder(gp55.thickBorder);
						
						gp55.p2item3.setIcon(new ImageIcon("resources/images/test33.png"));
						gp55.ich2.setVisible(true);
						try {
							gp55.setItemTimer(2);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp55.p2item3.removeActionListener(this);
					}
				}
				break;
				
				case Function.GAMEITEM4 : {	
					String rand = st.nextToken();
					
					if (rs.rb3.isSelected()) {
						gp33.Sound(gp33.itemSound, false);
						gp33.setCenter(rand);
						gp33.p2item4.setIcon(new ImageIcon("resources/images/test44.png"));
						gp33.ich.setVisible(true);
						try {
							gp33.setItemTimer(1);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp33.p2item4.removeActionListener(this);
					} else if (rs.rb4.isSelected()) {
						gp44.Sound(gp44.itemSound, false);
						
						gp44.p2item4.setIcon(new ImageIcon("resources/images/test44.png"));
						gp44.ich.setVisible(true);
						try {
							gp44.setItemTimer(1);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp44.p2item4.removeActionListener(this);
					} else if (rs.rb5.isSelected()) {
						gp55.Sound(gp55.itemSound, false);

						gp55.p2item4.setIcon(new ImageIcon("resources/images/test44.png"));
						gp55.ich.setVisible(true);
						try {
							gp55.setItemTimer(1);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						gp55.p2item4.removeActionListener(this);
					}
				}
				break;
				
				case Function.MYGAMEITEM4 : {
					String rand = st.nextToken();
					
					if (rs.rb3.isSelected()) {
						gp33.Sound(gp33.itemSound, false);
						gp33.setCenter(rand);
						gp33.ich.setVisible(true);
						try {
							gp33.setItemTimer(1);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						//gp33.p2item4.removeActionListener(this);
					} else if (rs.rb4.isSelected()) {
						gp44.Sound(gp44.itemSound, false);


						gp44.ich.setVisible(true);
						try {
							gp44.setItemTimer(1);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						//gp44.p2item4.removeActionListener(this);
					} else if (rs.rb5.isSelected()) {
						gp55.Sound(gp55.itemSound, false);


						gp55.ich.setVisible(true);
						try {
							gp55.setItemTimer(1);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						//gp55.p2item4.removeActionListener(this);
					}
				}
				break;
				
				case Function.SETCENTERCARD : {
					String rand = st.nextToken();
					
					StringTokenizer st1 = new StringTokenizer(rand, "/");
					
					if (rs.rb3.isSelected()){
						for(int i = 0; i < 9; i++){
							gp33.numArr3[i] = Integer.parseInt(st1.nextToken());
						}
					} else if (rs.rb4.isSelected()){
						for(int i = 0; i < 9; i++){
							gp44.numArr3[i] = Integer.parseInt(st1.nextToken());
						}
					} else if (rs.rb5.isSelected()){
						for(int i = 0; i < 9; i++){
							gp55.numArr3[i] = Integer.parseInt(st1.nextToken());
						}
					}
					
					
				}
				break;
				
				case Function.GAMEREADY : {
					String rj = st.nextToken();
					String rTitle = getTitle();
					StringTokenizer st1 = new StringTokenizer(rTitle, "/");
					String temp = st1.nextToken();
					
					if (rj.equals(temp)){
						rb33.btn5.setEnabled(true);
					} else {
						rb33.btn5.setEnabled(false);
					}
					rb33.btn1.setEnabled(false);
				}
				break;
				
				case Function.ORDERCHOICE : {
					String aa = st.nextToken();
					level = st.nextToken().charAt(0);
					
					if (rs.rb3.isSelected()){
						oc.initPanel(getUsernick, getUserava);
						oc.initPanel1(rb33.p1tf.getText(), rb33.p1ava);	
						gp33.setImageCard(level);
						clip.stop();
						BGMStr = "resources/sounds/"+level+".wav";
						Sound(BGMStr, true);
						
					} else if (rs.rb4.isSelected()){
						oc.initPanel(getUsernick, getUserava);
						oc.initPanel1(rb44.p1tf.getText(), rb44.p1ava);
						gp44.setImageCard(level);
					} else if (rs.rb5.isSelected()){
						oc.initPanel(getUsernick, getUserava);
						oc.initPanel1(rb55.p1tf.getText(), rb55.p1ava);
						gp55.setImageCard(level);
					}
			         
					oc.setVisible(true);
					
					try {
						setImageTimer();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
			        card.show(getContentPane(), aa);
			        
				}
				break;
				
				case Function.LOGIN : 
					String data = st.nextToken();
					if (data.equalsIgnoreCase("true")){
						JOptionPane.showMessageDialog(null, id+" 님 환영합니다");
						
						setTitle(id + "/ 같은 그림 찾기");
						setSize(1000,750);
						setLocationRelativeTo(null);
						
						card.show(getContentPane(), "WR");
					} else {
						JOptionPane.showMessageDialog(null, "로그인을 다시 시도해주세요");
					}
					break;
					
				case Function.WAITCHAT :{
					String chat = st.nextToken();
					String color = st.nextToken();
					
					wr.bar.setValue(wr.bar.getMaximum());
					wr.initStyle();
					wr.append(chat,color, id);
				}
					break;
					
				case Function.LOGOUT :{
					setSize(800,600);
	        		setLocationRelativeTo(null);
	        		login.tf.setText("");
	        		login.pf.setText("");
	        		wr.pane.setText("");
	        		
	        		for(int i = wr.model1.getRowCount()-1; i >= 0; i--){
	        			wr.model1.removeRow(i);
	        		}
	        		
					card.show(getContentPane(), "LOG");
				}
					break;
					
				case Function.SIGNUP :{
					JOptionPane.showMessageDialog(this, id + " 님 가입을 환영합니다");
					card.show(getContentPane(), "LOG");
				}
					break;	
				
				case Function.CHECKID :{
					String checkId = st.nextToken();
					if (checkId.equalsIgnoreCase("true")) {
						JOptionPane.showMessageDialog(this, "아이디가 사용중 입니다");
						sg.checkId = false;
					} else {
						JOptionPane.showMessageDialog(this, "사용가능한 아이디 입니다");
						sg.checkId = true;
					}
				}
					break;
					
				case Function.CHECKNICK :{
					String checkNick = st.nextToken();
					
					if (checkNick.equalsIgnoreCase("true")) {
						JOptionPane.showMessageDialog(this, "닉네임이 사용중 입니다");
						sg.checkNick = false;
					} else {
						JOptionPane.showMessageDialog(this, "사용가능한 닉네임 입니다");
						sg.checkNick = true;
					}
				}
					break;
					
				case Function.GETUSER :{
					getId = id;
					getUsernick = st.nextToken();
					getGender = st.nextToken();
					getUserava = st.nextToken();
				}
					break;
				
				case Function.MAKEROOM : {
					myRoomNum = id;
					String roombang = st.nextToken();
					String[] Rdata = {id, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()};
					wr.model1.addRow(Rdata);
					rs.removeJDialog();
					//wr.table1.repaint();
				}
				break;
				
				case Function.GAMEINIT : {
					rb33.btn1.setEnabled(true);
				}
				break;
				
				case Function.MYROOMIN : {		
					setSize(1100, 900);
					setLocationRelativeTo(null);
					
					String cId = st.nextToken();
					String nick = st.nextToken();
					String gender = st.nextToken();
					String avatar = st.nextToken();
					String roombang = st.nextToken();
					
					if (rs.rb3.isSelected()){
						rb33.initPanel(getUsernick, getUserava);
						card.show(getContentPane(), "RB33");
					} else if (rs.rb4.isSelected()){
						rb44.initPanel(getUsernick, getUserava);
						card.show(getContentPane(), "RB44");
					} else if (rs.rb5.isSelected()){
						rb55.initPanel(getUsernick, getUserava);
						card.show(getContentPane(), "RB55");
					}
					wr.table1.repaint();
					rs.setVisible(false);
				}
				break;
				
				case Function.ROOMIN : {
					setSize(1100, 900);
					setLocationRelativeTo(null);
					
					String cId = st.nextToken();
					String nick = st.nextToken();
					String gender = st.nextToken();
					String avatar = st.nextToken();
					String roombang = st.nextToken();
				
					int row = wr.table1.getSelectedRow();
					
					if (row == 0){
						String val = wr.model1.getValueAt(row, 2).toString();
						
						if (val.equals("3x3")) {
							rs.rb3.setSelected(true);
						} else if (val.equals("4x4")) {
							rs.rb3.setSelected(true);
						} else if (val.equals("4x4")) {
							rs.rb3.setSelected(true);
						}
					}
					
					if (rs.rb3.isSelected()){
						rb33.initPanel(getUsernick, getUserava);
						rb33.initPanel1(nick, avatar);
						gp33.initPanel(getUsernick, getUserava);
						gp33.initPanel1(nick, avatar);
						card.show(getContentPane(), "RB33");
					} else if (rs.rb4.isSelected()){
						rb44.initPanel(getUsernick, getUserava);
						rb44.initPanel1(nick, avatar);
						gp44.initPanel(getUsernick, getUserava);
						gp44.initPanel1(nick, avatar);
						card.show(getContentPane(), "RB44");
					} else if (rs.rb5.isSelected()){
						rb55.initPanel(getUsernick, getUserava);
						rb55.initPanel1(nick, avatar);
						gp55.initPanel(getUsernick, getUserava);
						gp55.initPanel1(nick, avatar);
						card.show(getContentPane(), "RB55");
					}
					wr.table1.repaint();
					rs.setVisible(false);
				}
				break;
				
				case Function.GAMETURN:{
					
					String m = st.nextToken();
					
					if(m.equals("true")){
						bTurn = true;
						this.setEnabled(true);
					}else{
						bTurn = false;
						this.setEnabled(false);
					}
				}
				break;
				
				case Function.ROOMCHAT : {
					String nick = st.nextToken();
					String roomChat = st.nextToken();
					String roomColor = st.nextToken();
					
					if (rs.rb3.isSelected()){
						rb33.initStyle();
						rb33.append(roomChat,roomColor, nick);
						rb33.bar.setValue(wr.bar.getMaximum());
						rb33.tf.setText("");
					} else if (rs.rb4.isSelected()){
						rb44.initStyle();
						rb44.append(roomChat,roomColor, nick);
						rb44.bar.setValue(wr.bar.getMaximum());
						rb44.tf.setText("");
					} else if (rs.rb5.isSelected()){
						rb55.initStyle();
						rb55.append(roomChat,roomColor, nick);
						rb55.bar.setValue(wr.bar.getMaximum());
						rb55.tf.setText("");
					}
				}
				break;
				case Function.GAMECHAT : {
					String nick = st.nextToken();
					String roomChat = st.nextToken();
					String roomColor = st.nextToken();
					
					if (rs.rb3.isSelected()){
						gp33.initStyle();
						gp33.append(roomChat,roomColor, nick);
						gp33.bar.setValue(gp33.bar.getMaximum());
						gp33.tf.setText("");
					} else if (rs.rb4.isSelected()){
						gp44.initStyle();
						gp44.append(roomChat,roomColor, nick);
						gp44.bar.setValue(gp44.bar.getMaximum());
						gp44.tf.setText("");
					} else if (rs.rb5.isSelected()){
						gp55.initStyle();
						gp55.append(roomChat,roomColor, nick);
						gp55.bar.setValue(gp55.bar.getMaximum());
						gp55.tf.setText("");
					}
				}
				break;
				
				case Function.MYROOMOUT : {
					
					setSize(1000,750);
					setLocationRelativeTo(null);

					card.show(getContentPane(), "WR");
				}
				break;
				
				case Function.ROOMOUT : {
					//wr.table1.repaint();
					
					rb33.p1tf.setText("");
					rb33.p1card.setIcon(new ImageIcon("resources/images/userbackcard33.png"));
				}
				break;
				
				case Function.WAITUPDATE : {
					String roomNum = st.nextToken();
					String inwon = st.nextToken();
					
					for(int i = 0; i < wr.model1.getRowCount(); i++){
						String temp = wr.model1.getValueAt(i, 0).toString();
						
						if (roomNum.equals(temp)){
							if (Integer.parseInt(inwon) < 1){
								wr.model1.removeRow(i);
							} else {
								wr.model1.setValueAt(inwon, i, 4);
							}
							break;
						}
						wr.table1.repaint();
					}
				}
				break;
				case Function.GAMETURNS:
				{
					String m = st.nextToken();
					
					if(m.equals("true"))
					{
						gp33.turn1.setVisible(true);
						gp33.turn2.setVisible(false);
					}
					else
					{
						gp33.turn1.setVisible(false);
						gp33.turn2.setVisible(true);
					}
				}
				break;

				case Function.SETTURN:
				{
					String m = st.nextToken();
					if(m.equals("true"))
					{
						gp33.turn1.setVisible(true);
						gp33.turn2.setVisible(false);
					}
					else
					{
						gp33.turn1.setVisible(false);
						gp33.turn2.setVisible(true);
					}
					
				}
				break;
				}	
			}
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		i = 0;
	}
	
	public void connection(){
		try {
			s = new Socket("localhost", 19872);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = s.getOutputStream();
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		new Thread(this).start();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login.b1) {
			setSize(750,600);
            card.show(getContentPane(), "SG");

            if (s == null){
				connection();
			}
            
		} else if (e.getSource() == login.b2 || e.getSource() == login.pf) {
			id = login.tf.getText();
			pwd = String.valueOf(login.pf.getPassword());
			
			if (id.length() < 1){
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
				login.tf.requestFocus();
				return;
			}
			
			if (pwd.length() < 1){
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
				login.pf.requestFocus();
				return;
			}
			
			if (s == null){
				connection();
			}
			
			try {
				out.write((Function.LOGIN+"|"+id+"|"+pwd+"\n").getBytes());
			} catch (Exception ex){
				
			}
		} else if (e.getSource() == login.b3){
			int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "종료 선택창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == 0){
				try {
					out.write((Function.LOGEND+"\n").getBytes());
				} catch (Exception ex){
					System.out.println(ex.getMessage());
				}
				System.exit(0);
			}
		} else if (e.getSource() == wr.tf) {
			String id = login.tf.getText();
			String msg = wr.tf.getText().trim();
			String color = wr.box.getSelectedItem().toString();
			if (msg.length() < 1){
				return;
			}
			
			try {
				out.write((Function.WAITCHAT+"|"+id+"|"+msg+"|"+color+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			wr.tf.setText("");
		} else if (e.getSource() == wr.b1) {
			setLocationRelativeTo(null);
			rs.getJDialog();
		} else if (e.getSource() == wr.b2) {
			try {
				int a = 0;
				String roomNum = " ";
				for(int i = 0; i < wr.model1.getRowCount(); i++){
					String inwon = wr.model1.getValueAt(i, 4).toString();
					if (Integer.parseInt(inwon) < 2) {
						roomNum = wr.model1.getValueAt(i, 0).toString();
						break;
					}
				}
				out.write((Function.MYROOMIN+"|"+roomNum+"|"+getId+"|"+getUsernick+"|"+getGender+"|"+getUserava+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == wr.b3) {
			setLocationRelativeTo(null);
			t.getJDialog();
		} else if (e.getSource() == wr.b4) {
			if (!(getUsernick.equals(""))){
				setSize(1100, 900);
				setLocationRelativeTo(null);
				rbtt.initPanel(getUsernick, getUserava);
				card.show(getContentPane(), "RBTT");
			} else {
				return;
			}
		} else if (e.getSource() == wr.b5) {
			made.setVisible(true);
			made.setLocationRelativeTo(null);
		} else if (e.getSource() == wr.b6) {
			String id = login.tf.getText();
			int result = JOptionPane.showConfirmDialog(null, "로그아웃하시겠습니까?", "로그아웃 선택창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == 0){
				try {
					out.write((Function.LOGOUT+"|"+id+"\n").getBytes());
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
			
		} else if (e.getSource() == wr.b7) {
			String id = login.tf.getText();
			int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "종료 선택창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == 0){
				try {
					out.write((Function.CHATEND+"|"+id+"\n").getBytes());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
			
		} else if (e.getSource() == t.b1) {
			t.removeJDialog();
		} else if (e.getSource() == made.b1) {
			made.setVisible(false);
		} else if (e.getSource() == rbtt.btn5) {
			level = 'f';
			setSize(1100, 900);
			setLocationRelativeTo(null);
			gptt.setImageCard(level);	
			gptt.initPanel(getUsernick, getUserava);
			card.show(getContentPane(), "GPTT");
		} else if (e.getSource() == rbtt.btn6) {
			setSize(1000,750);
			setLocationRelativeTo(null);
			card.show(getContentPane(), "WR");
		} else if (e.getSource() == gptt.btn4) {
			int result = JOptionPane.showConfirmDialog(null, "연습 모드를 종료하시겠습니까?", "종료 선택창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == 0){
				setSize(1000,750);
				setLocationRelativeTo(null);
				card.show(getContentPane(), "WR");
			}
			
		} else if (e.getSource() == gptt.btn5) {
			level = 'f';
			setSize(1100, 900);
			setLocationRelativeTo(null);
			gptt.setImageCard(level);
			gptt.initPanel(getUsernick, getUserava);
			card.show(getContentPane(), "GPTT");
		} else if (e.getSource() == sg.b1){
			setSize(800, 600);
			setLocationRelativeTo(null);
			
			String nickname = sg.tf1.getText();
			String id = sg.tf2.getText();
			String pwd = String.valueOf(sg.pf1.getPassword());
			String gender = sg.cb.getSelectedItem().toString();
			String avatar = "";	
			if (gender.equals("남자")){
				if (sg.rb1.isSelected()){
					avatar = "resources/images/avatar_m_1.png";
				} else if (sg.rb2.isSelected()){
					avatar = "resources/images/avatar_m_2.png";
				} else if (sg.rb3.isSelected()){
					avatar = "resources/images/avatar_m_3.png";
				} else if (sg.rb4.isSelected()){
					avatar = "resources/images/avatar_m_4.png";
				} else {
					avatar = "resources/images/avatar_m_5.png";
				}
			} else {
				if (sg.rb1.isSelected()){
					avatar = "resources/images/avatar_f_1.png";
				} else if (sg.rb2.isSelected()){
					avatar = "resources/images/avatar_f_2.png";
				} else if (sg.rb3.isSelected()){
					avatar = "resources/images/avatar_f_3.png";
				} else if (sg.rb4.isSelected()){
					avatar = "resources/images/avatar_f_4.png";
				} else {
					avatar = "resources/images/avatar_f_5.png";
				}
			}
			
			if (nickname.length() <= 0){
				JOptionPane.showMessageDialog(this, "닉네임을 입력하세요");
				return;
			}
			
			if (id.length() <= 0){
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
				return;
			}
			
			if (pwd.length() <= 0){
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
				return;
			}
			
			if (sg.checkId == false) {
				JOptionPane.showMessageDialog(this, "아이디 중복을 체크하세요");
				return;
			}
			
			if (sg.checkNick == false) {
				JOptionPane.showMessageDialog(this, "닉네임 중복을 체크 하세요");
				return;
			}
			
			if (sg.checkPwd == false) {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다");
				return;
			}
			
			try {
				out.write((Function.SIGNUP+"|"+id+"|"+nickname+"|"+pwd+"|"+gender+"|"+avatar+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else if (e.getSource() == sg.b2){
			setSize(800, 600);
			setLocationRelativeTo(null);
			
			sg.tf1.setText("");
			sg.tf2.setText("");
			sg.pf1.setText("");
			sg.pf2.setText("");
			sg.tf3.setText("");
			
			card.show(getContentPane(), "LOG");
		} else if (e.getSource() == sg.b3){
			String nick = sg.tf1.getText();
			
			if (nick.length() < 1){
				JOptionPane.showMessageDialog(this, "닉네임을 입력하세요");
				sg.tf1.requestFocus();
				return;
			}
			
			try {
				out.write((Function.CHECKNICK+"|"+nick+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else if (e.getSource() == sg.b4){
			String id = sg.tf2.getText();
			
			if (id.length() < 1){
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
				sg.tf2.requestFocus();
				return;
			}
			
			try {
				out.write((Function.CHECKID+"|"+id+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else if (e.getSource() == rs.b1){
			rb33.initPanel(getUsernick, getUserava);
			
			//level = rs.returnLevel();
			
			String title = rs.tf.getText();
			String level = "";
			String cate = "";
			
			if (rs.rb3.isSelected()){
				level = "3x3";
			} else if (rs.rb4.isSelected()) {
				level = "4x4";
			} else if (rs.rb5.isSelected()) {
				level = "5x5";
			}
			
			if (rs.rb6.isSelected()){
				cate = "연예인";
			} else if (rs.rb7.isSelected()){
				cate = "축구";
			} else if (rs.rb8.isSelected()){
				cate = "농구";
			} else if (rs.rb9.isSelected()){
				cate = "미생물";
			} else if (rs.rb10.isSelected()){
				cate = "모양";
			} else if (rs.rb11.isSelected()){
				cate = "포켓몬";
			} 
			
			try {
				out.write((Function.MAKEROOM + "|" + title+"|"+level+"|"+cate+"|"+"1"+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else if (e.getSource() == rs.b2){
			rs.removeJDialog();
		} else if (e.getSource()== rb33.btn6) {
			it.setVisible(true);
		} else if (e.getSource()== rb44.btn6) {
			it.setVisible(true);
		} else if (e.getSource()== rb55.btn6) {
			it.setVisible(true);
		} else if (e.getSource() == it.b1) {
			it.setVisible(false);
		} else if (e.getSource() == rb33.btn4){
			int result = JOptionPane.showConfirmDialog(null, "대기실로 이동하시겠습니까?", "종료 선택창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == 0){
				try {
					out.write((Function.ROOMOUT+"|"+myRoomNum+"\n").getBytes());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == rb44.btn4){
			int result = JOptionPane.showConfirmDialog(null, "대기실로 이동하시겠습니까?", "종료 선택창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == 0){
				try {
					out.write((Function.ROOMOUT+"|"+myRoomNum+"\n").getBytes());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == rb55.btn4){
			int result = JOptionPane.showConfirmDialog(null, "대기실로 이동하시겠습니까?", "종료 선택창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == 0){
				try {
					out.write((Function.ROOMOUT+"|"+myRoomNum+"\n").getBytes());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == rb33.tf){
			String data = rb33.tf.getText().trim();
			try {
				out.write((Function.ROOMCHAT+"|"+getId + "|" + getUsernick+"|"+data+"|"+rb33.box.getSelectedItem().toString()+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == rb44.tf){
			String data = rb44.tf.getText().trim();
			try {
				out.write((Function.ROOMCHAT+"|"+getId + "|" + getUsernick+"|"+data+"|"+rb33.box.getSelectedItem().toString()+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == rb55.tf){
			String data = rb55.tf.getText().trim();
			try {
				out.write((Function.ROOMCHAT+"|"+getId + "|" + getUsernick+"|"+data+"|"+rb33.box.getSelectedItem().toString()+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == rb33.btn5){
			level = rs.returnLevel();
			try {
				out.write((Function.ORDERCHOICE+"|"+getId+"|"+"GP33"+"|"+"9" + "|" + level + "\n").getBytes());
				out.write((Function.SETTURN+"|"+id+"|"+roomNum+"|"+tCheck+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		} else if (e.getSource() == rb44.btn5){
			level = rs.returnLevel();
			try {
				out.write((Function.ORDERCHOICE+"|"+getId+"|"+"GP44"+"|" + "16" + "|" + level + "\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		} else if (e.getSource() == rb55.btn5){
			level = rs.returnLevel();
			try {
				out.write((Function.ORDERCHOICE+"|"+getId+"|"+"GP55"+ "|" + "25" + "|" + level + "\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		} else if (e.getSource() == gp33.tf){
			String data = gp33.tf.getText().trim();
			try {
				out.write((Function.GAMECHAT+"|"+getId + "|" + getUsernick+"|"+data+"|"+gp33.box.getSelectedItem().toString()+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == gp44.tf){
			String data = gp44.tf.getText().trim();
			try {
				out.write((Function.GAMECHAT+"|"+getId + "|" + getUsernick+"|"+data+"|"+gp44.box.getSelectedItem().toString()+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == gp55.tf){
			String data = gp55.tf.getText().trim();
			try {
				out.write((Function.GAMECHAT+"|"+getId + "|" + getUsernick+"|"+data+"|"+gp55.box.getSelectedItem().toString()+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == gp33.btn4){
			int result = JOptionPane.showConfirmDialog(null, "게임을 포기하시겠습니까?", "종료 선택창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == 0){
				try {
					out.write((Function.ROOMOUT+"|"+myRoomNum+"\n").getBytes());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == gp44.btn4){
			int result = JOptionPane.showConfirmDialog(null, "게임을 포기하시겠습니까?", "종료 선택창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == 0){
				try {
					out.write((Function.ROOMOUT+"|"+myRoomNum+"\n").getBytes());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == gp55.btn4){
			int result = JOptionPane.showConfirmDialog(null, "게임을 포기하시겠습니까?", "종료 선택창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == 0){
				try {
					out.write((Function.ROOMOUT+"|"+myRoomNum+"\n").getBytes());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == gp33.btn6) {
			it.setVisible(true);
		} else if (e.getSource() == gp44.btn6) {
			it.setVisible(true);
		} else if (e.getSource() == gp55.btn6) {
			it.setVisible(true);
		} else if (e.getSource() == gp33.p2item1 || e.getSource() == gp44.p2item1 || e.getSource() == gp55.p2item1) {
			try {
				out.write((Function.GAMEITEM1+"|"+id+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == gp33.p2item2 || e.getSource() == gp44.p2item2 || e.getSource() == gp55.p2item2) {
			try {
				out.write((Function.GAMEITEM2+"|"+id+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == gp33.p2item3 || e.getSource() == gp44.p2item3 || e.getSource() == gp55.p2item3) {
			try {
				out.write((Function.GAMEITEM3+"|"+id+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == gp33.p2item4 || e.getSource() == gp44.p2item4 || e.getSource() == gp55.p2item4) {
			try {
				out.write((Function.GAMEITEM4+"|"+id+"\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == rb33.btn1) {
			try {
				out.write((Function.GAMEREADY + "|" + myRoomNum + "\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == rb44.btn1) {
			try {
				out.write((Function.GAMEREADY + "|" + myRoomNum + "\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == rb55.btn1) {
			try {
				out.write((Function.GAMEREADY + "|" + myRoomNum + "\n").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		for(int p=0;p<gp33.ct.length;p++){
			if(e.getSource() == gp33.ct[p]){
				Image image = gp33.k[p].getImage();
				Image newimg = image.getScaledInstance(gp33.ct[0].getWidth(), gp33.ct[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
				ImageIcon cI = new ImageIcon(newimg);
				
				try {
               	 out.write((Function.CLICKCENTER+"|"+id+"|" + gp33.k[p].toString() + "|" + p + "\n").getBytes());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				for(int n = 0; n < gp33.b.length; n++){
					String yid=gp33.p1tf.getText();
					String mid = gp33.p2tf.getText();
					
					if (gp33.b[n].getBorder() == gp33.thickBorder){
						if(gp33.j[n].toString().equals(gp33.k[p].toString())){
							
							try {
								out.write((Function.GAMETURNS+"|"+myRoomNum+"|" + id + "|" + tCheck + "\n").getBytes());
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							
						  if (gp33.countBorder == 10){
			                     try {
			                    	 out.write((Function.ENDGAME+"|" +mid + "|" + yid +"\n").getBytes());
								} catch (Exception e1) {
									e1.printStackTrace();
								}
			                }
						    gp33.Sound(gp33.okSound, false);
						  	
						  	gp33.countBorder++;
						  	
						  	
						  	try {						  		
								out.write((Function.MOVEBORDER+"|"+id+"|"+n+"\n").getBytes());
								out.write((Function.GAMETURNS+"|"+myRoomNum+"|" + id + "|" + tCheck + "\n").getBytes());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						  	
							break;
						} else {
							
							String data1 = mid + "님 한번 더 턴입니다";
							if (gp33.count == 1){
								
								try{
									out.write((Function.GAMECHAT+"|"+"System"+ "|" + "System"+"|"+data1+"|"+"red"+"\n").getBytes());
									out.write((Function.GAMETURNS+"|"+myRoomNum+"|"+yid+"|"+"true"+"\n").getBytes());
								}catch(Exception ex){
									
								}
								
								gp33.count = 0;
								return;
							}
							
							String data = yid +" 님 턴입니다";
							
							try{
								out.write((Function.GAMETURN+"|"+ myRoomNum+"|"+yid+"\n").getBytes());
								out.write((Function.GAMECHAT+"|"+"System"+ "|" + "System"+"|"+data+"|"+"red"+"\n").getBytes());
								out.write((Function.GAMETURNS+"|"+myRoomNum+"|"+yid+"|"+tCheck+"\n").getBytes());
							}catch(Exception ex){
								
							}
							Sound(gp33.failSound, false);
						}
					}
				}
				try {
					for(int n = 0; n < gp33.ct.length; n++){
						if (n == p){
							continue;
						} else {
							gp33.ct[n].setEnabled(false);
						}
					}
					
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		}
		
		for(int p=0;p<gp44.ct.length;p++){
			if(e.getSource() == gp44.ct[p]){
				Image image = gp44.k[p].getImage();
				Image newimg = image.getScaledInstance(gp44.ct[0].getWidth(), gp44.ct[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
				ImageIcon cI = new ImageIcon(newimg);
				
				gp44.ct[p].setIcon(cI);
				
				for(int n = 0; n < gp44.b.length; n++){
					if (gp44.b[n].getBorder() == gp44.thickBorder){
						if(gp44.j[n].toString().equals(gp44.k[p].toString())){
						  if (gp44.countBorder == 10){
			                     ch.setVisible(true);
			                     try {
			                    	 out.write((Function.ENDGAME+"|"+id+"\n").getBytes());
								} catch (Exception e1) {
									e1.printStackTrace();
								}
			                }
						  	Sound(gp44.okSound, false);
						  	
						  	gp44.countBorder++;

						  	try {
								out.write((Function.MOVEBORDER+"|"+id+"|"+n+"\n").getBytes());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							break;
						} else {
							if (gp44.count == 1){
								gp44.count = 0;
								return;
							}
							
							// 턴넘기기
							Sound(gp44.failSound, false);
						}
					}
				}
				try {
					for(int n = 0; n < gp44.ct.length; n++){
						if (n == p){
							continue;
						} else {
							gp44.ct[n].setEnabled(false);
						}
					}
					
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		}
		
		for(int p=0;p<gp55.ct.length;p++){
			if(e.getSource() == gp55.ct[p]){
				Image image = gp55.k[p].getImage();
				Image newimg = image.getScaledInstance(gp55.ct[0].getWidth(), gp55.ct[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
				ImageIcon cI = new ImageIcon(newimg);
				
				gp55.ct[p].setIcon(cI);
				
				for(int n = 0; n < gp55.b.length; n++){
					if (gp55.b[n].getBorder() == gp55.thickBorder){
						if(gp55.j[n].toString().equals(gp55.k[p].toString())){
						  if (gp55.countBorder == 10){
			                     ch.setVisible(true);
			                     try { 
			                    	 out.write((Function.ENDGAME+"|"+id+"\n").getBytes());
								} catch (Exception e1) {
									e1.printStackTrace();
								}
			                }
						  	Sound(gp55.okSound, false);
						  	
						  	gp55.countBorder++;

						  	try {
								out.write((Function.MOVEBORDER+"|"+id+"|"+n+"\n").getBytes());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							break;
						} else {
							if (gp55.count == 1){
								gp55.count = 0;
								return;
							}
							
							// 턴넘기기
							Sound(gp55.failSound, false);
						}
					}
				}
				try {
					for(int n = 0; n < gp55.ct.length; n++){
						if (n == p){
							continue;
						} else {
							gp55.ct[n].setEnabled(false);
						}
					}
					
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		}
		 
	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == wr.table1){
			if (e.getClickCount() == 2){
				int row = wr.table1.getSelectedRow();
				
				String roomNum = wr.model1.getValueAt(row, 0).toString();
				String roomInwon = wr.model1.getValueAt(row, 4).toString();
				
				if (Integer.parseInt(roomInwon) >= 2){
					JOptionPane.showMessageDialog(this,"방의 인원이 차서 입장하실 수 없습니다");
					return;
				}
				try {
					out.write((Function.MYROOMIN+"|"+roomNum+"|"+getId+"|"+getUsernick+"|"+getGender+"|"+getUserava+"\n").getBytes());
				} catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
	}

	@Override
	public void mouseExited(MouseEvent e) {	
	}
}


