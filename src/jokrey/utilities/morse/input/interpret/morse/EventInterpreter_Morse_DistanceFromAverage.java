package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.BinarySignalUnit;
import jokrey.utilities.morse.StandardMoseAlphabet;
import jokrey.utilities.morse.input.Event;
import jokrey.utilities.morse.input.interpret.EventInterpreter;

import java.util.ArrayList;

/**
 * TODO: Explain the idea
 * TODO: outlier detection
 */
public class EventInterpreter_Morse_DistanceFromAverage extends EventInterpreter {
    //Theoretically adjustable Parameters
    public double times_av_shortTOlong_threshold = 1.6;
    public double percent_of_av_longer_to_trigger_space = 8;

    public EventInterpreter_Morse_DistanceFromAverage() {
        super(new StandardMoseAlphabet());
    }


    @Override public String getAnalysisResult() {
        double av_signal_length = Event.getAvSignalLength(getCapturedEvents());

        StringBuilder toBuild = new StringBuilder();

        ArrayList<BinarySignalUnit> currentLetter_morseUnits = new ArrayList<>();
        for(int i=0;i<getCapturedEvents() .length;i++) {
            Event event = getCapturedEvents()[i];

            if(event.isASignal()) {
                if(event.tookInSeconds < av_signal_length*times_av_shortTOlong_threshold) {
                    currentLetter_morseUnits.add(BinarySignalUnit.MORSE_SHORT_SIGNAL);
                } else {
                    currentLetter_morseUnits.add(BinarySignalUnit.MORSE_LONG_SIGNAL);
                }
            } else {
                if(event.tookInSeconds < av_signal_length*times_av_shortTOlong_threshold || currentLetter_morseUnits.isEmpty()) {
                    //ignore
                } else {
                    char[] chars = alphabet.getCharacterForSignalSequence(currentLetter_morseUnits);
                    toBuild.append(chars==null || chars.length==0? "?" : chars[0]);
                    currentLetter_morseUnits.clear();

                    if(event.tookInSeconds > av_signal_length*percent_of_av_longer_to_trigger_space) {
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