package com.sist.client;

import java.awt.*;
import javax.swing.*;

public class Tutorial extends JPanel {
	JButton b1;
	Image img;//�������
	
	JFrame jf=new JFrame();
    JDialog jd=new JDialog(jf, "���Ӽ���");
    
	public Tutorial(){
		setLayout(null);
		
		b1=new JButton("Ȯ��");
		
		ImageIcon image = new ImageIcon("resources/images/tuto.gif");
		JLabel label = new JLabel("", image, JLabel.CENTER);
		JPanel p1 = new JPanel();
		p1.add("Center",label);
		p1.add("South", b1);
		p1.setBackground(Color.BLACK);
		
		jd.add(p1);
		jd.setSize(800, 550);

	}
	
	public void getJDialog(){
   	    jd.setLocationRelativeTo(null);
        jd.setVisible(true);     
    }
    
    public void removeJDialog(){
       jd.setVisible(false);
       
    }
    
	@Override
	protected void paintComponent(Graphics g) {
		//g.fillRect(0, 0, getWidth(), getHeight(), this);
		//g.drawImage(jd.getToolkit().getDefaultToolkit().getImage("images/back.gif"), 0, 0, getWidth(), getHeight(),this); // ȭ�� ��� Ȯ���ҋ��� �̹����� ������ ����

	}
	
}
