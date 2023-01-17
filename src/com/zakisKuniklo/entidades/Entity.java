package com.zakisKuniklo.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.zakisKuniklo.world.Camera;
import com.zakiskuniklo.main.Game;

public class Entity {
	
	public static BufferedImage LIFEPACK_EN = Game.spriteSheet.getSprite(8*16, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spriteSheet.getSprite(9*16, 0, 16, 16);
	public static BufferedImage AMMO_EN = Game.spriteSheet.getSprite(8*16, 16, 16, 16);
	public static BufferedImage ENEMY1_EN = Game.spriteSheet.getSprite(9*16, 16, 16, 16);
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	private BufferedImage sprite;
	
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}

	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, getX() - Camera.x, getY() - Camera.y, null);
	}
	
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	
	
	
}
