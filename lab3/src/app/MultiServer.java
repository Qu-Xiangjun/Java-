/*
 * @author Qu Xiangjun
 * @date 2020.05.28
 * @version 1.0
 */
package app;

import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.* ;
import java.io.*;
import java.util.Vector;

/*
 * �������˳���ui
 * ��������ͻ��˵�����
 */
public class MultiServer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	
	/*
	 * MultiServer constructor
	 */
	public MultiServer() {
		JTextArea jtaLog = new JTextArea();

		// Create a scroll pane to hold text area
		JScrollPane scrollPane = new JScrollPane(jtaLog);

		// Add the scroll pane to the frame
		add(scrollPane, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setTitle("MultiServer by 20186471 quxiangjun");
		setVisible(true);
		
		/*
		 * ������ͻ��˵�����
		 */
		try {
			// Create a server socket
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(5500);
			
			jtaLog.append(new Date() + ": MultiServer started at socket 5500\n");
			int count = 1;  //�ͻ��˼��������
			while(true) {						
				// Connect to player
				Socket player = serverSocket.accept();
				
				//�ڷ����gui��������ʾ����ͻ��˵���Ϣ
				jtaLog.append(new Date() + ": Player" + count + " joined " + '\n');
				jtaLog.append("Player" + count + "'s IP address" + player.getInetAddress().getHostAddress() + '\n');
				
				//Ϊ�ͻ��˴�һ������˵ļ����̣߳��������ݵĴ���
				ServerThread task = new ServerThread(player);
				task.start();
				ServerThread.initIns(task);
				//����ÿһ��server client�̣߳��������̬��
				DrawManager.GetDrawManager().AddDrawPeople(task);	
				count++;
			}		
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiServer frame = new MultiServer();
	}

}

/*
 * Ϊÿһ���ͻ��˵���һ���̣߳�ӵ�н����뷢�͵���ά���߳�ָ�����ݵ�
 */
class ServerThread extends Thread{
	/*
	 * �洢�����Ѿ�������ָ������
	 */
	private static ArrayList<instruction> insList = new ArrayList<instruction>();
	
	/*
	 * io
	 */
	Socket player;
	DataOutputStream oos;
	DataInputStream ois;
	
	/*
	 * Constructor
	 */
	public ServerThread(Socket player) throws IOException {
		// TODO Auto-generated constructor stub
		this.player = player;		
		InputStream is = player.getInputStream();
		ois = new DataInputStream(is);
	}
	
	/*
	 * ��ָ���͵��˿ͻ����߳������ӵĿͻ���
	 * @param sm һ��5��ָ���һ��Ϊ�������ͣ�����Ϊ����
	 */
	public synchronized void Out(int sm1, int sm2, int sm3, int sm4, int sm5) {
		try {
			OutputStream os = player.getOutputStream();
			oos = new DataOutputStream(os);
			oos.writeInt(sm1);
			oos.writeInt(sm2);
			oos.writeInt(sm3);
			oos.writeInt(sm4);
			oos.writeInt(sm5);
			oos.flush();
		}catch(UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ���������߳�
	 * ÿһ�ν������ݣ�������Ⱥ����ָ��
	 */
	public void run() {
		try{
			while(true) {
				/*
				 * ѭ����ȡ���޴��߳��µ����ݴ���
				 */
				int sm1 = ois.readInt();
				int sm2 = ois.readInt();
				int sm3 = ois.readInt();
				int sm4 = ois.readInt();
				int sm5 = ois.readInt();
				//����ָ��洢
				instruction ins = new instruction();
				ins.operation = sm1;
				ins.a = sm2;
				ins.b = sm3;
				ins.c = sm4;
				ins.d = sm5;
				insList.add(ins);
				//���������̵߳ľ�̬�����࣬Ⱥ����ÿһ���߳�
				DrawManager.GetDrawManager().Send(this,sm1,sm2,sm3,sm4,sm5);
			}
		}catch(UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/*
	 * ��ʼ�����̵߳Ŀͻ���
	 * ���������е�ָ��ȫ����һ��
	 * ��̬��
	 * @param serverThread �¿����߳�
	 */
	public static void initIns(ServerThread st) {
		if(insList.isEmpty()) {
			return;
		}
		for(instruction ins:insList) {
			st.Out(ins.operation, ins.a, ins.b, ins.c, ins.d);
		}
	}
}



/*
 * �������м���Ŀͻ����߳�
 */
class DrawManager {
	//��Ϊһ����ͼϵͳֻ��һ��������������е�����private
    /*
     *������ 
     */
    private DrawManager() {}
    private static final DrawManager cm=new DrawManager();
    public static DrawManager GetDrawManager() {
        return cm;
    }

  //��������socket�Ķ���
    Vector<ServerThread> vector=new Vector<ServerThread>();

    //��ӻ�ͼ�û�
    public void AddDrawPeople(ServerThread cs) {
        vector.add(cs);
    }

    //Ⱥ����Ϣ
    public void Send(ServerThread cs,int sm1, int sm2, int sm3, int sm4, int sm5) {
        for (int i = 0; i < vector.size(); i++){
        	ServerThread drawSocket=(ServerThread)vector.get(i);
            if(!cs.equals(drawSocket))
            {
            	//���ö�Ӧ�̵߳ķ���ָ���
                drawSocket.Out(sm1,sm2,sm3,sm4,sm5);
            }              
        }
        System.out.println("send");
    }
}

/*
 * ָ����
 * operation Ϊ��������
 * abcd Ϊ����
 */
class instruction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public int operation;
	public int a;
	public int b;
	public int c;
	public int d;	
}
