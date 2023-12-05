package org.openqa.selenium;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
    FirefoxOptions options = new FirefoxOptions();

    WebDriver driver = new FirefoxDriver(options);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    driver.get("https://www.flashscore.es/futbol/espana/laliga-ea-sports-2021-2022/#/MPV5cuep/table/overall");

    WebElement tournamentTable = driver.findElement(By.id("tournament-table"));
    //wait.until(ExpectedConditions.elementToBeClickable(tournamentTable));

    wait.until(ExpectedConditions.elementToBeClickable(By.className("ui-table__row")));
    List<WebElement> tableRows = tournamentTable.findElements(By.className("ui-table__row"));

    WebElement goleadores = driver.findElement(By.id("tournament-table"));
    //wait.until(ExpectedConditions.elementToBeClickable(tournamentTable));


    ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();

    for (WebElement row : tableRows) {

      List<WebElement> cells = row.findElements(By.className("table__cell"));
      ArrayList<String> line = new ArrayList<String>();

      int i = 0;
      for (WebElement cell : cells) {
        //System.out.println(i+" "+cell.getText());
        if (i != 9) line.add(cell.getText());
        i++;

      }
      lines.add(line);

      for (String e : line) {
        System.out.println(e);
      }
      CSV csv = new CSV();
      csv.generarCsvEstat(line);
    }

  }
}