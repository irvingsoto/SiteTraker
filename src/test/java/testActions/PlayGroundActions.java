package testActions;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pageObjects.PlayGroundPage;
import utils.ReportUtil;

import java.util.concurrent.TimeUnit;

public class PlayGroundActions {
    protected WebDriver driver = null;
    PlayGroundPage pg = null;
    final TestData data = TestData.TEST_DATA_01;
    ReportUtil reporter = null;

    public PlayGroundActions (WebDriver driver, ReportUtil reporter){
        this.reporter= reporter;
        this.driver= driver;
        pg = new PlayGroundPage(driver);
    }

    public void updateAllColumnsInTable() {
        Actions actions = new Actions(driver);
        try {
            Thread.sleep(15000);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
            driver.switchTo().frame(0);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
            driver.switchTo().frame(1);
            pg.getLabelEditRow3Btn().click();
            pg.getLabelRow3Txt().clear();
            pg.getLabelRow3Txt().sendKeys(data.getLabel());
            actions.sendKeys(Keys.TAB).perform();
            pg.getWebsiteEditRow3Btn().click();
            pg.getWebsiteRow3Txt().clear();
            pg.getWebsiteRow3Txt().sendKeys(data.getWebsite());
            actions.sendKeys(Keys.TAB).perform();
            pg.getPhoneNumberRow3Btn().click();
            pg.getPhoneNumberRow3Txt().clear();
            pg.getPhoneNumberRow3Txt().sendKeys(data.getPhone());
            actions.sendKeys(Keys.TAB).perform();
            pg.getDateRow3Btn().click();
            pg.getDateRow3Txt().clear();
            pg.getDateRow3Txt().sendKeys(data.getDate());
            pg.getTimeRow3Txt().click();
            pg.getTimeRow3Txt().clear();
            pg.getTimeRow3Txt().sendKeys(data.getTime());
            actions.sendKeys(Keys.TAB).perform();
            pg.getBalanceRow3Btn().click();
            pg.getBalanceRow3Txt().clear();
            pg.getBalanceRow3Txt().sendKeys(data.getBalance());
            actions.sendKeys(Keys.TAB).perform();
            System.out.println("Actual Result: All the columns in row 3 are Updated");
            reporter.printActualResult("All the columns in row 3 are Updated");

        } catch (Exception e) {
            System.out.println("Actual Result: Not Possible to Update all the columns in row 3" + e);
            reporter.printActualResult("Not Possible to Update all the columns in row 3");
        }
        reporter.printImageIntoReport(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
    }

    public void assertColumnsAreUptatedInTable() {
        boolean valueToAssert=false;
        try {
            if (pg.getUpdatedRow3Txt().getText().contains(data.getLabel())) valueToAssert=true;
            else valueToAssert=false;
            if (pg.getUpdatedWebsiteRow3Txt().getText().contains(data.getWebsite())) valueToAssert=true;
            else valueToAssert=false;
            if (pg.getUpdatedPhoneTxt().getText().contains(data.getPhone())) valueToAssert=true;
            else valueToAssert=false;
            if (pg.getUpdatedDateTxt().getText().contains(data.getDate())) valueToAssert=true;
            else valueToAssert=false;
            if (pg.getUpdatedBalanceTxt().getText().contains(data.getBalance())) valueToAssert=true;
            else valueToAssert=false;
        } catch (Exception e) {
            System.out.println("Actual Result: Not possible to assert updated values in table " + e);
            reporter.printActualResult("Not possible to assert updated values in table");
            reporter.printImageIntoReport(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
        }
        Assert.assertTrue("Actual Result: Data is not successfully updated in table", valueToAssert);
        System.out.println("Actual Result: Data in table is successfully updated");
        reporter.printActualResult("Data in table is successfully updated");
        reporter.printImageIntoReport(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
    }

    public void scrollDownToTndOfTable(){
        try{
            Thread.sleep(11000);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
            driver.switchTo().frame(0);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
            driver.switchTo().frame(1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pg.getLastRowNameTxt());
            System.out.println("Actual Result: Last record found");
            reporter.printActualResult("Last record found");
        }catch(Exception e){
            System.out.println("Actual Result: Last record NOT found" + e);
            reporter.printActualResult("Last record NOT found");
        }
        reporter.printImageIntoReport(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
    }

    public void cliclkOnShowDetails(){
        try{
            pg.getShowDownBtn().click();
            pg.getShowDetailsOpstion().click();
            System.out.println("Actual Result: 'Show Details' is clicked");
            reporter.printActualResult("'Show Details' is clicked");
        }catch(Exception e){
            System.out.println("Actual Result: Not Possible to click on 'Show Details'" + e);
            reporter.printActualResult("Not Possible to click on 'Show Details'");
        }
        reporter.printImageIntoReport(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
    }

    public void assertRecords(){
        try{
            Assert.assertSame(pg.getLastRowNameTxt().getText().trim(), pg.getName().getText().trim());
            Assert.assertSame(pg.getBalanceRow100Txt().getText().trim(), pg.getBalance().getText().trim());
            System.out.println("Actual Result: Records match");
            reporter.printActualResult("Records match");
        }catch(Exception e){
            System.out.println("Actual Result: Not Possible to assert records'" + e);
            reporter.printActualResult("Not Possible to assert records");
        }
        reporter.printImageIntoReport(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
    }
}
