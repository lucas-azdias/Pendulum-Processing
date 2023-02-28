package com.FunNy_LuAz.program;

//TODO Import
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Main extends Canvas implements KeyListener, Runnable {

	private static final long serialVersionUID = 1L;

	// TODO Variables
	// Public
	public boolean isRunning;
	public static JFrame frame;
	public final static int WIDTH = 960, HEIGHT = 720, FSCALE = 1; // 360x240x2
	public final static int FWIDTH = WIDTH * FSCALE, FHEIGHT = HEIGHT * FSCALE;
	public final static double aGrav = 9.80665;
	public final static Color colorBg = new Color(200, 200, 200);
	public static Ball ball;
	public static Line line;
	// Private
	private Thread thread;
	private BufferedImage layer;

	private Main() {
		initFrame();
		initObj();
		addKeyListener(this);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop(String error) {
		isRunning = false;
		System.err.println(error);
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void initFrame() {
		setPreferredSize(new Dimension(FWIDTH, FHEIGHT));
		setBackground(colorBg);
		frame = new JFrame("Game");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void initObj() {
		layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		ball = new Ball(50, 10, 0);
		line = new Line();
	}

	public void update() {
		ball.update();
		line.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		renderScreen(g, g2);
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, FWIDTH, FHEIGHT, null);
		bs.show();
	}

	private void renderScreen(Graphics g, Graphics2D g2) {
		background(g, g2);
		program(g, g2);
	}

	private void background(Graphics g, Graphics2D g2) {
		g.setColor(colorBg);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	private void program(Graphics g, Graphics2D g2) {
		g.translate(Main.WIDTH / 2, 0);
		g.setColor(Color.BLACK);
		ball.render(g, g2);
		line.render(g, g2);
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountTicks = 60.0;
		double ns = 1000000000 / amountTicks;
		double delta = 0;
		int fps = 0;
		double timer = System.currentTimeMillis();

		requestFocus();

		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				fps++;
				delta--;

				if (System.currentTimeMillis() - timer >= 1000) {
					System.out.println("FPS: " + fps);
					fps = 0;
					timer = System.currentTimeMillis();
				}
			}

		}

		stop("");
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

		}
	}

	public void keyReleased(KeyEvent e) {

	}

}
