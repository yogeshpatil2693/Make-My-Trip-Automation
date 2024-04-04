package demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");


        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(2000);
        String cUrl = driver.getCurrentUrl();
        System.out.println(cUrl);
        if(cUrl.contains("makemytrip")){
	        System.out.println("Test Case01 is pass");
        }else{
	        System.out.println("Test Case01 is fail");
        }
        System.out.println("end Test case: testCase01");
    }

    public  void testCase02() throws InterruptedException{

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        System.out.println("Start Test case: testCase02");
        driver.get("https://www.makemytrip.com/");

        try {
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'notif')]")));
		
		Thread.sleep(2000);
		WebElement close_popup = driver.findElement(By.cssSelector("a.close"));
		
		close_popup.click();
		} catch(Exception e) {
			e.printStackTrace();
		}

        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='fromCity']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("blr");
        Thread.sleep(8000);

        driver.findElement(By.xpath("(//*[text()='Bengaluru'])[1]")).click();
        Thread.sleep(3000);

        String blrcity = driver.findElement(By.xpath("(//*[@class='truncate airPortName '])[1]")).getText();
        System.out.println(blrcity);
        if(blrcity.contains("Bengaluru")){
            System.out.println("Correct from city");
        }else{
            System.out.println("Incorrect fromcity");
        }

        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='toCity']")).click();
       
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("del");

        Thread.sleep(7000);
        driver.findElement(By.xpath("//*[text()='New Delhi']")).click();
       

        Thread.sleep(3000);
        String tocity = driver.findElement(By.xpath("(//*[@class='truncate airPortName '])[2]")).getText();
        System.out.println(tocity);
        if(tocity.contains("Delhi")){
            System.out.println("correct tocity");
        }else{
            System.out.println("incorrect tocity");
        }

        
            // String month1= "April2024";
            // String day1="11";
            // while(true) {
            //     String text1=driver.findElement(By.xpath("(//div[@class='DayPicker-Caption'])[2]")).getText();
            //     System.out.println(text1);
            //     if (text1.equalsIgnoreCase(month1)) {
            //         break;
            //     }
            //     else 
            //     {
            //         driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click(); 
            //     }
            // }
            // driver.findElement(By.xpath("(//p[text()='"+day1+"'])[2]")).click();
            // driver.findElement(By.xpath("//a[text()='Search']")).click();
        

        Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();  
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Mon Apr 29 2024']")));
        driver.findElement(By.xpath("//div[@aria-label='Mon Apr 29 2024']")).click();
        Thread.sleep(4000);

        Thread.sleep(3000);
        String dateentered = driver.findElement(By.xpath("//p[@data-cy='departureDate']")).getText();
        if(dateentered.contains("29")){
            System.out.println("correct date");
        }else{
            System.out.println("incorrect date");
        }

        WebElement searchButn = driver.findElement(By.xpath("//a[text()='Search']"));
        searchButn.click();

        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='OKAY, GOT IT!']")));
            WebElement infoContainer = driver.findElement(By.xpath("//button[text()='OKAY, GOT IT!']"));
            infoContainer.click();
        }catch(Exception e) {
			e.printStackTrace();
		}
        

        List<WebElement> priceList = driver.findElements(By.xpath("//div[@class='blackText fontSize18 blackFont white-space-no-wrap clusterViewPrice']"));

        ArrayList<String> priceText = new ArrayList<>();

        for(WebElement price:priceList){
            priceText.add(price.getText());
        }

        for (String text : priceText) {
            System.out.println(text);
        }

        String price = driver.findElement(By.xpath("(//div[@class='blackText fontSize18 blackFont white-space-no-wrap clusterViewPrice'])[1]")).getText();
        System.out.println(price);
        if(price.contains("per adult")){
            System.out.println("correct price");
        }else{
            System.out.println("incorrect price");
        }
        
        String frcity = driver.findElement(By.xpath("((//div[@class='timingOptionOuter'])[1]//p[@class='blackText'])[1]")).getText();
        System.out.println(frcity);
        if(frcity.toLowerCase().contains("Beng")){
            System.out.println("Test Case02 is pass");
        }else{
            System.out.println("Test Case02 is fail");
        }

        String tcity = driver.findElement(By.xpath("((//div[@class='timingOptionOuter'])[1]//p[@class='blackText'])[2]")).getText();
        System.out.println(tcity);
        if(tcity.toLowerCase().contains("del"))
        {
            System.out.println("Test Case02 is pass");
        }else{
            System.out.println("Test Case02 is fail");
        }
        System.out.println("end Test case: testCase02");
    }

    public  void testCase03() throws InterruptedException{
        System.out.println("Start Test case: testCase03");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
       driver.get("https://www.makemytrip.com/");
       Thread.sleep(2000);
       

        try {
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'notif')]")));
		
		Thread.sleep(2000);
		WebElement close_popup = driver.findElement(By.cssSelector("a.close"));
		
		close_popup.click();
		} catch(Exception e) {
			e.printStackTrace();
		}

        driver.findElement(By.xpath("//*[text()='Trains']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='fromCity']")).click();
        
        Thread.sleep(7000);
        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("ypr");

        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[text()='YPR']")).click();
       

        String bcity = driver.findElement(By.xpath("(//p[@class='code resetTextTransform'])[1]")).getText();
        System.out.println(bcity);
        if(bcity.contains("Bangaluru")){
            System.out.println("correct city");
        }else{
            System.out.println("incorrect city");
        }

        
        //driver.findElement(By.xpath("//input[@class='react-autosuggest__input react-autosuggest__input--open']")).sendKeys("New Delhi");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("ndls");

        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[text()='NDLS']")).click();
        Thread.sleep(2000);

        String ndcity = driver.findElement(By.xpath("(//p[@class='code resetTextTransform'])[2]")).getText();
        System.out.println(ndcity);
         if(ndcity.contains("Delhi")){
            System.out.println("correct city");
         }else{
            System.out.println("incorrect city");
         }

 
        Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();  
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Mon Apr 29 2024']")));
        driver.findElement(By.xpath("//div[@aria-label='Mon Apr 29 2024']")).click();
        Thread.sleep(4000);

        String dateentered = driver.findElement(By.xpath("//p[@data-cy='departureDate']")).getText();
        System.out.println(dateentered);
        if(dateentered.contains("29")){
            System.out.println("correct date");
        }else{
            System.out.println("incorrect date");
        }

        WebElement classbtn = driver.findElement(By.xpath("//*[@class='appendBottom5 downArrow']"));
        classbtn.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Third AC']")));
        WebElement thirdAC = driver.findElement(By.xpath("//*[text()='Third AC']"));
        thirdAC.click();
        
        WebElement searchButn = driver.findElement(By.xpath("//a[text()='Search']"));
        searchButn.click();

        Thread.sleep(4000);

        String dateSelected = driver.findElement(By.xpath("//p[text()='29 Apr, Mon']")).getText();
        System.out.println(dateSelected);
        if (dateSelected.contains("29")) {
            System.out.println("Correct date selected");
        } else {
            System.out.println("incorrect date selected");
        }

        System.out.println("end Test case: testCase03");
    }

    public  void testCase04() throws InterruptedException{

        System.out.println("Start Test case: testCase04");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        driver.get("https://www.makemytrip.com/");
        // driver.findElement(By.xpath("(//*[text()='Buses'])[1]")).click();
        // Thread.sleep(2000);

        // try {
		// 	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'notif')]")));
		
		// Thread.sleep(2000);
		// WebElement close_popup = driver.findElement(By.cssSelector("a.close"));
		
		// close_popup.click();
		// } catch(Exception e) {
		// 	e.printStackTrace();
		// }
        
        // driver.findElement(By.xpath("//p[text()='Login or Create Account']")).click();
        // driver.findElement(By.xpath("//div[@class='landingSprite2 signInByMailIcon appendLeft15 mousePointer']")).click();
        // driver.findElement(By.xpath("//input[@placeholder='Enter Email Address']")).sendKeys("yogeshpatil4646@gmail.com");
        // driver.findElement(By.xpath("//*[text()='Continue']")).click();
        // driver.findElement(By.id("password")).sendKeys("yogesh_2693");
        // driver.findElement(By.xpath("//*[text()='Login']")).click();
        
        // Thread.sleep(20000);

        // driver.findElement(By.xpath("null"))

        try {
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'notif')]")));
		
		Thread.sleep(2000);
		WebElement close_popup = driver.findElement(By.cssSelector("a.close"));
		
		close_popup.click();
		} catch(Exception e) {
			e.printStackTrace();
		}

        driver.findElement(By.xpath("//*[text()='Buses']")).click();

        driver.findElement(By.xpath("//label[@for='fromCity']")).click();

        driver.findElement(By.xpath("//input[@aria-controls='react-autowhatever-1']")).sendKeys("bangl");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Bangalore, Karnataka']")));
        driver.findElement(By.xpath("//*[text()='Bangalore, Karnataka']")).click();

        Thread.sleep(4000);
        String busfcity = driver.findElement(By.xpath("(//input[@class='bussw_inputField font30 latoBlack'])[1]")).getAttribute("value");
        if(busfcity.contains("Bangalore")){
            System.out.println("correct city");
        }else{
            System.out.println("incorrect city");
        }

        // Thread.sleep(3000);
        // driver.findElement(By.xpath("//label[@for='toCity']")).click();
        Thread.sleep(8000);
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("ran");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Ranchi, Jharkhand']")));
        driver.findElement(By.xpath("//*[text()='Ranchi, Jharkhand']")).click();

        Thread.sleep(4000);
        String bustcity = driver.findElement(By.xpath("(//input[@class='bussw_inputField font30 latoBlack'])[2]")).getAttribute("value");
        System.out.println(bustcity);
        if(bustcity.contains("Ranchi")){
            System.out.println("correct city");
        }else{
            System.out.println("incorrect city");
        }


        Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();  
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Mon Apr 29 2024']")));
        driver.findElement(By.xpath("//div[@aria-label='Mon Apr 29 2024']")).click();
        Thread.sleep(4000);
        

        driver.findElement(By.id("search_button")).click();

        String fbus = driver.findElement(By.id("from")).getAttribute("value");
        System.out.println(fbus);
        if (fbus.contains("Bangalore")) {
            System.out.println("correct city");
        } else {
            System.out.println("incorrect city");
        }

        String tbus = driver.findElement(By.id("to")).getAttribute("value");
        System.out.println(tbus);
        if (tbus.contains("Ranchi")) {
            System.out.println("correct city");
        } else {
            System.out.println("incorrect city");
        }

        String errorText = driver.findElement(By.xpath("//span[@class='error-title']")).getText();
        System.out.println(errorText);
        if(errorText.contains("No buses found for 29 Apr")){
            System.out.println("Test Case is pass");
        }else{
            System.out.println("Test Case is fail");
        }
        
        System.out.println("end Test case: testCase04");
    }
}
