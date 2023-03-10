package com.zakisKuniklo.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.zakisKuniklo.entidades.*;
import com.zakiskuniklo.main.Game;

public class World {

	public static Tile[] tiles;
	public static int width,height;
	public static final int tile_size = 16;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth()*map.getHeight()];
			width = map.getWidth();
			height = map.getHeight();
			tiles = new Tile[map.getWidth()*map.getHeight()];
			map.getRGB(	0, 0, map.getHeight(), map.getWidth(), pixels, 0, map.getWidth());
			for(int xx=0;xx< map.getWidth();xx++ ) {
				for(int yy = 0; yy < map.getHeight();yy++) {
					int pixela = pixels[xx+(yy*map.getWidth())];
					tiles[xx+(yy*width)] = new FloorTile(Tile.Tile_Floor,xx*16,yy*16);
					
					if( pixela == 0xff000000) {
						//floor
						tiles[xx+(yy*width)] = new FloorTile(Tile.Tile_Floor,xx*16,yy*16);
					}else if(pixela == 0xffffffff) {
						//wall
						tiles[xx+(yy*width)] = new WallTile(Tile.Tile_Wall,xx*16,yy*16);
					}else if(pixela == 0xff101edb) {
						//player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}else if(pixela == 0xffdb1038) {
						//Enemy
						Enemy en = new Enemy(xx*16,yy*16,16,16,Entity.ENEMY1_EN);
						Game.entities.add(en);
						Game.enemies.add(en);
					}else if(pixela == 0xff3f2a0b) {
						//weapon
						Game.entities.add(new Weapon(xx*16,yy*16,16,16,Entity.WEAPON_EN));
					}else if(pixela == 0xffdbce10) {
						//ammo
						Game.entities.add(new Ammo(xx*16,yy*16,16,16,Entity.AMMO_EN));
					}else if(pixela == 0xff10db23) {
						//health pack
						Game.entities.add(new LifePack(xx*16,yy*16,16,16,Entity.LIFEPACK_EN));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext, int ynext){
		int x1 = xnext / tile_size;
		int y1 = ynext / tile_size;
		
		int x2 = (xnext + tile_size -1) / tile_size;
		int y2 = ynext / tile_size;
		
		int x3 = xnext / tile_size;
		int y3 = (ynext + tile_size -1) / tile_size;
		
		int x4 = (xnext + tile_size -1) / tile_size;
		int y4 = (ynext + tile_size -1) / tile_size;
		
		return !(tiles[x1 + (y1*World.width)] instanceof WallTile ||
				 tiles[x2 + (y2*World.width)] instanceof WallTile ||
				 tiles[x3 + (y3*World.width)] instanceof WallTile ||
				 tiles[x4 + (y4*World.width)] instanceof WallTile);
	}
	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart+ (Game.width >> 4);
		int yfinal = ystart+ (Game.height >> 4);
		
		for(int xx = xstart; xx <= xfinal;xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= width || yy >= height) {
					continue;
				}
				Tile tile = tiles[xx + (yy*width)];
				tile.render(g);
			}
		}
	}
}
