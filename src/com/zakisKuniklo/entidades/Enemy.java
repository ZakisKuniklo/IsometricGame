package com.zakisKuniklo.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.zakisKuniklo.world.Camera;
import com.zakisKuniklo.world.World;
import com.zakiskuniklo.main.Game;

public class Enemy extends Entity{
	
	private double speed = 1;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick() {
		if(Game.rand.nextInt(100)>30) {
			if(x<Game.player.getX() && World.isFree((int)(x+speed),this.getY())
					&& !isColliding((int)(x+speed),this.getY())) {
				x+=speed;
			}else if(x>Game.player.getX()&& World.isFree((int)(x-speed),this.getY())
					&& !isColliding((int)(x-speed),this.getY())) {
				x-=speed;
			}
			
			if(y<Game.player.getY() && World.isFree(this.getX(),(int)(y+speed))
					&& !isColliding(this.getX(),(int)(y+speed))) {
				y+=speed;
			}else if(y>Game.player.getY() && World.isFree(this.getX(),(int)(y-speed)) 
					&& !isColliding(this.getX(),(int)(y-speed))) {
				y-=speed;
			}
		}	
	}
	
	public boolean isColliding(int xnext, int ynext) {
		Rectangle currentEnemy = new Rectangle(xnext,ynext,World.tile_size,World.tile_size);
		for(int i =0;i<Game.enemies.size();i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this)
				continue;
			
			Rectangle targetEnemy = new Rectangle(e.getX(),e.getY(),World.tile_size,World.tile_size);
			if(currentEnemy.intersects(targetEnemy)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.blue);
		g.fillRect(this.getX()-Camera.x, this.getY() - Camera.y, 16, 16);
	}
}
