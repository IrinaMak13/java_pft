package ru.stqa.pft.addressbook.tests.tests;

import ru.stqa.pft.addressbook.tests.model.ContactData;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {


  @Test
  public void testAddressPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().getContactsWithPhonesAndEmails().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

  }
}

