import mcrailmap.Parser
from PrintBlocks import print_blocks

def test_parsing():
	blocks = Parser.getRailBlocks('region/')
	print_blocks(blocks[4])
	# print blocks[4]



if __name__ == '__main__':
	test_parsing()