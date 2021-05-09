package tests;

import model.ContactData;
import model.Contacts;
import org.testng.annotations.Test;

public class ContactPhoneTests extends TestBase {


  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().goToAddNewContactPage();
    ContactData contact = new ContactData().withFirstname("First Name1").withLastName("Last Name 1").withAddress("Address1").withHome("213456789").withMobile("123456789").withWork("323456789").withGroup("name1").withEmail("test1@mail.ru");
    contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }
}
