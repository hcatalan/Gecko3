package org.openqa.selenium;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Main {

  public static void main(String[] args) throws InterruptedException, IOException {
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
    FirefoxOptions options = new FirefoxOptions();

    WebDriver driver = new FirefoxDriver(options);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    driver.get("https://www.flashscore.es/futbol/espana/laliga-ea-sports-2021-2022/#/MPV5cuep/table/overall");

    WebElement tournamentTable = driver.findElement(By.id("tournament-table"));

    wait.until(ExpectedConditions.elementToBeClickable(By.className("ui-table__row")));
    List<WebElement> tableRows = tournamentTable.findElements(By.className("ui-table__row"));

    Document document = DocumentHelper.createDocument();
    Element root = document.addElement("data");

    for (WebElement row : tableRows) {
      List<WebElement> cells = row.findElements(By.className("table__cell"));
      Element rowElement = root.addElement("row");

      int i = 0;
      for (WebElement cell : cells) {
        if (i != 9) {
          Element cellElement = rowElement.addElement("cell");
          cellElement.setText(cell.getText());
        }
        i++;
      }
    }

    // También puedes guardar el documento XML en un archivo si es necesario
    XMLWriter writer = new XMLWriter(new FileWriter("./archivo.xml"));
    writer.write(document);
  }
}
