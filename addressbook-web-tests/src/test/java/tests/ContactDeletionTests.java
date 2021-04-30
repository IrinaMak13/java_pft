package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Name1", "Last Name 1", "Address1", "123456789", "name1", "test1@mail.ru"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().clickDeletebutton();
    app.getContactHelper().agreeForDeletion();
  }

}


