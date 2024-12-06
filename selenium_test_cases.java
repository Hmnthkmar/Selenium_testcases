import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class TipTopTest {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        try {
           
            driver.get("https://d3pv22lioo8876.cloudfront.net/tiptop/");
            testCase1(driver);
            testCase2(driver);
            testCase3(driver);
            testCase4(driver);
            testCase5(driver);
            testCase6(driver);
            testCase7(driver);
        } finally {
            driver.quit();
        }
    }

    // Test Case 1: Verify that the text input element with xpath .//input[@name='my-disabled'] is disabled
    public static void testCase1(WebDriver driver) {
        WebElement disabledInput = driver.findElement(By.xpath(".//input[@name='my-disabled']"));
        assert !disabledInput.isEnabled() : "Test Case 1 Failed";
        System.out.println("Test Case 1 Passed");
    }

    // Test Case 2: Verify that the text input with value 'Readonly input' is in readonly state using 2 xpaths
    public static void testCase2(WebDriver driver) {
        WebElement readonlyInput1 = driver.findElement(By.xpath(".//input[@value='Readonly input']"));
        WebElement readonlyInput2 = driver.findElement(By.xpath(".//input[@readonly]"));
        assert readonlyInput1.getAttribute("readonly") != null : "Test Case 2 Failed";
        assert readonlyInput2.getAttribute("readonly") != null : "Test Case 2 Failed";
        System.out.println("Test Case 2 Passed");
    }

    // Test Case 3: Verify that the dropdown field to select color is having 8 elements using 2 xpaths
    public static void testCase3(WebDriver driver) {
        WebElement dropdown = driver.findElement(By.xpath(".//select[@name='my-select']"));
        List<WebElement> options = dropdown.findElements(By.tagName("option"));
        assert options.size() == 8 : "Test Case 3 Failed";
        System.out.println("Test Case 3 Passed");
    }

    // Test Case 4: Verify that the submit button is disabled when no data is entered in Name field
    public static void testCase4(WebDriver driver) {
        WebElement submitButton = driver.findElement(By.xpath(".//button[@type='submit']"));
        WebElement nameField = driver.findElement(By.xpath(".//input[@name='name']"));
        
        nameField.clear(); // Ensure the field is empty
        assert !submitButton.isEnabled() : "Test Case 4 Failed";
        System.out.println("Test Case 4 Passed");
    }

    // Test Case 5: Verify that the submit button is enabled when both Name and Password fields are entered
    public static void testCase5(WebDriver driver) {
        WebElement nameField = driver.findElement(By.xpath(".//input[@name='name']"));
        WebElement passwordField = driver.findElement(By.xpath(".//input[@name='password']"));
        WebElement submitButton = driver.findElement(By.xpath(".//button[@type='submit']"));
        
        nameField.clear();
        passwordField.clear();
        
        // Fill in both fields
        nameField.sendKeys("Test Name");
        passwordField.sendKeys("TestPassword123");
        
        assert submitButton.isEnabled() : "Test Case 5 Failed";
        System.out.println("Test Case 5 Passed");
    }

    // Test Case 6: Verify that on submit of 'Submit' button the page shows 'Received' text
    public static void testCase6(WebDriver driver) {
        WebElement submitButton = driver.findElement(By.xpath(".//button[@type='submit']"));
        submitButton.click();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement receivedText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Received')]")));
        
        assert receivedText.isDisplayed() : "Test Case 6 Failed";
        System.out.println("Test Case 6 Passed");
    }

    // Test Case 7: Verify that on submit of form all the data passed to the URL
    public static void testCase7(WebDriver driver) {
        WebElement nameField = driver.findElement(By.xpath(".//input[@name='name']"));
        WebElement passwordField = driver.findElement(By.xpath(".//input[@name='password']"));
        WebElement submitButton = driver.findElement(By.xpath(".//button[@type='submit']"));
        
        nameField.clear();
        passwordField.clear();
        
        // Fill in the fields
        nameField.sendKeys("Test Name");
        passwordField.sendKeys("TestPassword123");
        
        submitButton.click();
        
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("name=Test+Name") : "Test Case 7 Failed";
        assert currentUrl.contains("password=TestPassword123") : "Test Case 7 Failed";
        System.out.println("Test Case 7 Passed");
    }
}
