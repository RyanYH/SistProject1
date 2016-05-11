package com.sist.client;

import java.awt.*;
import javax.swing.*;

public class Made extends JFrame {
	JButton b1;
	Image img;
	  
	public Made(){
		setLayout(null);
		
		Image image = new ImageIcon("resources/images/12345.png").getImage();
		Image newimg = image.getScaledInstance(680, 580,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon cI = new ImageIcon(newimg);
		
	    JLabel label = new JLabel(cI);
	    b1 = new JButton("확인");
	    
	    JPanel p1 = new JPanel();
	    p1.add(label);
	    
	    JPanel p = new JPanel();
	    p.add(b1);
	    
	    p1.setBounds(0, -10, 680, 580);
	    p.setBounds(0,580,680,40);
	    
	    setTitle("만든사람들");
	    add(p1);
	    add(p);
	    setSize(680, 660);
	    setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new Made();
	}
}
