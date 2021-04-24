package tests;


import model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  protected void testContactCreation() throws Exception {
    app.getContactHelper().goToAddNewContactPage();
    app.getContactHelper().createContact(new ContactData("Name1", "Last Name 1", "Address1", "123456789", "name1"), true);

  }

}
