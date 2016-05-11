package com.sist.client;

import java.awt.*;
import javax.swing.*;

public class ItemCheck2 extends JWindow{
	public ItemCheck2(){
	      setLayout(null);
	      
	      ImageIcon image = new ImageIcon("resources/images/ItemCheck2.gif");
	      JLabel label = new JLabel(image);
	      
	      JPanel p1 = new JPanel();
	      p1.add(label);
	      p1.setBackground(Color.BLACK);
	      
	      p1.setBounds(0, 0, 430, 335);
	      add(p1);
	      setSize(430, 335);
	      setLocationRelativeTo(null);
	   }
   
	   public static void main(String [] args){
	       new ItemCheck2();
	    }
}