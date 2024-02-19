package dev.blue.ide;

import java.awt.Color;
import java.awt.Font;

import windowAPI.ui.TextBit;

public class Keyword extends TextBit {
	public Keyword(String s, Type type) {
		this.c = new Color(200, 200, 200);
		this.f = plain();
		switch(type) {
		case VISIBILITY:f = bold(); c = new Color(180, 60, 180); break;
		case VARIABILITY:f = italibold(); c = new Color(200, 200, 200); break;
		case FIELD:f = plain(); c = new Color(200, 200, 0); break;
		case STRING:f = plain(); c = new Color(0, 200, 100); break;
		case OPERATOR:f = bold(); c = new Color(220, 220, 220); break;
		case DELIMITER:f = plain(); c = new Color(250, 250, 250); break;
		case NUM:f = plain(); c = new Color(250, 190, 0); break;
		default:break;
		}
		this.s = s;
		this.hover = new Runnable() {
			@Override
			public void run() {
				//Show tooltip popup. 
			}
		};
		this.click = new Runnable() {
			@Override
			public void run() {
				//Highlight word, if right click open options. 
			}
		};
		this.isContainer = false;
	}
	
	public enum Type {
		COMMENT, VISIBILITY, VARIABILITY, OPERATOR, BRACKET, STRUCT, FUNC, NUM, STRING, FIELD, DELIMITER
	}
	
	private Font plain() {
		return new Font("Courier New", Font.PLAIN, 16);
	}
	
	private Font bold() {
		return new Font("Courier New", Font.BOLD, 16);
	}
	
	private Font italibold() {
		return new Font("Courier New", Font.BOLD | Font.ITALIC, 16);
	}
}
