package tests;


import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  protected void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().goToAddNewContactPage();
    ContactData contact = new ContactData("First Name1", "Last Name 1", "Address1", "123456789", "name1", "test1@mail.ru");
    boolean b = true;
    app.getContactHelper().createContact((contact),b);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size (), before.size () + 1);

    int max = 0;
    for (ContactData g:after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }

    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }

}
