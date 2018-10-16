package jokrey.utilities.morse.output.example;

import jokrey.utilities.animation.implementations.swing.display.Swing_FullScreenStarter;
import jokrey.utilities.animation.implementations.swing.pipeline.AnimationDrawerSwing;
import jokrey.utilities.animation.sound.ToggleableSoundThread;
import jokrey.utilities.morse.BinarySignalSequenceOutputEngine;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

/**
 * Basic UI for audio and visual output testing. Also serves as an example
 */
public class MorseOutputExample {
	public static void main(String[] args) throws LineUnavailableException {
		String to_morse = JOptionPane.showInputDialog("Please enter text to be shown as visual morse code:");

		String[] options = {"Visual + Audio", "Visual"};
		int option = JOptionPane.showOptionDialog(
		        null,
                "Choose which kind of morse output you would like to have for your text:",
                "Choose output mode", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        ToggleableSoundThread soundThread = options[option].toLowerCase().contains("audio")?new ToggleableSoundThread(333):null;

		BinarySignalSequenceOutputEngine engine = new BinarySignalSequenceOutputEngine(to_morse) {
            @Override protected void calculateTick() {
                super.calculateTick();

                if (soundThread != null)
                    soundThread.paused=!isSignalCurrentlyOn();
            }
        };
		JFrame f = Swing_FullScreenStarter.start(engine, new VisualSignalOutputPipeline(new AnimationDrawerSwing()), false);
		f.setTitle("Example - don't judge ui design");
		f.setExtendedState( f.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		f.setUndecorated(false);
		f.setVisible(true);
	}
}
