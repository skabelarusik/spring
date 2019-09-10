package by.epam.crackertracker.entity;

import java.util.Objects;

public class Bucket {
    private int id;
    private String userLogin;
    private String product;
    private int calories;
    private Double portions;

    public Bucket(String userLogin, String product, int calories, Double portions) {
        this.userLogin = userLogin;
        this.product = product;
        this.calories = calories;
        this.portions = portions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Double getPortions() {
        return portions;
    }

    public void setPortions(Double portions) {
        this.portions = portions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return id == bucket.id &&
                calories == bucket.calories &&
                Objects.equals(userLogin, bucket.userLogin) &&
                Objects.equals(product, bucket.product) &&
                Objects.equals(portions, bucket.portions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userLogin, product, calories, portions);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", userLogin='" + userLogin + '\'' +
                ", product='" + product + '\'' +
                ", calories=" + calories +
                ", portions=" + portions +
                '}';
    }
}
