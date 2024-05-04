package org.db.students;

import java.util.Scanner;
//Задание 1.
//Для всех пунктов, подразумевающих ввод данных сделать проверку ввода.
//Если введенные данные не могут быть интерпретированы,
//то вывести сообщение и перейти к выбору вариантов действия.

//Задание 2.
//Реализовать действие 5
//(статистика по количеству студентов на разных курсах)
//по аналогии с действием 4
//(статистика по количеству студентов из разных городов).
//Пример:
//Ввод: 5
//Вывод: Москва - 5 Краснодар - 3

//Задание 3.
//Изменить реализацию поиска по фамилии:
//Если введена пустая строка, то вывести полный список студентов.
//Если введена только одна фамилия, то выполнить точный поиск студентов по фамилии.
//Если введены 2 фамилии, разделенные запятой (,),
// то вывести всех студентов, чьи фамилии в алфавитном порядке >= первой фамилии и <= второй фамилии.
//Пример:
//Ввод: Абрикосов,Персиков
//Вывод: Все студенты, чьи фамилии >= Абрикосов и <= Персиков
//Если ввод не может быть интерпретирован, как один из вариантов выше, то вывести соответствующее сообщение и перейти к выбору вариантов.

public class Main {
    private static StudentCommandHandler STUDENT_COMMAND_HANDLER
            = new StudentCommandHandler();
    public static void main(String[] args) {
        while (true) {
            printMessage();
            Command command = readCommand();
            if (command.getAction() == Action.EXIT) {
                return;
            } else if (command.getAction() == Action.ERROR) {
                continue;
            } else {
                STUDENT_COMMAND_HANDLER.processCommand(command);
            }
        }
    }

    private static Command readCommand() {
        Scanner scanner = new Scanner(System.in);
        try {
            String code = scanner.nextLine();
            Integer actionCode;
            try {
                actionCode = Integer.valueOf(code);
            } catch (NumberFormatException e) {
                System.out.println("Введено некорректое значение. Пожалуйста, введите число.");
                return new Command(Action.ERROR);
            }
            Action action = Action.fromCode(actionCode);
            if (action.isRequiredAdditionalData()) {
                String data = scanner.nextLine();
                return new Command(action, data);
            } else {
                return new Command(action);
            }
        } catch (Exception ex) {
            System.out.println("Проблема обработки ввода. " + ex.getMessage());
            return new Command(Action.ERROR);
        }
    }

    private static void printMessage() {
        System.out.println("---------------------");
        System.out.println("0. Выход");
        System.out.println("1. Создание данных");
        System.out.println("2. Обновление данных");
        System.out.println("3. Удаление данных");
        System.out.println("4. Вывод статистика по курсам");
        System.out.println("5. Статистика по количеству студентов из разных городов");
        System.out.println("6. Поиск по фамилии");
        System.out.println("---------------------");
    }
}