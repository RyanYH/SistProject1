package com.sist.client;

import java.awt.*;
import javax.swing.*;

public class Check1 extends JWindow {
	public Check1(){
      setLayout(null);
      
      ImageIcon image = new ImageIcon("resources/images/check3.gif");
      JLabel label = new JLabel(image);
      
      JPanel p1 = new JPanel();
      p1.add(label);
      p1.setBackground(Color.BLACK);
      
      p1.setBounds(0, 0, 580, 240);
      add(p1);
      setSize(580, 240);
      setLocationRelativeTo(null);
   }
	
	public static void main(String[] args) {
		new Check1();
	}

}
