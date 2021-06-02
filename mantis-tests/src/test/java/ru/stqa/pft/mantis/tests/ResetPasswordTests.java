package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

  @Test
  public void testResetPassword() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "root"));
//    assertTrue(session.isLoggedInAs("administrator"));
    resetPassword();

  }

  private void resetPassword() {
    click(By.linkText("Manage Users"));
    click(By.xpath("//a[@href='manage_user_edit_page.php?user_id=" + id + "']"));
    click(By.xpath("//input[@value='Reset Password']"));
  }
  protected void click(By locator) {
    findElement(locator).click();
  }
}
  }
}
