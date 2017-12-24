package Program;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import Values.BestSolution;
import Values.Data;
import Values.Parameter;

public class MainMethod {
	
	private Data data = Data.getInstance();
	private Parameter parameter = Parameter.getInstance();
	
	private ArrayList<Integer> solution = new ArrayList<>();
	private double distance = 0;
	private double fitness = 0;
	private boolean isInteger = false;
	
	// 演算法主程式
	public BestSolution mainProgram(boolean isInteger) {
		BestSolution bestSolution = new BestSolution();
		int run = 0;
		int temperature = parameter.getStartTemperature();
		this.isInteger = isInteger;
		while (run < parameter.getGeneration() 
				&& temperature >= parameter.getEndTemperature()) {
			if (run == 0) {
				initialSolution();
			} else {
				Optimization optimization = new Optimization();
				this.solution = optimization.twoOptimization(this.solution);
				caculateDistance();
			}
			getBest(bestSolution);
			run++;
			temperature *= parameter.getReduceParameter();
		}
		clearAll();
		return bestSolution;
	}
	
	// 初始化解
	private void initialSolution() {
		ArrayList<Integer> point = getDataSetList();
		this.solution = createSolution(point);
		point.clear();
		caculateDistance();
	}
	
	private ArrayList<Integer> getDataSetList() {
		ArrayList<Integer> point = new ArrayList<>();
		for (int i=0; i<data.total; i++) {
			point.add(i);
		}
		return point;
	}
	
	
	// 產生解
	private ArrayList<Integer> createSolution(ArrayList<Integer> point) {
		Random random = new Random();
		boolean run = true;
		ArrayList<Integer> newSolution = new ArrayList<Integer>();
		ArrayList<Integer> allPoint = new ArrayList<Integer>();
		allPoint.addAll(point);
		while (run) {
			int tmp = random.nextInt(allPoint.size());
			newSolution.add(allPoint.get(tmp));
			allPoint.remove(tmp);
			if (allPoint.size() <= 0)
				run = false;
		}
		return newSolution;
	}
	
	// 計算解距離
	private void caculateDistance() {
		int size = this.solution.size();
		double tmpDistance = 0;
		
		for (int i=0; i<size; i++){
			if (i == size-1) {
				if (this.isInteger)
					tmpDistance += pointDistanceInt(this.solution.get(i), this.solution.get(0));
				else {
					tmpDistance += pointDistanceDouble(this.solution.get(i), this.solution.get(0));
				}
			}
			else {
				if (this.isInteger)
					tmpDistance += pointDistanceInt(this.solution.get(i), this.solution.get(i+1));
				else
					tmpDistance += pointDistanceDouble(this.solution.get(i), this.solution.get(i+1));
			}
			this.distance = tmpDistance;
			this.fitness = 1/tmpDistance;
		}
	}
	
	private double pointDistanceInt(int pointA, int pointB) {
		return (double)Math.round(Point.distance(
				this.data.x[pointA], this.data.y[pointA], 
				this.data.x[pointB], this.data.y[pointB]));
	}
	
	private double pointDistanceDouble(int pointA, int pointB) {
		return Point.distance(
				this.data.x[pointA], this.data.y[pointA], 
				this.data.x[pointB], this.data.y[pointB]);
	}
	
	// 找出最佳解
	private BestSolution getBest(BestSolution bestSolution) {
		BestSolution tmpBestSolution = bestSolution;
		if (tmpBestSolution.distance == 0) {
			tmpBestSolution.solution = this.solution;
			tmpBestSolution.distance = this.distance;
			tmpBestSolution.fitness = this.fitness;
		} else {
			if (tmpBestSolution.distance > this.distance) {
				tmpBestSolution.solution = this.solution;
				tmpBestSolution.distance = this.distance;
				tmpBestSolution.fitness = this.fitness;
			}
		}
		return tmpBestSolution;
	}
	
	// 釋放記憶體
	private void clearAll() {
		this.data = null;
		this.parameter = null;
		this.solution = null;
	}
}
