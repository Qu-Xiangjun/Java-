/*
 * @author Qu Xiangjun
 * @date 2020.05.28
 * @version 1.0
 */
package graph;

import java.io.Serializable;
import java.awt.*;
import java.util.ArrayList;

public class Graphic implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	// Shape dynamic list
	ArrayList<Shape> GList; 
	//Graphics ��ͼ��
	private Graphics g;
	
	/*
	 *  Constructor	
	 */
	public Graphic(Graphics graphics) {
		// TODO Auto-generated constructor stub
		GList = new ArrayList<Shape>();
		this.g = graphics;
	}

	/*
	 *  Graphics ��ͼ������
	 *  @param gr1 frame��Graphics��ͼ��
	 */
	public void setgraphics(Graphics gr1) {
		g = gr1;
	}
	
	/*
	 *  ArrayList ͼ��洢����
	 *  @param list ��Ҫ���õ�Arraylist
	 */
	public void setArrayListShape(ArrayList<Shape> list) {
		System.out.println("in setArrayListShape");		
		GList.clear();
		for(Shape s:list) {
			GList.add(s);
		}		
	}
	
	/*
	 *  ArrayList ͼ��洢����
	 *  @return ����Arraylist
	 */
	public ArrayList<Shape> getArrayListShape() {
		return this.GList;
	}
	
	/*
	 * @param s ��ͼ��
	 * @return ����ͼ����Arraylist�е�index
	 */
	public int getShapeIndex(Shape s) {
		return GList.indexOf(s);
	}
	
	/*
	 * @return ����index��Ӧ��arraylist�е�shape
	 * @param index ���±�
	 */
	public Shape getShapeByIndex(int index) {
		return GList.get(index);
	}
	
	/*
	 *  Add shape
	 *  @param shape ��Ҫ��ӵ�ͼ��
	 */
	public void add(Shape shape) {
		GList.add(shape);
	}
	
	/*
	 *  Draw all shape in list
	 *  @param graphics ��ͼ��Ҫframe��Graphics�� ʵ��
	 */
	public void draw(Graphics graphics) {
		for(Shape i:GList) {
			i.draw(graphics);
		}
	};
	
	/*
	 * �ı�洢ͼ�ε����ԣ��ҵ�������Ƿ�����ĳ���洢��ͼ�Ρ�
	 * ������ڣ�����Ǹ�ͼ�ε��Ǹ����������ƫ�������޸�
	 * @param source ����ĵ�
	 * @param offset �õ��ƫ����
	 */
	public void reshape(Point source, Point newPoint) {
		System.out.println("in graphic reshape");
		for(int i=0;i<GList.size();i++) {
			if(GList.get(i).FindVertexPoint(source) != -1) {
				System.out.println("graphics find point in reshape");
				int index=GList.get(i).FindVertexPoint(source);
				System.out.println("In Graphic reshape");
				GList.get(i).reshape(index, newPoint);
				break;
			}
		}
	}
	
	/*
	 * �ж�����ĵ�������һͼ���ڲ������ڶ��ͼ�������ڣ����ص�һ���������ġ�
	 * @param sourse ����ĵ�
	 * @return ����������ͼ��
	 */
	public Shape isInShape(Point source) {
		for(int i=0;i<GList.size();i++) {
			if(GList.get(i).isPointInShape(source)) {
				return GList.get(i);
			}
		}
		return null;
	}
	
	/*
	 * �ж�����ĵ�������һͼ�ζ��㣬���ڶ��ͼ�������ڣ����ص�һ���������ġ�
	 * @param sourse ����ĵ�
	 * @return Shape ����������ͼ��
	 */
	public Shape isInPoint(Point source) {
		for(int i=0;i<GList.size();i++) {
			if(GList.get(i).FindVertexPoint(source) != -1) {
				return GList.get(i);
			}
		}
		return null;
	}
	
	/*
	 * @param ͼ��s����Ҫɾ����
	 */
	public void remove(Shape s) {
		GList.remove(s);
	}
	
}
