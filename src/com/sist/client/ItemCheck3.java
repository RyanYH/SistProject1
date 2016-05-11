package com.sist.client;

import java.awt.*;
import javax.swing.*;

public class ItemCheck3 extends JWindow{
   public ItemCheck3(){
      setLayout(null);
      
      ImageIcon image = new ImageIcon("resources/images/ItemCheck3.gif");
      JLabel label = new JLabel(image);
      
      JPanel p1 = new JPanel();
      p1.add(label);
      p1.setBackground(Color.BLACK);
      
      p1.setBounds(0, 0, 250, 260);
      add(p1);
      setSize(250, 260);
      setLocationRelativeTo(null);
      
   }
   
   public static void main(String [] args){
       new ItemCheck3();
    }
}
