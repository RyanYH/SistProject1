package com.sist.client;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import com.sist.user.UserDTO;
import javax.swing.text.Document;
import javax.swing.text.BadLocationException;

public class WaitRoom extends JPanel implements MouseListener{
	JTable table1,table2;
	DefaultTableModel model1,model2,model4;
	JTextField tf;
	JComboBox box;
	JButton b1,b2,b3,b4,b5,b6,b7;
	JTextPane pane;
	Image img;
	String nick;
	JPanel la;
	JScrollBar bar;
	
	public void showMessage(String nick) {
		// ���� ���� �޼���
		initStyle();
		String color="red";
		append("���� ���� �ϼ̽��ϴ�.", color, nick);
	}
	
	public WaitRoom(){
		String[] col1={"���ȣ","���̸�","���̵�","��������", "�ο�"};
		String[][] row1=new String[0][5];
		model1=new DefaultTableModel(row1, col1){

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		table1=new JTable(model1);
		table1.getTableHeader().setResizingAllowed(false);
		table1.getTableHeader().setReorderingAllowed(false);
		table1.setFont(new Font("�޸յձ�������",Font.ITALIC,13));
		JScrollPane js1=new JScrollPane(table1);
		
		img = Toolkit.getDefaultToolkit().getImage("resources/images/back08.png");
		
		pane=new JTextPane();
		pane.setEditable(false);
		JScrollPane js=new JScrollPane(pane);
		bar = js.getVerticalScrollBar();
		
		la = new JPanel();
		la.setOpaque(false);
		
		tf=new JTextField(20);
		box=new JComboBox();
		box.addItem("����");
		box.addItem("�Ķ�");
		box.addItem("��ȫ");
		box.addItem("���");
		box.addItem("�ʷ�");
		
		b1=new JButton("�游���");
		b2=new JButton("�����");
		b3=new JButton("���Ӽ���");
		b4=new JButton("�������");
		b5=new JButton("��������");
		b6=new JButton("�α׾ƿ�");
		b7=new JButton("������");
		
		b1.setFont(new Font("�޸յձ�������",Font.ITALIC,15));
		b2.setFont(new Font("�޸յձ�������",Font.ITALIC,15));
		b3.setFont(new Font("�޸յձ�������",Font.ITALIC,15));
		b4.setFont(new Font("�޸յձ�������",Font.ITALIC,15));
		b5.setFont(new Font("�޸յձ�������",Font.ITALIC,15));
		b6.setFont(new Font("�޸յձ�������",Font.ITALIC,15));
		b7.setFont(new Font("�޸յձ�������",Font.ITALIC,15));
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(5,1,5,5));
		p.add(b1);p.add(b2);
		p.add(b3);p.add(b4);
		p.add(b5);
		p.setOpaque(false);
		
		JPanel p2=new JPanel();
		p2.setLayout(new GridLayout(1, 2));
		p2.add(b6);
		p2.add(b7);
		p2.setOpaque(false);
		
		setLayout(null);

		Image image = new ImageIcon("resources/images/ad6.gif").getImage();
		ImageIcon ad = new ImageIcon(image);
		JLabel label = new JLabel("", ad, JLabel.CENTER);
		JPanel p1 = new JPanel(new BorderLayout());
		p1.add( label, BorderLayout.CENTER );
		
		p1.setBounds(680,50,280,115);
		p.setBounds(30, 200, 150, 450);
		js.setBounds(190, 200, 500, 400);
		tf.setBounds(190, 620, 400, 30);
		box.setBounds(600, 620, 90, 30);
		js1.setBounds(700, 200, 250, 390);
		p2.setBounds(700, 600, 250, 50);
		la.setBounds(222,663,600,30);
		
		//tf.addActionListener(this);
		la.addMouseListener(this);
		
		add(js1);
		add(js);
		add(tf);
		add(box);
		add(p);
		add(p1);
		add(p2);
		add(la);
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
	
	public void append(String msg, String color, String nick){
		Document doc=pane.getDocument();
		try {
			doc.insertString(doc.getLength(), "["+nick+"] "+msg+"\n", pane.getStyle(color));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(),getHeight(),this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		 if (e.getSource() == la){
				System.out.println("la click");
				if (Desktop.isDesktopSupported()) {
	                Desktop desktop = Desktop.getDesktop();
	                try {
	                    URI uri = new URI("http://www.sist.co.kr/intro/intro.do?cmd=5");
	                    desktop.browse(uri);
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                } 
				}
			}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

