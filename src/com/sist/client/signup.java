package com.sist.client;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

import javax.swing.*;
import com.sist.user.UserDAO;
import com.sist.user.UserDTO;

import java.io.*;

public class signup extends JPanel implements ItemListener,FocusListener, MouseListener{
	JLabel la1;
	JLabel la2;
	JLabel la3;
	JLabel la4;
	JLabel la5;

	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	
	JPasswordField pf1;
	JPasswordField pf2;
	
	JComboBox cb;
	
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton b5;
	
	JPanel p1;
	JPanel p2;
	JPanel p3;
	
	String[] gender = {"남자", "여자"};
	Image img;
	
	JLabel av1,av2,av3,av4,av5;
	JRadioButton rb1,rb2,rb3,rb4,rb5;
	ButtonGroup bg;
	
	boolean checkId= false;
	boolean checkNick = false;
	boolean checkPwd = false;
	
	public signup(){
		
		img = Toolkit.getDefaultToolkit().getImage("resources/images/back04-1.png");
		
		la1 = new JLabel("닉네임");
		la2 = new JLabel("아이디");
		la3 = new JLabel("비밀번호");
		la4 = new JLabel("비밀번호 확인");
		la5 = new JLabel("성별");
		
		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		
		pf1 = new JPasswordField();
		pf2 = new JPasswordField();
		
		cb = new JComboBox(gender);
		cb.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,13));
		
		b1 = new JButton("가입");
		b2 = new JButton("취소");
		b3 = new JButton("중복확인");
		b4 = new JButton("중복확인");
		
		p1 = new JPanel();
		
		p1.add(b1);
		p1.add(b2);
		p1.setOpaque(false); 
		
		Image temp1 = new ImageIcon("resources/images/avatar_m_1.png").getImage().getScaledInstance(105, 150, Image.SCALE_SMOOTH);
		av1=new JLabel(new ImageIcon(temp1));
		
		Image temp2 = new ImageIcon("resources/images/avatar_m_2.png").getImage().getScaledInstance(105, 150, Image.SCALE_SMOOTH);
		av2=new JLabel(new ImageIcon(temp2));
		
		Image temp3 = new ImageIcon("resources/images/avatar_m_3.png").getImage().getScaledInstance(105, 150, Image.SCALE_SMOOTH);
		av3=new JLabel(new ImageIcon(temp3));
		
		Image temp4 = new ImageIcon("resources/images/avatar_m_4.png").getImage().getScaledInstance(105, 150, Image.SCALE_SMOOTH);
		av4=new JLabel(new ImageIcon(temp4));
		
		Image temp5 = new ImageIcon("resources/images/avatar_m_5.png").getImage().getScaledInstance(105, 150, Image.SCALE_SMOOTH);
		av5=new JLabel(new ImageIcon(temp5));
		
		rb1=new JRadioButton("");
		rb2=new JRadioButton("");
		rb3=new JRadioButton("");
		rb4=new JRadioButton("");
		rb5=new JRadioButton("");
		
		rb1.setOpaque(false);
		rb2.setOpaque(false);
		rb3.setOpaque(false);
		rb4.setOpaque(false);
		rb5.setOpaque(false);
		
		bg=new ButtonGroup();
		bg.add(rb1); 
		bg.add(rb2); 
		bg.add(rb3);
		bg.add(rb4);
		bg.add(rb5);
		rb1.setSelected(true);
		
		setLayout(null);
		
		la1.setBounds(200,140,100,30);
		la1.setForeground(Color.YELLOW);
		la1.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,13));

		la2.setBounds(200,175,100,30);
		la2.setForeground(Color.YELLOW);
		la2.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,13));
		
		la3.setBounds(200,210,100,30);
		la3.setForeground(Color.YELLOW);
		la3.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,13));
		
		la4.setBounds(200,245,100,30);
		la4.setForeground(Color.YELLOW);
		la4.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,13));
		
		la5.setBounds(200,280,100,30);
		la5.setForeground(Color.YELLOW);
		la5.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,13));
		
		tf1.setBounds(295, 140, 150, 30);
		tf2.setBounds(295, 175, 150, 30);
		b3.setBounds(450, 140, 100, 20);
		b3.setFont(new Font("휴먼둥근헤드라인",Font.BOLD,15));
		
		pf1.setBounds(295, 210, 150, 30);
		pf2.setBounds(295, 245, 150, 30);
		b4.setBounds(450, 175, 100, 20);
		b4.setFont(new Font("휴먼둥근헤드라인",Font.BOLD,15));
		
		tf3.setBounds(450, 245, 250, 30);
		tf3.setEditable(false);
		tf3.setOpaque(false);
		tf3.setBorder(null);
		cb.setBounds(295, 280, 150, 30);
		
		av1.setBounds(100, 325, 105, 150);
		av2.setBounds(210, 325, 105, 150);
		av3.setBounds(320, 325, 105, 150);
		av4.setBounds(430, 325, 105, 150);
		av5.setBounds(540, 325, 105, 150);
		
		rb1.setBounds(145, 480, 30, 20);
		rb2.setBounds(255, 480, 30, 20);
		rb3.setBounds(365, 480, 30, 20);
		rb4.setBounds(475, 480, 30, 20);
		rb5.setBounds(585, 480, 30, 20);
		
		p1.setBounds(240, 510, 255, 50);
		b1.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,13));
		b2.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,13));
		
		add(la1);add(la2);add(la3);add(la4);add(la5);
		add(tf1);add(tf2);add(b3);add(pf1);add(pf2);
		add(b4);add(cb);add(tf3);add(p1);
		add(av1); add(av2); add(av3); add(av4); add(av5);
		add(rb1); add(rb2); add(rb3); add(rb4); add(rb5);
		
		pf2.addFocusListener(this);
		
		cb.addItemListener(this);
		
		av1.addMouseListener(this);
		av2.addMouseListener(this);
		av3.addMouseListener(this);
		av4.addMouseListener(this);
		av5.addMouseListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==cb){
			int index=cb.getSelectedIndex();
			char c='m';
			if(index==0){
				c='m';
			}else if (index == 1){
				c='f';
			}
			
			Image temp1 = new ImageIcon("resources/images/avatar_"+c+"_1.png").getImage().getScaledInstance(105, 150, Image.SCALE_SMOOTH);
			av1.setIcon(new ImageIcon(temp1));
			
			Image temp2 = new ImageIcon("resources/images/avatar_"+c+"_2.png").getImage().getScaledInstance(105, 150, Image.SCALE_SMOOTH);
			av2.setIcon(new ImageIcon(temp2));
			
			Image temp3 = new ImageIcon("resources/images/avatar_"+c+"_3.png").getImage().getScaledInstance(105, 150, Image.SCALE_SMOOTH);
			av3.setIcon(new ImageIcon(temp3));
			
			Image temp4 = new ImageIcon("resources/images/avatar_"+c+"_4.png").getImage().getScaledInstance(105, 150, Image.SCALE_SMOOTH);
			av4.setIcon(new ImageIcon(temp4));
			
			Image temp5 = new ImageIcon("resources/images/avatar_"+c+"_5.png").getImage().getScaledInstance(105, 150, Image.SCALE_SMOOTH);
			av5.setIcon(new ImageIcon(temp5));
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource()== pf2)
		{
			char[] pwd1 = pf1.getPassword();
			char[] pwd2 = pf2.getPassword();
			
			if (pwd1.length == 0){
				tf3.setText("비밀번호를  한번 더 입력하세요.");
				tf3.setForeground(Color.RED);
				tf3.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,10));
				tf3.setOpaque(false);
			} else {
				if(pwd1 != null){
					if(Arrays.equals(pwd1, pwd2) == true){
						checkPwd = true;
						tf3.setText("비밀번호가 일치 합니다.");
						tf3.setForeground(Color.WHITE);
						tf3.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,10));
						tf3.setOpaque(false);
					} else {
						tf3.setText("비밀번호가 일치하지가 않습니다.");
						tf3.setForeground(Color.RED);
						tf3.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,10));
						tf3.setOpaque(false);
						tf3.setBorder(null);
					}
				}
			}
		} 
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == av1) {
			rb1.setSelected(true);
		} else if (e.getSource() == av2) {
			rb2.setSelected(true);
		} else if (e.getSource() == av3) {
			rb3.setSelected(true);
		} else if (e.getSource() == av4) {
			rb4.setSelected(true);
		} else if (e.getSource() == av5) {
			rb5.setSelected(true);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}

