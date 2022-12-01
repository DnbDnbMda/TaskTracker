import java.util.Scanner;

public class Main {
    static StepTracker stepTracker = new StepTracker();
    static String[] namesOfMonth = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
            "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    final static int NUMBER_DAYS = 30;
    final static int NUMBER_MONTH = 12;

    public static void main(String[] args) {
        int userSelectionForm;
        printFormMain();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (scanner.hasNextInt()) {
                userSelectionForm = scanner.nextInt();
                switch (userSelectionForm) {
                    case (1):
                        printFormNumberSteps(scanner);
                        break;
                    case (2):
                        printFormStatistics(scanner);
                        break;
                    case (3):
                        printFormGoal(scanner);
                        break;
                    case (4):
                        printFormExit();
                        break;
                    default:
                        printFormMainErrorInput();
                        break;
                }
                if (userSelectionForm == 4) break;
            } else {
                printFormMainErrorInput();
                scanner.next();
            }
        }
    }

    public static void printFormMain() {
        System.out.println("Счетчик калорий v1.0!");
        System.out.println("Выберите требуемый пункт:");
        System.out.println("1. Ввести количество шагов");
        System.out.println("2. Вывести статистику за месяц");
        System.out.println("3. Изменить цель по количеству шагов в день");
        System.out.println("4. Выход");
    }

    public static void printFormNumberSteps(Scanner scanner) {
        System.out.println("-------------------------------");
        System.out.println("Ввод количества шагов");
        System.out.println("Укажите номер месяца (от 1 до 12), где 1 – Январь, 2 – февраль и т.д.");
        int UserInputNumberMonth;
        int UserInputNumberDay;
        int UserInputNumberSteps;

        while (true) {
            if (scanner.hasNextInt()) {
                UserInputNumberMonth = scanner.nextInt();
                if (checkCorrectUserInputMonth(UserInputNumberMonth)) {
                    break;
                } else {
                    printErrorInputMonth();
                }
            } else {
                printErrorInputMonth();
                scanner.next();
            }
        }

        System.out.println("Укажите номер дня от 1 до 30");

        while (true) {
            if (scanner.hasNextInt()) {
                UserInputNumberDay = scanner.nextInt();
                if (checkCorrectUserInputDay(UserInputNumberDay)) {
                    break;
                } else {
                    printErrorInputDay();
                }
            } else {
                printErrorInputDay();
                scanner.next();
            }
        }

        System.out.println("Укажите пройденное количество шагов");

        while (true) {
            if (scanner.hasNextInt()) {
                UserInputNumberSteps = scanner.nextInt();
                if (checkCorrectUserInputSteps(UserInputNumberSteps)) {
                    System.out.println("Возврат в основное меню");
                    System.out.println("-------------------------------");
                    break;
                } else {
                    printErrorInputSteps();
                }
            } else {
                printErrorInputSteps();
                scanner.next();
            }
        }
        stepTracker.setNumberOfSteps(UserInputNumberMonth, UserInputNumberDay, UserInputNumberSteps);
        printFormMain();
    }


    public static void printFormStatistics(Scanner scanner) {
        System.out.println("-------------------------------");
        System.out.println("Вывод статистики");
        System.out.println("Укажите номер месяца (от 1 до 12, где 1 – Январь, 2 – февраль и т.д.) " +
                "для подсчёта и ввода статистики по шагам:");
        int UserInputNumberMonth;
        while (true) {
            if (scanner.hasNextInt()) {
                UserInputNumberMonth = scanner.nextInt();
                if (checkCorrectUserInputMonth(UserInputNumberMonth)) {
                    break;
                } else {
                    printErrorInputMonth();
                }
            } else {
                printErrorInputMonth();
                scanner.next();
            }
        }

        System.out.println("-------------------------------");
        System.out.println("Статистика за " + namesOfMonth[UserInputNumberMonth - 1]);
        System.out.print("1. Количество пройденных шагов по дням: ");
        printAllStepsMonth(UserInputNumberMonth);
        System.out.println("2. Общее количество шагов за месяц: " +
                stepTracker.getSumNumberSteps(UserInputNumberMonth));
        System.out.println("3. Максимальное пройденное количество шагов в месяце: " +
                stepTracker.getMaxNumberStepsMonth(UserInputNumberMonth));
        System.out.println("4. Среднее количество шагов: " + stepTracker.getAverageNumberSteps(UserInputNumberMonth));
        System.out.println("5. Пройденная дистанция (в км): " + stepTracker.getDistanceCovered(UserInputNumberMonth));
        System.out.println("6. Количество сожжённых килокалорий: " +
                stepTracker.getNumberCaloriesBurned(UserInputNumberMonth));
        System.out.println("7. Лучшая серия: " + stepTracker.getBestSeries(UserInputNumberMonth));
        System.out.println();
        System.out.println("Возврат в основное меню");
        System.out.println("-------------------------------");
        printFormMain();
    }

    public static void printFormGoal(Scanner scanner) {
        System.out.println("-------------------------------");
        System.out.println("Изменение целевого значения количества шагов");
        System.out.println("Текущее целевое значения количества шагов: " + stepTracker.getTargetNumber());
        System.out.println("Укажите новое целевое значения количества шагов:");

        while (true) {
            if (scanner.hasNextInt()) {
                int UserInputNumberSteps = scanner.nextInt();
                if (checkCorrectUserInputSteps(UserInputNumberSteps)) {
                    stepTracker.setTargetNumberOfSteps(UserInputNumberSteps);
                    break;
                } else {
                    printErrorInputSteps();
                }
            } else {
                printErrorInputSteps();
                scanner.next();
            }
        }
        System.out.println();
        System.out.println("Возврат в основное меню");
        System.out.println("-------------------------------");
        printFormMain();
    }


    public static void printFormExit() {
        System.out.println("Приложение закрыто.");
    }

    public static void printFormMainErrorInput() {
        System.out.println("Введено некорректное значения. " +
                "Для выбора пункта меню воспользуйтесь числовыми занчениями от 1 до 4");
    }

    public static void printErrorInputMonth() {
        System.out.println("Введено некорректное значения месяца. " +
                "Укажите номер месяца (от 1 до 12), где 1 – Январь, 2 – февраль и т.д.");
    }

    public static void printErrorInputDay() {
        System.out.println("Введено некорректное значения номера дня. Укажите номер дня от 1 до 30");
    }

    public static void printAllStepsMonth(int UserInputNumberMonth) {
        int[] arrayStepsDay = stepTracker.getNumberStepsByDay(UserInputNumberMonth);
        for (int i = 0; i < arrayStepsDay.length; i++) {
            System.out.print((i + 1) + " день:" + arrayStepsDay[i]);
            if (i == arrayStepsDay.length - 1) {
                System.out.println();
            } else {
                System.out.print(", ");
            }
        }
    }

    public static void printErrorInputSteps() {
        System.out.println("Введено некорректное значения количества шагов");
    }

    public static boolean checkCorrectUserInputFormMain(int userSelectionForm) {
        return (userSelectionForm >= 1 && userSelectionForm <= 4) ? true : false;
    }

    public static boolean checkCorrectUserInputMonth(int UserInputNumberMonth) {
        return (UserInputNumberMonth >= 1 && UserInputNumberMonth <= NUMBER_MONTH) ? true : false;
    }

    public static boolean checkCorrectUserInputDay(int UserInputNumberDay) {
        return (UserInputNumberDay >= 1 && UserInputNumberDay <= NUMBER_DAYS) ? true : false;
    }

    public static boolean checkCorrectUserInputSteps(int UserInputNumberSteps) {
        return (UserInputNumberSteps >= 0 && UserInputNumberSteps <= Integer.MAX_VALUE) ? true : false;
    }
}
