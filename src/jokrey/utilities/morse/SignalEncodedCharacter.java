package jokrey.utilities.morse;

/**
 * Conceptually just a simple tuple of Character and the Signal Sequence that encodes it.
 * Here a char array is used to allow multiple Characters to optionally be encoded by the same Signal Sequence.
 * Typically that will likely be lower and upper case letters.
 *
 * SHOULD BE IMMUTABLE
 */
public class SignalEncodedCharacter {
    private final char[] chars;
    private final BinarySignalUnit[] units;
    public boolean encodes(char c) {
        for(char en:chars)
            if(c==en)
                return true;
        return false;
    }
    public BinarySignalUnit[] getUnits() {
        return units.clone();
    }


    public SignalEncodedCharacter(char c, BinarySignalUnit... units) {
        this(new char[] {c}, units);
    }
    public SignalEncodedCharacter(char[] chars, BinarySignalUnit... units) {
        if(chars.length==0)throw new IllegalArgumentException("Must have Characters");
        if(units.length==0)throw new IllegalArgumentException("Must have Units");

        this.chars=chars;
        this.units = units;
    }

    public char[] getChars() {
        return chars.clone();
    }
}