package model;

import java.io.Serializable;

public class Billboard implements Serializable {

	private static final long serialVersionUID = 1;
	private double width;
	private double height;
	private double area;
	private boolean inUse;
	private String brand;
	
	public Billboard(){
		width = 0;
		height = 0;
		inUse = false;
		brand = new String();
	}//End constructor1
	
	public Billboard(double w, double h, boolean u, String b){
		width = w;
		height = h;
		inUse = u;
		brand = b;
	}//End constructor2
	
	public void calculateArea(){
		area = width * height;
	}//End calculateArea
	public double getArea(){
		return area;
	}//getArea
	
	public void setWidth(double w){
		width = w;
	}//End setWidth
	public void setHeight(double h){
		height = h;
	}//End setWidth
	public void setInUse(boolean u){
		inUse = u;
	}//End setWidth
	public void setBrand(String b){
		brand = b;
	}//End setWidth
	public double getWidth(){
		return width;
	}//End getWidth
	public double getHeight(){
		return height;
	}//End getHeight
	public boolean getInUse(){
		return inUse;
	}//End getInUse
	public String getBrand(){
		return brand;
	}//End getBrand
	
}//End Billboard
