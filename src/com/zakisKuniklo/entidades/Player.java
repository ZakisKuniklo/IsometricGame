package com.zakisKuniklo.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.zakisKuniklo.world.Camera;
import com.zakisKuniklo.world.World;
import com.zakiskuniklo.main.Game;

public class Player extends Entity{
	
	public boolean right, left, up, down;
	public int speed = 1;
	public int dir = 1;
	
	private int frames = 0, maxFrames = 5, index =1, maxIndex=4;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[6];
		leftPlayer = new BufferedImage[6];
		
		for(int i =0; i<6;i++) {
			rightPlayer[i] = Game.spriteSheet.getSprite(32 + (i*16), 0, 16, 16);
		}
		
		for(int i =0; i<6;i++) {
			leftPlayer[i] = Game.spriteSheet.getSprite(32 + (i*16), 16, 16, 16);
		}
	}
	
	public void tick() {
		moved = false;
		if(right && World.isFree(x+speed,y)) {
			moved = true;
			this.setX(this.getX()+speed);
			dir = 1;
			//System.out.println("direita");
		}else if(left && World.isFree(x-speed,y)) {
			moved = true;
			this.setX(this.getX()-speed);
			dir = 2;
			//System.out.println("esquerda");
		}
		if(up && World.isFree(x,y-speed)) {
			moved = true;
			dir = 3;
			this.setY(this.getY()-speed);
			//System.out.println("cima");
		}else if(down && World.isFree(x,y+speed)) {
			moved = true;
			dir = 4;
			this.setY(this.getY()+speed);
			//System.out.println("baixo");
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames =1;
				index++;
				if(index >maxIndex) {
					index =1;
				}
			}
		}
		
		Camera.x =  Camera.clamp(this.getX() - (Game.width/2),0, World.width * 16 - Game.width);
		Camera.y =  Camera.clamp(this.getY() - (Game.height/2),0,World.height * 16 - Game.height);
		
		
	}
	
	public void render(Graphics g) {
		if(right || dir == 1) {
			if(up) {
				g.drawImage(rightPlayer[5],this.getX() - Camera.x,this.getY() - Camera.y,null);
			} else if(down) {
				g.drawImage(rightPlayer[0],this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else{
				g.drawImage(rightPlayer[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
			}
		} else if(left || dir == 2) {
			if(up) {
				g.drawImage(leftPlayer[0],this.getX() - Camera.x,this.getY() - Camera.y,null);
			} else if(down) {
				g.drawImage(leftPlayer[5],this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else {
				g.drawImage(leftPlayer[(maxIndex-(index-1))],this.getX() - Camera.x,this.getY() - Camera.y,null);
			}
		} 
		
		if(up|| dir == 3) {
			g.drawImage(leftPlayer[0],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(down || dir == 4) {
			g.drawImage(rightPlayer[0],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}	
	}
}
