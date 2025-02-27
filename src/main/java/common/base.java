package common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.jetbrains.annotations.NotNull;

public class base {

    public ExtentReports report;
    public ExtentTest logger;
    private static String _reportDirectory;
    public static String  TestName;

    public static void setReportDirectoty(String dir){_reportDirectory =dir;}
    public static String getReportDirectoty(){return _reportDirectory;}


    @NotNull
    public  static String generateDateTimeString() {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
        return dateFormat.format(dateNow).toString();
    }

    static Properties prop = null;
    //Load config.properties file
    public base() {

        prop = new Properties();

        try {
            FileInputStream propFile = new FileInputStream(System.getProperty("user.dir") +
                    "/src/main/resources/config.properties");
            prop.load(propFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProp() {
        return prop;
    }

}
