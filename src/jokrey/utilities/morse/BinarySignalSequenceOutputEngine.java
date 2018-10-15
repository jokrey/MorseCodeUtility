package jokrey.utilities.morse;

import jokrey.utilities.animation.engine.TickEngine;
import jokrey.utilities.animation.util.AESize;

/**
 * BinarySignalSequenceOutputEngine(can technically step through any alphabet, but works with morse alphabet by default)
 *
 * Used to step through the signal's and pauses of a string given.
 *
 * Interacts pretty flawlessly with the Animation engine(i.e. making it easy to animate)
 *
 * Can also be used to step through a morse alphabet without animation or literally anything except this engine.
 */
public class BinarySignalSequenceOutputEngine extends TickEngine {
    private boolean flameOn = false;
    public boolean isSignalCurrentlyOn() {
        return flameOn;
    }
    private int ticks = 4;
    public void setTicksPerSecond(int ticks) {
        if(ticks>0)this.ticks = ticks;
    }

    private String text;
    private final SignalCharacterAlphabet alphabet;
    public BinarySignalSequenceOutputEngine(String text) {
        this(text, true);
    }
    public BinarySignalSequenceOutputEngine(String text, boolean alter_text_to_be_better) {
        this(new StandardMoseAlphabet(), text, alter_text_to_be_better);
    }
    public BinarySignalSequenceOutputEngine(SignalCharacterAlphabet alphabet, String text, boolean alter_text_to_be_better) {
        this.alphabet=alphabet;
        if(alter_text_to_be_better)
            setText(" "+text.trim()+" "); //add a space at beginning and end to ensure good pauses
        else
            setText(text);
    }


    @Override public AESize getVirtualBoundaries() {
        return new AESize(1, 1);
    }
    @Override public void initiate() {}
    @Override public void mouseClicked(int mouseCode) {
        reset();
    }

    public void setText(String text) {
        if(text==null) throw new NullPointerException();
        this.text=text;
        reset();
    }
    public void reset() {
        currentChar_index = 0;
        currentUnit = 0;
        currentUnit_currentTick = 0;
    }


    public int getTicksPerSecond() {return ticks;}

    private int currentChar_index = 0;
    private int currentUnit = 0;
    private int currentUnit_currentTick = 0;

    public boolean isCurrentlyPausing() {
        return currentChar_index%2!=0;
    }
    public char getCurrentlyDisplayedChar() {
        return text.charAt(currentChar_index/2);
    }

    private BinarySignalUnit[] currentChar_binarySignalUnits = null;
    private BinarySignalUnit currentBinarySignalUnit = null;
    private void updateUnits() {
        if(currentChar_index%2==0){
            char current_c = text.charAt(currentChar_index/2);
            if (current_c == ' ') {
                if(currentChar_index==0)
                    currentChar_binarySignalUnits = new BinarySignalUnit[]{BinarySignalUnit.MORSE_LETTER_PAUSE};
                else
                    currentChar_binarySignalUnits = new BinarySignalUnit[]{BinarySignalUnit.MORSE_WORD_PAUSE};
            } else {
                currentChar_binarySignalUnits = alphabet.getSignalSpaceSequenceFor(current_c);
            }
        } else {
            currentChar_binarySignalUnits = new BinarySignalUnit[]{BinarySignalUnit.MORSE_LETTER_PAUSE};
        }

        currentBinarySignalUnit = currentChar_binarySignalUnits[currentUnit];
    }
    @Override protected void calculateTick() {
        if(currentBinarySignalUnit ==null || currentChar_binarySignalUnits ==null)
            updateUnits();

        currentUnit_currentTick++;
        if(currentUnit_currentTick>= currentBinarySignalUnit.length) {
            currentUnit_currentTick=0;
            currentUnit++;
            if (currentUnit >= currentChar_binarySignalUnits.length) {
                currentUnit = 0;
                currentChar_index++;
                if(currentChar_index/2>=text.length())
                    currentChar_index=0;
            }
        }

        updateUnits();

        flameOn = currentBinarySignalUnit.isSignal();
    }
}





















































//OLD - VERY SMALL, no advanced systems
//package evade.game;
//
//		import neural_network.abstract_logic.UTIL;
//		import neural_network.abstract_logic.animation.engine.AnimationEngine;
//		import neural_network.abstract_logic.animation.engine.MovingAnimationObject;
//		import neural_network.abstract_logic.animation.pipeline.AnimationObject;
//		import neural_network.abstract_logic.animation.neural_network.abstract_logic.*;
//
//		import java.neural_network.abstract_logic.List;
//
//public class BinarySignalSequenceOutputEngine extends AnimationEngine {
////    @Override public Dimension getVirtualBoundaries() {return new Dimension(500, 500); }
////    @Override public double getInitialPixelsPerBox() {
////    	return 0.2;
////    }
//
//	@Override public AESize getVirtualBoundaries() {
//		return new AESize(1920, 1080);
//	}
//	@Override public void initiate() {
//		clearObjects();
//		AEColor randClr = AEColor.AEColor.getRandomColor();
//		initateObjectAt(0, new AnimationObject(null, AnimationObject.LINE,randClr));
//		initateObjectAt(1, new MovingAnimationObject(
//				getVirtualLimit_width()/2,
//				getVirtualLimit_height()/2,
//				0,0,0,0,55,55,
//				MovingAnimationObject.OVAL,randClr));
//	}
//	@Override public void mouseClicked(int mouseCode) {
//		gameBall.drawParam=AEColor.AEColor.getRandomColor();
//		lineToMouse.drawParam=gameBall.drawParam;
//	}
//
//	private AEPoint mouseP;
//	@Override public void locationInputChanged(AEPoint p) {
//		mouseP=p;
//	}
//
//	double lastSpawn = 0;
//	@Override protected void calculateTick() {
//		if(gameBall==null)initiate();
//
//		if(System.nanoTime()/1e9-lastSpawn>0.15) {
//			lastSpawn = System.nanoTime()/1e9;
//			if(UTIL.AE_UTIL.getRandomNr(0, 1) == 1) {
//				double diameter = UTIL.AE_UTIL.getRandomNr(22.0, 55.0);
//				initateNewObject(new MovingAnimationObject(UTIL.AE_UTIL.getRandomNr(0,getVirtualLimit_width()), -diameter+1, UTIL.AE_UTIL.getRandomNr(-11, 11), UTIL.AE_UTIL.getRandomNr(11, 44), UTIL.AE_UTIL.getRandomNr(-11, 11), UTIL.AE_UTIL.getRandomNr(0, 22),
//						diameter, diameter, (UTIL.AE_UTIL.getRandomNr(0, 1) == 1)?MovingAnimationObject.OVAL:MovingAnimationObject.RECT, AEColor.AEColor.getRandomColor()));
//			}
//		}
//
//		if(mouseP!=null) {
//			double[] a_s = AE_UTIL.angleVelocityToXYVelocity(AE_UTIL.getAngle(new AEPoint(gameBall.getMidAsPoint().x, gameBall.getMidAsPoint().y), new AEPoint(mouseP.x, mouseP.y)), 444);
//			((MovingAnimationObject) gameBall).setF_X(a_s[0]);((MovingAnimationObject) gameBall).setF_Y(a_s[1]);
//			((MovingAnimationObject) gameBall).move(getTicksPerSecond());
//			lineToMouse.setW(gameBall.getMid().x);
//			lineToMouse.setH(gameBall.getMid().y);
//			lineToMouse.setX(mouseP.x);
//			lineToMouse.setY(mouseP.y);
//		}
//
//		if((gameBall.overlapingBoundsLeft()||gameBall.overlapingBoundsRight(getVirtualLimit_width())||
//				gameBall.overlapingBoundsTop()||gameBall.overlapingBoundsBottom(getVirtualLimit_height()))) {
//			initateObjectAt(1, null);
//			return;
//		}
//		List<AnimationObject> obstacles = getObjectsInRange(2, getObjectsCount());
//		for(AnimationObject ao:obstacles) {
//			MovingAnimationObject obstacle = (MovingAnimationObject) ao;
//			obstacle.move(getTicksPerSecond());
//			if(AnimationObject.collision(gameBall, obstacle)) {
//				initateObjectAt(1, null);
//				return;
//			} else if (!new AERect(getVirtualBoundaries()).intersects(obstacle.getBounds()))
//				obstacles.remove(obstacle);
//		}
//	}
//
//}
