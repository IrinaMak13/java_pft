package model;

public class ContactData {
  private String firstname;
  private final String lastName;
  private final String address;
  private final String mobile;
  private final String group;
  private final String email;


  public ContactData(String firstname, String lastName, String address, String mobile, String group, String email) {
    this.firstname = firstname;
    this.lastName = lastName;
    this.address = address;
    this.mobile = mobile;
    this.group = group;
    this.email = email;
  }

  public String getName() {
    return firstname;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getMobile() {
    return mobile;

  }

  public String getGroup() {
    return group;
  }


  public void add(ContactData contact) {
  }
}
