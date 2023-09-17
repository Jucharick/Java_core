package ru.jucharick.lesson3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Program {

    public static List<Employee> employees = new ArrayList<>();
    public static int numberOfEmployees  = 25;

    public static void main(String[] args) {
        for (int i = 1; i <= numberOfEmployees; i++) {
            switch (new Random().nextInt(2)) {
                case 0:
                    employees.add(Worker.getInstance());
                    break;
                case 1:
                    employees.add(Freelancer.getInstance());
                    break;
            }
        }

        for (Employee employee: employees) {
            System.out.println(employee);
        }

        Collections.sort(employees, new EmployeeNameComparator());
        System.out.println();
        System.out.println("Отсортированный список сотрудников: ");

        for (Employee employee: employees) {
            System.out.println(employee);
        }
    }
}
