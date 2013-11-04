package br.com.kenobi;


import java.text.DecimalFormat;

public class Util {

	public static void main(String[] args) {
		
//Suppose we want to find places within a distance d=1000 km from M=(lat, lon)=(1.3963, -0.6981) 
//in a database. Given that we have a table named Places with columns Lat 
//and Lon that hold the coordinates in radians, then we could use this SQL query:		
//SELECT * FROM Places WHERE
//acos(sin(1.3963) * sin(Lat) + cos(1.3963) * cos(Lat) * cos(Lon - (-0.6981))) * 6371 <= 1000;

//		Radians = Degrees * PI / 180
//				and on the inverse,
//
//				Degrees = Radians * 180 / PI
//		
//For example, the distance between the Statue of Liberty at 
//		
//		(40.6892�, -74.0444�)=(0.7102 rad, -1.2923 rad) and the Eiffel Tower
//				at (48.8583�, 2.2945�)=(0.8527 rad, 0.0400 rad) � assuming a spherical approximationa of the figure of the Earth with radius 6371 km � is:
////
//dist = arccos(sin(0.7102) � sin(0.8527) + cos(0.7102) � cos(0.8527) � cos(-1.2923 - 0.0400)) � 6371 km
//			      = 5837 km
//		
		
		//-22.881928,-43.451089 testpoint 1
		Double grausLat = -22.881928d;
		Double grausLong = -43.451089;
	
		
		//-22.888373,-43.443128 test point 2
		Double grausLat_plus = -22.888373d;
		Double grausLong_plus = -43.443128d;
		
		System.out.println(grausLat- grausLat_plus);
		System.out.println(grausLong- grausLong_plus);
		
		
		
		DecimalFormat df = new DecimalFormat("#");
		
		Double dist = 
	Math.acos( (Math.sin(grausLat) * Math.sin(grausLat_plus)) + (Math.cos(grausLat) * Math.cos(grausLat_plus) * Math.cos( (grausLong-grausLong_plus))) )* 6371;
		System.out.println("disntancia entre os dois pontos:" + dist + "kilometros");
		df.setDecimalSeparatorAlwaysShown(true);
		df.setParseBigDecimal(true);
		display(grausLat,grausLong,grausLat_plus,grausLong_plus);
		System.out.println("Raio de : " + ((dist/2))/1000);
	
		System.out.println("hey");
		System.out.println(grausLat+0.01);
		System.out.println(grausLong+0.01);
		
		
		Double x1 = grausLat;
		Double y1 = grausLong;
		
		Double x2 = grausLat;
		Double y2 = (grausLong) *-1;
		
		Double x3 = (grausLat ) *-1;
		Double y3 = grausLong;
		
		
		//0.01325 equivale 2km
		
		//-22.884281,-43.445477
		//-22.881473,-43.449361
		//-22.884182,-43.442216
		Double px= -22.884182;
		Double py = -43.442216;
		//-22.881098,-43.442731
		//-22.882146,-43.450606
		//-22.888135,-43.44316
		System.out.println(
				InsideTriagulo.pntInTriangle
				(px, py, -22.881098, -43.442731,
						-22.882146, -43.450606, 
						-22.888135, -43.44316));
		
		
		System.out.println((-22.881098+0.01325));
		System.out.println((-43.442731+0.01325));
		System.out.println((-22.881098+0.01325));
		System.out.println((-1)*(-43.442731+0.01325));
		System.out.println((-1)*(-22.881098+0.01325));
		System.out.println((-43.442731+0.01325));
		
		
	//	Triangulo listTriangulos[] = new Util().geraTriangulo(px, py);	
		
	System.out.println("Est� dentro da �rea ? :");
//	-22.868425,-43.440113
//	-23.016983,-43.477027
	   Triangulo[] t = new Util().geraTriangulo(   new Double(-23.016983),new Double(-43.477027), new Integer(2));
//	      Triangulo t[] =  util.geraTriangulo(Double.parseDouble((ponto.get(0).getLat())), 
//	    		  Double.parseDouble(ponto.get(0).getLon()));
	      System.out.println("__________________________________________________________");
//	      -23.027351,-43.473943
	      boolean dentro = new Util().inLocation(-23.027351, -43.473943, t);
	      System.out.println(dentro);
	
//	System.out.println(inLocation(-22.868425d, -43.440113, listTriangulos));
	}
	
	public  boolean inLocation(Double px,Double py,Triangulo t[]){
		
		boolean insidePoligono = false;
		
		
		for (int i = 0; i < t.length; i++) {
			
		
		 insidePoligono = (
				 InsideTriagulo.pntInTriangle(
						 px, py,
						 t[i].getX0(),t[i].getY0(),
						 t[i].getX1(),t[i].getY1(), 
						 t[i].getX2(), t[i].getY2()));
		 
		 if(insidePoligono)
			 		return true;
		}
		
		
		return false;
	}
	
	//recupera 4 triagulos pra fazero quadrado.
	public  Triangulo[] geraTriangulo(Double x0,Double y0,int fat){
	System.out.println("leorleor");
		//gerando triango
		Double fator = geraFator(fat);
		
		//triagulo 1 pontos
		
		//veritice 1
		Double t1X1 = x0;
		Double t1Y1 = y0;
		//veritice 2
		Double t1X2 = x0;
		Double t1Y2 = y0 + fator;
		//vertice 3
		Double t1X3 = x0 + fator;
		Double t1Y3 = y0;
		
		
		
		//triagulo 2 pontos

		//veritice 1
		Double t2X1 = x0;
		Double t2Y1 = y0;
		//veritice 2
		Double t2X2 = x0;
		Double t2Y2 = (y0 ) +( (fator)*(-1));
		//vertice 3
		Double t2X3 = x0 + fator;
		Double t2Y3 = y0;
		
		
		//triagulo 3 pontos

		//veritice 1
		Double t3X1 = x0;
		Double t3Y1 = y0;
		//veritice 2
		Double t3X2 = (x0 + 0)    +  ((fator) *(-1));
		Double t3Y2 = (y0 );
		//vertice 3
		Double t3X3 = x0 ;
		Double t3Y3 = (y0) + (( fator) * (-1));
		
		//triagulo 4 pontos

		//veritice 1
		Double t4X1 = x0;
		Double t4Y1 = y0;
		//veritice 2
		Double t4X2 = x0;
		Double t4Y2 = (y0 + fator)*(1);
		//vertice 3
		Double t4X3 = (x0)  + ((fator)*(-1));
		Double t4Y3 = y0;
		
		
		Triangulo t1 = new Triangulo(t1X1, t1Y1, t1X2, t1Y2, t1X3, t1Y3);
		Triangulo t2 = new Triangulo(t2X1, t2Y1, t2X2, t2Y2, t2X3, t2Y3);
		Triangulo t3 = new Triangulo(t3X1, t3Y1, t3X2, t3Y2, t3X3, t3Y3);
		Triangulo t4 = new Triangulo(t4X1, t4Y1, t4X2, t4Y2, t4X3, t4Y3);
		
		
		
		Triangulo t[] = new Triangulo[4] ;
		
		t[0]= t1;
		t[1]= t2;
		t[2]= t3;
		t[3]= t4;
		
		for (Triangulo triangulo : t) {
			System.out.println(triangulo);
		}
		return t;
	}
	
	
	static Double geraFator(int kmRaio){
		
		if(kmRaio==0 || kmRaio==2){
			return 0.01325d;
				
		}else{
		
			return kmRaio * 0.01325;
			
		}
		
		
	}
	
	
	static void display(Double x1,Double x2,Double y1,Double y2)
	  {   
	     Double difx = (x2 - x1) * (x2 - x1);
		 Double dify = (y2 - y1) * (y2 - y1);
		 Double d = Math.sqrt(difx + dify);
	      System.out.println("\n Distance between (" + x1 + "," + y1 + ") and (" + x2 + "," + y2 + ") is : " + d + " unit(s) " );
	  }
	
}