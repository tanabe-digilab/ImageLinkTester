package jp.co.digilab.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Tester {

	public static final void main(String[] arguments) {
		try {
			new Tester(arguments);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);;
		}
	}

	private WebDriver webDriver;

	public Tester(String[] arguments) {
		webDriver = new FirefoxDriver();
		webDriver.get("http://imagelink.kyodonews.jp");
		new WebDriverWait(webDriver, 30).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return "complete".equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState"));
			}
		});

		WebElement frontDiv = webDriver.findElement(By.cssSelector("div.ui-widget-overlay.ui-front"));
		if (frontDiv != null) {
			frontDiv.click();
			new WebDriverWait(webDriver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.ui-widget-overlay.ui-front")));
			System.out.println("OK");
		}
	}
}
