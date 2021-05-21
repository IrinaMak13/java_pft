package ru.stqa.pft.addressbook.tests.tests;

import ru.stqa.pft.addressbook.tests.model.GroupData;
import ru.stqa.pft.addressbook.tests.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size()==0) {
    app.goTo().groupPage();
      app.group().create(new GroupData().withName("name1"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("name1").withHeader("header1").withFooter("footer1");
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().count(),equalTo(before.size ()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)) );
  }

}
