package ru.stqa.pft.addressbook;


import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  protected void testContactCreation() throws Exception {
    goToAddNewContactPage();
    fillContactForm(new ContactData("Name1", "Last Name 1", "Address1", "123456789"));
    submitContactCreation();
    returnToHomePage();
  }


}
