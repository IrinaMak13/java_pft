package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;


public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  public boolean isIssueOpen(int issueId) {
    try {
      Issue issue = app.rest().getIssue(issueId);
      return issue != null && issue.getState() != 2 && issue.getState() != 3;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }


  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}

