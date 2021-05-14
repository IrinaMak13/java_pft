package tests;


import model.ContactData;
import model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().withFirstname("First Name1").withLastName("Last Name 1").withAddress("Address1").withMobile("+7123456789").withGroup("name1").withEmail("test1@mail.ru")});
    list.add(new Object[]{new ContactData().withFirstname("First Name2").withLastName("Last Name 2").withAddress("Address2").withMobile("+7223456789").withGroup("name1").withEmail("test2@mail.ru")});
    list.add(new Object[]{new ContactData().withFirstname("First Name3").withLastName("Last Name 3").withAddress("Address3").withMobile("+7323456789").withGroup("name1").withEmail("test3@mail.ru")});
    return list.iterator();
  }


  @Test(dataProvider = "validContacts")
  protected void testContactCreation(ContactData contact) throws Exception {
    app.goTo().homePage();
      Contacts before = app.contact().all();
      app.contact().goToAddNewContactPage();
      File photo = new File("src/test/resources/smile.jpg");
      boolean b = true;
      app.contact().create((contact), b);
      app.goTo().homePage();
      assertThat(app.contact().count(), equalTo(before.size() + 1));
      Contacts after = app.contact().all();
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


  @Test
  protected void testBadContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().goToAddNewContactPage();
    ContactData contact = new ContactData().withFirstname("First Name1'").withLastName("Last Name 1").withAddress("Address1").withMobile("+7123456789").withGroup("name1").withEmail("test1@mail.ru");
    boolean b = true;
    app.contact().create((contact),b);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));

  }
}
