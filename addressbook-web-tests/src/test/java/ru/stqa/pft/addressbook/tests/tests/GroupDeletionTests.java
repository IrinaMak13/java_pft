package ru.stqa.pft.addressbook.tests.tests;

import ru.stqa.pft.addressbook.tests.model.GroupData;
import ru.stqa.pft.addressbook.tests.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
   if (app.db().groups().size()==0) {
     app.goTo().groupPage();
     app.group().create(new GroupData().withName("name1"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(),equalTo(before.size ()-1));
    Groups after = app.db().groups();
    app.group().returnToGroupPage();
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListInUI();
    }

}

