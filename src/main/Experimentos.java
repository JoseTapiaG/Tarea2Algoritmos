package main;

import java.io.PrintWriter;
import java.util.ArrayList;

import kdtree.*;

public class Experimentos {
	
	private KDTree mainKDTree;
	private KDTree medianKDTree;
	private PrintWriter printWriter;
	
	
	static public void main(String[]args){
		
		Experimentos exp=new Experimentos();
		
		exp.testMemoriaPrincipal();
		exp.testMemoriaSecundaria();
		
	}
	
	public Experimentos(PrintWriter printwriter){
		
		mainKDTree = new MainKDTRee();
		medianKDTree= new MedianKDTRee();
		this.printWriter=printwriter;
		
	}
	
	public Experimentos(){
		
		mainKDTree = new MainKDTRee();
		medianKDTree= new MedianKDTRee();
		this.printWriter=new PrintWriter(System.out); //en pantalla
		
	}
	
	
	public void testMemoriaPrincipal(){
	
		
		
	}
	
	public void testMemoriaSecundaria(){
		
		
		
	}
	
	private ArrayList<double[]> generarPuntosAleatorios(int n){
		
		return null;
		
	}
	
	private ArrayList<double[]> generarPuntosPocaDiscrepancia(int n){
		return null;
	}

}
