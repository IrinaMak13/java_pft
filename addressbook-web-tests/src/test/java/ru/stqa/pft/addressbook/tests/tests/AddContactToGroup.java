package ru.stqa.pft.addressbook.tests.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.model.ContactData;
import ru.stqa.pft.addressbook.tests.model.Contacts;
import ru.stqa.pft.addressbook.tests.model.GroupData;
import ru.stqa.pft.addressbook.tests.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {
  ContactData contact;
  GroupData group;


  @BeforeMethod
  public void ensurePreconditions() {
    Contacts contacts = app.db().contacts();
    if (contacts.size() == 0) {
      app.goTo().homePage();
      ContactData cd = new ContactData().withFirstname("First Name1").withLastName("Last Name 1").withAddress("Address1").withHome("+7213456789").withMobile("+7123456789").withWork("+7323456789").withEmail("test1@mail.ru");
      app.contact().goToAddNewContactPage();
      app.contact().create(cd, true);
      contact = app.db().contacts().iterator().next();
    } else {
      contact = contacts.iterator().next();
    }

    Groups groups = app.db().groups();
    if (groups.size() == 0) {
      app.goTo().groupPage();
      GroupData groupNew = new GroupData().withName("myGreatGroup").withHeader("someHeader").withFooter("someFooter");
      app.group().create(groupNew);
      group = app.db().groups().iterator().next();
    } else {
      group = groups.iterator().next();
    }

  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().homePage();
    app.contact().selectContactById(contact.getId());
    app.contact().addContactToGroup(group.getId());

    ContactData contactBeforeWithGroup = contact.inGroup(group);
    Contacts afterAllContacts = app.db().contacts();
    ContactData contactAfter = afterAllContacts.stream().filter(contactData -> contactData.getId() == contact.getId()).findFirst().get();
    assertThat(contactBeforeWithGroup.getGroups(), equalTo(contactAfter.getGroups()));
  }

}
