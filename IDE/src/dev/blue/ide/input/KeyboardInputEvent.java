package dev.blue.ide.input;

import java.awt.event.KeyEvent;

import windowAPI.ui.UIObject;

public class KeyboardInputEvent extends InputEvent {
	private char keyChar;
	private int keyCode;
	private KeyEvent event;
	private EventType type;
	private UIObject focus;
	
	public KeyboardInputEvent(EventType type, KeyEvent e, UIObject focus) {
		if(type == EventType.TYPE) {
			this.keyChar = e.getKeyChar();
		}
		this.type = type;
		this.keyCode = e.getKeyCode();
		this.focus = focus;
		this.event = e;
	}

	@Override
	public void execute() {
		if(type == EventType.PRESS) {
			focus.onKeyPressed(event);
		}else if(type == EventType.RELEASE) {
			focus.onKeyReleased(event);
		}else if(type == EventType.TYPE) {
			focus.onType(event);
		}
	}
	
	public enum EventType {
		PRESS, RELEASE, TYPE
	}
}
