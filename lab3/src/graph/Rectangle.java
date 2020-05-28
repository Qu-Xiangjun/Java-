/*
 * @author Qu Xiangjun
 * @date 2020.05.28
 * @version 1.0
 */
package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Rectangle extends Shape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 13L;
	
	/*
	 * ͼ�����ԣ�����ͳ���
	 */
	public Point a,b;
	public int width,height;
	
	/*
	 * ͼ����ɫ���ԣ��߿�����
	 */
	public Color lineColor = Color.BLACK;
	public Color fillColor = Color.WHITE;
	
	/*
	 * Rectangle���캯��
	 * ������������
	 */
	public Rectangle(){
		// Initialize (0,0) for every Point
		a = new Point();
		b = new Point();
		width = height = 0;
	}
	public Rectangle(Point aa,Point bb) {
		// Initialize by Point copy constructor
		if(aa.getX()>bb.getX()) {
			//aʼ�������Ͻ�
			int x,y;
			x = aa.getX();
			y = aa.getY();
			bb.setPoint(aa);
			aa.setPoint(new Point(x,y));
		}
		a = new Point(aa);
		b = new Point(bb);
		height = Math.abs(aa.getY()-bb.getY());
		width = Math.abs(aa.getX()-bb.getX());
	}
	public Rectangle(Point aa,Point bb,int w,int h) {
		// Initialize by Point copy constructor
		if(aa.getX()>bb.getX()) {
			//aʼ�������Ͻ�
			int x,y;
			x = aa.getX();
			y = aa.getY();
			bb.setPoint(aa);
			aa.setPoint(new Point(x,y));
		}
		a = new Point(aa);
		b = new Point(bb);
		width = w;
		height = h;
	}
	
	/*
	 *  ��Rectangle����
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(this.lineColor);
		g.drawRect(a.getX(), a.getY(), width, height);
		g.setColor(this.fillColor);
		g.fillRect(a.getX()+1, a.getY()+1, width-2, height-2);
		
		System.out.println("--------------------");
		System.out.println("Draw Rectangle:");
		System.out.println("Point a: ("+a.x+","+a.y+")");
		System.out.println("Point b: ("+b.x+","+b.y+")");
		System.out.println("width: "+width);
		System.out.println("height: "+height);
		System.out.println("lineColor: "+lineColor.toString());
		System.out.println("fillColor: "+fillColor.toString());
		System.out.println("--------------------");
	}
	/*
	 * ���Խӿ�
	 */
	public void setPoint(Point aa,Point bb) {
		a = new Point(aa);
		b = new Point(bb);
	}
	public void setA(Point a) {
		this.a = a;
	}
	public Point getA() {
		return a;
	}
	public void setB(Point b) {
		this.b = b;
	}
	public Point getB() {
		return b;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	/*
	 *  �жϴ���ĵ��Ƿ���Rectangle�ڵĵ�
	 *  @param mousePoint ����һ��Point���͵Ĳ���
	 *  @return �жϸĲ����Ƿ������Rectangle�У����򷵻�true�����򷵻�false
	 */
	@Override
	public boolean isPointInShape(Point mousePoint) {
		if(mousePoint.getX()>=a.getX() && mousePoint.getX()<=b.getX() && mousePoint.getY()>=a.getY() && mousePoint.getY()<=b.getY() ) {
			return true;
		}
		return false;
	}
	
	
	/*
	 *  ���ݴ���ĵ����Լ�ƫ������Rectangle�ĵ���������޸�
	 *  @param index ���ţ�ͨ���ò��������ҵ���֮��Ӧ��ͼ���еĵ�
	 *  @param newPoint newPoint=�µ�����
	 */
	@Override
	public void reshape(int index, Point newPoint) {
		if(index==1) {
			a.setX(newPoint.getX());
			a.setY(newPoint.getY());
			System.out.println("Rectangle:Point a �޸ĳɹ�");
		}else if(index==2) {
			b.setX(newPoint.getX());
			b.setY(newPoint.getY());
			System.out.println("Rectangle:Point b �޸ĳɹ�");
		}
		width = Math.abs(a.getX()-b.getX());
		height = Math.abs(a.getY()-b.getY());
	}
	
	/*
	 *  �ҵ�������Ӧͼ�εı��
	 *  @param mousePoint ����һ��Point���͵Ĳ���
	 *  @return ���ظò�����ͼ��������Ӧ�ı��,û�ж�Ӧ�򷵻�-1
	 */
	@Override
	public int FindVertexPoint(Point mousePoint) {
		//���õ����ΧΪ1
		if( (Math.abs(mousePoint.getX()-a.getX())<=1) && (Math.abs(mousePoint.getY()-a.getY())<=1) ) {
			return 1;
		}else if( (Math.abs(mousePoint.getX()-b.getX())<=1) && (Math.abs(mousePoint.getY()-b.getY())<=1)  ) {
			return 2;
		}
		return -1;
	}
	
	/*
	 * �ƶ�ͼ��
	 * @param x x�����ƶ�
	 * @param y y�����ƶ�	
	 */
	@Override
	public void moveShape(int x, int y) {
		// TODO Auto-generated method stub
		a.movePoint(x, y);
		b.movePoint(x, y);
	}
	
	/*
	 *  �޸�ͼ��߿���ɫ
	 *  @paramg ��ɫ
	 */
	public void setLineColor(Color c) {
		lineColor = c;
	}
	/*
	 *  �޸�ͼ���ڲ������ɫ
	 *  @paramg ��ɫ
	 */
	public void setFillColor(Color c) {
		fillColor = c;
	}
}
