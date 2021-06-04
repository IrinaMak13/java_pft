package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

  private final String mantisAdminLogin;
  private final String mantisAdminPassword;
  private final String mantisSoapUrl;

  private ApplicationManager app;

  public SoapHelper (ApplicationManager app) {
    this.app = app;
    this.mantisAdminLogin = app.getProperty("mantis.admin.login");
    this.mantisAdminPassword = app.getProperty("mantis.admin.password");
    this.mantisSoapUrl = app.getProperty("mantis.soap.url");
  }
  public Set<Project> getProjects() throws MalformedURLException, RemoteException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible(mantisAdminLogin, mantisAdminPassword);
    return Arrays.asList(projects).stream()
            .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());

  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    MantisConnectPortType mc = new MantisConnectLocator()
            .getMantisConnectPort(new URL(mantisSoapUrl));
    return mc;
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories(mantisAdminLogin, mantisAdminPassword, BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()),issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add(mantisAdminLogin, mantisAdminPassword, issueData);
    IssueData createdIssueData = mc.mc_issue_get(mantisAdminLogin, mantisAdminPassword, issueId);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                    .withName(createdIssueData.getProject().getName()));

  }

  public Issue getIssue(int id) throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData issueData = mc.mc_issue_get(mantisAdminLogin, mantisAdminPassword, BigInteger.valueOf(id));
    return new Issue().withId(issueData.getId().intValue())
            .withSummary(issueData.getSummary()).withDescription(issueData.getDescription())
            .withProject(new Project().withId(issueData.getProject().getId().intValue()).withName(issueData.getProject().getName()))
            .withStatus(issueData.getStatus().getName());
  }

}
