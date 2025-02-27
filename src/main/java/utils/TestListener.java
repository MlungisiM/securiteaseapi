package utils;

import common.base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends base implements ITestListener{
    private static ExtentReports extent = new ExtentReports();
    private static ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/SecuritEase_API_Execution"+generateDateTimeString()+".html");
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ITestListener.super.onTestSuccess(iTestResult);
        String passedTest = iTestResult.getName();
        String screenshotsDir = "./reports/";
        reporter.config().setDocumentTitle("SecuritEase API Health Check");
        reporter.config().setTheme(Theme.DARK);
        extent.attachReporter(reporter);
        extent.createTest(passedTest).log(Status.PASS,"Test Passed");
        extent.flush();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ITestListener.super.onTestFailure(iTestResult);
        String failedTest = iTestResult.getName();
        String screenshotsDir = "./reports/";
        reporter.config().setDocumentTitle("SecuritEase API Health Check");
        reporter.config().setTheme(Theme.DARK);
        extent.attachReporter(reporter);
        extent.createTest(failedTest).log(Status.FAIL,"Test Failed");
        extent.flush();

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }

}
