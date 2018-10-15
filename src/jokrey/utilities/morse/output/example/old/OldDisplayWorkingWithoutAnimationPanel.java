package jokrey.utilities.morse.output.example.old;

import jokrey.utilities.morse.BinarySignalUnit;
import jokrey.utilities.morse.SignalCharacterAlphabet;
import jokrey.utilities.morse.StandardMoseAlphabet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Works without AnimationEngine, because one's essential components have been copy pasted into this class below
 */
public class OldDisplayWorkingWithoutAnimationPanel extends JPanel {
	public static void main(String[] args) {
	    String to_morse = JOptionPane.showInputDialog("Please enter text to be shown as visual morse code.");
		final OldDisplayWorkingWithoutAnimationPanel s = new OldDisplayWorkingWithoutAnimationPanel(to_morse);
		
		final JFrame f = new JFrame();
		f.add(s);
        f.setExtendedState( f.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		s.addMouseListener(new MouseAdapter() {
			@Override public void mousePressed(MouseEvent arg0) {
				if(SwingUtilities.isMiddleMouseButton(arg0)) {
					f.dispose();
					System.exit(0);
				} else {
					s.currentChar_index = 0;
					s.currentUnit = 0;
					s.currentUnit_currentTick = 0;
				}
			}
		});
		
		f.setVisible(true);
	}
	public OldDisplayWorkingWithoutAnimationPanel(String toTransmit) {
		this.toTransmit=" "+toTransmit+"  ";//+"  " should equal 7*2=14 wait states
		startThread();
	}
	
	@Override protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(signalOn?Color.white:Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());

        String character = currentChar_index%2==0?Character.toString(toTransmit.charAt(currentChar_index/2)):" ";
        if(!character.isEmpty() && !character.equals(" ")) {
    		g.setColor(Color.red);
    		g.setFont(new Font("Arial", Font.BOLD, 50));
        	g.drawString(character, getWidth()/2, getHeight()/2);
        }
	}
	
	boolean signalOn = false;

    public final String toTransmit;
    private int currentChar_index = 0; public int getCurrentCharIndex() {return currentChar_index;}
    private int currentUnit = 0;
    private int currentUnit_currentTick = 0;
    private SignalCharacterAlphabet morseCodingUnit = new StandardMoseAlphabet();

    public double getTicksPerSecond() {return 4;}
    private BinarySignalUnit[] currentChar_binarySignalUnits = null;
    private BinarySignalUnit currentBinarySignalUnit = null;
    private void updateUnits() {
        if(currentChar_index%2==0){
            char current_c = toTransmit.charAt(currentChar_index/2);
            if (current_c == ' ') {
                if(currentChar_index==0)
                    currentChar_binarySignalUnits = new BinarySignalUnit[]{BinarySignalUnit.MORSE_LETTER_PAUSE};
                else
                    currentChar_binarySignalUnits = new BinarySignalUnit[]{BinarySignalUnit.MORSE_WORD_PAUSE};
            } else {
                currentChar_binarySignalUnits = morseCodingUnit.getSignalSpaceSequenceFor(current_c);
            }
        } else {
            currentChar_binarySignalUnits = new BinarySignalUnit[]{BinarySignalUnit.MORSE_LETTER_PAUSE};
        }

        currentBinarySignalUnit = currentChar_binarySignalUnits[currentUnit];
    }
    public void calculateTick() {
        if(currentBinarySignalUnit ==null || currentChar_binarySignalUnits ==null)
            updateUnits();

        currentUnit_currentTick++;
        if(currentUnit_currentTick>= currentBinarySignalUnit.length) {
            currentUnit_currentTick=0;
            currentUnit++;
            if (currentUnit >= currentChar_binarySignalUnits.length) {
                currentUnit = 0;
                currentChar_index++;
                if(currentChar_index/2>=toTransmit.length())
                    currentChar_index=0;
            }
        }

        updateUnits();

        repaint();
        signalOn = currentBinarySignalUnit.isSignal();
    }
    
    
    
    
    
    
    
    
    
    
    void startThread() {
        Thread loop_thread = new Thread(() -> {
            while(true) {
                try {
                    if(calculate()) {
                        repaint();
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        loop_thread.start();
    }
    

    private long last = System.nanoTime();
    public final boolean calculate() {
       /* if(isPaused()) {
            last = System.nanoTime();
            return false;
        }*/
        long newLast = System.nanoTime();
        if(calculate((newLast - last)/1e9)) {
            last = newLast;
            return true;
        }
        return false;
    }
    private boolean calculate(double delta) {
        double tickEvery = 1.0/getTicksPerSecond();
        if(delta > tickEvery) {
            while(delta > tickEvery) {
                calculateTick();
                delta-=tickEvery;
            }
            return true;
        }
        return false;
    }
}