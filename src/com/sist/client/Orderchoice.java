package com.sist.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Random;
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

public class Orderchoice extends JFrame{

	Image image;
	JProgressBar bar1;
	
	Border thickBorder = new LineBorder(Color.RED, 5);
	Border thinBorder=new LineBorder(null);
	Border panelBorder = new LineBorder(Color.white, 3); 
	
	Timer timer;
	Timer itemTimer;
	Timer imageTimer;
	
	char level;
	
	static int count = 0;
	int iCoun = 0;
	
	String nick = "";
	String avatar = "";
	
	JLabel p1id, p2id;
	ImageIcon cardBack, cardBack2;
	JTextField p1tf, p2tf;
	JLabel p1card, p2card;
	JPanel p1, p2;
	JButton b1,b2;
	JTextField tf1;
	
	static int countBorder = 0;
	
	public void initPanel(String nick, String avatar) {	
		ImageIcon r = new ImageIcon(avatar);

		p2tf.setText(nick);
		p2card.setIcon(r);
	}
	
	public void initPanel1(String nick, String avatar) {	
		ImageIcon r = new ImageIcon(avatar);

		p1tf.setText(nick);
		p1card.setIcon(r);
	}
	
	public Orderchoice(){
		
//=====================================
		//상대방 왼쪽위
		p1 = new JPanel();
		p1.setBorder(panelBorder);
		p1id = new JLabel();
		p1id.setForeground(Color.white);
		
		cardBack = new ImageIcon("resources/images/avatar_f_1.png");	
		p1card = new JLabel(cardBack);
		
		p1tf = new JTextField("상대방");
		p1tf.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		
		p1tf.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,15));
		p1tf.setEnabled(false);
		
		p1.setBackground(Color.BLACK);
		
		p1.setLayout(null);
		p1id.setBounds(20,15,50,15);
		p1card.setBounds(10,10,280,345);
		p1tf.setBounds(75, 360, 150, 40);
		
		p1.add(p1id);
		p1.add(p1card);
		p1.add(p1tf);
		add(p1);
		
		
		
		p1.setBounds(90, 90, 300, 410);
		p1.setVisible(true);
//=======================================
		//나 오른쪽밑에
		p2 = new JPanel();
		p2.setBorder(panelBorder);
		p2id = new JLabel();
		p2id.setForeground(Color.white);
		
		cardBack2 = new ImageIcon("resources/images/avatar_f_2.png");
		p2card = new JLabel(cardBack2);
		
		p2tf = new JTextField("나");
		p2tf.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		
		p2tf.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,15));
		p2tf.setEnabled(false);
		
		p2.setBackground(Color.BLACK);
		
		p2.setLayout(null);
		p2id.setBounds(20,15,50,15);
		p2card.setBounds(10,10,280,345);
		p2tf.setBounds(75, 360, 150, 40);
		
		
		add(p2);
		p2.add(p2id);
		p2.add(p2card);
		p2.add(p2tf);
		p2.setBounds(710, 360, 300, 410);		
		
		bar1 = new JProgressBar();
	    bar1.setMinimum(0);
	    bar1.setMaximum(100);
	    bar1.setBackground(Color.white);
	    bar1.setForeground(Color.green);
	    bar1.setStringPainted(true);
	    
	    //bar1.setBounds(20, 780, 1040, 30);
	    //add(bar1);
	    JPanel p = new JPanel();
	    p.setBounds(20, 800, 600, 40);
		setLayout(new BorderLayout());
		JLabel background = new JLabel(new ImageIcon("resources/images/back03.gif"));
		add(background);
		background.setLayout(null);
		background.add(p1); background.add(p2); 
		
		setSize(1100,900);
		setVisible(false);
		setLocationRelativeTo(null);
	    
	}

}
