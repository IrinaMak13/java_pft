package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class ResetPasswordTests extends TestBase {

  private User user;

  @BeforeMethod
  public void before() {
    app.mail().start();
    // find one(not admin) or create user
    List<User> users = app.db().users();
    user = users.stream().filter(u -> !u.getUsername().equalsIgnoreCase("administrator")).findFirst().orElse(null);
    if (user == null) {
      // create new user and confirm registration email
      app.resetPassword().createUser();
      user = app.db().users().stream().filter(u -> !u.getUsername().equalsIgnoreCase("administrator")).findFirst().orElse(null);
      assertNotNull(user);
    }
  }

  @AfterMethod
  public void after() {
    app.mail().stop();
  }

  @Test
  public void testResetPassword() throws IOException {
    //login as admin
    app.resetPassword().login("administrator", "root");
    //go to management page
    app.resetPassword().goToAllUsersManagementPage();
    //go to user management page
    app.resetPassword().goToUserManagementPage(String.valueOf(user.getId()));
    //click on reset btn
    app.resetPassword().clickOnReset();
    //read email
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
    String link = app.resetPassword().findConfirmationLink(mailMessages, user.getEmail());
    //confirm link and replace password
    String newPassword = String.format("newpassword%s", System.currentTimeMillis());
    app.resetPassword().confirmReset(link, newPassword);
    // test new login;
    HttpSession session = app.newSession();
    assertTrue(session.login(user.getUsername(), newPassword));
  }

}