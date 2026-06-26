package rollcall.util;

import rollcall.model.Student;

import java.util.List;
import java.util.Random;

public class WeightedRandom {

    private static final Random RANDOM = new Random();

    public static int select(List<Student> students, List<Integer> excludeIndices) {
        if (students.isEmpty()) return -1;

        double[] weights = new double[students.size()];
        double totalWeight = 0.0;

        for (int i = 0; i < students.size(); i++) {
            double w = students.get(i).getCallWeight();
            if (excludeIndices != null && excludeIndices.contains(i)) {
                w *= 0.01;
            }
            weights[i] = Math.max(w, 0.01);
            totalWeight += weights[i];
        }

        double random = RANDOM.nextDouble() * totalWeight;
        double cumulative = 0.0;
        for (int i = 0; i < weights.length; i++) {
            cumulative += weights[i];
            if (random <= cumulative) return i;
        }
        return weights.length - 1;
    }
}
