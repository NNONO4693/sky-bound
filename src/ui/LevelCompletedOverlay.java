package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class LevelCompletedOverlay {
	private Playing playing;
	private BufferedImage img;
	private MenuButton info;
	private int imgX, imgY, imgW, imgH;

	public LevelCompletedOverlay(Playing playing) {
		this.playing = playing;
		createImg();
		createButtons();
	}

	private void createButtons() {
		info = new MenuButton(Game.GAME_WIDTH / 2, (int) (200 * Game.SCALE), 3, Gamestate.INFO);
	}

	private void createImg() {
		img = LoadSave.GetSpriteAtlas(LoadSave.info_background);
		imgW = (int) (img.getWidth() * Game.SCALE);
		imgH = (int) (img.getHeight() * Game.SCALE);
		imgX = Game.GAME_WIDTH / 2 - imgW / 2;
		imgY = (int) (100 * Game.SCALE);

	}

	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

		g.drawImage(img, imgX, imgY, imgW, imgH, null);

		info.draw(g);
	}

	public void update() {
		info.update();
	}

	private boolean isIn(MenuButton b, MouseEvent e) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		info.setMouseOver(false);

		 if (isIn(info, e))
			info.setMouseOver(true);
	}

	public void mouseReleased(MouseEvent e) {
		 if (isIn(info, e))
			if (info.isMousePressed()) {
				playing.resetAll();
				playing.resetGameCompleted();
				playing.loadNextLevel();
				playing.setGamestate(Gamestate.INFO);

			}

		
		info.resetBools();
	}

	public void mousePressed(MouseEvent e) {
		 if (isIn(info, e))
			info.setMousePressed(true);
	}
}
