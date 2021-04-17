package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("name1", "header1", "footer1"));
    submitGroupCreation();
    returnToGroupPage();
    logOut();
  }

}
