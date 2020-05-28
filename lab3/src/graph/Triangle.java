/*
 * @author Qu Xiangjun
 * @date 2020.05.28
 * @version 1.0
 */
package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Triangle extends Shape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;

	//Foundation Point
	public Point a,b,c;
	
	//Color
	Color lineColor = Color.BLACK;
	Color fillColor = Color.WHITE;

	/*
	 * Rectangle���캯��
	 * ����������
	 */
	public Triangle(){
		// Initialize (0,0) for every Point
		a = new Point();
		b = new Point();
		c = new Point();
	}
	public Triangle(Point aa, Point bb, Point cc){
		// Initialize by Point copy constructor
		a = new Point(aa);
		b = new Point(bb);
		c = new Point(cc);
	}
	
	/*
	 *  ��Triangle����
	 */
	public void draw(Graphics g) {
		int px[] = {a.getX(),b.getX(),c.getX(),a.getX()};
		int py[] = {a.getY(),b.getY(),c.getY(),a.getY()};
		g.setColor(lineColor);
		g.drawPolygon(px, py, 4);  //��β���ӻ���
		g.setColor(fillColor);
		g.fillPolygon(px, py, 4);
		
		System.out.println("--------------------");
		System.out.println("Draw Triangle:");
		System.out.println("Point a: ("+a.x+","+a.y+")");
		System.out.println("Point b: ("+b.x+","+b.y+")");
		System.out.println("Point c: ("+c.x+","+c.y+")");
		System.out.println("lineColor: "+lineColor.toString());
		System.out.println("fillColor: "+fillColor.toString());
		System.out.println("--------------------");
	}
	
	/*
	 *  ���Խӿ�
	 */
	public void setPoint(Point aa, Point bb, Point cc){
		a = new Point(aa);
		b = new Point(bb);
		c = new Point(cc);
	}
	public Point getA() {
		return a;
	}
	public void setA(Point a) {
		this.a = a;
	}
	public Point getB() {
		return b;
	}
	public void setB(Point b) {
		this.b = b;
	}
	public Point getC() {
		return c;
	}
	public void setC(Point c) {
		this.c = c;
	}
	
	/*
	 *  �жϴ���ĵ��Ƿ���Triangle�ڵĵ�
	 *  @param mousePoint ����һ��Point���͵Ĳ���
	 *  @return �жϸĲ����Ƿ������Rectangle�У����򷵻�true�����򷵻�false
	 */
	@Override
	public boolean isPointInShape(Point mousePoint) {
		double ABC = triAngleArea(a, b, c);
        double ABp = triAngleArea(a, b, mousePoint);
        double ACp = triAngleArea(a, c, mousePoint);
        double BCp = triAngleArea(b, c, mousePoint);
        double sumOther = ABp + ACp + BCp;
        double ABS_DOUBLE_0 = 1;
        if (Math.abs(ABC - sumOther) < ABS_DOUBLE_0) { // �����֮�͵���ԭ�����������֤��������������
            return true;
        } else {
            return false;
        }
	}
	/*
	 * �������������
	 * @param A B CΪ����������
	 * @return ���
	 */
	private static double triAngleArea(Point A, Point B, Point C) { // �������������������������������
		double a = Math.pow(Math.pow(A.x-B.x,2)+Math.pow(A.y-B.y,2),0.5);
		double b = Math.pow(Math.pow(A.x-C.x,2)+Math.pow(A.y-C.y,2),0.5);
		double c = Math.pow(Math.pow(C.x-B.x,2)+Math.pow(C.y-B.y,2),0.5);
		double d = (a + b + c) / 2f;
		double s = (float) Math.sqrt(d * (d - a) * (d - b) * (d - c));
		return s;
    }
	
	/*
	 *  ���ݴ���ĵ����Լ�ƫ������Triangle�ĵ���������޸�
	 *  @param index ���ţ�ͨ���ò��������ҵ���֮��Ӧ��ͼ���еĵ�
	 *  @param newPoint newPoint=�µ�����
	 */
	@Override
	public void reshape(int index, Point newPoint) {
		if(index==1) {
			a.setX(newPoint.getX());
			a.setY(newPoint.getY());
			System.out.println("Triangle a �޸ĳɹ�");
		}else if(index==2) {
			b.setX(newPoint.getX());
			b.setY(newPoint.getY());
			System.out.println("Triangle b �޸ĳɹ�");
		}else if(index==3) {
			c.setX(newPoint.getX());
			c.setY(newPoint.getY());
			System.out.println("Triangle c �޸ĳɹ�");
		}
	}
	
	/*
	 *  �ҵ�������Ӧͼ�εı��
	 *  @param mousePoint ����һ��Point���͵Ĳ���
	 *  @return ���ظò�����ͼ��������Ӧ�ı��,���û�ж�Ӧ�򷵻�-1
	 */
	@Override
	public int FindVertexPoint(Point mousePoint) {
		if( (Math.abs(mousePoint.getX()-a.getX())<=1) && (Math.abs(mousePoint.getY()-a.getY())<=1)) {
			return 1;
		}else if( (Math.abs(mousePoint.getX()-b.getX())<=1) && (Math.abs(mousePoint.getY()-b.getY())<=1) ) {
			return 2;
		}else if( (Math.abs(mousePoint.getX()-c.getX())<=1) && (Math.abs(mousePoint.getY()-c.getY())<=1) ) {
			return 3;
		}
		return -1;
	}
	
	/*
	 * �ƶ�ͼ��
	 * @param x x�����ƶ�
	 * @param y y�����ƶ�	
	 */
	public void moveShape(int x,int y) {
		a.movePoint(x, y);
		b.movePoint(x, y);
		c.movePoint(x, y);
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





















