package ru.stqa.pft.addressbook.tests.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.model.ContactData;
import ru.stqa.pft.addressbook.tests.model.Contacts;
import ru.stqa.pft.addressbook.tests.model.GroupData;
import ru.stqa.pft.addressbook.tests.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class DeleteContactFromGroup extends TestBase{

  ContactData contact;
  GroupData group;

  @BeforeMethod
  public void ensurePreconditions() {
    Contacts contacts = app.db().contacts();

    for (ContactData contactData : contacts) {
      if (contactData.getGroups().size() > 0) {
        contact = contactData;
        group = contactData.getGroups().iterator().next();
        break;
      }
    }

    if (contact == null && contacts.iterator().hasNext()) {
      contact = contacts.iterator().next();
    } else if (contact == null) {
      app.goTo().homePage();
      ContactData cd = new ContactData().withFirstname("First Name1").withLastName("Last Name 1").withAddress("Address1").withHome("+7213456789").withMobile("+7123456789").withWork("+7323456789").withEmail("test1@mail.ru");
      app.contact().goToAddNewContactPage();
      app.contact().create(cd, true);
      contact = app.db().contacts().iterator().next();
    }

    if (group == null) {
      Groups groups = app.db().groups();
      if (groups.size() == 0) {
        app.goTo().groupPage();
        GroupData groupNew = new GroupData().withName("myGreatGroup").withHeader("someHeader").withFooter("someFooter");
        app.group().create(groupNew);
        group = app.db().groups().iterator().next();
      } else {
        group = groups.iterator().next();
      }

      app.goTo().homePage();
      app.contact().selectContactById(contact.getId());
      app.contact().addContactToGroup(group.getId());

      contact = contact.withGroup(group);
    }

  }

  @Test
  public void testDeleteContactToGroup() {
    app.goTo().homePage();
    app.contact().goToGroupPage(group.getId());
    app.contact().selectContactById(contact.getId());
    app.contact().removeContactFromGroup();

    ContactData contactAfter = app.db().contacts().stream().filter(contactData -> contactData.getId() == contact.getId()).findFirst().get();
    ContactData contactBeforeWithoutGroup = contact.withoutGroup(group);

    assertThat(contactBeforeWithoutGroup.getGroups(), equalTo(contactAfter.getGroups()));
  }
}
