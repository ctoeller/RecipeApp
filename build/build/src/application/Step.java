package application;

public class Step {
	String step;
	int stepNumber;
	Step(int stepNumber, String step){
		this.step = step;
		this.stepNumber = stepNumber;
	}
	
	public String getStep() {
		return step;
	}
	public int getStepNumber() {
		return stepNumber;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}
	@Override
	public String toString() {
		return step;
	}
}
