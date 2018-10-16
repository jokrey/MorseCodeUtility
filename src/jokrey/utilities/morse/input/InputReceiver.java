package jokrey.utilities.morse.input;

/**
 * Very minimal superclass for all InputReceiver's
 */
public abstract class InputReceiver {
    public abstract Event[] getEvents();
}
