package jokrey.utilities.morse.input;

/**
 * The Input expression of a BinarySignalUnit.
 * A Sequence of Events will be interpreted by the EventInterpreter and converted into a String.
 */
public class Event {
    public final double tookInSeconds;
    private final boolean isASignal;

    public boolean isASignal(){return isASignal;}
    public boolean isAPause(){return !isASignal;}

    public Event(double tookInSeconds, boolean isASignal) {
        this.tookInSeconds=tookInSeconds;
        this.isASignal=isASignal;
    }

    @Override public String toString() {
        return "[Event: "+(isASignal?"signal":"pause")+" for "+tookInSeconds+"]";
    }

    @Override public boolean equals(Object obj) {
        if(obj instanceof Event) {
            Event o = (Event) obj;
            return tookInSeconds==o.tookInSeconds&& isASignal==o.isASignal;
        }
        return false;
    }















//Helper for Event arrays
    public static double getTotalLength(Event... events) {
        double total = 0;
        for(Event event:events)
            total+=event.tookInSeconds;
        return total;
    }
    public static double[] getShortestAndLongestSignal(Event... events) {
        double shortest_signal_length = Integer.MAX_VALUE;
        double longest_signal_length = Integer.MIN_VALUE;
        for (Event event : events) {
            if (event.isASignal()) {
                if (shortest_signal_length > event.tookInSeconds)
                    shortest_signal_length = event.tookInSeconds;
                if (longest_signal_length < event.tookInSeconds)
                    longest_signal_length = event.tookInSeconds;
            }
        }
        return new double[] {shortest_signal_length, longest_signal_length};
    }
    public static double getAverageSignalLength(Event... events) {
        double total_signal_length = 0;
        for (Event event : events) {
            if (event.isASignal()) {
                total_signal_length += event.tookInSeconds;
            }
        }
        return total_signal_length/events.length;
    }
    public static double[] getShortestAndLongestPause(Event... events) {
        double shortest_signal_length = Integer.MAX_VALUE;
        double longest_signal_length = Integer.MIN_VALUE;
        for (Event event : events) {
            if (event.isAPause()) {
                if (shortest_signal_length > event.tookInSeconds)
                    shortest_signal_length = event.tookInSeconds;
                if (longest_signal_length < event.tookInSeconds)
                    longest_signal_length = event.tookInSeconds;
            }
        }
        return new double[] {shortest_signal_length, longest_signal_length};
    }
    public static double getAveragePauseLength(Event... events) {
        double total_signal_length = 0;
        for (Event event : events) {
            if (event.isAPause()) {
                total_signal_length += event.tookInSeconds;
            }
        }
        return total_signal_length/events.length;
    }
    public static double[] getShortestAndLongest(Event... events) {
        double shortest_signal_length = Integer.MAX_VALUE;
        double longest_signal_length = Integer.MIN_VALUE;
        for (Event event : events) {
            if (shortest_signal_length > event.tookInSeconds)
                shortest_signal_length = event.tookInSeconds;
            if (longest_signal_length < event.tookInSeconds)
                longest_signal_length = event.tookInSeconds;
        }
        return new double[] {shortest_signal_length, longest_signal_length};
    }
    public static double getAverageLength(Event... events) {
        double total_signal_length = 0;
        for (Event event : events) {
                total_signal_length += event.tookInSeconds;
        }
        return total_signal_length/events.length;
    }
}