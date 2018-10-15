package jokrey.utilities.morse.input.example;

import jokrey.utilities.morse.input.ActiveInputsOverTimeInputReceiver;
import jokrey.utilities.morse.input.interpret.EventInterpreter;
import jokrey.utilities.morse.input.interpret.morse.EventInterpreter_Morse_DistanceFromAverage;
import jokrey.utilities.morse.input.interpret.morse.EventInterpreter_Morse_MinMaxRatio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Click Input Morse Interpreter UI Example
 */
public class ClickInputMorseInterpreterUIExample {
    static EventInterpreter interpreter = new EventInterpreter_Morse_MinMaxRatio(); //this may look weird, but for an example pretty much nothing matters
    public static void main(String[] args) {
        ActiveInputsOverTimeInputReceiver receiver = new ActiveInputsOverTimeInputReceiver();

        final JFrame f = new JFrame();
        JTextArea detectedArea = new JTextArea();
        f.add(new JScrollPane(detectedArea));
        detectedArea.setEditable(false);
        detectedArea.setFont(new Font("Monospaced", Font.BOLD, 18));
        detectedArea.setLineWrap(true);
        detectedArea.setWrapStyleWord(true);
        JLabel huge_header_label = new JLabel("please enter text by clicking morse code into the area below");
        huge_header_label.setFont(new Font("Arial", Font.BOLD, 25));
        f.add(huge_header_label, BorderLayout.NORTH);
        JLabel small_footer_label = new JLabel(
                "use a right click to toggle the interpretation algorithm(both have their strengths so don't judge too early) | " +
                "please note that pressing for very long may confuse the algorithm. So please do not do that. | " +
                "Writing incorrect or just almost correct morse code will also be interpreted incorrectly or almost correctly.");
        small_footer_label.setFont(new Font("Arial", Font.BOLD, 12));
        f.add(small_footer_label, BorderLayout.SOUTH);
        f.setSize(777, 333);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        detectedArea.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) {
                    receiver.addInput_active();

                    interpreter.setEventsFrom(receiver);
                    detectedArea.setText(interpreter.getAnalysisResult());
                }
            }
            @Override public void mouseReleased(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) {
                    receiver.addInput_inactive();

                    interpreter.setEventsFrom(receiver);
                    detectedArea.setText(interpreter.getAnalysisResult());
                } else if(SwingUtilities.isRightMouseButton(e)) {
                    if(interpreter instanceof EventInterpreter_Morse_MinMaxRatio) {
                        interpreter = new EventInterpreter_Morse_DistanceFromAverage();
                        huge_header_label.setText("Using \"Distance From Average\" interpretation algorithm");
                    } else {
                        interpreter = new EventInterpreter_Morse_MinMaxRatio();
                        huge_header_label.setText("Using \"Min Max Ratio\" interpretation algorithm");
                    }
                } else {
                    receiver.clear();
                }
            }
        });

        new Thread(() -> {
            while(f.isVisible()) {
                interpreter.setEventsFrom(receiver);
                detectedArea.setText(interpreter.getAnalysisResult());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
