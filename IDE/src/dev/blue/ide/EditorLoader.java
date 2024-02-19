package dev.blue.ide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dev.blue.jcomp.Token;
import dev.blue.jcomp.exceptions.InvalidTokenTypeException;
import dev.blue.jcomp.exceptions.UnexpectedLexerStateException;
import dev.blue.jcomp.lexing.Lexer;
import windowAPI.ui.TextArea;
import windowAPI.ui.TextBit;
import windowAPI.ui.geometry.SP;
import windowAPI.ui.geometry.Transform;
import windowAPI.ui.gfx.Pattern;
import windowAPI.ui.gfx.Shape;

public class EditorLoader {
	private Lexer lexer;
	
	public EditorLoader(Graphics g) {
		lexer = new Lexer();
		Transform contentsT = new Transform(300, 10);//300 10
		Dimension d = new Dimension(1600, 1000);
		TextArea contents = new TextArea("contents", contentsT, d.width, d.height, 20, contentsPattern(contentsT, d.width, d.height)) {
			@Override
			public void run() {
				this.selected = true;
				Engine.getUIObjectRegistry().setFocusedObject(this);
			}
		};
		contents.setGraphics(g);
		for(String line:FileParser.readLines(new File("min.smth"))) {
			contents.addLine(parse(line));
		}
		//contents.addLine(new Keyword("pub set ", Keyword.Type.VIS), new Keyword("smth", Keyword.Type.FIELD), new Keyword(": ", Keyword.Type.OPER), new Keyword("\"Hello, World!\"", Keyword.Type.STRING));
		Engine.getUIObjectRegistry().registerObject(contents);
	}
	
	private Pattern contentsPattern(Transform t, int width, int height) {
		Pattern p = new Pattern();
		Color outlineC = new Color(180, 180, 0);
		Color fillC = new Color(30, 30, 32);
		Shape panel = new Shape(t, fillC, new SP(0, 0), new SP(height, 0), new SP(height, width), new SP(0, width));
		panel.setOutlineColor(outlineC);
		panel.setStyle(2);//0 for outline only, 1 for filled only, 2 for filled with outline of other color. 
		p.addShape(panel);
		return p;
	}
	
	private TextBit[] parse(String line) {
		List<TextBit> bits = new ArrayList<TextBit>();
		List<Token> tokens = new ArrayList<Token>();
		try {
			tokens = lexer.lex(line, 0);
		} catch (InvalidTokenTypeException | UnexpectedLexerStateException e) {
			e.printStackTrace();
		}
		for(Token each:tokens) {
			switch(each.getType()) {
			case PRIVACY:bits.add(new Keyword(each.getRaw()+" ", Keyword.Type.VISIBILITY));break;
			case ACCESS:bits.add(new Keyword(each.getRaw()+" ", Keyword.Type.VARIABILITY));break;
			case DEFINED:bits.add(new Keyword(each.getRaw(), Keyword.Type.FIELD));break;
			case DEFINER:bits.add(new Keyword(each.getRaw()+" ", Keyword.Type.DELIMITER));break;
			case DATA:bits.add(new Keyword(each.getRaw(), Keyword.Type.NUM));break;
			case BREAK:bits.add(new Keyword(each.getRaw(), Keyword.Type.DELIMITER));break;
			default:continue;
			}
		}
		TextBit[] bitArr = new TextBit[bits.size()];
		return bits.toArray(bitArr);
	}
}
