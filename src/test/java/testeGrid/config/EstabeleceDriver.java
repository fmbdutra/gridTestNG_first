package testeGrid.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface EstabeleceDriver {
	  //Declarações de métodos
	  
	   WebDriver obterObjetoWebDriver(DesiredCapabilities desiredCapabilities);
	  
	   WebDriver obterObjetoWebDriverRemoto(DesiredCapabilities desiredCapabilities,
	    Platform  plataforma, String enderecoRemoto);
	  
	   DesiredCapabilities obterCapacidadesDesejadas();
}
