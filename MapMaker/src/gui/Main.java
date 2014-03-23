package gui;

import java.util.ArrayList;
import java.util.HashMap;

import parser.BlockFileParser;
import container.Block;
import container.Position;

public class Main {

	static HashMap<Position, Block> orderBlocks(ArrayList<Block> blocks) {
		HashMap<Position, Block> out = new HashMap<>();
		for (Block block : blocks) {
			out.put(new Position(block.xPos, block.zPos), block);
		}
		return out;
	}

	public static void main(String[] args) {
		ArrayList<Block> blocks = BlockFileParser.parse("../parsed_blocks");
		HashMap<Position, Block> orderedBlocks = orderBlocks(blocks);
	}

}
