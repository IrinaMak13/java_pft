package tests;

import org.testng.annotations.*;
import model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("name1", "header1", "footer1"));
    app.logOut();
  }

}
