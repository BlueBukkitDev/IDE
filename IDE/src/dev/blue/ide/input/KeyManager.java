package dev.blue.ide.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dev.blue.ide.Engine;

public class KeyManager implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Engine.getEventManager().registerEvent(new KeyboardInputEvent(KeyboardInputEvent.EventType.PRESS, e, Engine.getUIObjectRegistry().getFocusedObject()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
