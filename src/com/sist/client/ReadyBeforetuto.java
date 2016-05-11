package com.sist.client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.sist.user.UserDTO;


public class ReadyBeforetuto extends JPanel implements ActionListener{
	
	JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15;// ��ī�� ����
	JButton ct0,ct1,ct2,ct3,ct4,ct5,ct6,ct7,ct8,ct9,ct10,ct11,ct12,ct13,ct14,ct15,ct16,ct17,ct18,ct19,ct20,ct21,ct22,ct23,ct24,ct25;
	JButton btn5, btn6;
	JButton p2item1, p2item2, p2item3, p2item4;
	
	boolean[] btnChk = new boolean[16];
	
	JTextPane pane;
	JTextField tf;
	JComboBox box;
	
	Timer timer = new Timer();
	Image img;
	
	Border panelBorder = new LineBorder(Color.white, 3); 
	
	String nick = "";
	String avatar = "";
	
	JLabel p2id;
	ImageIcon cardBack2;
	JTextField p2tf;
	JLabel p2card;
	JPanel p2;
	
	public void initPanel(String nick, String avatar) {	
		ImageIcon r = new ImageIcon(avatar);
		Image i = r.getImage();
		Image newimg = i.getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH);

		p2tf.setText(nick);
		p2card.setIcon(new ImageIcon(newimg));
	}
	
	public ReadyBeforetuto(){
		img=Toolkit.getDefaultToolkit().getImage("resources/images/f.png");
		
        // Ʃ�丮�� �ΰ�
		Image image = new ImageIcon("resources/images/tuto3.gif").getImage();
		ImageIcon ad = new ImageIcon(image);
		JLabel label = new JLabel("", ad, JLabel.CENTER);
		JPanel p0 = new JPanel(new BorderLayout());
		p0.add( label );
	    add(p0);
	    p0.setBounds(30, 15, 400, 100);
//=====================================================
		//�� ���� �����ִ°�
		p2 = new JPanel();
		p2.setBorder(panelBorder);
		p2id = new JLabel("�г���");
		p2id.setForeground(Color.white);
		
		cardBack2 = new ImageIcon("resources/images/f.jpg");
		p2card = new JLabel(cardBack2);
		p2tf = new JTextField();
		p2tf.setEnabled(false);
		p2item1 = new RoundButton("item1");
		p2item1.setIcon(new ImageIcon("resources/images/test1.png"));
		p2item2 = new RoundButton("item2");
		p2item2.setIcon(new ImageIcon("resources/images/test2.png"));
		p2item3 = new RoundButton("item3");
		p2item3.setIcon(new ImageIcon("resources/images/test3.png"));
		p2item4 = new RoundButton("item4");
		p2item4.setIcon(new ImageIcon("resources/images/test4.png"));
		p2.setBackground(Color.BLACK);
		p2.setLayout(null);
		p2id.setBounds(20,15,50,17);
		p2card.setBounds(100,10,120,140);
		p2.add(p2id);
		p2tf.setBounds(15, 30, 90, 20);
		p2item1.setBounds(15, 55, 40, 40);
		p2item2.setBounds(60, 55, 40, 40);
		p2item3.setBounds(15, 100, 40, 40);
		p2item4.setBounds(60, 100, 40, 40);
		add(p2);
		p2.add(p2card);
		p2.add(p2tf);
		p2.add(p2item1);
		p2.add(p2item2);
		p2.add(p2item3);
		p2.add(p2item4);
		p2.setBounds(850, 680, 210, 150);
		
//======================================================
		//ä��â �Է�, ũ�� ����, ��ư�����
		
		pane=new JTextPane();
		pane.setEditable(false);
		JScrollPane js=new JScrollPane(pane);
		
		// ���� ���� �޼���
		initStyle();
		String color="red";
		append("������� �Դϴ�", color, "System");
		append("�������� 4x4 �౸���� ����˴ϴ�", color, "System");		
		
		tf=new JTextField(); //ä��â �Էºκ�
		tf.setText("����� �� �����ϴ�.");
		tf.setEditable(false);
		box=new JComboBox(); // �۾����ٲٴºκ�
		box.addItem("����");
		box.addItem("�Ķ�");
		box.addItem("��ȫ");
		box.addItem("���");
		box.addItem("�ʷ�");
		
		box.setEnabled(false);
		
		btn5 = new JButton("���� ����");
		btn6 = new JButton("�׸� �ϱ�");
		
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1, 3, 3));
		p.add(btn5);
		p.add(btn6);
		p.setOpaque(false);

		add(js);
		add(tf);
		add(box);
		add(p);

		setLayout(null);

		js.setBounds(850, 15, 210, 500);
		tf.setBounds(850, 520, 150, 25);
		box.setBounds(1005, 520, 55, 25);
		p.setBounds(850, 550, 210, 100);
//======================================================
		
		// ī�� �迭(��)
		String[] arrImg = new String[16];
		for(int i = 0; i < arrImg.length; i++){
			arrImg[i] = "resources/images/f.jpg";
		}
		
		// ī�� �迭(��)
		String[] arrImg1 = new String[11];
		for(int i = 0; i < arrImg1.length; i++){
			arrImg1[i] = "resources/images/userbackcard33.png";
		}
				
		// ���� �ε��� (�� ī��)
		int[] numArr1 = new int[11];
		for(int i = 0; i < numArr1.length; i++){
			numArr1[i] = (int)(Math.random()*9);
		}


		// ���� �ε��� (��� ī��)
		int[] numArr3 = new int[16];
		boolean check3;
		int su3 = 0;
		
		for(int i = 0; i < numArr3.length; i++){
			check3 = true;
			while(check3){
				su3 = (int)(Math.random()*16);
				
				check3 = false;
				for(int j = 0; j < i; j++){
					if (numArr3[j] == su3){
						check3 = true;
						break;
					}
				}
			}
			numArr3[i] = su3;
		}
		
		// �̹��� ���� (��ī��)
		ImageIcon i0 = new ImageIcon(arrImg1[numArr1[0]]);
		ImageIcon i1 = new ImageIcon(arrImg1[numArr1[1]]);
		ImageIcon i2 = new ImageIcon(arrImg1[numArr1[2]]);
		ImageIcon i3 = new ImageIcon(arrImg1[numArr1[3]]);
		ImageIcon i4 = new ImageIcon(arrImg1[numArr1[4]]);
		ImageIcon i5 = new ImageIcon(arrImg1[numArr1[5]]);
		ImageIcon i6 = new ImageIcon(arrImg1[numArr1[6]]);
		ImageIcon i7 = new ImageIcon(arrImg1[numArr1[7]]);
		ImageIcon i8 = new ImageIcon(arrImg1[numArr1[8]]);
		ImageIcon i9 = new ImageIcon(arrImg1[numArr1[9]]);
		ImageIcon i10 = new ImageIcon(arrImg1[numArr1[10]]);
		
		
		// �̹��� ���� (��� ī��)
		Image image1 = new ImageIcon("resources\\images\\f.jpg").getImage();
		Image newimg = image1.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);
		
		ImageIcon k0 = new ImageIcon(newimg);
		ImageIcon k1 = new ImageIcon(newimg);
		ImageIcon k2 = new ImageIcon(newimg);
		ImageIcon k3 = new ImageIcon(newimg);
		ImageIcon k4 = new ImageIcon(newimg);
		ImageIcon k5 = new ImageIcon(newimg);
		ImageIcon k6 = new ImageIcon(newimg);
		ImageIcon k7 = new ImageIcon(newimg);
		ImageIcon k8 = new ImageIcon(newimg);
		ImageIcon k9 = new ImageIcon(newimg);
		ImageIcon k10 = new ImageIcon(newimg);
		ImageIcon k11 = new ImageIcon(newimg);
		ImageIcon k12 = new ImageIcon(newimg);
		ImageIcon k13 = new ImageIcon(newimg);
		ImageIcon k14 = new ImageIcon(newimg);
		ImageIcon k15 = new ImageIcon(newimg);

		setLayout(null);
		Border thickBorder = new LineBorder(Color.CYAN, 5); // ��ư�� ������ ���� ǥ��

//======================================================
		//�� ī�� ��ư���� �߰�
		b0= new JButton(i0);
		b1 = new JButton(i1);
		b2 = new JButton(i2);
		b3 = new JButton(i3);
		b4 = new JButton(i4);
		b5 = new JButton(i5);
		b6 = new JButton(i6);
		b7 = new JButton(i7);
		b8 = new JButton(i8);
		b9 = new JButton(i9);
		b10 = new JButton(i10);
		//b0.setBorder(thickBorder);
//======================================================
		//�߾� 9�� �ѷ��ֱ�
		ct0 = new JButton(k0);
		ct1 = new JButton(k1);
		ct2 = new JButton(k2);
		ct3 = new JButton(k3);
		ct4 = new JButton(k4);
		ct5 = new JButton(k5);
		ct6 = new JButton(k6);
		ct7 = new JButton(k7);
		ct8 = new JButton(k8);
		ct9 = new JButton(k9);
		ct10 = new JButton(k10);
		ct11 = new JButton(k11);
		ct12 = new JButton(k12);
		ct13 = new JButton(k13);
		ct14 = new JButton(k14);
		ct15 = new JButton(k15);
//======================================================

		add(b10);
		add(b9);
		add(b8);
		add(b7);
		add(b6);
		add(b5);
		add(b4);
		add(b3);
		add(b2);
		add(b1);
		add(b0);
		
		add(ct0);
		add(ct1);
		add(ct2);
		add(ct3);
		add(ct4);
		add(ct5);
		add(ct6);
		add(ct7);
		add(ct8);
		add(ct9);
		add(ct10);
		add(ct11);
		add(ct12);
		add(ct13);
		add(ct14);
		add(ct15);

//======================================================
		//��ī�� ��ġ����
		
		b0.setBounds(15,730,70,100);
		b1.setBounds(90, 730, 70, 100);
		b2.setBounds(165,730,70,100);
		b3.setBounds(240,730,70,100);
		b4.setBounds(315,730,70,100);
		b5.setBounds(390,730,70,100);
		b6.setBounds(465,730,70,100);
		b7.setBounds(540,730,70,100);
		b8.setBounds(615,730,70,100);
		b9.setBounds(690,730,70,100);
		b10.setBounds(765,730,70,100);
		
//======================================================
		//���� ī�� ��ġ����
		ct0.setBounds(100,170,130,130);
		ct1.setBounds(255,170,130,130);
		ct2.setBounds(410,170,130,130);
		ct3.setBounds(565,170,130,130);
		
		ct4.setBounds(100,305,130,130);
		ct5.setBounds(255,305,130,130);
		ct6.setBounds(410,305,130,130);
		ct7.setBounds(565,305,130,130);
		
		ct8.setBounds(100,440,130,130);
		ct9.setBounds(255,440,130,130);
		ct10.setBounds(410,440,130,130);
		ct11.setBounds(565,440,130,130);
		
		ct12.setBounds(100,575,130,130);
		ct13.setBounds(255,575,130,130);
		ct14.setBounds(410,575,130,130);
		ct15.setBounds(565,575,130,130);
		
		//�׼Ǹ����� �߰�
		tf.addActionListener(this);
	}		
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);	
	}
	
	public void initStyle(){
		//default ����
		Style def=StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		Style green=pane.addStyle("�ʷ�", def);
		StyleConstants.setForeground(green, Color.green);
		Style pink=pane.addStyle("��ȫ", def);
		StyleConstants.setForeground(pink, Color.pink);
		Style yellow=pane.addStyle("���", def);
		StyleConstants.setForeground(yellow, Color.yellow);
		Style blue=pane.addStyle("�Ķ�", def);
		StyleConstants.setForeground(blue, Color.blue);
		Style red=pane.addStyle("red", def);
		StyleConstants.setForeground(red, Color.red);	
	}
	
	public void append(String msg,String color, String id)
	{
		Document doc=pane.getDocument();
		try {
			doc.insertString(doc.getLength(), "["+id+"] "+msg+"\n", pane.getStyle(color));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == tf){
			String data=tf.getText();
			if(data.length()<1){
				return;
			}
			initStyle();
			String color=box.getSelectedItem().toString();
			append(data, color, nick);
			tf.setText("");
			tf.requestFocus();
		}
	}
	
}
