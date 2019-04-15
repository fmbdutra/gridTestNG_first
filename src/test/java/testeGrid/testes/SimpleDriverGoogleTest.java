package br.com.verdecard.homologacao.Automacao_de_Teste_Distribuida;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SimpleDriverGoogleTest {
	private WebDriver driver;
	private WebDriverWait wait;
	
	private String urlNode = "http://172.22.2.106:5555"+"/wd/hub";
	
  @Test
  public void f() {
		driver.get("http://www.google.com.br/");
		driver.findElement(By.name("q")).sendKeys("verdecard" + Keys.ENTER);

		this.wait = new WebDriverWait(driver, 60);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#resultStats"))));

		String expected = "www.verdecard.com.br";
		
		driver.get("");
		
		Assert.assertTrue(driver.getPageSource().contains(expected));
  }
  
  @BeforeClass
  public void beforeClass() throws MalformedURLException {
	  
	  DesiredCapabilities caps = DesiredCapabilities.firefox();
	  caps.setVersion("65.0.1");
	  caps.setPlatform(Platform.LINUX);
	  driver = new RemoteWebDriver(new URL(urlNode), caps);
  }

  @AfterClass
  public void afterClass() {
		if (driver != null) {
			driver.quit();
		}
  }

}
