package com.sist.client;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ItemClass {
	
	Border thickBorder = new LineBorder(Color.RED, 5);
	Border thinBorder=new LineBorder(null);
	
	public void setItem3(JButton[] a){
		for (int i=0; i<a.length; i++) {
			a[i].setBorder(thinBorder);
		}
		a[0].setBorder(thickBorder);
	}
	
	public ImageIcon[] setItem1(int[] numArr, String[] arrImg, ImageIcon[] j, JButton[] b, char level, int a) {		
		for(int i = 0; i < numArr.length; i++){
			numArr[i] = (int)(Math.random()*a);
		}
		for(int i = 0; i < arrImg.length; i++){
			arrImg[i] = "resources/images/170/"+level+i+".png";
		}
		for(int n = 0; n < j.length; n++){
			j[n] = new ImageIcon(arrImg[numArr[n]]);
		}
		
		for(int n = 0; n < b.length; n++){
			Image image = j[n].getImage();
			Image newimg = image.getScaledInstance(b[0].getWidth(), b[0].getHeight(),  java.awt.Image.SCALE_SMOOTH);
			ImageIcon cI = new ImageIcon(newimg);
			
			b[n].setIcon(cI);
		}
		
		ImageIcon[] result = j;
		return result;
	}
	
	public ImageIcon[] MysetItem1(int[] numArr, String[] arrImg, ImageIcon[] j, JButton[] b, char level, int a) {		
		for(int i = 0; i < numArr.length; i++){
			numArr[i] = (int)(Math.random()*a);
		}
		for(int i = 0; i < arrImg.length; i++){
			arrImg[i] = "resources/images/170/"+level+i+".png";
		}
		for(int n = 0; n < j.length; n++){
			j[n] = new ImageIcon(arrImg[numArr[n]]);
		}
		
		ImageIcon[] result = j;
		return result;
	}
	
	public ImageIcon[] setItem4(int[] numArr, String[] arrImg, ImageIcon[] k, char level, int a){
		boolean check3;
		int su3 = 0;
		for(int i = 0; i < numArr.length; i++){
			check3 = true;
			while(check3){
				su3 = (int)(Math.random()*a);
				check3 = false;
				for(int j = 0; j < i; j++){
					if (numArr[j] == su3){
						check3 = true;
						break;
					}
				}
			}
			numArr[i] = su3;
		}
		
		for(int i = 0; i < arrImg.length; i++){
			arrImg[i] = "resources/images/170/"+level+i+".png";
		}
		
		for(int i = 0; i < k.length; i++){		
			k[i] = new ImageIcon(arrImg[numArr[i]]);
	    }
		
		ImageIcon[] result = k;
		return result;
	}
}
