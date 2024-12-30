/**
 * Implementació bàsica de l'algoritme MiniMax per trobar
 * la millor jugada en el joc de Hex.
 */

package edu.upc.epsevg.prop.hex.algorithms;

import edu.upc.epsevg.prop.hex.HexGameStatus;
import edu.upc.epsevg.prop.hex.MoveNode;
import edu.upc.epsevg.prop.hex.PlayerType;

import java.awt.*;
import java.util.List;

public class Basic extends MiniMax
{
	
	/**
	 * Constructor de la classe Basic.
	 *
	 * @param maxDepth La profunditat màxima de cerca per a l'algoritme MiniMax.
	 */
    public Basic(int maxDepth)
    {
        super(maxDepth);
    }

	/**
	 * Troba la millor jugada utilitzant l'algoritme MiniMax.
	 *
	 * @param status L'estat actual del joc de Hex.
	 * @return El punt del tauler corresponent a la millor jugada calculada.
	 */
    @Override
    public Point findBestMove(HexGameStatus status)
    {
        exploratedNodes = 0;
        PlayerType player = status.getCurrentPlayer();
        List<MoveNode> moveList = status.getMoves();
        int bestScore = Integer.MIN_VALUE;
        Point move = moveList.getFirst().getPoint();

        for (MoveNode mn : moveList) {
            HexGameStatus newStatus = new HexGameStatus(status);
            newStatus.placeStone(mn.getPoint());

            if (newStatus.isGameOver()) {
                return mn.getPoint();
            }

            int score = getBestScore(newStatus, maxDepth-1, bestScore, Integer.MAX_VALUE, false, player);

            if (score > bestScore) {
                bestScore = score;
                move = mn.getPoint();
            }
        }

        return move;
    }

    @Override
    public void stop()
    {
        //NOTHING
    }

	/**
	 * Obté el nombre total de nodes explorats durant la cerca.
	 *
	 * @return El nombre de nodes explorats.
	 */
    @Override
    public long getExplorationDepth()
    {
        return exploratedNodes;
    }

	/**
	 * Obté la profunditat màxima configurada per a l'algoritme.
	 *
	 * @return La profunditat màxima de cerca.
	 */
    @Override
    public int getMaxDepth()
    {
        return maxDepth;
    }
}
