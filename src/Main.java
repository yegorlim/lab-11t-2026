import exceptions.TimeSpanException;

import java.io.IOException;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) throws IOException {
        demonstrateTimeSpan();
        ClientInterface clientInterface = new ClientInterface();
        clientInterface.start();
    }

    private static void demonstrateTimeSpan() {
        TimeSpan a = new TimeSpan(2, 30);
        TimeSpan b = new TimeSpan(0, 45);
        System.out.println("TimeSpan demo:");
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + a.add(b));
        System.out.println("a - b = " + a.subtract(b));
        System.out.println("a * 1.5 = " + a.scale(1.5));

        runInvalid(() -> new TimeSpan(1, 60));
        runInvalid(() -> b.subtract(a));
        runInvalid(() -> a.scale(-1));
        System.out.println();
    }

    private static void runInvalid(Supplier<TimeSpan> action) {
        try {
            action.get();
        } catch (TimeSpanException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
