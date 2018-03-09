package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class customlisteners implements ITestListener{
	
	
	public static String methodname;
	

	public void onTestStart(ITestResult result) {
	//System.out.println("Test Case Started");
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		methodname = result.getName();
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
