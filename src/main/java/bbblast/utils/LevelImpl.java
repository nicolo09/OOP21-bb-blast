package bbblast.utils;

import java.io.Serializable;

import bbblast.model.BubbleGenerator;
import bbblast.model.BubblesGrid;
import bbblast.model.BubblesGridImpl;
import bbblast.model.GridInfo;

/**
 * 
 * Implementation of {@link Level}
 *
 */
public class LevelImpl implements Level, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -868557846923024533L;
	private static final int INIT_SCORE = 0;
	private int score;
	private final GridInfo infos;
	private final BubblesGrid gameGrid;
	private final BubbleGenerator generator;

	public LevelImpl(final GridInfo infos, final BubbleGenerator generator) {
		this.score = INIT_SCORE;
		this.infos = infos;
		this.gameGrid = new BubblesGridImpl(infos);
		this.generator = generator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BubblesGrid getGameBubblesGrid() {
		return this.gameGrid;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GridInfo getGameGridInfo() {
		return this.infos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCurrentScore() {
		return this.score;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateScore(final int points) {
		this.score += points;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillGameBubblesGrid(final int rows) {
		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < this.infos.getBubbleWidth(); j++) {
				this.gameGrid.addBubble(generator.generate(new PositionImpl(
						j * 2 * infos.getBubbleRadius() + infos.getBubbleRadius(), i * infos.getBubbleRadius())));
			}
		}

	}

}
