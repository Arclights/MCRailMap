package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import container.Block;

public class BlockFileParser {

	public static ArrayList<Block> parse(String fileName) {
		ArrayList<Block> out = new ArrayList<>();
		Scanner s;
		try {
			s = new Scanner(new File(fileName));
			while (s.hasNext()) {
				int xPos = s.nextInt();
				int zPos = s.nextInt();
				int data = s.nextInt();
				out.add(new Block(xPos, zPos, data));
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
}
