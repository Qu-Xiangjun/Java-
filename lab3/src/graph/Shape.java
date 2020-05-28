/*
 * @author Qu Xiangjun
 * @date 2020.05.28
 * @version 1.0
 */
package graph;

import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 14L;
	/*
	 * ͼ����ɫ���ԣ��߿���ɫ�������ɫ
	 */
	public Color lineColor = Color.BLACK;
	public Color fillColor = Color.WHITE;
	/*
	 *  ��ͼ����
	 *  @paramgΪ����Ļ�ͼ��
	 */
	public abstract void draw(Graphics g); 
	/*
	 *  �жϴ���ĵ��Ƿ���shape�Ķ���
	 *  @param mousePoint ����һ��Point���͵Ĳ���
	 *  @return �жϸĲ����Ƿ������Rectangle�Ķ����У����򷵻�true�����򷵻�false
	 */
	public abstract boolean isPointInShape(Point mousePoint);
	/*
	 *  �ҵ�������Ӧͼ�εı��
	 *  @param mousePoint ����һ��Point���͵Ĳ���
	 *  @return ���ظò�����ͼ��������Ӧ�ı��
	 */
	public abstract int FindVertexPoint(Point mousePoint); 
	/*
	 *  ���ݴ���ĵ����Լ�ƫ������Triangle�ĵ���������޸�
	 *  @param index ���ţ�ͨ���ò��������ҵ���֮��Ӧ��ͼ���еĵ�
	 *  @param newPoint �µĵ����
	 */
	public abstract void reshape(int index, Point newPoint);
	/*
	 * �ƶ�ͼ��
	 * @param x x�����ƶ�
	 * @param y y�����ƶ�	
	 */
	public abstract void moveShape(int x,int y);
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
