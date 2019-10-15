/**
 * class user-entity
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class User implements Serializable {

    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Gender gender;
    private LocalDate birthDate;
    private LocalDate registrDate;
    private String email;
    private Role role;
    private String path;
    private BigDecimal balance;
    private int active;

    public User(){}

    public User (String login, String password){
        this.login = login;
        this.password = password;
        name = null;
        gender = Gender.MALE;
        email = null;
        surname = null;
        role = Role.USER;
        active = 1;
        path = "/picture/user.png";
    }

    public User(String login){
        this.login = login;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getRegistrDate() {
        return registrDate;
    }

    public void setRegistrDate(LocalDate registrDate) {
        this.registrDate = registrDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        if(role == null){
            role = Role.USER;
        }
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                active == user.active &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                gender == user.gender &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(registrDate, user.registrDate) &&
                Objects.equals(email, user.email) &&
                role == user.role &&
                Objects.equals(path, user.path) &&
                Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, surname, gender, birthDate, registrDate, email, role, path, balance, active);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", registrDate=" + registrDate +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", path='" + path + '\'' +
                ", balance=" + balance +
                ", active=" + active +
                '}';
    }
}

