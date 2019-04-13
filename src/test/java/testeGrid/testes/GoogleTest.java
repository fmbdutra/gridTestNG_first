package testeGrid.testes;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testeGrid.config.TipoDriver;

public class GoogleTest {

	static String nodeURL = "http://192.168.1.27:5555" + "/wd/hub";

	WebDriver driver;
	WebDriverWait wait;	
	

	@BeforeClass
	public void inicializarDriver() throws Exception {
		// Atribuição da instância do Enum do Tipo Firefox
		TipoDriver selecionadoTipoDriver = TipoDriver.FIREFOX;
		// Atribuição das capacidades para usar o tipo driver Firefox
		DesiredCapabilities capacidadesDesejadas = selecionadoTipoDriver.obterCapacidadesDesejadas();
		capacidadesDesejadas.setVersion("64");
//		capacidadesDesejadas.setBrowserName("chrome");

		driver = selecionadoTipoDriver.obterObjetoWebDriverRemoto(capacidadesDesejadas, Platform.LINUX, nodeURL);

		/**
		 * driver = selecionadoTipoDriver.obterObjetoWebDriver(capacidadesDesejadas);
		 */

	}

	@Parameters({ "pesquisa","resultado" })
	// Executa o método como teste
	@Test
	public void google(String pesquisa, String resultado) {
		
		wait = new WebDriverWait(driver, 15);
		driver.get("http://www.google.com");

		driver.findElement(By.name("q")).sendKeys(pesquisa, Keys.ENTER);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultStats")));
		
		Assert.assertTrue(driver.getPageSource().contains(resultado));

		driver.quit();

	}

	@AfterClass
	public void fecharDriver() {
		if (null != driver) {
			driver.quit();
		}
	}
}
