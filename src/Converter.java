public class Converter {
    double sumDistance;
    double sumCaloriesBurned;
    final static double LENGTH_ONE_STEP = 0.00075;
    final static double CALORIES_ONE_STEP = 50;

    public double convertStepsToDistance(int sumNumberOfSteps) {
        double sumDistance = LENGTH_ONE_STEP * sumNumberOfSteps;
        return sumDistance;
    }

    public double convertStepsToCaloriesBurned(int sumNumberOfSteps) {
        sumCaloriesBurned = sumNumberOfSteps * CALORIES_ONE_STEP / 1000;
        return sumCaloriesBurned;
    }
}
