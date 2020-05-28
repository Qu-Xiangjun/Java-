/*
 * @author Qu Xiangjun
 * @date 2020.05.28
 * @version 1.0
 */
package graph;

import java.io.Serializable;

public class Point implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 15L;
	
	/*
	 * ��������
	 */
	public int x;
	public int y;
	
	/*
	 * point���캯��
	 * @param xx ���x����
	 * @param yy ���y����
	 * @param a ������Point��
	 */
	public Point(){
		x = 0;
		y = 0;
	}
	public Point(int xx,int yy){
		x = xx;
		y = yy;
	}
	public Point(Point a){
		x = a.x;
		y = a.y;
	}
	
	/*
	 * �ӿں���
	 */
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setPoint(Point a) {
		x = a.x;
		y = a.y;
	}
	public void setPoint(int xx,int yy) {
		x = xx;
		y = yy;
	}
	
	/*
	 * �ƶ���
	 * @param xx x������ƶ����루��������
	 * @param yy y������ƶ����루��������
	 */
	public void movePoint(int xx,int yy) {
		x += xx;
		y += yy;
	}
}
