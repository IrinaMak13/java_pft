package tests;


import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  protected void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().goToAddNewContactPage();
    app.getContactHelper().createContact(new ContactData("First Name1", "Last Name 1", "Address1", "123456789", "name1", "test1@mail.ru"), true);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size (), before.size () + 1);
  }

}
