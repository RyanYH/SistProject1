package com.sist.server;

import java.util.Vector;

public class Room {
	int roomNum;
	String roomTitle;
	String roomLevel;
	String roomCate;
	String roomBang;
	int inwon;
	
	static Vector<Server.Client> userVc = new Vector<Server.Client>();
	
	public Room(String rn, String rt, String lv, String ct, String inwon){
		roomNum = Integer.parseInt(rn);
		roomTitle = rt;
		roomLevel = lv;
		roomCate = ct;
		this.inwon = Integer.parseInt(inwon);
	}
	
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getRoomTitle() {
		return roomTitle;
	}
	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}
	public String getRoomLevel() {
		return roomLevel;
	}
	public void setRoomLevel(String roomLevel) {
		this.roomLevel = roomLevel;
	}
	public String getRoomCate() {
		return roomCate;
	}
	public void setRoomCate(String roomCate) {
		this.roomCate = roomCate;
	}
	public String getRoomBang() {
		return roomBang;
	}
	public void setRoomBang(String roomBang) {
		this.roomBang = roomBang;
	}
	public int getInwon() {
		return inwon;
	}
	public void setInwon(int inwon) {
		this.inwon = inwon;
	}

}
