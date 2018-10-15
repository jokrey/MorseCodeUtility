package jokrey.utilities.morse;

/**
 * Implementation of SignalCharacterAlphabet for the standard morse alphabet (with basic punctuation).
 */
public class StandardMoseAlphabet extends SignalCharacterAlphabet {
    public StandardMoseAlphabet() {
        super(getStandardMorseAlphabet());
    }






    //http://www.itu.int/dms_pubrec/itu-r/rec/m/R-REC-M.1677-1-200910-I!!PDF-E.pdf
    //International Morsing Code Standard
    public static SignalEncodedCharacter[] getStandardMorseAlphabet() {
        return new SignalEncodedCharacter[]{
                new SignalEncodedCharacter(new char[]{'a', 'A'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'b', 'B'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'c', 'C'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'d', 'D'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'e', 'E'}, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'f', 'F'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'g', 'G'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'h', 'H'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'i', 'I'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'j', 'J'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'k', 'K'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'l', 'L'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'m', 'M'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'n', 'N'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'o', 'O'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'p', 'P'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'q', 'Q'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'r', 'R'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'s', 'S'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'t', 'T'}, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'u', 'U'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'v', 'V'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'w', 'W'}, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'x', 'X'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'y', 'Y'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(new char[]{'z', 'Z'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL)
                ,
                new SignalEncodedCharacter('1', BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter('2', BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter('3', BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter('4', BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter('5', BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter('6', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter('7', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter('8', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter('9', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter('0', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL)
                ,
                new SignalEncodedCharacter('.', BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(',', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter(':', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter('?', BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter('\'', BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(new char[]{'-'}, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter('/', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter('(', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter(')', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter('"', BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter('=', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL),
                new SignalEncodedCharacter('+', BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL),
                new SignalEncodedCharacter('*', BinarySignalUnit.MORSE_LONG_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_SHORT_SIGNAL, BinarySignalUnit.MORSE_LONG_SIGNAL)
        };
    }
}
