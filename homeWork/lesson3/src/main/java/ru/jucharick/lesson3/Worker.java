package ru.jucharick.lesson3;

import java.util.ArrayList;
import java.util.List;
public class Worker extends Employee {

    private Worker(String surName, String name, double salary){
        super(getCountId(), surName, name, salary);
    }

    public static Employee getInstance(){
        return new Worker(
                surNamesMen[random.nextInt(surNamesMen.length)],
                namesMen[random.nextInt(surNamesMen.length)],
                random.nextInt(30000, 250000));
    }

    public static List<Employee> getEmployees(int count){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++)
            employees.add(getInstance());
        return employees;
    }

    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("ID %d, %s %s; Рабочий; Среднемесячная заработная плата (фиксированная месячная оплата): %.2f (руб.)", getId(),
                surName, name, calculateSalary());
    }
}
