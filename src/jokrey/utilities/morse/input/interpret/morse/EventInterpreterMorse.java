package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.BinarySignalUnit;
import jokrey.utilities.morse.StandardMoseAlphabet;
import jokrey.utilities.morse.input.Event;
import jokrey.utilities.morse.input.interpret.EventInterpreter;

import java.util.ArrayList;

/**
 * Upper class algorithm for all the Morse code algorithms.
 */
public abstract class EventInterpreterMorse extends EventInterpreter {
    public EventInterpreterMorse() {
        super(new StandardMoseAlphabet());
    }


    public abstract Event[] getPreProcessedEvents();
    protected abstract void preCalculateRuntimeVariables(Event[] preprocessed_events);
    public abstract boolean isShortSignal(Event event);
    public abstract boolean isLongSignal(Event event);

//    public abstract boolean isSignalPause(Event event);
    public abstract boolean isLetterPause(Event event);
    public abstract boolean isWordPause(Event event);

    @Override public final String getAnalysisResult() {
        Event[] events = getPreProcessedEvents();
        preCalculateRuntimeVariables(events);


        StringBuilder toBuild = new StringBuilder();

        ArrayList<BinarySignalUnit> currentLetter_morseUnits = new ArrayList<>();
        for (Event event : events) {
            if (event.isASignal()) {
                if (isShortSignal(event)) {
                    currentLetter_morseUnits.add(BinarySignalUnit.MORSE_SHORT_SIGNAL);
                } else if(isLongSignal(event)) {
                    currentLetter_morseUnits.add(BinarySignalUnit.MORSE_LONG_SIGNAL);
                } //else System.out.println("whao else - "+getClass().getName());//else - if the algorithm does not decide mutually exclusive(like the 2Thresholds algorithm, then just ignore it)
            } else {
                if(isLetterPause(event) && !currentLetter_morseUnits.isEmpty()) {
                    char[] chars = alphabet.getCharacterForSignalSequence(currentLetter_morseUnits);
                    toBuild.append(chars == null || chars.length == 0 ? "?" : chars[0]);
                    currentLetter_morseUnits.clear();

                    //every word pause is already also a letter pause.
                    if (isWordPause(event)) {
                        toBuild.append(" ");
                    }
                }//else if(isSignalPause(event)) //else it is a signal pause and will be handled by ignoring it
            }
        }
        if(!currentLetter_morseUnits.isEmpty()) {
            char[] chars = alphabet.getCharacterForSignalSequence(currentLetter_morseUnits);
            toBuild.append(chars==null || chars.length==0 ? "?" : chars[0]);
        }
        return toBuild.toString();
    }



    public String getName() {
        String name = getClass().getName();
        int l = EventInterpreterMorse.class.getName().length();
        return name.length()>l? name.substring(l + 1):name;
    }
    public static EventInterpreterMorse[] getAll() {
        return new EventInterpreterMorse[] {
                new EventInterpreterMorse_1ThresholdEach(),
                new EventInterpreterMorse_2ThresholdsForSignal(),
                new EventInterpreterMorse_MinMaxRatio_SignalMinMaxForBoth(),
                new EventInterpreterMorse_MinMaxRatio_SignalMinMaxForBoth_Normalize(),   //performs well - calibrates fast
                new EventInterpreterMorse_MinMaxRatio_MinMaxFromEach(),
                new EventInterpreterMorse_MinMaxRatio_MinMaxFromEach_Normalize(),
                new EventInterpreterMorse_SignalAverageAsSignalAndPauseThreshold(),     //performs well - surprisingly stable
                new EventInterpreterMorse_SignalAverageAsSignalAndPauseThreshold_Normalize(),  //performs well, when there is a long signal outlier (terribly when there are short outliers)
                new EventInterpreterMorse_SignalAverageAsSignalThresholdAndPauseAverageAsPauseThreshold(),
                new EventInterpreterMorse_SignalAverageAsSignalThresholdAndPauseAverageAsPauseThreshold_Normalize(),  //performs well, when there are short outliers
        };
    }
    public static EventInterpreterMorse getNext(EventInterpreterMorse morse) {
        EventInterpreterMorse[] all = getAll();
        for(int i=0;i<all.length;i++) {
            if(all[i].getName().equals(morse.getName()))
                return all[(i+1)%all.length];
        }
        return all[0];
    }
}