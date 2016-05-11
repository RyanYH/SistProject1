package com.sist.client;

import java.awt.*;
import javax.swing.*;

public class ItemCheck extends JWindow {
   
   public ItemCheck(){
      setLayout(null);
      
      ImageIcon image = new ImageIcon("resources/images/ItemCheck.gif");
      JLabel label = new JLabel(image);
      
      JPanel p1 = new JPanel();
      p1.add(label);
      p1.setBackground(Color.BLACK);
      
      p1.setBounds(0, 0, 400, 205);
      add(p1);
      setSize(400, 205);
      setLocationRelativeTo(null);

   }
   
    public static void main(String [] args){
       new ItemCheck();
    }
}