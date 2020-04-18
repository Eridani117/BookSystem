# J2ee_tmall
J2ee_tmall for the scholl lab
package work;

import java.util.Scanner;
class TDVector {
	private double x;
	private double y;
	public String toString() {
		return "("+this.x+","+this.y+")";
	}
	
	/** 你所提交的代码将被嵌在这里（替换此行） **/
	public TDVector(double x2,double y2) {
		x=x2;
		y=y2;
	}
	
	public  TDVector(TDVector b){
		x=b.x;
		y=b.y;
	}
	
	public TDVector() {
		
	}
	
	public void setY(double z) {
		y=z;
	}
	
	public void setX(double z) {
		x=z;
	}
	
	public TDVector add(TDVector c) {
		TDVector newt=new TDVector();
		newt.x=x+c.x;
		newt.y=y*2;
		return newt;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
public class Main {
	public static void main(String[] args) {
		TDVector a = new TDVector();  /* 无参构造，向量的x和y默认为0 */
		Scanner sc = new Scanner(System.in);
		double x,y,z;
		x = sc.nextDouble();
		y = sc.nextDouble();
		z = sc.nextDouble();		
		TDVector b = new TDVector(x, y);  /* 按照参数构造向量的x和y */
		TDVector c = new TDVector(b);  /* 按照向量b构造向量 */
		a.setY(z);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		c.setX(z);
		a = b.add(c);
		System.out.println(a);
		System.out.println("b.x="+b.getX()+" b.y="+b.getY());
		sc.close();
	}
}









