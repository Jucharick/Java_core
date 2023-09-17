package ru.jucharick.lesson3;

import java.util.ArrayList;
import java.util.List;

public class Freelancer extends Employee {

    private Freelancer(String surName, String name, double salary){
        super(getCountId(), surName, name, salary);
    }

    public static Employee getInstance(){
        return new Freelancer(
                surNamesWomen[random.nextInt(surNamesWomen.length)],
                namesWomen[random.nextInt(surNamesWomen.length)],
                random.nextInt(1000, 5000));
    }

    public static List<Employee> getEmployees(int count){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++)
            employees.add(getInstance());
        return employees;
    }

    @Override
    public double calculateSalary() {
        return 20.8 * 8 * salary;
    }

    @Override
    public String toString() {
        return String.format("ID %d, %s %s; Фрилансер; Среднемесячная заработная плата (почасовая оплата - %.2f (руб/ч)): %.2f (руб.)", getId(),
                surName, name, salary, calculateSalary());
    }
}
