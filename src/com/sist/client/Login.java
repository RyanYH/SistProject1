package com.sist.client;

import java.awt.*;
import javax.swing.*;
public class Login extends JPanel{ //�߻�Ŭ���� - �ܵ����� �޸� �Ҵ��� ����
	JLabel la1,la2; // ������� �߻�Ŭ����
	JTextField tf; //���̵� �Է�â
	JPasswordField pf; // ��й�ȣ �Է�â
	JButton b1,b2,b3;
	Image img;//�������
	
	Login(){
		img = Toolkit.getDefaultToolkit().getImage("resources/images/��.gif");
		setLayout(null);//default �� flowlayout -> ��ư�������� 1�ڷ� ��ġ�Ҷ�
		
		la1 = new JLabel("I D");
		la2 = new JLabel("P W");
		tf=new JTextField();
		pf=new JPasswordField();
		b1=new JButton("ȸ������");
		b2=new JButton("�α���");
		b3=new JButton("����");
		
		//��ġ
		la1.setBounds(295, 300, 40, 30);
		la1.setForeground(Color.white); //�۾��� �ٲܶ� ����
		la1.setFont(new Font("Poplar Std",Font.ITALIC,15));
		tf.setBounds(330, 300, 150, 30);
		la2.setBounds(295, 335, 40, 30);
		la2.setForeground(Color.white);
		la2.setFont(new Font("Poplar Std",Font.ITALIC,15));
		pf.setBounds(330, 335, 150, 30);
		b1.setFont(new Font("�޸յձ�������",Font.ITALIC,13));
		b2.setFont(new Font("�޸յձ�������",Font.ITALIC,13));
		b3.setFont(new Font("�޸յձ�������",Font.ITALIC,13));
		
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
		g.drawImage(img, 0, 0, getWidth(), getHeight(),this); // ȭ�� ��� Ȯ���ҋ��� �̹����� ������ ����

	}
	
}
