package com.sist.client;

import javax.swing.*;
import java.awt.*;

public class Itemtutorial extends JFrame{
	JButton b1;
	Image img;
	
	public Itemtutorial(){
		b1=new JButton("Ȯ��");
		
		JPanel p1 = new JPanel();
		ImageIcon image = new ImageIcon("resources/images/Itemtutorial.png");
		JLabel label = new JLabel(image);
		p1.add("Center",label);
		p1.add(b1);
		p1.setBackground(Color.BLACK);
		p1.add("South", b1);
		
		add(p1);
		setSize(450, 400);
		setTitle("������ ����");
		setLocationRelativeTo(null);
	}
    
	public static void main(String[] args){
		new Itemtutorial();
	}

	
}

