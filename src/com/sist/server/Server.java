package com.sist.server;

import java.util.*;
import java.io.*;
import java.net.*;
import com.sist.common.*;
import com.sist.server.Server.Client;
import com.sist.user.*;

public class Server implements Runnable {
	ServerSocket ss;
	final int PORT=19872;
	Vector<Client> waitVc=new Vector<Client>();
	Vector<Room> roomVc = new Vector<Room>();
	static String roomNum = "0";
	
	UserDTO d = new UserDTO();
	UserDAO dao;
	
	static int i = 0;
	
	public Server() {
		try{
			ss=new ServerSocket(PORT);
			dao=UserDAO.newInstance();
			System.out.println("Server Start");
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Server server=new Server();
        new Thread(server).start();
	}

	@Override
	public void run() {
		try{
			while(true){
				Socket s=ss.accept();
				Client client=new Client(s);
				client.start();
			}
		}catch(Exception ex){
			
		}
		
	}
	
	public String rand(int length){
		String res = "";
		int[] numArr3 = new int[length];
		
		// ·£´ý ÀÎµ¦½º (°¡¿îµ¥ Ä«µå)
		boolean check3;
		int su3 = 0;
		for(int i = 0; i < numArr3.length; i++){
			check3 = true;
			while(check3){
				su3 = (int)(Math.random()*9);
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
		
		
		for(int i = 0; i < numArr3.length; i++){
			if (i != numArr3.length-1){
				res = res + numArr3[i]+"/";
			} else {
				res = res + numArr3[i];
			}
		}
		
		return res;
	}
	
	public class Client extends Thread {
		Socket s;
    	BufferedReader in;
    	OutputStream out;
    	
    	String id;
		String pwd;
		String chat;
		String nickname;
		String gender;
		String avatar;
		boolean bCheck;
		
		public Client(Socket s){
			try {
				this.s=s;
    			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
    			out=s.getOutputStream();
			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}

		@Override
		public void run() {
			try {
				while(true){
					String msg=in.readLine();
					StringTokenizer st=new StringTokenizer(msg, "|");
					
					System.out.println("server : " +msg);
					
					int protocol = Integer.parseInt(st.nextToken());

					switch(protocol){
					case Function.CLICKCENTER : {
						String id1 = st.nextToken();
						String card = st.nextToken();
						String p = st.nextToken();
						
						for(Client c : Room.userVc){
							c.out.write((Function.CLICKCENTER + "|" + id1 + "|" + card + "|" + p + "\n").getBytes());
						}
					}
					break;
					
					case Function.ENDGAME : {
						String mid = st.nextToken();
						String yid = st.nextToken();
						
						for (Client c : Room.userVc){
							c.out.write((Function.ENDGAME + "|" + " " + "|" + mid + "|" + yid + "\n").getBytes());
						}
					}
					break;
					
					case Function.MOVEBORDER : {
						String id1 = st.nextToken();
						String n = st.nextToken();
						
						for (Client c : Room.userVc){
							if (c.id.equals(id1)){
								c.out.write((Function.MOVEBORDER + "|" + id1 + "|" + n + "\n").getBytes());
							} else {
								c.out.write((Function.MYMOVEBORDER + "|" + id1 + "|" + n + "\n").getBytes());
							}
						}
					}
					break;
					
					case Function.GAMETURN :{
    					String rn=st.nextToken();
    					String yid=st.nextToken();
    					for(int i=0; i<roomVc.size(); i++){
    						Room room = roomVc.elementAt(i);
    						if(rn.equals(String.valueOf(room.roomNum))){
    							for(int j=0; j<room.userVc.size(); j++){
    								Client client = room.userVc.elementAt(j);
    								if(client.id.equals(id)){
    		                               client.sendTo(Function.GAMETURN+"|"+" " + "|" + "false");
    		                            }else{
    		                               client.sendTo(Function.GAMETURN+"|"+" " + "|" + "true");
    		                            }
    							}
    						}
    					}
					}
					break;
					
					case Function.GAMEREADY : {
    					String rn = st.nextToken();
    					
    					for(int i = 0; i < roomVc.size(); i++){
    						Room room = roomVc.elementAt(i);
    						System.out.println("room.roomNum : " + room.roomNum);
    						
    						if(rn.equals(String.valueOf(room.roomNum))){
    							for(int j = 0; j < room.userVc.size(); j++){
    								Client client = room.userVc.elementAt(j);
    								
    								if (client.id.equals(id)){
    									bCheck = true;
    								}
    							}
    							if (room.userVc.elementAt(0).bCheck == true && room.userVc.elementAt(1).bCheck == true){
    								for(int j = 0; j < room.userVc.size(); j++){
	    								Client client = room.userVc.elementAt(j);
	    								client.sendTo(Function.GAMEREADY + "|" + id + "|" + room.roomBang);
	    							}
    							}
    							
    						}
    					}
    				}
    				break;
    				
					case Function.GAMEITEM1 : {
						String id1 = st.nextToken();
						for (Client c : Room.userVc){
							if (c.id.equals(id1)){
								c.out.write((Function.GAMEITEM1 + "|" + id + "\n").getBytes());
							} else {
								c.out.write((Function.MYGAMEITEM1 + "|" + id + "\n").getBytes());
							}
						}
					}
					break;
					
					case Function.GAMEITEM2 : {
						String id1 = st.nextToken();
						for (Client c : Room.userVc){
							if (c.id.equals(id1)){
								c.out.write((Function.MYGAMEITEM2 + "|" + id + "\n").getBytes());
							} else {
								c.out.write((Function.GAMEITEM2 + "|" + id + "\n").getBytes());
							}
						}
					}
					break;
					
					case Function.GAMEITEM3 : {
						String id1 = st.nextToken();
						for (Client c : Room.userVc){
							if (c.id.equals(id1)){
								c.out.write((Function.MYGAMEITEM3 + "|" + id + "\n").getBytes());
							} else {
								c.out.write((Function.GAMEITEM3 + "|" + id + "\n").getBytes());
							}
						}
					}
					break;
					
					case Function.GAMEITEM4 : {
						String id1 = st.nextToken();
						
						String rand = rand(9);
						
						for (Client c : Room.userVc){
							if (c.id.equals(id1)){
								c.out.write((Function.GAMEITEM4 + "|" + id + "|" + rand + "\n").getBytes());
							} else {
								c.out.write((Function.MYGAMEITEM4 + "|" + id + "|" + rand + "\n").getBytes());
							}
						}
					}
					break;
					
					case Function.LOGIN : {
						id = st.nextToken();
						pwd = st.nextToken();
						
						UserDTO d = dao.userDataByid(id);
						nickname = d.getNickname();
						gender = d.getGender();
						avatar = d.getAvatar();
						
						boolean loginCheck = dao.isLogin(id, pwd);
						
						if (loginCheck){
							waitVc.addElement(this);
						} else {
							return;
						}
						
						out.write((Function.LOGIN + "|" + id + "|" + loginCheck + "\n").getBytes());
						sendAll(Function.WAITCHAT + "|" + "System" + "|"+(id+" ´ÔÀÌ Á¢¼ÓÇÏ¼Ì½À´Ï´Ù")+"|"+"red");
						
						
						d = dao.userDataByid(id);
						out.write((Function.GETUSER+"|"+id+"|"+d.getNickname()+"|"+d.getGender()+"|"+d.getAvatar()+"\n").getBytes());
						
						for(Room room : roomVc) {
							sendTo(Function.MAKEROOM+"|"+room.getRoomNum()+"|"+room.getRoomBang()+"|"+room.getRoomTitle()+"|"+room.getRoomLevel()+"|"+room.getRoomCate()+"|"+room.getInwon());
						}
						
					}
						break;
					case Function.LOGMSG : {
						sendAll(Function.LOGMSG + "|" + id + "\n");
					}
						break;
					case Function.WAITCHAT : {
						id = st.nextToken();
						chat = st.nextToken();						
						String color = st.nextToken();
						
						sendAll(Function.WAITCHAT+"|"+id+"|"+chat+"|"+color);
					}
						break;	
					case Function.ROOMCHAT :{
						String id = st.nextToken();
						String nick = st.nextToken();
						String chat = st.nextToken();
						String color = st.nextToken();
					
						sendAll(Function.ROOMCHAT+"|"+"|"+ id + "|" + nickname+"|"+chat+"|"+color);
					}
					
						break;	
					case Function.GAMECHAT : {
						String id = st.nextToken();
						String nick = st.nextToken();
						String chat = st.nextToken();
						String color = st.nextToken();
					
						sendAll(Function.GAMECHAT+"|"+id + "|" + nick+"|"+chat+"|"+color);
					}
						break;
					case Function.CHATEND : {
						id = st.nextToken();
			   
			    		sendAll(Function.CHATEND + "|" + id);
    					sendTo(Function.CHATEND + "|");
    					
						for (int i = 0; i < waitVc.size(); i++){
							Client c = waitVc.elementAt(i);
							if (c.id.equals(id)){
								waitVc.removeElementAt(i);
								in.close();
    							out.close();
							}
						}
					}
						break;	
					case Function.LOGOUT : {
						id = st.nextToken();
						
						for (int i = 0; i < waitVc.size(); i++){
							Client c = waitVc.elementAt(i);
							if (c.id.equals(id)){
								waitVc.removeElementAt(i);
							}
						}
						
						out.write((Function.LOGOUT + "|" + id + "\n").getBytes());
					}
						break;
					case Function.SIGNUP : {
						id = st.nextToken();
						nickname = st.nextToken();
						pwd = st.nextToken();
						gender = st.nextToken();
						avatar = st.nextToken();
						
						boolean signCheck = dao.userInsert(id, nickname, pwd, gender, avatar);
						out.write((Function.SIGNUP + "|" + id + "\n").getBytes());
					}
					break;	
						
					case Function.CHECKID : {
						id = st.nextToken();
						boolean checkId = dao.checkID(id);
						out.write((Function.CHECKID + "|" + id + "|" + checkId + "\n").getBytes());
					}
					break;	
					
					case Function.CHECKNICK : {
						nickname = st.nextToken();
						boolean checkNick = dao.checkNick(nickname);
						out.write((Function.CHECKNICK + "|" + nickname + "|" + checkNick + "\n").getBytes());
					}
					break;		
					
					case Function.GETUSER : {
						id = st.nextToken();
						d = dao.userDataByid(id);
						out.write((Function.GETUSER + "|" + id + "|" + d.getNickname() + "|" + d.getGender() + "|" + d.getAvatar() + "\n").getBytes());
					}
					break;
					
					case Function.MAKEROOM : {
						roomNum = String.valueOf(Integer.parseInt(roomNum)+1);
						
						Room room = new Room(roomNum, st.nextToken(), st.nextToken(), st.nextToken(),st.nextToken());
						
						room.roomBang = id;
						
						sendAll(Function.MAKEROOM+"|"+room.getRoomNum()+"|"+room.getRoomBang()+"|"+room.getRoomTitle()+"|"+room.getRoomLevel()+"|"+room.getRoomCate()+"|"+room.getInwon());
						
						room.userVc.addElement(this);
						roomVc.addElement(room);
						
						sendTo(Function.MYROOMIN+"|"+room.getRoomNum()+"|"+id+"|"+nickname+"|"+gender+"|"+avatar+"|"+room.roomBang);
			
					}
					break;
						
					case Function.ROOMIN : {
						
					}
					break;
					
					case Function.ORDERCHOICE : {
						String id = st.nextToken();
						String level = st.nextToken();
						int length = Integer.parseInt(st.nextToken());
						String cate = st.nextToken();
						
						String rand = rand(length);
						
						for (Client c : Room.userVc){
							c.out.write((Function.SETCENTERCARD + "|" + id + "|" + rand + "\n").getBytes());
							c.sendTo(Function.ORDERCHOICE+"|"+id+"|" +level + "|" + cate);
						}
					}
					break;
					
					case Function.MYROOMIN : {
						String roomNum = st.nextToken();
						
						for(Room r : roomVc){
							if (roomNum.equals(String.valueOf(r.getRoomNum()))){
								
								r.setInwon(r.getInwon()+1);
								
								for(Client c : r.userVc){
									c.sendTo(Function.ROOMCHAT+"|"+id + "|" + nickname+"| ´ÔÀÌ ÀÔÀåÇÏ¼Ì½À´Ï´Ù"+"|"+"red");
									c.sendTo(Function.ROOMIN+"|"+" "+"|"+id+"|"+nickname+"|"+gender+"|"+avatar+"|"+r.getRoomBang());
									c.sendTo(Function.GAMEINIT + "|" + id);
								}
								
								sendTo(Function.ROOMIN+"|"+r.getRoomNum()+"|"+id+"|"+nickname+"|"+gender+"|"+r.getRoomNum()+"|"+r.getRoomBang());
								r.userVc.addElement(this);
								
								for(Client c : r.userVc){
									if(!id.equals(c.id)){
										sendTo(Function.ROOMIN+"|"+" "+"|"+c.id+"|"+c.nickname+"|"+c.gender+"|"+c.avatar+"|"+r.getRoomBang());
										sendTo(Function.GAMEINIT + "|" + id);
									}
								}
								sendAll(Function.WAITUPDATE+"|"+id+"|"+r.getRoomNum()+"|"+r.getInwon());
							}
						}
					}
					break;
					
					case Function.ROOMOUT : {
						String roomNum = st.nextToken();
						int i = 0;
						
						for(Room r : roomVc){
							if (roomNum.equals(String.valueOf(r.getRoomNum()))){
								if (id.equals(r.getRoomBang())){
									if (r.getInwon() > 1){
										r.setRoomBang((r.userVc.elementAt(1)).id);
									}		
								}
								
								r.setInwon(r.getInwon()-1);
								
								if (r.getInwon() == 1){
									for(Client c : r.userVc){
										c.sendTo(Function.ROOMCHAT+"|"+id + "|" + nickname+"| ´ÔÀÌ ÅðÀåÇÏ¼Ì½À´Ï´Ù"+"|"+"red");
										c.sendTo(Function.ROOMOUT+"|"+" "+"|"+id+"|"+nickname);
									}
									
									int k = 0;
									for(Client c : r.userVc){
										if (!id.equals(c.id)){
											sendTo(Function.MYROOMOUT+"|"+" "+"|"+id+"|"+nickname);
											r.userVc.removeElementAt(k);
										}
										k++;
									}
									sendAll(Function.WAITUPDATE+"|"+id+"|"+r.getRoomNum()+"|"+r.getInwon());
								} else {
									sendTo(Function.MYROOMOUT+"|"+" "+"|"+id+"|"+nickname);
									r.userVc.removeElementAt(0);
									sendAll(Function.WAITUPDATE+"|"+id+"|"+r.getRoomNum()+"|"+r.getInwon());
								}
								
								if (r.getInwon() < 1){
    								roomVc.removeElementAt(i);
    							}
							}
							i++;
						}
						
					}
					break;

					case Function.GAMETURNS :{
    					String rn =st.nextToken();
    					String id =st.nextToken();
    					String tCheck = st.nextToken();
    					String bCheck = "";
    					
    					if (tCheck.equals("true")) {
    						bCheck = "false";
    					} else {
    						bCheck = "true";
    					}
    					
    					for(int i=0; i<roomVc.size(); i++){
    						Room room = roomVc.elementAt(i);
    						if(rn.equals(String.valueOf(room.roomNum))){
    							for(int j=0; j<room.userVc.size(); j++){
    								Client client = room.userVc.elementAt(j);
    								if(client.id.equals(id)){
    		                               client.out.write((Function.GAMETURNS+"|"+" "+"|"+ tCheck+"\n").getBytes());
    		                            }else{
    		                            	client.out.write((Function.GAMETURNS+"|"+" "+"|"+ bCheck+"\n").getBytes());
    		                            }
    							}
    						}
    					}
					}
					break;
					
					case Function.SETTURN :{
    					String rn =st.nextToken();
    					String id =st.nextToken();
    					String tCheck = st.nextToken();
    					String bCheck = "";
    					
    					if (tCheck.equals("true")) {
    						bCheck = "false";
    					} else {
    						bCheck = "true";
    					}
    					for(int i=0; i<roomVc.size(); i++){
    						Room room = roomVc.elementAt(i);
    						if(rn.equals(String.valueOf(room.roomNum))){
    							for(int j=0; j<room.userVc.size(); j++){
    								Client client = room.userVc.elementAt(j);
    								if(client.id.equals(id)){
    		                               client.out.write((Function.SETTURN+"|"+" "+"|"+ tCheck+"\n").getBytes());
    		                            }else{
    		                            	client.out.write((Function.SETTURN+"|"+" "+"|"+ bCheck+"\n").getBytes());
    		                            }
    							}
    						}
    					}
					}
					break;
					
					}
				}
			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		
		public synchronized void sendTo(String msg){
			try{
    			out.write((msg+"\n").getBytes());
    		} catch(Exception ex){
    			for(int i=0;i<waitVc.size();i++){
    				Client client=waitVc.elementAt(i);
    				if(id.equals(client.id)){
    					waitVc.removeElementAt(i);
    					break;
    				}
    			}
    		}
		}
		
		public synchronized void sendAll (String msg){
			for(int i=0;i<waitVc.size();i++){
				Client client=waitVc.elementAt(i);

				try{
    				client.sendTo(msg);
    			} catch(Exception ex){
    				waitVc.removeElementAt(i);
    			}
    		}
		}
	}

}
