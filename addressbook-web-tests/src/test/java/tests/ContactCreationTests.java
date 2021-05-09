package tests;


import model.ContactData;
import model.Contacts;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  protected void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().goToAddNewContactPage();
    ContactData contact = new ContactData().withFirstname("First Name1").withLastName("Last Name 1").withAddress("Address1").withHome("213456789").withMobile("123456789").withWork("323456789").withGroup("name1").withEmail("test1@mail.ru");
    boolean b = true;
    app.contact().create((contact),b);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
        assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

  }

  @Test
  protected void testBadContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().goToAddNewContactPage();
    ContactData contact = new ContactData().withFirstname("First Name1'").withLastName("Last Name 1").withAddress("Address1").withMobile("123456789").withGroup("name1").withEmail("test1@mail.ru");
    boolean b = true;
    app.contact().create((contact),b);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));

  }
}
