package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.input.interpret.EventInterpreterTest;
import org.junit.Test;

import java.util.Arrays;

public class EventInterpreter_MorseMinMaxRatioTest {
    @Test
    public void pure_test() {
        EventInterpreterTest.run_sure_universal_tests(new EventInterpreter_Morse_MinMaxRatio());

        //up to the interpretation of the interpreter - funny.
        EventInterpreterTest.pure_test(new EventInterpreter_Morse_MinMaxRatio(), Arrays.asList(1.0), "e");
        EventInterpreterTest.pure_test(new EventInterpreter_Morse_MinMaxRatio(), Arrays.asList(1.0, 1.0), "e");
    }

    @Test
    public void other() {
        EventInterpreterTest.active_input_short_test(new EventInterpreter_Morse_MinMaxRatio(), 10, 40);
        EventInterpreterTest.active_input_short_test(new EventInterpreter_Morse_MinMaxRatio(), 10, 400);
    }
}