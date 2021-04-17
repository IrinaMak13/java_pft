package tests;


import org.testng.annotations.*;
import model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  protected void testContactCreation() throws Exception {
    app.getContactHelper().goToAddNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Name1", "Last Name 1", "Address1", "123456789"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }


}
