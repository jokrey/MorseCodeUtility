package jokrey.utilities.morse.input.example;

import jokrey.utilities.morse.input.ActiveInputsOverTimeInputReceiver;
import jokrey.utilities.morse.input.Event;
import jokrey.utilities.morse.input.interpret.morse.EventInterpreterMorse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Click Input Morse Interpreter UI Example
 *
 * Also a test for the different morse interpretation algorithms.
 *    With end user level noise.
 */
public class ClickInputMorseInterpreterUIExample {
    static EventInterpreterMorse interpreter = EventInterpreterMorse.getAll()[3]; //this may look weird, but for an example pretty much nothing matters
    public static void main(String[] args) {
        ActiveInputsOverTimeInputReceiver receiver = new ActiveInputsOverTimeInputReceiver();

        final JFrame f = new JFrame("Example - don't judge ui design");
        JTextArea detectedArea = new JTextArea() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Event[] events = interpreter.getPreProcessedEvents();
                double total_length = Event.getTotalLength(events);

                int width = getWidth();
                double elapsed_pixels = 0;
                for (Event event : events) {
                    g.setColor(event.isASignal()? new Color(200, 10, 10, 100) : new Color(66, 66, 66, 100));
                    double size = (event.tookInSeconds/total_length) * width;
                    g.fillRect((int) elapsed_pixels, 0, (int) Math.ceil(size), getHeight());
                    elapsed_pixels+=size;
                }
            }
        };
        f.add(new JScrollPane(detectedArea));
        detectedArea.setEditable(false);
        detectedArea.setFont(new Font("Monospaced", Font.BOLD, 18));
        detectedArea.setLineWrap(true);
        detectedArea.setWrapStyleWord(true);
        JLabel huge_header_label = new JLabel("please enter text by clicking morse code into the area below");
        huge_header_label.setFont(new Font("Arial", Font.BOLD, 25));
        f.add(huge_header_label, BorderLayout.NORTH);
        JTextArea small_display_footer_area = new JTextArea(
                "use a right click to toggle the interpretation algorithm(both have their strengths so don't judge too early)\n" +
                "Some algorithms may require a couple of short and a couple of long presses to be calibrated on what you think is short or long\n" +
                "please note that pressing for very long may confuse some algorithms. So please do not do that.\n" +
                "Writing incorrect or just almost correct morse code will also be interpreted incorrectly or almost correctly.");
        small_display_footer_area.setFont(new Font("Arial", Font.BOLD, 12));
        small_display_footer_area.setEnabled(false);
        f.add(small_display_footer_area, BorderLayout.SOUTH);
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
                    interpreter = EventInterpreterMorse.getNext(interpreter);
                    huge_header_label.setText("Using \""+interpreter.getName()+"\" interpretation algorithm");
                } else {
                    receiver.clear();
                }
            }
        });

        new Thread(() -> {
            while(f.isVisible()) {
                interpreter.setEventsFrom(receiver);
                detectedArea.setText(interpreter.getAnalysisResult());
                detectedArea.repaint();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
