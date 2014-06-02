package main;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import kdtree.*;

public class Experimentos {
	
	private KDTree mainKDTree;
	private KDTree medianKDTree;
	private PrintWriter printWriter;
	private double c;
	private Random randomGenerator;
	
	
	static public void main(String[]args){
		
		Experimentos exp=new Experimentos();
		
		exp.testMemoriaPrincipal();
		exp.testMemoriaSecundaria();
		
	}
	
	public Experimentos(PrintWriter printwriter){
		
		mainKDTree = new MainKDTRee();
		medianKDTree= new MedianKDTRee();
		this.printWriter=printwriter;
		randomGenerator = new Random();
		
	}
	
	public Experimentos(){
		
		this(new PrintWriter(System.out));
		
	}
	
	
	public void testMemoriaPrincipal(){
		
		for(int n=10; n<=20; n++){
			
			testPuntosAleatorios((int)Math.pow(2, n),mainKDTree);
			testPuntosAleatorios((int)Math.pow(2, n),medianKDTree);
			testPuntosPocaDisc((int)Math.pow(2, n),mainKDTree);
			testPuntosPocaDisc((int)Math.pow(2, n),medianKDTree);
			
			
		}
		
	}
	
	public void testMemoriaSecundaria(){
		
		//TODO
		
	}

	private void testPuntosAleatorios(int n, KDTree k){
		//TODO
		
	}
	
	private void testPuntosPocaDisc(int n, KDTree k){
		//TODO
		
	}
	
	private double mean(ArrayList<Double> list){
		double val=0;
		for (double v : list){
			val+=v;
		}
		return val/list.size();
	}
	
	private double std(ArrayList<Double> list){
		double m=mean(list);
		double val=0;
		for(double v : list){
			val+=Math.pow(v-m,2);
		}
		
		return Math.sqrt(val/list.size());
	}
	
	private ArrayList<double[]> generarPuntosAleatorios(int n){
		
		c=randomGenerator.nextDouble();
		
		ArrayList<double[]> points=new ArrayList<double[]>();
		
		for(int i=0; i<n; i++){
			double[] newPoint={randomGenerator.nextDouble()*Math.sqrt(n)*c,randomGenerator.nextDouble()*Math.sqrt(n)*c};
			points.add(newPoint);
		}
		return points;
		
	}
	
	private ArrayList<double[]> generarPuntosPocaDiscrepancia(int n){
		return null;
	}

}
