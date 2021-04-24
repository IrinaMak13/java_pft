package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification () {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Name2", "Last Name 2", "Address2", "123456789", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
