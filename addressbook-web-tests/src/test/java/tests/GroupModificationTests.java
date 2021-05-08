package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("name1"));
    }
  }

  @Test
  public void testGroupModification() {
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    int index = before.size ()-1;
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("name1").withHeader("header1").withFooter("footer1");
    app.group().modify(index, group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size (), before.size () );

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
  }


}
