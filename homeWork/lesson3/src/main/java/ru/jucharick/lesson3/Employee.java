package ru.jucharick.lesson3;

import java.util.Random;
public abstract class Employee implements Comparable<Employee>{

    //region Public Methods

    /**
     * Расчет среднемесячной заработной платы
     * @return
     */
    public abstract double calculateSalary();

    @Override
    public int compareTo(Employee o) {
        return Double.compare(calculateSalary(), o.calculateSalary());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", salary=" + salary +
                '}';
    }

    //endregion

    //region Constructors And Initializers

//    {
//        //System.out.println("Initialize - Employee");
//        //id = ++counter;
//    }

    private Employee(){
        this("#Surnane#", "#Name#");
    }

    private Employee(String surName, String name){
        this(getCountId(),surName, name, 500);
    }

    protected Employee(int id, String surName, String name, double salary){
        this.id = id;
        if (salary < 500){
            throw new RuntimeException("Ставка заработной платы должна быть не менее 500");
        }
        this.surName = surName;
        this.name = name;
        this.salary = salary;
    }

    //endregion

    //region Getters and Setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 30000){
            throw new RuntimeException("Уровень заработной платы должен быть не менее 30000");
        }
        this.salary = salary;
    }

    //endregion

    //region Fields

    private int id;

    /**
     * Имя
     */
    protected String name;

    /**
     * Фамилия
     */
    protected String surName;

    /**
     * Ставка заработной платы
     */
    protected double salary;

    //endregion

    //region Static Fields

    protected static String[] namesMen = new String[] { "Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим", "Панкратий", "Рубен", "Герман" };
    protected static String[] namesWomen = new String[] { "Нинэль", "Татьяна", "Анна", "Катерина", "Любава", "Юлия", "Светлана", "Ольга", "Полина", "Милена" };
    protected static String[] surNamesMen = new String[] { "Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков", "Копылов", "Горбунов", "Лыткин", "Соколов" };
    protected static String[] surNamesWomen = new String[] { "Иванова", "Гавриш", "Визнер", "Салтыкова", "Беггинс", "Петрова", "Волошаненко", "Понкратова", "Плахотник", "Емельянова" };
    protected static Random random = new Random();
    private static int counter = 1000;

    //endregion

    //region Static Methods
    public static int getCountId() {
        counter = counter + 1;
        return counter;
    }

    //endregion
}
