package algorithm;

import java.util.HashMap;

import container.Block;
import container.Graph;
import container.Node;
import container.Position;

/*-
 * 
 *              z-
 *              N
 *              |
 *  x- W ----   .   ---- E x+
 *              |
 *              S
 *              z+
 * 
 */

public class Algorithm {

	public static Graph extractGraph(HashMap<Position, Block> blocks) {
		Graph out = new Graph();

		while (!blocks.isEmpty()) {
			Position pos = (Position) blocks.keySet().toArray()[0];
			Block block = blocks.get(pos);
			boolean startNode = true;
			while (block != null) {
				Node n = new Node(block.direction, pos);
				if (startNode) {
//					out.addStartNode(n);
					startNode = false;
				}
				switch (block.direction) {
				case Directions.ASC_EAST:
					break;
				case Directions.ASC_NORTH:
					break;
				case Directions.ASC_SOUTH:
					break;
				case Directions.ASC_WEST:
					break;
				case Directions.EAST_T_NORTH:
					pos = new Position(n.pos.x, n.pos.z - 1);
					break;
				case Directions.EAST_T_SOUTH:
					break;
				case Directions.NORTH_SOUTH:
					break;
				case Directions.WEST_EAST:
					break;
				case Directions.WEST_T_NORTH:
					break;
				case Directions.WEST_T_SOUTH:
					break;
				default:
					break;
				}
				if (blocks.containsKey(pos)) {
					
				} else {
					block = null;
				}
			}
		}

		return out;
	}
}
