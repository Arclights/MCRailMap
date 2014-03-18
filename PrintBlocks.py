import sys

'''
Going north-south:
	  |	
	  |
	  |

Going west-east:
			
---	---	---


ascending to the east:

	AE


ascending to the west:

	AW


ascending to the north:

	AN


ascending to the south:

	AS


Going east turning south:
	  
---- -. 
	  |

Going west turning south:
	  
	  .-----
	  |

Going west turning north:
	  |
	  .-----


Going east turning north:
	  |
---- -. 


Unknown data:

	?

	'\t\t\t\n\t\t\t\n\t\t\t'
'''

def get_north_south():
	return '\t  |\t\t\n\t  |\t\t\n\t  |\t\t'

def get_west_east():
	return '\t\t\t\n------------\n\t\t\t'

def get_AE():
	return '\t\t\t\n\tAE\t\n\t\t\t'

def get_AW():
	return '\t\t\t\n\tAW\t\n\t\t\t'

def get_AN():
	return '\t\t\t\n\tAN\t\n\t\t\t'

def get_AS():
	return '\t\t\t\n\tAS\t\n\t\t\t'

def get_ets():
	return '\t\t\t\n\t\t.-----\n\t  |\t\t'

def get_wts():
	return '\t\t\t\n-----.\t\t\n\t  |\t\t'

def get_wtn():
	return '\t  |\t\t\n-----.\t\t\n\t\t\t'

def get_etn():
	return '\t  |\t\t\n\t\t.-----\n\t\t\t'

def get_unknown():
	return '\t\t\t\n\t  ?\t\t\n\t\t\t'

def get_empty():
	return '\t\t\t\n\t\t\t\n\t\t\t'

def merge_text_blocks(text1, text2):
	# print '*-------------*'
	# print text1
	# print '---------------'
	# print text2
	# print '*-------------*\n'
	text1_split = text1.split('\n')
	text2_split = text2.split('\n')
	out = ''
	for i in range(len(text1_split)):
		out += ''.join([text1_split[i], text2_split[i]])
		if i != 2:
			out += '\n'
	return out


def print_blocks(blocks):
	grid = {}
	minXPos = sys.maxint
	minZPos = sys.maxint
	maxXPos = -sys.maxint - 1
	maxZPos = -sys.maxint - 1
	for block in blocks:
		x = block.xPosReal
		z = block.zPosReal

		if(x > maxXPos):
			maxXPos = x
		elif(x < minXPos):
			minXPos = x
		
		if(z > maxZPos):
			maxZPos = z
		elif(z < minZPos):
			minZPos = z

		if z in grid:
			grid[z][x] = block
		else:
			grid[z] = {x:block}

	out = ''
	zs = sorted(grid.keys())
	for z in range(minZPos, maxZPos + 1):
		row = '\n\n'
		if z in zs:
			xs = sorted(grid[z].keys())
			for x in range(minXPos, maxXPos + 1):
				if x in xs:
					block = grid[z][x]
					if block.data == 0:
						row = merge_text_blocks(row, get_north_south())
					elif block.data == 1:
						row = merge_text_blocks(row, get_west_east())
					elif block.data == 2:
						row = merge_text_blocks(row, get_AE())
					elif block.data == 3:
						row = merge_text_blocks(row, get_AW())
					elif block.data == 4:
						row = merge_text_blocks(row, get_AN())
					elif block.data == 5:
						row = merge_text_blocks(row, get_AS())
					elif block.data == 6:
						row = merge_text_blocks(row, get_ets())
					elif block.data == 7:
						row = merge_text_blocks(row, get_wts())
					elif block.data == 8:
						row = merge_text_blocks(row, get_wtn())
					elif block.data == 9:
						row = merge_text_blocks(row, get_etn())
					else:
						row = merge_text_blocks(row, get_unknown())
					# row = merge_text_blocks(row, ''.join(['\t\t\t\n\t(',str(block.zPosReal),',',str(block.xPosReal),')\t\n\t\t\t']))
				else:
					row = merge_text_blocks(row, get_empty())
		out += ''.join([row, '\n'])
	
	f = open('printed_blocks.txt', 'w')
	f.write(out)
	f.close()
	# print out

if __name__ == '__main__':
	print merge_text_blocks(get_west_east(), get_west_east())


