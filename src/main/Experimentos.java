package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import node.Node;
import kdtree.*;

public class Experimentos {
	
	private KDTree mainKDTree;
	private KDTree medianKDTree;
	private PrintWriter printWriter;
	private double c;
	private Random randomGenerator;
	
	
	static public void main(String[]args) throws IOException{
		
		Experimentos exp=new Experimentos(new PrintWriter(new FileWriter("test.txt")));
		
		exp.testMemoriaPrincipal();
		exp.testMemoriaSecundaria();
		
	}
	
	public Experimentos(PrintWriter printwriter){
		
		mainKDTree = new MainKDTRee();
		medianKDTree= new MedianKDTRee();
		this.printWriter=printwriter;
		randomGenerator = new Random();
		
	}

	
	public void testMemoriaPrincipal(){
		
		String[]titles = {"Main KDTree --------","Median KDTree --------"};
		KDTree[]trees = {mainKDTree, medianKDTree};
		
		for(int i=0; i<2; i++){
			
			printWriter.println(titles[i]);
			System.out.println(titles[i]);
			
			for(int n=10; n<=20; n++){
				
				printWriter.println("test puntos aleatorios n="+n);
				System.out.println("test puntos aleatorios n="+n);
				testPuntosAleatorios((int)Math.pow(2, n),trees[i]);
				
				printWriter.println("test puntos aleatorios MedianKDTree n="+n);
				System.out.println("test puntos aleatorios Median n="+n);
				testPuntosPocaDisc((int)Math.pow(2, n),trees[i]);

			}
		}
		printWriter.close();
		
	}
	
	public void testMemoriaSecundaria(){
		
		
		
	}

	private void testPuntosAleatorios(int n, KDTree k){
		
		boolean endCond = false;
		
		ArrayList<Double> constructionTime=new ArrayList<Double>();
	
		Node currentNode=null;
		
		while(!endCond){
			
			ArrayList<double[]> currentList = generarPuntosAleatorios(n);
			
			double startTime = System.currentTimeMillis();
			currentNode = k.construirKDTree(currentList, KDTree.x);
			constructionTime.add((System.currentTimeMillis()-startTime)/1000);
			
			endCond= (constructionTime.size()>=2 && std(constructionTime)<= 0.05*mean(constructionTime)) || constructionTime.size()>=10;
			
			System.out.println("Mean = "+mean(constructionTime)+", std="+std(constructionTime));
			
		}
		testVecinoMasCercano(currentNode);
		printWriter.println("Tiempo de construcción promedio: "+mean(constructionTime)+"; Std: "+std(constructionTime));
		
	}
	
	private void testVecinoMasCercano(Node node){
		
		printWriter.println("Búsqueda vecino más cercano--------");
		System.out.println("Búsqueda vecino más cercano--------");
		boolean endCond = false;
		ArrayList<Double> findTime=new ArrayList<Double>();
		VecinoMasCercano finder = new VecinoMasCercano();
			
		while(!endCond){
			
			double[] point = {randomGenerator.nextDouble()*c,randomGenerator.nextDouble()*c};
			double startTime = System.currentTimeMillis();
			
			finder.vecinoMasCercano(node,point);
			findTime.add(System.currentTimeMillis()-startTime);
			
			endCond = (findTime.size()>=2 && std(findTime)<= 0.05*mean(findTime)) || findTime.size()>=100;
			System.out.println("Mean:"+mean(findTime)+"; Std: "+std(findTime));
			
		}
		printWriter.println("Tiempo de búsqueda promedio: "+mean(findTime)+"; Std: "+std(findTime));
		
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
