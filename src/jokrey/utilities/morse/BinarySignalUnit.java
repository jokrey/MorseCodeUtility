package jokrey.utilities.morse;

/**
 * BinarySignalUnit.
 * A tuple of signal length and whether or not it actually is a signal or a pause.
 */
public class BinarySignalUnit {
    public static BinarySignalUnit MORSE_SHORT_SIGNAL = new BinarySignalUnit(1, true);
    public static BinarySignalUnit MORSE_LONG_SIGNAL = new BinarySignalUnit(3, true);

    public static BinarySignalUnit MORSE_SIGNAL_PAUSE = new BinarySignalUnit(1, false);
    public static BinarySignalUnit MORSE_LETTER_PAUSE = new BinarySignalUnit(3, false);
    public static BinarySignalUnit MORSE_WORD_PAUSE = new BinarySignalUnit(7, false);




    public final int length;
    public final boolean isSignal;
    private BinarySignalUnit(int length, boolean isSignal) {
        this.length=length;
        this.isSignal=isSignal;
    }
    public boolean isSignal() {
        return isSignal;
    }
    public boolean isPause() {
        return !isSignal;
    }

    @Override public String toString() {
        if(isSignal) {
            if(length==1) {
                return "•";
            } else if(length==3) {
                return "—";
            }
        } else {
            if(length==1) {
                return " ";
            } else if(length==3) {
                return "   ";
            } else if(length==7) {
                return "         ";
            }
        }
        return "UNRECOGNISED NON STANDARD SYMBOL";
    }

    public static String getAsString(BinarySignalUnit... units) {
        StringBuilder sb = new StringBuilder();

        for(BinarySignalUnit mu:units)
            sb.append(mu.toString());

        return sb.toString();
    }


    public static double getRatioBetween(double n, double min, double max) {
        return (n - min) / (max - min);
    }
}
