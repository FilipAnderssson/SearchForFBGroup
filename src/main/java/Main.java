import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Main {
        //Defining the logger
        private static final Logger logger = LoggerFactory.getLogger(Main.class);

        public static void main(String args []){


                //disables notification request
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");

                System.setProperty("webdriver.chrome.driver", "C:/Users/Filip/Desktop/chromedriver_win32 (1)/chromedriver.exe");
                WebDriver driver = new ChromeDriver(options);


                // Load the JSON file into a String
                String jsonString = null;
                try {
                        jsonString = new String(Files.readAllBytes(Paths.get("c:/Temp/credentials.json")));
                } catch (IOException e) {
                        logger.error("Error occurred with json: ", e);
                }

                // Create a JSONObject from the JSON String
                JSONObject jsonObject = new JSONObject(jsonString);

                // Get the password value from the JSONObject
                String email = jsonObject.getString("email");
                String password = jsonObject.getString("password");


                try {
                        driver.get("https://www.facebook.com/");

                        //accepts cookies
                        Thread.sleep(100); //waits 0.1 second
                        WebElement ck_btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[3]/button[2]"));
                        ck_btn.click();

                        logger.info("Cookies Accepted");

                        //types in mail from credentials
                        WebElement mail_cred = driver.findElement(By.id("email"));
                        mail_cred.sendKeys(email);

                        //types in password from credentials
                        WebElement pass_cred = driver.findElement(By.name("pass"));
                        pass_cred.sendKeys(password);

                        //press login
                        WebElement log_btn = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div/div/div/div[2]/div/div[1]/form/div[2]/button"));
                        log_btn.click();
                        logger.info("User logged in");

                        Thread.sleep(6000); //waits 6 seconds
                        //press the search field
                        WebElement search_btn = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[3]/div/div/div/div/div/label"));
                        search_btn.click();
                        logger.info("Search button clicked");

                        //types in the search field
                        WebElement write_btn = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[3]/div/div/div/div/div/label/input"));
                        write_btn.sendKeys("World of warcraft Sverige");
                        logger.info("Searches for World of warcraft Sverige");

                        Thread.sleep(1000); //waits 1 second
                        //clicks on the group searched for
                        WebElement group_btn = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[3]/div/div/div[2]/div/div/div[1]/div/ul/li[1]/ul/li"));
                        group_btn.click();
                        logger.info("Clicks the group");


                } catch (InterruptedException | org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
                        // Log any exceptions related to interruptions, missing elements, or timeouts during the execution.
                        logger.error("Error occurred: ", e);
                }
        }
}