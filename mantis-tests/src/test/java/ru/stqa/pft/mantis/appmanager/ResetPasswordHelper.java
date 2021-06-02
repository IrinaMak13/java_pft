package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

import static org.testng.Assert.assertNotNull;

public class ResetPasswordHelper extends HelperBase {
  public ResetPasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void goToAllUsersManagementPage() {
    goToPage("/manage_user_page.php");
  }

  public void goToUserManagementPage(String id) {
    click(By.xpath("//a[@href='manage_user_edit_page.php?user_id=" + id + "']"));
  }

  public void clickOnReset() {
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void login(String username, String password) {
    goToPage("/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Login']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void confirmReset(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.tagName("button"));
  }

  public void createUser() {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String email = String.format("user%s@localhost.localdomain", now);
    app.registration().start(user, email);
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).
            filter(m -> m.text.contains("Your password has been reset")).findFirst().orElse(null);
    assertNotNull(mailMessage);
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
