package com.sist.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Roomselect extends JPanel {
    JLabel la1,la2,la3,la4, la5;
    JTextField tf;
    JPasswordField pf;
    JRadioButton rb1,rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11;
    JComboBox box;
    JButton b1,b2;
    JFrame jf=new JFrame();
    JDialog jd=new JDialog(jf, "방만들기");
    String[] arrTitle = new String[5];
    
    public char returnLevel(){
    	char result = 0;
    	
    	if (rb6.isSelected() == true){
    		result = 's';
    	}
    	
    	if (rb7.isSelected() == true){
    		result = 'f';
    	}
    	
    	if (rb8.isSelected() == true){
    		result = 'e';
    	}
    	if (rb9.isSelected() == true){
    		result = 'a';
    	}
    	if (rb10.isSelected() == true){
    		result = 'b';
    	}
    	if (rb11.isSelected() == true){
    		result = 'c';
    	}
    	return result;
    }
    
    public Roomselect(){	
    	
    	arrTitle[0] = "니가 까라 카드";
    	arrTitle[1] = "너와 나의 연결고리";
    	arrTitle[2] = "스겜ㄱㄱ";
    	arrTitle[3] = "드루와 드루와 드루와";
    	arrTitle[4] = "대형이 잔다..";
    	
    	la1=new JLabel("방이름");
    	la2=new JLabel("상태");
    	la3=new JLabel("비밀번호");
    	la4=new JLabel("난이도");
    	la5=new JLabel("종류");
    	
    	tf=new JTextField();
    	pf=new JPasswordField();
    	 
    	rb1=new JRadioButton("공개");
    	rb2=new JRadioButton("비공개");
   //==========================
    	rb3=new JRadioButton("3x3");
    	rb4=new JRadioButton("4x4");
    	rb5=new JRadioButton("5x5");
   //==========================
    	rb6=new JRadioButton("연예인");
    	rb7=new JRadioButton("축구");
    	rb8=new JRadioButton("농구");
    	rb9=new JRadioButton("미생물");
    	rb10=new JRadioButton("모양");
    	rb11=new JRadioButton("포켓몬");
    	 
		 ButtonGroup bg=new ButtonGroup();
		 bg.add(rb1);
		 bg.add(rb2);
		 rb1.setSelected(true);
		 
		 ButtonGroup bg1=new ButtonGroup();
		 bg1.add(rb3);
		 bg1.add(rb4);
		 bg1.add(rb5);
		 rb3.setSelected(true);
		 
		 ButtonGroup bg2=new ButtonGroup();
		 bg2.add(rb6);
		 bg2.add(rb7);
		 bg2.add(rb8);
		 bg2.add(rb9);
		 bg2.add(rb10);
		 bg2.add(rb11);
		 rb6.setSelected(true);
		 
		 b1=new JButton("확인");
		 b2=new JButton("취소");
		 
		 la3.setVisible(false);
		 pf.setVisible(false);
		 
		 //배치
		 setLayout(null);
		 la1.setBounds(50, 25, 50, 20);
		 tf.setBounds(110, 25, 160, 20);
		 
		 la4.setBounds(50, 65, 50, 20);
		 rb3.setBounds(105, 65, 70, 20);
		 rb4.setBounds(180, 65, 70, 20);
		 rb5.setBounds(250, 65, 70, 20);
		 
		 la5.setBounds(50, 110, 50, 20);
		 rb6.setBounds(105, 110, 70, 20);
		 rb7.setBounds(180, 110, 70, 20);
		 rb8.setBounds(250, 110, 70, 20);
		 rb9.setBounds(105, 140, 70, 20);
		 rb10.setBounds(180, 140, 70, 20);
		 rb11.setBounds(250, 140, 70, 20);
		 
		 JPanel p=new JPanel();
		 p.add(b1);
		 p.add(b2);
		 
		 p.setBounds(80, 180, 195, 35);
		 
		 //추가
		 jd.add(la1);jd.add(tf);
		 jd.add(la4);
		 jd.add(rb3);jd.add(rb4);jd.add(rb5);
		 jd.add(la5);jd.add(rb6);jd.add(rb7);jd.add(rb8);
		 jd.add(rb9);jd.add(rb10); jd.add(rb11);
		 jd.add(p);
		 
		 jd.setSize(370, 260);
		 jd.setLayout(null);
		 setVisible(false);
	
    }
    
    public void getJDialog(){
    	int ran = (int)(Math.random()*arrTitle.length);   	
    	tf.setText(arrTitle[ran]);
   	    jd.setLocationRelativeTo(null);
        jd.setVisible(true);     
    }
    
    public void removeJDialog(){
       jd.setVisible(false);
       
    }
}
