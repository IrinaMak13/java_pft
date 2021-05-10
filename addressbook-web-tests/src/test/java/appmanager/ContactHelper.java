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

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.name("theform"));
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());

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
    click(By.xpath("(//input[@type='checkbox' and @name='selected[]' and @value='" + id + "'])"));
  }

  public void clickDeletebutton() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void agreeForDeletion() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact, boolean b) {
    fillContactForm((contact), b);
    submitContactCreation();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("(//input[@type='checkbox' and @name='selected[]'])[position()=1]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Set<ContactData> getContactsWithPhones() {
    Set<ContactData> contacts = new HashSet<>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastName(lastname).withAllPhones(allPhones));
    }
    return contacts;
  }

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> subElements = element.findElements(By.cssSelector("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = subElements.get(1).getText();
      String firstname = subElements.get(2).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastName(lastname));
    }
    return new Contacts(contactCache);
  }

  public void selectContact(int i) {
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
    contactCache = null;
  }

  public void fillContactForm(ContactData contact) {
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastName(lastname).withHome(home).withMobile(mobile).withWork(work).withAddress(address).withEmail(email);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

  }
}
