package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("First Name1", "Last Name 1", "Address1", "123456789", "name1", "test1@mail.ru"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().clickDeletebutton();
    app.getContactHelper().agreeForDeletion();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size (), before.size () - 1);
  }

}


