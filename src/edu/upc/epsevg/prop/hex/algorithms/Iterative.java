package edu.upc.epsevg.prop.hex.algorithms;

import edu.upc.epsevg.prop.hex.HexGameStatus;
import edu.upc.epsevg.prop.hex.MoveNode;

import java.awt.*;
import java.util.List;

public class Iterative extends MiniMax
{
    private int currentDepth;

    public Iterative(int maxDepth)
    {
        super(maxDepth);
    }

    @Override
    public Point findBestMove(HexGameStatus status)
    {
        exploratedNodes = 0;

        List<MoveNode> moveList = status.getMoves();
        int bestScore = Integer.MIN_VALUE;
        Point move = moveList.getFirst().getPoint();
        Point trueMove = move;

        for (int depth = 1; depth <= maxDepth; depth++) {

            for (MoveNode mn : moveList) {
                if (stop) {break;}

                HexGameStatus newStatus = new HexGameStatus(status);
                newStatus.placeStone(mn.getPoint());

                if (newStatus.isGameOver()) {
                    return mn.getPoint();
                }

                int score = getBestScore(newStatus, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

                if (score > bestScore) {
                    bestScore = score;
                    move = mn.getPoint();
                }
            }

            if (!stop) {
                currentDepth = depth;
                trueMove = move;
            }
            else break;
        }

        stop = false;
        return trueMove;
    }

    @Override
    public void stop()
    {
        this.stop = true;
    }

    @Override
    public long getExplorationDepth()
    {
        return exploratedNodes;
    }

    @Override
    public int getMaxDepth()
    {
        return currentDepth;
    }
}
