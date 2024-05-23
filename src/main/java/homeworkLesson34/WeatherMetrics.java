package homeworkLesson34;

import java.util.ArrayList;
import java.util.List;

public class WeatherMetrics {

    //Метод расчёта средней температуры
    public Double calculateAverageTemperature(List<Double> temperatures) {
        double sum;
        double average;
        if (temperatures == null || temperatures.isEmpty()) {
            System.err.println("Error! List of temperatures is empty");
            return null;
        } else {
            sum = 0.0;
            for (double temperature : temperatures) {
                sum += temperature;
            }
            average = sum / temperatures.size();
        }
        return average;
    }

    //Метод проверки предупреждений о заморозках
    public boolean checkForFrostWarnings(List<Double> temperatures) {
        if (temperatures == null) {
            //Так как нельзя вернуть true или false и невозможно вернуть null:
            throw new IllegalArgumentException("Error! List of temperatures is null");
        } else if (temperatures.isEmpty()) {
            throw new IllegalArgumentException("Error! List of temperatures is empty");
        }
        for (Double temperature : temperatures) {
            if (temperature < 0) {
                return true;
            }
        }
        return false;
    }

    //Метод оценки уровня осадков

        public List<PrecipitationLevels> evaluatePrecipitationLevels(List<Double> precipitationLevels) {
            List<PrecipitationLevels> classifications = new ArrayList<>();
            if (precipitationLevels == null || precipitationLevels.isEmpty()) {
                System.err.println("Error! List of precipitation levels is empty or null");
                classifications.add(PrecipitationLevels.INCORRECT);
                return classifications;
            }

            for (Double level : precipitationLevels) {
                if (level == null || level < 0 || level > 3000) {
                    System.out.println(PrecipitationLevels.INCORRECT.getDescription());
                    classifications.add(PrecipitationLevels.INCORRECT);
                } else if (level < 2.5) {
                    System.out.println(PrecipitationLevels.LOW.getDescription());
                    classifications.add(PrecipitationLevels.LOW);
                } else if (level < 7.6) {
                    System.out.println(PrecipitationLevels.MEDIUM.getDescription());
                    classifications.add(PrecipitationLevels.MEDIUM);
                } else {
                    System.out.println(PrecipitationLevels.HIGH.getDescription());
                    classifications.add(PrecipitationLevels.HIGH);
                }
            }

            return classifications;
        }
    }
