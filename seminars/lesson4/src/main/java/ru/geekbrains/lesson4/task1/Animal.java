package ru.geekbrains.lesson4.task1;

public abstract class Animal {

    protected final String name;

    public String getName() {
        return name;
    }

    /**
     * Создает новое животное
     * @param name имя животного
     * @throws AnimalNameException некорректное имя животного
     */
    public Animal(String name) throws AnimalNameException  {
        if (name == null || name.length() < 3)
            throw new AnimalNameException("Имя животного указано некорректно", name);

        this.name = name;
    }

    public abstract void swim(int distance) throws AnimalSwimException;

    public abstract void run(int distance) throws AnimalRunException;

}
