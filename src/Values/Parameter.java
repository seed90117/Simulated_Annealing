package Values;

public class Parameter {

	private int generation;
	private int startTemperature;
	private int endTemperature;
	private double reduceParameter;
	private double sizeX;
	private double sizeY;
	private double addNumber;
	private static Parameter instance = null;
	private Parameter(){}
	
	public static synchronized Parameter getInstance() {
		if (instance == null) {
			instance = new Parameter();
		}
		return instance;
	}
	
	public void setGeneration(int generation) {
		this.generation = generation;
	}
	
	public void setStartTemperature(int startTemperature) {
		this.startTemperature = startTemperature;
	}
	
	public void setEndTemperature(int endTemperature) {
		this.endTemperature = endTemperature;
	}
	
	public void setReduceParameter(double reduceParameter) {
		this.reduceParameter = reduceParameter;
	}
	
	public void setPointParameter(double sizeX, double sizeY, double addNumber) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.addNumber = addNumber;
	}
	
	public int getGeneration() {
		return this.generation;
	}
	
	public int getStartTemperature() {
		return this.startTemperature;
	}
	
	public double getEndTemperature() {
		return this.endTemperature;
	}
	
	public double getReduceParameter() {
		return this.reduceParameter;
	}
	
	public double getSizeX () {
		return this.sizeX;
	}
	
	public double getSizeY () {
		return this.sizeY;
	}
	
	public double getAddNumber () {
		return this.addNumber;
	}
}
