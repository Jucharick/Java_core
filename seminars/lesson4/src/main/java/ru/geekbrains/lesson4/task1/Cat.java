package ru.geekbrains.lesson4.task1;

public class Cat extends Animal {


    /**
     * Создает новое животное
     *
     * @param name имя животного
     * @throws AnimalNameException некорректное имя животного
     */
    public Cat(String name) throws AnimalNameException {
        super(name);
    }

    @Override
    public void swim(int distance) throws AnimalSwimException {
        throw new AnimalSwimException("Кот не умеет плавать", name, distance);
    }

    @Override
    public void run(int distance) throws AnimalRunException {
        if (distance < 50){
            //TODO: Выполнение дальнейших инструкций
        }
        else{
            throw new AnimalRunException("Кот не умеет далеко бегать", name, distance);
        }
    }
}
