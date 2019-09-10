/**
 * class program-entity
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.entity;

import java.util.Objects;

public class Program {

    private  int id;
    private String programName;
    private String product;
    private MealTime time;
    private double portions;
    private MealDay day;
    private int showProgram;

    public Program(String programName, String product, double portions, MealDay mealDay, MealTime mealTime){
        this.programName = programName;
        this.product = product;
        this.portions = portions;
        this.day = mealDay;
        this.time = mealTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public MealTime getTime() {
        return time;
    }

    public void setTime(MealTime time) {
        this.time = time;
    }

    public double getPortions() {
        return portions;
    }

    public void setPortions(double portions) {
        this.portions = portions;
    }

    public MealDay getDay() {
        return day;
    }

    public void setDay(MealDay day) {
        this.day = day;
    }

    public int getShowProgram() {
        return showProgram;
    }

    public void setShowProgram(int showProgram) {
        this.showProgram = showProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return id == program.id &&
                Double.compare(program.portions, portions) == 0 &&
                showProgram == program.showProgram &&
                Objects.equals(programName, program.programName) &&
                Objects.equals(product, program.product) &&
                time == program.time &&
                day == program.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, programName, product, time, portions, day, showProgram);
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", programName='" + programName + '\'' +
                ", product='" + product + '\'' +
                ", time=" + time +
                ", portions=" + portions +
                ", day=" + day +
                ", showProgram=" + showProgram +
                '}';
    }
}

