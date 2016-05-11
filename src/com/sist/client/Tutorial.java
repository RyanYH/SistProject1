package com.sist.client;

import java.awt.*;
import javax.swing.*;

public class Tutorial extends JPanel {
	JButton b1;
	Image img;//여기까지
	
	JFrame jf=new JFrame();
    JDialog jd=new JDialog(jf, "게임설명");
    
	public Tutorial(){
		setLayout(null);
		
		b1=new JButton("확인");
		
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
		//g.drawImage(jd.getToolkit().getDefaultToolkit().getImage("images/back.gif"), 0, 0, getWidth(), getHeight(),this); // 화면 축소 확대할떄도 이미지가 꽉차게 나옴

	}
	
}
