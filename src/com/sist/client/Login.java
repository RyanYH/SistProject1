package com.sist.client;

import java.awt.*;
import javax.swing.*;
public class Login extends JPanel{ //추상클래스 - 단독으로 메모리 할당을 못함
	JLabel la1,la2; // 여기부터 추상클래스
	JTextField tf; //아이디 입력창
	JPasswordField pf; // 비밀번호 입력창
	JButton b1,b2,b3;
	Image img;//여기까지
	
	Login(){
		img = Toolkit.getDefaultToolkit().getImage("resources/images/포.gif");
		setLayout(null);//default 가 flowlayout -> 버튼여러개를 1자로 배치할때
		
		la1 = new JLabel("I D");
		la2 = new JLabel("P W");
		tf=new JTextField();
		pf=new JPasswordField();
		b1=new JButton("회원가입");
		b2=new JButton("로그인");
		b3=new JButton("종료");
		
		//배치
		la1.setBounds(295, 300, 40, 30);
		la1.setForeground(Color.white); //글씨색 바꿀때 쓰임
		la1.setFont(new Font("Poplar Std",Font.ITALIC,15));
		tf.setBounds(330, 300, 150, 30);
		la2.setBounds(295, 335, 40, 30);
		la2.setForeground(Color.white);
		la2.setFont(new Font("Poplar Std",Font.ITALIC,15));
		pf.setBounds(330, 335, 150, 30);
		b1.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,13));
		b2.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,13));
		b3.setFont(new Font("휴먼둥근헤드라인",Font.ITALIC,13));
		
		JPanel p = new JPanel();
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.setBounds(245,370,300,35);
		p.setOpaque(false);
		
		add(la1);
		add(tf);
		add(la2);
		add(pf);
		add(p);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(),this); // 화면 축소 확대할떄도 이미지가 꽉차게 나옴

	}
	
}
