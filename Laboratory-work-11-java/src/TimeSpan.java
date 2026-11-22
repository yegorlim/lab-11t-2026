import exceptions.IllegalInputTimeSpanException;
import exceptions.IllegalScaleFactorException;
import exceptions.NegativeTimeSpanException;

public class TimeSpan {

    private long totalMinutes;

    public TimeSpan(int minutes) {
        if (minutes < 0) {
            throw new IllegalInputTimeSpanException("Minutes must not be negative");
        }
        this.totalMinutes = minutes;
    }

    public TimeSpan(int hours, int minutes) {
        if (hours < 0 || minutes < 0) {
            throw new IllegalInputTimeSpanException("Hours and minutes must not be negative");
        }
        if (minutes > 59) {
            throw new IllegalInputTimeSpanException("Minutes must not exceed 59 when hours and minutes are given");
        }
        this.totalMinutes = hours * 60L + minutes;
    }

    public long getHours() {
        return totalMinutes / 60;
    }

    public long getMinutes() {
        return totalMinutes % 60;
    }

    public long getTotalMinutes() {
        return totalMinutes;
    }

    public TimeSpan add(TimeSpan other) {
        return new TimeSpan((int) (totalMinutes + other.totalMinutes));
    }

    public TimeSpan subtract(TimeSpan other) {
        long result = totalMinutes - other.totalMinutes;
        if (result < 0) {
            throw new NegativeTimeSpanException("Time span became negative");
        }
        return new TimeSpan((int) result);
    }

    public TimeSpan scale(double factor) {
        if (factor < 0) {
            throw new IllegalScaleFactorException("Scale factor must not be negative");
        }
        return new TimeSpan((int) Math.round(totalMinutes * factor));
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", getHours(), getMinutes());
    }
}
