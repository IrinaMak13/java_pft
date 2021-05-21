package ru.stqa.pft.addressbook.tests.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="addressbook")
@XStreamAlias("contact")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private  String firstname;

  @Expose
  @Column(name = "lastName")
  private  String lastName;

  @Expose
  @Transient
  @Column(name = "address")
  private  String address;

  @Column(name = "home")
  @Type(type="text")
  private  String home;

  @Expose
  @Column(name = "mobile")
  @Type(type="text")
  private  String mobile;

  @Column(name = "work")
  @Type(type="text")
  private  String work;

  @Transient
  private  String allPhones;

  @Expose
  @Transient
  private  String group;

  @Expose
  @Transient
  private  String email;

  @Transient
  private  String email2;

  @Transient
  private  String email3;

  @Transient
  private  String allEmails;

  @Column(name = "photo")
  @Type(type="text")
  private String photo;

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }



  public File getPhoto() {
    return new File (photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }
  public ContactData withHome(String home) {
    this.home = home;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withWork(String work) {
    this.work = work;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
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


  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile; }

  public String getWork() {
    return work;
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
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastName);
  }
}
