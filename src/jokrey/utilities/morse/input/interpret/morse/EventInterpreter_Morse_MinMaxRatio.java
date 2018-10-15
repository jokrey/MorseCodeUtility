package jokrey.utilities.morse.input.interpret.morse;

import java.util.ArrayList;

import jokrey.utilities.morse.BinarySignalUnit;
import jokrey.utilities.morse.SignalCharacterAlphabet;
import jokrey.utilities.morse.StandardMoseAlphabet;
import jokrey.utilities.morse.input.Event;
import jokrey.utilities.morse.input.interpret.EventInterpreter;

import static jokrey.utilities.morse.BinarySignalUnit.getRatioBetween;

public class EventInterpreter_Morse_MinMaxRatio extends EventInterpreter {
    //Theoretically adjustable Parameters
    public double shortLongRatioToTriggerLongSignal = 0.6;
    public double shortLongRatioToTriggerSpace = 3;

    public EventInterpreter_Morse_MinMaxRatio() {
        super(new StandardMoseAlphabet());
    }


    @Override public String getAnalysisResult() {
        Event[] events = getCapturedEvents();

        if(events.length<=2) {
            return "e";
        }
        StringBuilder toBuild = new StringBuilder();

        double[] shortest_longest_signal = Event.getShortestAndLongestSignal(events);

        ArrayList<BinarySignalUnit> currentLetter_morseUnits = new ArrayList<>();
        for (Event event : events) {
            double time_ratio = getRatioBetween(event.tookInSeconds, shortest_longest_signal[0], shortest_longest_signal[1]);

            if (event.isASignal()) {
                if (time_ratio < shortLongRatioToTriggerLongSignal) {
                    currentLetter_morseUnits.add(BinarySignalUnit.MORSE_SHORT_SIGNAL);
                } else {
                    currentLetter_morseUnits.add(BinarySignalUnit.MORSE_LONG_SIGNAL);
                }
            } else {
                if (time_ratio < shortLongRatioToTriggerLongSignal) {
                    //ignore
                } else {
                    char[] chars = alphabet.getCharacterForSignalSequence(currentLetter_morseUnits);
                    if (currentLetter_morseUnits.isEmpty()) {
                        //ignore
                    } else if (chars == null || chars.length == 0) {
                        toBuild.append("?");
                    } else {
                        toBuild.append(chars[0]);
                    }
                    currentLetter_morseUnits.clear();

                    if (time_ratio > shortLongRatioToTriggerSpace) {
                        toBuild.append(" ");
                    }
                }
            }
        }
        if(!currentLetter_morseUnits.isEmpty()) {
            char[] chars = alphabet.getCharacterForSignalSequence(currentLetter_morseUnits);
            toBuild.append(chars==null || chars.length==0 ? "?" : chars[0]);
        }

        return toBuild.toString();
    }
}