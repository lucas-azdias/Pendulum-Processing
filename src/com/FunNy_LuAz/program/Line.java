package com.FunNy_LuAz.program;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line {

	// TODO Variables
	// Private
	private int xB = 0, yB = 0; // len, width, height;

	public Line() {
		// this.len = len;
		// width = xb + len / 2;
		// height = yB - yb;
	}

	public void update() {
		xB = Main.ball.getX();
		yB = Main.ball.getY();
	}

	public void render(Graphics g, Graphics2D g2) {
		// g2.fillRoundRect(xb - len / 2, yb - (int) (Main.HEIGHT * 0.2), width, height
		// + (int) (Main.HEIGHT * 0.2), 10, 10);
		g.drawLine(0, 0, xB, yB);
	}

}
