package jokrey.utilities.morse;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An entire alphabet of available characters to be encoded as a signal sequence
 * Has methods to encode a character or character sequence into a signal sequence.
 */
public class SignalCharacterAlphabet {
    private final SignalEncodedCharacter[] alphabet;
    public SignalCharacterAlphabet(SignalEncodedCharacter... alphabet) {
        this.alphabet = alphabet;
    }

    public boolean isInAlphabet(char c) {
        for(SignalEncodedCharacter c_mu:alphabet) {
            if(c_mu.encodes(c)) {
                return true;
            }
        }
        return false;
    }

    private BinarySignalUnit[] getRawSignalSequence(char c) {
        for(SignalEncodedCharacter c_mu:alphabet) {
            if(c_mu.encodes(c)) {
                return c_mu.getUnits();
            }
        }
        throw new IllegalArgumentException("Character \""+c+"\" could not be found in the known alphabet");
    }
    public BinarySignalUnit[] getSignalSpaceSequenceFor(char c) {
        BinarySignalUnit[] rawSignalSequence = getRawSignalSequence(c);
        BinarySignalUnit[] withAddedSpaces = new BinarySignalUnit[rawSignalSequence.length*2 - 1];
        for(int i=0;i<withAddedSpaces.length;i+=2) {
            withAddedSpaces[i]=rawSignalSequence[i/2];
            if(i+1<withAddedSpaces.length)
                withAddedSpaces[i+1] = BinarySignalUnit.MORSE_SIGNAL_PAUSE;
        }
        return withAddedSpaces;
    }

    public BinarySignalUnit[] getSignalSpaceSequenceFor(String text) {
        return getSignalSpaceSequenceFor(text, 0);
    }
    public BinarySignalUnit[] getSignalSpaceSequenceFor(String text, int spacesAtTheEnd) {
        ArrayList<BinarySignalUnit> units = new ArrayList<>();
        for(int i=0;i<text.length();i++) {
            if (text.charAt(i) == ' ') {
                if (i + 1 < text.length()) units.add(BinarySignalUnit.MORSE_WORD_PAUSE);
            } else {
                char toEncode = text.charAt(i);
                BinarySignalUnit[] signalSpaceSequence = getSignalSpaceSequenceFor(toEncode);
                units.addAll(Arrays.asList(signalSpaceSequence));

                if (i + 1 < text.length()) units.add(BinarySignalUnit.MORSE_LETTER_PAUSE);
            }
        }

        { //technically not required, but might make some calculations under some circumstances either faster or slower.... - just keep it, why not.. At least it saves a minimal amount of memory
            int word_spaces_to_add = spacesAtTheEnd / 7;
            for (int i = 0; i < word_spaces_to_add; i++)
                units.add(BinarySignalUnit.MORSE_WORD_PAUSE);
            spacesAtTheEnd %= 7;
            int letter_spaces_to_add = spacesAtTheEnd / 3;
            for (int i = 0; i < letter_spaces_to_add; i++)
                units.add(BinarySignalUnit.MORSE_LETTER_PAUSE);
            spacesAtTheEnd %= 3;
        }
        for(int i=0;i<spacesAtTheEnd;i++)
            units.add(BinarySignalUnit.MORSE_SIGNAL_PAUSE);

        return units.toArray(new BinarySignalUnit[0]);
    }



    public char[] getCharacterForSignalSequence(ArrayList<BinarySignalUnit> morseUnitArrayList) {
        return getCharacterForSignalSequence(morseUnitArrayList.toArray(new BinarySignalUnit[0]));
    }
    public char[] getCharacterForSignalSequence(BinarySignalUnit[] morseUnits) {
        for(SignalEncodedCharacter signalSequence:alphabet) {
            if(Arrays.equals(morseUnits, signalSequence.getUnits())) {
                return signalSequence.getChars();
            }
        }
        return null;
    }
}
