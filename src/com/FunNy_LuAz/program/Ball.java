package com.FunNy_LuAz.program;

//TODO Import
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ball {

	// TODO Variables
	// Private
	private int x = 0, y = 0, r;
	private double m, ang;

	public Ball(int r, double m, double ang) {
		this.r = r;
		this.m = m;
		this.ang = ang;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void update() {
		x = (int) (r * Math.sin(ang));
		y = (int) (r * Math.cos(ang));
	}

	public void render(Graphics g, Graphics2D g2) {
		g.fillOval(x - r / 2, y - r / 2, r, r);
	}

}
