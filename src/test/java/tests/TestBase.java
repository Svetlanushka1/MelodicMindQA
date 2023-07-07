package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.Duration;

public class TestBase {

    public  static WebDriver driver;
    private String browser;

    protected Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite(alwaysRun = true)
    public void init(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://la-vocal-staging.web.app/");
        int e = 78;
    }

    @BeforeMethod(alwaysRun = true)
    public void startTest(Method method){
        logger.info("Start test --> "+method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void stopTest(Method method, ITestResult result){
        if(result.isSuccess())
            logger.info("Test result --> PASSED");
        else
            logger.info("Test result --> FAILED");
        logger.info("Stop test ---> "+method.getName());
        logger.info("=========================================================================");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
//        driver.quit();
    }
}
