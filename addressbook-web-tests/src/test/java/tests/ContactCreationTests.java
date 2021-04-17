package tests;


import org.testng.annotations.*;
import model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  protected void testContactCreation() throws Exception {
    app.goToAddNewContactPage();
    app.fillContactForm(new ContactData("Name1", "Last Name 1", "Address1", "123456789"));
    app.submitContactCreation();
    app.returnToHomePage();
  }


}
