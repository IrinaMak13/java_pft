package tests;


import com.thoughtworks.xstream.XStream;
import model.ContactData;
import model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null){
      xml +=line;
     line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData>contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((c)-> new Object[] {c}).collect(Collectors.toList()).iterator();

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
    ContactData contact = new ContactData().withFirstname("First Name1'").withLastName("Last Name 1").withAddress("Address1").withMobile("+7123456789").withGroup("test1").withEmail("test1@mail.ru");
    boolean b = true;
    app.contact().create((contact),b);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));

  }
}
