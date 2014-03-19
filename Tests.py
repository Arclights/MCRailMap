import Parser
from PrintBlocks import print_blocks

def test_parsing():
	blocks = Parser.get_rail_blocks('region/')
	print_blocks(blocks)
	# print blocks[4]



if __name__ == '__main__':
	test_parsing()