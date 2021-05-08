package appmanager;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper (WebDriver wd) {
    super(wd);}

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
   click(By.name("theform"));
   click (By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type (By.name("firstname"), contactData.getName());
    type (By.name("lastname"), contactData.getLastName());
    type (By.name("address"), contactData.getAddress());
    type (By.name("mobile"), contactData.getMobile());
    type (By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void goToAddNewContactPage() {
    click(By.linkText("add new"));
  }

  public void initContactModification(int id) {
    click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void selectContact() {
    click(By.xpath("(//input[@type='checkbox' and @name='selected[]'])[position()=1]"));
  }

  public void selectContactById(int id) {
    click(By.xpath("(//input[@type='checkbox' and @name='selected[]' and @value='"+id+"'])"));
  }

  public void clickDeletebutton() {
    click (By.xpath("//input[@value='Delete']"));
  }

  public void agreeForDeletion() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact, boolean b) {
    fillContactForm((contact), b);
    submitContactCreation();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("(//input[@type='checkbox' and @name='selected[]'])[position()=1]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement>elements = wd.findElements(By.name("entry"));
    for (WebElement element: elements) {
      List<WebElement> subElements = element.findElements(By.cssSelector("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = subElements.get(1).getText();
      String firstname = subElements.get(2).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastName(lastname));
    }
    return contacts;
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement>elements = wd.findElements(By.name("entry"));
    for (WebElement element: elements) {
      List<WebElement> subElements = element.findElements(By.cssSelector("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = subElements.get(1).getText();
      String firstname = subElements.get(2).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastName(lastname));
    }
    return contacts;
  }

  public void selectContact(int i)  {
    click(By.xpath("(//input[@type='checkbox' and @name='selected[]'])[position()=" + i + "]"));
  }

  public void delete() {
    selectContact();
    clickDeletebutton();
    agreeForDeletion();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    clickDeletebutton();
    agreeForDeletion();
  }

  public void fillContactForm(ContactData contact) {
  }

}
