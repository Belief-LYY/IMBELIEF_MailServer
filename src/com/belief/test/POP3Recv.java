package com.belief.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;;

/**
 * 
 * @author KING-ERIC
 * 
 */
public class POP3Recv extends TransportModel {

	// 静态内部类，在该内部类中创建单例对象，再将该单例对象通过getInstance()方法返回给外部使用
	private static class InternalClass {
		private final static POP3Recv POP3Recv = new POP3Recv();
	}
	// 公有静态成员方法，返回唯一实例
	public static synchronized POP3Recv getPOP3Recv() {
		return InternalClass.POP3Recv;
	}

	// 公有静态成员方法，返回唯一实例
	public static synchronized POP3Recv getPOP3Recv(String Host, int Port) {
		InternalClass.POP3Recv.POP3Host = Host;
		InternalClass.POP3Recv.POP3Port = Port;
		return InternalClass.POP3Recv;
	}

	// 测试Main函数
	public static void main(String[] args) {
		POP3Recv aPOP3Recv = POP3Recv.getPOP3Recv();
		aPOP3Recv.setPOP3Host("127.0.0.1");
		aPOP3Recv.setPOP3Port(110);
		aPOP3Recv.setUsername("296293760");
		aPOP3Recv.setPassword("vzujaubksmhvbhc");
		aPOP3Recv.Login();
		aPOP3Recv.getList();
		aPOP3Recv.getState();
		aPOP3Recv.getUIDL("1.0179314590686979");
		aPOP3Recv.getContent("1.0179314590686979");
		aPOP3Recv.Delete("1.0179314590686979");
		aPOP3Recv.Quit();
	}

	public static synchronized boolean SaveFile(byte[] Data, String Path, String FileName) {
		File SaveFile = new File(Path, FileName);
		try {
			SaveFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileOutputStream iFileOutputStream = null;
		try {
			iFileOutputStream = new FileOutputStream(SaveFile);
			iFileOutputStream.write(Data);
			iFileOutputStream.flush();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (iFileOutputStream != null) {
				try {
					iFileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 邮件服务器域名地址
	private String POP3Host = null;

	// 邮件服务器端口，正常是110，腾讯SSL加密是995
	private int POP3Port;

	// 私有构造函数
	private POP3Recv() {

	}

	public synchronized String Delete(String Message) {
		if (isConnected) {
			System.out.println("<------- 删除 ------->");
			Send("DELE " + Message + CRLF);
			return GetResponse();
		}
		return null;
	}

	public synchronized String getContent(String Message) {
		if (isConnected) {
			Query("RETR " + Message);
			return GetResponses();
		}
		return null;
	}

	public synchronized String getList() {
		if (isConnected) {
			Query("LIST");
			return GetResponses();
		}
		return null;
	}

	public String getPassword() {
		return Password;
	}

	public String getPOP3Host() {
		return POP3Host;
	}

	public int getPOP3Port() {
		return POP3Port;
	}

	private String GetResponse() {
		String aString = null;
		try {
			aString = aInputStream.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("R: " + aString);
		return aString;
	}

	private String GetResponses() {
		String aString = null;
		StringBuffer aStringBuffer = new StringBuffer();
		try {
			/* LIST和RETR命令的应答虽然有多行，但都用一句"."作为结束，可据此取应答信息 */
			for (aString = aInputStream.readLine(); (!aString.equals(".")); aString = aInputStream.readLine()) {
				aStringBuffer.append(aString + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(aStringBuffer.toString());
		return aStringBuffer.toString();
	}

	public synchronized String getState() {
		if (isConnected) {
			try {
				System.out.println("<------- 查询 ------->");
				Send("STAT" + CRLF);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return GetResponse();
		}
		return null;
	}

	public synchronized String getUIDL(String Message) {
		if (isConnected) {
			Query("UIDL " + Message);
			return GetResponse();
		}
		return null;
	}

	public String getUsername() {
		return Username;
	}

	public synchronized boolean Login() {
		if (POP3Host == null || POP3Port == 0 || Username == null || Password == null) {
			try {
				throw new Exception("数据未初始化...");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return isConnected;
			}
		}
		if (!isConnected) {
			try {
				if (POP3Port == 110)
					iSocket = new Socket(POP3Host, POP3Port);
				else
					iSocket = SSLSocketFactory.getDefault().createSocket(POP3Host, POP3Port);
				aOutputStream = new BufferedWriter(new OutputStreamWriter(iSocket.getOutputStream()));
				aInputStream = new BufferedReader(new InputStreamReader(iSocket.getInputStream()));

				GetResponse();

				System.out.println("<------- 登陆 ------->");
				Send("USER " + Username + CRLF);
				GetResponse();
				Send("PASS " + Password + CRLF);
				GetResponse();

			} catch (UnknownHostException e) {
				e.printStackTrace();
				return isConnected;
			} catch (IOException e) {
				e.printStackTrace();
				return isConnected;
			} catch (Exception e) {
				e.printStackTrace();
				return isConnected;
			}
			isConnected = true;
		}
		return isConnected;
	}

	private void Query(String Message) {
		try {
			System.out.println("<------- 查询 ------->");
			Send(Message + CRLF);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized boolean Quit() {
		System.out.println("<------- 退出 ------->");
		Send("QUIT" + CRLF);
		isConnected = false;
		POP3Host = null;
		POP3Port = 0;
		Username = null;
		Password = null;
		if (aInputStream != null) {
			try {
				aInputStream.close();
			} catch (IOException e) {
			}
		}
		if (aOutputStream != null) {
			try {
				aOutputStream.close();
			} catch (IOException e) {
			}
		}
		if (iSocket != null) {
			try {
				iSocket.close();
			} catch (IOException e) {
			}
		}
		return isConnected;
	}

	public synchronized String Reset() {
		if (isConnected) {
			System.out.println("<------- 重置 ------->");
			Send("RSET" + CRLF);
			return GetResponse();
		}
		return null;
	}

	private void Send(String Command) {
		try {
			aOutputStream.write(Command);
			// flush冲一下
			aOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("S: " + Command);
	}

	public void setPassword(String password) {
		Password = password;
	}

	public void setPOP3Host(String pOP3Host) {
		POP3Host = pOP3Host;
	}

	public void setPOP3Port(int pOP3Port) {
		POP3Port = pOP3Port;
	}

	public void setUsername(String username) {
		Username = username;
	}
}
