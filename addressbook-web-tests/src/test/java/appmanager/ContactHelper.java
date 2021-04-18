package appmanager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends HelperBase {

  public ContactHelper (FirefoxDriver wd) {
    super(wd);}

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
   click(By.name("theform"));
   click (By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData) {
    type (By.name("firstname"), contactData.getName());
    type (By.name("lastname"), contactData.getLastName());
    click (By.name("theform"));
    type (By.name("address"), contactData.getAddress());
    type (By.name("mobile"), contactData.getMobile());
  }

  public void goToAddNewContactPage() {
    click(By.linkText("add new"));
  }
}
