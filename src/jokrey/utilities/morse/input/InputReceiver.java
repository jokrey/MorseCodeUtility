package jokrey.utilities.morse.input;

/**
 * Very minimal superclass for all InputReceiver's
 */
public abstract class InputReceiver {
    public static double REAL_TIME_MAX_GAP = 14;
    public abstract Event[] getEvents();
}
