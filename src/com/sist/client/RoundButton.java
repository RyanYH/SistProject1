package com.sist.client;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
 
public class RoundButton extends JButton {
 
  public RoundButton(String label) {
    super(label);
 
    setBackground(Color.black);
    setFocusable(false);
    Dimension size = getPreferredSize();
    size.width = size.height = Math.max(size.width, size.height);
    setPreferredSize(size);
    setContentAreaFilled(false);
    setFocusPainted(false);
    setBorderPainted(false);
  }
 
  protected void paintComponent(Graphics g) {
    if (getModel().isArmed()) {
      g.setColor(Color.black);
    } else {
      g.setColor(getBackground());
    }
    g.fillOval(0, 0, getSize().width, getSize().height);
 
    super.paintComponent(g);
  }
 
  protected void paintBorder(Graphics g) {
    g.setColor(Color.black);
    g.drawOval(0, 0, getSize().width, getSize().height);
  }
  Shape shape;
 
  public boolean contains(int x, int y) {
    if (shape == null || !shape.getBounds().equals(getBounds())) {
      shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
    }
    return shape.contains(x, y);
  }
  public static void main(String [] args){
  }
}
