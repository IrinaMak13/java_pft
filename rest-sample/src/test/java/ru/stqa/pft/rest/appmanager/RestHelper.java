package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestHelper {

  private final String bugifyApiUrl;
  private final String apiKey;

  public RestHelper(ApplicationManager app) {
    this.bugifyApiUrl =  app.getProperty("bugify.api.url");
    this.apiKey =  app.getProperty("bugify.api.key");
  }

  public Issue getIssue(int id) throws IOException {
    String json = getExecutor().execute(Request.Get(bugifyApiUrl + "/issues/" + id + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issueSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    Issue issue = issueSet.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    return issue;
  }

  public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get(bugifyApiUrl + "/issues.json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues,new TypeToken<Set<Issue>>() { }.getType());
  }

  public int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post(bugifyApiUrl + "/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth(apiKey, "");
  }

}
