package menu.itest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SimpleTest {
	@Test
	public void test01() {
		WebDriver driver = new FirefoxDriver();// new SafariDriver();
		driver.get("http://localhost:8080");
		assertThat(driver.getTitle(), is("index"));

		WebElement h1 = driver.findElement(By.tagName("h1"));
		assertThat(h1, notNullValue());
		assertThat(h1.getText(), is("index"));

		WebElement text = driver
				.findElement(By.xpath("//input[@name='content']"));
		assertThat(text, notNullValue());
		text.sendKeys("ほげほげふがふが");

		WebElement submit = driver.findElement(By
				.xpath("//input[@type='submit']"));
		assertThat(submit, notNullValue());
		submit.click();
	}
}
