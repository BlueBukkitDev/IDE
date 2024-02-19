package dev.blue.ide;

import java.util.ArrayList;
import java.util.List;

import dev.blue.ide.input.InputEvent;

public class EventManager {
	private List<InputEvent> events;
	
	public EventManager() {
		events = new ArrayList<InputEvent>();
	}
	
	public void executeEvents() {
		for(InputEvent each:events) {
			each.execute();
		}
		events.clear();
	}
	
	public void registerEvent(InputEvent event) {
		events.add(event);
	}

	public int getEventCt() {
		return events.size();
	}
}
