package model;

import java.util.Objects;

public class ContactData {
  private final String id;
  private final String firstname;
  private final String lastName;
  private final String address;
  private final String mobile;
  private final String group;
  private final String email;



  public ContactData(String firstname, String lastName, String address, String mobile, String group, String email) {
    this.id = null;
    this.firstname = firstname;
    this.lastName = lastName;
    this.address = address;
    this.mobile = mobile;
    this.group = group;
    this.email = email;
  }

  public ContactData(String id, String firstname, String lastName, String address, String mobile, String group, String email) {
    this.id = id;
    this.firstname = firstname;
    this.lastName = lastName;
    this.address = address;
    this.mobile = mobile;
    this.group = group;
    this.email = email;
  }

  public String getId() {
    return id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastName);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

}
