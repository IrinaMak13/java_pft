package ru.stqa.pft.addressbook.tests.tests;

import ru.stqa.pft.addressbook.tests.model.ContactData;
import ru.stqa.pft.addressbook.tests.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
public void ensurePreconditions() {
    if (app.db().contacts().size() ==0){
    app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstname("First Name1").withLastName("Last Name 1").withAddress("Address1").withHome("+7213456789").withMobile("+7123456789").withWork("+7323456789").withEmail("test1@mail.ru"), true);
    }
  }

  @Test
  public void testContactModification() {
   Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("First Name1").withLastName("Last Name 1").withAddress("Address1").withHome("").withMobile("+7123456789").withWork("").withEmail("test1@mail.ru");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }


}


