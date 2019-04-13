package config.dutra.fabiano;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum TipoDriver implements EstabeleceDriver {
   FIREFOX {
               
     public DesiredCapabilities obterCapacidadesDesejadas() {
       DesiredCapabilities capabilities = DesiredCapabilities.firefox();
      return capabilities;
    }

   public WebDriver obterObjetoWebDriver(DesiredCapabilities capabilities) {
     return new FirefoxDriver(capabilities);
   }

   public WebDriver obterObjetoWebDriverRemoto(
     DesiredCapabilities capabilities, Platform plataforma,
     String enderecoRemoto) {

       capabilities.setPlatform(plataforma);

       WebDriver driver = null;
       try {
           driver = new RemoteWebDriver(new URL(enderecoRemoto),
           capabilities);

       } catch (MalformedURLException e) {

           e.printStackTrace();
       }

      return driver;
     }

   },
   CHROME {

    public DesiredCapabilities obterCapacidadesDesejadas() {
      DesiredCapabilities capabilities = DesiredCapabilities.chrome();

      capabilities.setCapability("chrome.switches",
      Arrays.asList("--no-default-browser-check"));
      HashMap<String, String> chromePreferences = new HashMap<String, String>();
      chromePreferences.put("profile.password_manager_enabled", "false");

      capabilities.setCapability("chrome.prefs", chromePreferences);
      // Fim

      return capabilities;
    }

     public WebDriver obterObjetoWebDriver(DesiredCapabilities capabilities) {
        return new ChromeDriver(capabilities);
     }

     public WebDriver obterObjetoWebDriverRemoto(
       DesiredCapabilities capabilities, Platform plataforma,
       String enderecoRemoto) {

         capabilities.setPlatform(plataforma);
         WebDriver driver = null;
        try {
           driver = new RemoteWebDriver(new URL(enderecoRemoto),
           capabilities);
         } catch (MalformedURLException e) {

           e.printStackTrace();
         }

         return driver;
      }
  }
}