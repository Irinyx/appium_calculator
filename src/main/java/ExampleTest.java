import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ExampleTest extends BaseTest {
    @Test
    public void testExample() throws InterruptedException {
        driver.findElement(By.id("btn5")).click();
        Thread.sleep(50000);
    }
}
