/*
 * @author Qu Xiangjun
 * @date 2020.05.28
 * @version 1.0
 */
package app;

import graph.Point;
import graph.Rectangle;
import graph.Shape;
import graph.Triangle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.*;
import java.net.Socket;

/*
 * �ͻ��˿��Ƴ���
 */
public class Client extends Thread {
	/*
	 * ����ѡ�а�ťȫ�ֱ���
	 */
	private String currentButton = new String("");	//��ǰѡ�еİ�ť
	private Color currentColor;  //��ǰѡ�е���ɫ
	private int selectedPoint;	//��ǰѡ�еĵ�
	private Shape selectedShape;	//��ǰѡ�е�ͼ��
	
	/*
	 * ����λ��
	 * �����ʼ�����ʼ��λ��
	 * ���� ����϶����̵�ʵʱλ��
	 */
	private int lastX = -1;
	private int lastY = -1;
	private int lastX2 = -1;
	private int lastY2 = -1;
	
	/*
	 * gui���򣬻�ͼ����
	 */
	private OpenGLApp gui;
	
	/*
	 * ���ݴ���io
	 */
	private DataInputStream fromServer;
	private DataOutputStream toServer;
	Socket socket;
	
	/*
	 * Constructor
	 */
	public Client() {
		gui = new OpenGLApp();
		
		/*
		 * ��ť����¼�
		 */
		gui.rectangleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentButton = "rectangleButton";
			}
			
		});
		gui.triangleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentButton = "triangleButton";
			}
			
		});
		gui.SelectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentButton = "SelectButton";
			}
			
		});
		gui.blackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("click black");
				// TODO Auto-generated method stub
				currentColor = Color.BLACK;
				//��ѡ����ͼ����ı�����ɫ
				if(selectedShape != null) {
					if(selectedPoint == -1) {
						//ѡ�����ͼ���ڲ������ͼ��
						selectedShape.setFillColor(currentColor);
						gui.repaint();
						
					}
					else {
						//ѡ����ǵ㣬�ı�߿���ɫ
						selectedShape.setLineColor(currentColor);
						gui.repaint();
					}
					try {
						toServer = new DataOutputStream(socket.getOutputStream());
						//���Ͳ���ʶ���� ��ɫΪ3
						toServer.writeInt(3);
						//����ͼ����arraylist�е�index
						toServer.writeInt(gui.getGraphic().getShapeIndex(selectedShape));
						//�����Ǳ�1�������2
						if(selectedPoint == -1)  
							toServer.writeInt(2);
						else 
							toServer.writeInt(1);
						//������ɫ blackΪ1
						toServer.writeInt(1);
						
						//���෢��
						toServer.writeInt(0);
						toServer.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("send change data");
				}
			}
			
		});
		gui.redButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("click red");
				// TODO Auto-generated method stub
				currentColor = Color.RED;
				if(selectedShape != null) {
					if(selectedPoint == -1) {
						//ѡ�����ͼ���ڲ������ͼ��
						selectedShape.setFillColor(currentColor);
						gui.repaint();
					}
					else {
						//ѡ����ǵ㣬�ı�߿���ɫ
						selectedShape.setLineColor(currentColor);
						gui.repaint();
					}
					try {
						toServer = new DataOutputStream(socket.getOutputStream());
						//���Ͳ���ʶ���� ��ɫΪ3
						toServer.writeInt(3);
						//����ͼ����arraylist�е�index
						toServer.writeInt(gui.getGraphic().getShapeIndex(selectedShape));
						//�����Ǳ�1�������2
						if(selectedPoint == -1)  
							toServer.writeInt(2);
						else 
							toServer.writeInt(1);
						//������ɫ red2
						toServer.writeInt(2);
						//���෢��
						toServer.writeInt(0);
						
						toServer.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("send change data");
				}
			}
			
		});
		gui.yellowButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("click yellow");
				// TODO Auto-generated method stub
				currentColor = Color.YELLOW;
				if(selectedShape != null) {
					if(selectedPoint == -1) {
						//ѡ�����ͼ���ڲ������ͼ��
						selectedShape.setFillColor(currentColor);
						gui.repaint();
					}
					else {
						//ѡ����ǵ㣬�ı�߿���ɫ
						selectedShape.setLineColor(currentColor);
						gui.repaint();
					}
					try {
						toServer = new DataOutputStream(socket.getOutputStream());
						//���Ͳ���ʶ���� ��ɫΪ3
						toServer.writeInt(3);
						//����ͼ����arraylist�е�index
						toServer.writeInt(gui.getGraphic().getShapeIndex(selectedShape));
						//�����Ǳ�1�������2
						if(selectedPoint == -1)  
							toServer.writeInt(2);
						else 
							toServer.writeInt(1);
						//������ɫ yellowΪ3
						toServer.writeInt(3);
						//���෢��
						toServer.writeInt(0);
						
						toServer.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("send change data");
				}
			}
			
		});
		gui.saveButton.addActionListener(new ActionListener() {
			/*
			 * ����ͼ��
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					gui.savePic();	
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
			
		});
		
		
		/*
		 * ������¼�
		 */
		gui.addMouseListener(new MouseAdapter() {
			/*
			 * ��ͼ����¼�
			 * ��������λ����Ϊ��ͼ��ʼ����
			 * ��ק��λ��Ϊʵʱ����ͼ��
			 * ����λ�ý�ͼ�񱣴���arraylist
			 */
			public void mousePressed(MouseEvent event) {
				System.out.println("mousePressed");
				//��ʼ����ѡ����
				selectedPoint = -1;
				selectedShape = null;
				lastX = -1; //��ʼ���������
				lastY = -1;
				
				lastX2 = lastX = event.getX();	//��������е���ʱ����
				lastY2 = lastY = event.getY();
				//ѡ��ͼ�������ı�
				if(currentButton == "SelectButton") {
					
					//��ѡ��ͼ��Ķ��㣬Ϊ�ı�ͼ�����״
					//ѡ��ͼ���ڲ���Ϊ�ƶ�ͼ��
					selectedShape = gui.getGraphic().isInPoint(new Point(lastX,lastY));
					if(selectedShape == null) {
						selectedShape = gui.getGraphic().isInShape(new Point(lastX,lastY));
					}else {
						selectedPoint = selectedShape.FindVertexPoint(new Point(lastX,lastY));
					}	
					
					
				}
			}
			/*
			 * ����ͷ�
			 * ��������������ĵ�graphic���У������»���
			 * ͬʱ�������ָ�������
			 */
			public void mouseReleased(MouseEvent event) {
				System.out.println("mouseReleased");
				int currentX,currentY;
				currentX = event.getX();
				currentY = event.getY();
				//������
				if(currentButton == "rectangleButton") {
					Rectangle rec = new Rectangle(new Point(lastX,lastY),new Point(currentX,currentY));
					gui.getGraphic().add(rec);
					gui.repaint();
					try {
						toServer = new DataOutputStream(socket.getOutputStream());
						//���Ͳ���ʶ���� ������Ϊ1
						toServer.writeInt(1);
						//�����¾��ε�xy x2,y2
						toServer.writeInt(lastX);
						toServer.writeInt(lastY);
						toServer.writeInt(currentX);
						toServer.writeInt(currentY);
						
						toServer.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("send change data");
				}
				//������������
				else if(currentButton == "triangleButton") {
					Point point3 = new Point(lastX-(currentX-lastX),currentY);
					Triangle triangle = new Triangle(new Point(lastX,lastY),new Point(currentX,currentY),point3);
					gui.getGraphic().add(triangle);
					gui.repaint();
					try {
						toServer = new DataOutputStream(socket.getOutputStream());
						//���Ͳ���ʶ���� ��������Ϊ2
						toServer.writeInt(2);
						//�����¾��ε�xy x2,y2
						toServer.writeInt(lastX);
						toServer.writeInt(lastY);
						toServer.writeInt(currentX);
						toServer.writeInt(currentY);
						toServer.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("send change data");
				}
				//ѡ��ͼ�������ı�
				else if(currentButton == "SelectButton") {
					// �ı�ͼ��������С�����Σ�
					if(selectedPoint != -1) {
						gui.getGraphic().reshape(new Point(lastX,lastY), new Point(currentX,currentY));
						System.out.println("change points");
						gui.repaint();
						try {
							toServer = new DataOutputStream(socket.getOutputStream());
							//���Ͳ���ʶ���� �ı��Ϊ5
							toServer.writeInt(5);
							//���͸ı�ĵ�ǰ��λ��
							toServer.writeInt(lastX);
							toServer.writeInt(lastY);
							toServer.writeInt(currentX);
							toServer.writeInt(currentY);
							
							toServer.flush();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("send change data");
					}
					// �ı�λ��
					else {
						if(selectedShape == null) return;
						int x = currentX-lastX2;
						int y = currentY-lastY2;
						selectedShape.moveShape(x,y);
						gui.repaint();
						try {
							toServer = new DataOutputStream(socket.getOutputStream());
							//���Ͳ���ʶ���� �ƶ�Ϊ4
							toServer.writeInt(4);
							//����ͼ����arraylist�е�index
							toServer.writeInt(gui.getGraphic().getShapeIndex(selectedShape));
							//�����ƶ����� xy
							toServer.writeInt(currentX-lastX);
							toServer.writeInt(currentY-lastY);
							
							//���෢��
							toServer.writeInt(0);
							
							toServer.flush();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("send change data");
					}
				}
			}
			
			
			/*
			 * ����¼�
			 * ������λ��
			 * ����ѡ�е�ǰλ���е����ͼ��
			 */
			public void mouseClicked(MouseEvent event) {
				System.out.println("mouseClicked");
				//��ʼ����ѡ����
				selectedPoint = -1;
				selectedShape = null;
				lastX = -1;
				lastY = -1;
				
				lastX = event.getX();
				lastY = event.getY();
				//ѡ��ͼ�������ı�
				if(currentButton == "SelectButton") {					
					//��ѡ��ͼ��Ķ��㣬Ϊ�ı�ͼ�����״
					selectedShape = gui.getGraphic().isInPoint(new Point(lastX,lastY));
					if(selectedShape == null) {
						selectedShape = gui.getGraphic().isInShape(new Point(lastX,lastY));
					}else {
						selectedPoint = selectedShape.FindVertexPoint(new Point(lastX,lastY));
					}	
				}
			}
			
		});	
		
		/*
		 * ����϶�ʱ��
		 * �����϶�ʱ�Ķ���
		 */
		gui.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent event) {
				System.out.println("mouseDragged");
				int currentX,currentY;
				currentX = event.getX();
				currentY = event.getY();
				//������
				if(currentButton == "rectangleButton") {
					Rectangle rec = new Rectangle(new Point(lastX,lastY),new Point(currentX,currentY));
					rec.draw(gui.getGraphics());
					gui.repaint();
					System.out.println("x:"+currentX+" y:"+currentY);
				}
				//������������
				else if(currentButton == "triangleButton") {
					Point point3 = new Point(lastX-(currentX-lastX),currentY);
					Triangle triangle = new Triangle(new Point(lastX,lastY),new Point(currentX,currentY),point3);
					triangle.draw(gui.getGraphics());
					gui.repaint();
				}
				//ѡ��ͼ�������ı�
				else if(currentButton == "SelectButton") {
					System.out.println("selectedPoint��"+selectedPoint);
					// �ı�ͼ��
					if(selectedPoint != -1 ) {
						System.out.println("�ı���״��");
						int x = lastX2;
						int y = lastY2;
						gui.getGraphic().reshape(new Point(x,y), new Point(currentX,currentY));
						gui.repaint();
						lastY2 = currentY;
						lastX2 = currentX;
					}
					// �ı�λ��
					else {
						if(selectedShape == null) return;
						int x = currentX - lastX2;
						int y = currentY - lastY2;
						selectedShape.moveShape(x,y);
						gui.repaint();
						lastY2 = currentY;
						lastX2 = currentX;
					}
					
				}
			}
        });
		
		connectToServer();
	}
	
	/*
	 * ���ӷ����
	 */
	private void connectToServer() {
		// TODO Auto-generated method stub
		System.out.println("in Client ThreadInt connectToServer");
		try {
			System.out.println("before connectToServer");
			socket = new Socket("localhost",5500);
			System.out.println("after connectToServer");
			
			fromServer = new DataInputStream(socket.getInputStream());
			System.out.println("to end connectToServer");
			
//			toServer.writeIntInt(null);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*
	 * �߳�����
	 * ���ڽ���ָ������
	 * ָ����չ������������һ���ǲ������ͣ�����Ϊ���ݣ�����λ����Ϣ��ͼ�α�ŵȣ�����������㴫�䣬������
	 */
	public void run() {
		System.out.println("in Client ThreadInt run");
		// TODO Auto-generated method stub
		try {
			
			while(true) {
				//��������
				int operation = fromServer.readInt();  //��������
				int a,b,c,d;	//����
				a = fromServer.readInt();
				b = fromServer.readInt();
				c = fromServer.readInt();
				d = fromServer.readInt();
				switch(operation) {  //�жϲ�������
				case 1:
					/*
					 * rectangle draw
					 */
					Rectangle rec = new Rectangle(new Point(a,b),new Point(c,d));
					gui.getGraphic().add(rec);
					gui.repaint();
					break;
				case 2:
					/*
					 * Triangle draw
					 */
					Point point3 = new Point(a-(c-a),d);
					Triangle triangle = new Triangle(new Point(a,b),new Point(c,d),point3);
					gui.getGraphic().add(triangle);
					gui.repaint();
					break;
				case 3:
					/*
					 * ��ɫ
					 */
					int shapeIndex = a;
					Shape tempShape = gui.getGraphic().getShapeByIndex(shapeIndex);
					int lineAndFill = b; //����ѡ���߻������	
					int color = c; //��ɫ
					Color c1;
					switch (color) {  	//ѡ����ɫ
					case 1:
						c1 = Color.black;
						break;
					case 2:
						c1 = Color.red;
						break;
					default:
						c1 = Color.yellow;
						break;
					}
					
					if(lineAndFill==1) {
						// �ı�߿����ɫ
						tempShape.setLineColor(c1);
						gui.repaint();
					}
					else {
						//�ı������ɫ
						tempShape.setFillColor(c1);
						gui.repaint();
					}
					break;
				case 4:
					int shapeIndex1 = a; //ͼ����arraylist �е�index
					Shape tempShape1 = gui.getGraphic().getShapeByIndex(shapeIndex1);
					int moveX,moveY;
					moveX = b;
					moveY = c;
					tempShape1.moveShape(moveX,moveY);
					gui.repaint();
					break;
				case 5:

					int oldX,oldY,newX,newY;
					oldX = a;
					oldY = b;
					newX = c;
					newY = d;
					gui.getGraphic().reshape(new Point(oldX,oldY), new Point(newX,newY));
					gui.repaint();
					break;
				}
				System.out.println("recieve change data");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client frame = new Client();
		frame.start();
		frame.gui.setVisible(true);
	}

	

}
