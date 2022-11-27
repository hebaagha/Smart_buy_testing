

	
import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;
	import org.testng.asserts.SoftAssert;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class webSiteTest  {

		public WebDriver driver;

		public int numberOfTry = 1000;
		public int items_in_inventory = 4;

		// hard coded

		SoftAssert softassertProcess = new SoftAssert();

		@BeforeTest()

		public void this_is_before_test() {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

			driver.get("https://smartbuy-me.com/smartbuystore/");

			driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();

			driver.manage().window().maximize();

		}

		@Test()
		public void Test_Adding_item_SAMSUNG_50_inch() {

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			for (int i = 0; i < numberOfTry; i++) {

				driver.findElement(By.xpath(
						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[3]/div[1]/div/div/form[1]/div[1]/button"))
						.click();

				String msg = driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/div[1]")).getText();

				if (msg.contains("Sorry")) {

					driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[1]")).click();

				} else {
					driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[2]")).click();

				}

			}

		}

		@Test()

		public void we_need_to_check_the_correct_price() {

			driver.navigate().back();

			String the_single_item_price = driver
					.findElement(By.xpath(
							"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[3]"))
					.getText();

			String[] the_updated_single_item_price = the_single_item_price.split("JOD");

			String The_Final_Single_item_price_in_string = the_updated_single_item_price[0].trim();

			String updated = The_Final_Single_item_price_in_string.replace(",", ".");

			Double final_Price = Double.parseDouble(updated);

			//System.out.println(final_Price);

			//System.out.println(final_Price * numberOfTry);

			String ActualWebsite = driver.getTitle();

			softassertProcess.assertEquals(final_Price * numberOfTry,269000.0 , "enta ma btfhm 3arabi");

			softassertProcess.assertAll();
			String the_original_price =driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[2]")).getText();
			//System.out.println(the_original_price);
			String[] the_updated_original_price = the_original_price.split("JOD");
			String The_Final_original_price_in_string = the_updated_original_price[0].trim();
			Double Final_price = Double.parseDouble(The_Final_original_price_in_string);
			System.out.println("price before discount ="+Final_price);
			String the_discount =driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[1]")).getText();
			//System.out.println(the_discount);
			String[] the_updated_discount = the_discount.split("%");
			String The_Final_discount_in_string = the_updated_discount[0].trim();
			Double Final_discount = Double.parseDouble(The_Final_discount_in_string)/100;
			//System.out.println(Final_discount);
			Double actual_discount = Math.floor(( Final_price -(Final_price * Final_discount)));
			
			System.out.println("price after discount  = "+actual_discount);
			softassertProcess.assertEquals(actual_discount,final_Price );
			softassertProcess.assertAll();
			
			
			
			
			
			
			
			

			

			

		}
	//	@Test()
		//public void check_discount() {
		//	String the_discount =driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[2]")).getText();
		//	System.out.println(the_discount);
	//	}
		
		

	}


