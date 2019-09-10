/**
 * class product-entity
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.entity;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

    private int idproducts;
    private String name;
    private int calories;
    private double proteins;
    private double fats;
    private double carbs;

    public Product(String name, int calories){
        this.calories = calories;
        this.name = name;
    }

    public void setIdproducts(int idproducts){
        this.idproducts = idproducts;
    }

    public int getId() {
        return idproducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return calories == product.calories &&
                Double.compare(product.proteins, proteins) == 0 &&
                Double.compare(product.fats, fats) == 0 &&
                Double.compare(product.carbs, carbs) == 0 &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, calories, proteins, fats, carbs);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + idproducts +
                ", name='" + name + '\'' +
                ", callories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbs=" + carbs  + '\'' +
                '}';
    }
}
