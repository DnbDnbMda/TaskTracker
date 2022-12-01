import java.util.Arrays;
import java.util.OptionalInt;

public class StepTracker {
    public int targetNumberOfSteps = 10000;
    final static int NUMBER_DAYS = 30;
    final static int NUMBER_MONTH = 12;
    public int[][] numberOfStepsByDay = new int[NUMBER_MONTH][NUMBER_DAYS];

    //Присваиваем всем дням количество шагов ноль
    StepTracker() {
        // + для тетсирования, перед сдачей удалить
        numberOfStepsByDay[0][0] = 5000;
        numberOfStepsByDay[0][1] = 15000;
        numberOfStepsByDay[0][2] = 20000;
        numberOfStepsByDay[0][10] = 20000;
        numberOfStepsByDay[0][29] = 5000;
        // - для тетсирования, перед сдачей удалить
    }

    // Записывает новое количество шагов;
    public void setNumberOfSteps(int numberOfMonth, int numberOfDay, int numberOfSteps) {
        numberOfStepsByDay[numberOfMonth - 1][numberOfDay - 1] = numberOfSteps;
    }

    //устанавливает навое целевое количество шагов
    public void setTargetNumberOfSteps(int targetNumberOfSteps) {
        this.targetNumberOfSteps = targetNumberOfSteps;
    }

    // возвращает массив шагов за месяц по дням
    public int[] getNumberStepsByDay(int numberOfMonth) {
        int[] arrayNumberStepsByDay = new int[NUMBER_DAYS];
        for (int i = 0; i < arrayNumberStepsByDay.length; i++) {
            arrayNumberStepsByDay[i] = numberOfStepsByDay[numberOfMonth - 1][i];
        }
        return arrayNumberStepsByDay;
    }

    //Возвращает максмиальное количество шагов в день за месяц
    public int getMaxNumberStepsMonth(int numberOfMonth) {
        int maxNumberStepsMonth = Arrays.stream(numberOfStepsByDay[numberOfMonth - 1]).max().getAsInt();
        return maxNumberStepsMonth;
    }

    //Возвращает среднее количество шагов в день за месяц
    public double getAverageNumberSteps(int numberOfMonth) {
        double maxNumberStepsMonth = Arrays.stream(numberOfStepsByDay[numberOfMonth - 1]).average().getAsDouble();
        return getShortValue(maxNumberStepsMonth);
    }

    //Возвращает возвращает пройденную дистанцию
    public double getDistanceCovered(int numberOfMonth) {
        Converter converter = new Converter();
        double distance = converter.convertStepsToDistance(getSumNumberSteps(numberOfMonth));
        return getShortValue(distance);
    }

    //Возвращает соженные каллории
    public double getNumberCaloriesBurned(int numberOfMonth) {
        Converter converter = new Converter();
        double CaloriesBurned = converter.convertStepsToCaloriesBurned(getSumNumberSteps(numberOfMonth));
        return getShortValue(CaloriesBurned);
    }

    // Лучшая серия: максимальное количество подряд идущих дней,
    // в течение которых количество шагов за день было равно или выше целевого
    //Возвращает лучшую серию
    public int getBestSeries(int numberOfMonth) {
        int[] arrayStepsDay = getNumberStepsByDay(numberOfMonth);
        int countSeries = 0;
        int maxSeries = 0;
        for (int i = 0; i < arrayStepsDay.length; i++) {
            if (arrayStepsDay[i] >= targetNumberOfSteps) {
                countSeries++;
            } else {
                if (countSeries - 1 > maxSeries) maxSeries = countSeries;
                countSeries = 0;
            }
        }
        return maxSeries;
    }

    //Возвращает среднее количество шагов в день за месяц
    public int getSumNumberSteps(int numberOfMonth) {
        int sumNumberStepsMonth = Arrays.stream(numberOfStepsByDay[numberOfMonth - 1]).sum();
        return sumNumberStepsMonth;
    }

    // округляет до двух знаков после запятой
    public double getShortValue(double longValue) {
        double scale = Math.pow(10, 1);
        double result = Math.ceil(longValue * scale) / scale;
        return result;
    }
}
