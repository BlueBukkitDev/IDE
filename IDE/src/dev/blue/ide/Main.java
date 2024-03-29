package dev.blue.ide;

public class Main {
	private static Engine engine;
	private static Window window;

	public static void main(String[] args) {
		window = new Window();
		engine = new Engine(window);
		engine.start();
	}
	
	public static void stop() {
		engine.stop();
		window.dispose();
		System.exit(0);
	}
}
