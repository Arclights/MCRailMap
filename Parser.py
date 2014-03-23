'''
Created on 30 jan 2013

@author: Tommy
'''
from Rail import RailBlock
from struct import unpack
from zlib import decompress
import re
import os

SECTOR_SIZE = BLOCKS_PER_SECTION = 4096
RAIL_ID = '\x42'
POWER_RAIL_ID = '\x1b'
DETECTOR_RAIL_ID = '\x1c'
ACTIVATOR_RAIL_ID = '\x9d'


def extract_chunk_datas(locations, fileobj):
	chunk_datas = []
	for loc in locations:
		fileobj.seek(loc[0])
		chunk_datas.append(fileobj.read(loc[1]))
	return chunk_datas

'''
Extracts the location table for the chunks from the raw file
'''
def extract_locations(fileobj):
	locations = []
	count = 0
	while count < 1024:
		location = unpack('>BBBB', fileobj.read(4))
		offset = ((location[0] << 16) + (location[1] << 8) + location[2]) * SECTOR_SIZE
		size = location[3] * SECTOR_SIZE
		if offset > 0 or size > 0:
			locations.append((offset, size))
		count += 1
	return sorted(locations, key=lambda l: l[0])

def uncompress_data(comp_chunk_data):
	length = unpack('>i', comp_chunk_data[:4])[0]
	comp_type = unpack('>B', comp_chunk_data[4])[0]
	comp_data = comp_chunk_data[5: length + 5]
	if comp_type == 1:
		raise NotImplementedError('Compressed with Gzip. This is not implemented')
	elif comp_type == 2:
		try:
			return decompress(comp_data)
		except Exception:
			print 'Failed to decompress with zlib'
			return None
	else:
		print 'Unknown compression type: {0}. This entry will be skipped'.format(comp_type)
		return None


def extract_block_data_arrays(chunk_data):
	return re.findall('(?<=Data\x00\x00\x08\x00)[\x00-\xFF]{0,2048}', chunk_data)

def extract_block_id_arrays(chunk_data):
	return re.findall('(?<=Blocks\x00\x00\x10\x00)[\x00-\xFF]{0,4096}', chunk_data)

def get_block_data(data_array, index):
	data = unpack('>B', data_array[index / 2])[0]
	if index % 2 == 0:
		return data ^ ((data >> 4) << 4)
	else:
		return data >> 4

def convert_block_id(id_):
	return unpack('>B', id_)[0]

def extract_chunk_pos(chunk_data):
	x_pos_raw = re.search('xPos[\x00\xFF]{2}([\x00-\xFF]{2})',  chunk_data)
	y_pos_raw = re.search('zPos[\x00\xFF]{2}([\x00-\xFF]{2})', chunk_data)
	if x_pos_raw is not None and y_pos_raw is not None:
		x_pos = unpack('>h', x_pos_raw.group(1))[0]
		z_pos = unpack('>h', y_pos_raw.group(1))[0]
		return (x_pos, z_pos)


'''
Calculates a blocks position within a chunk based on it's position in the array of blocks
'''
def calc_block_pos(index_in_data_array):
	y_pos = index_in_data_array >> 8
	z_pos = (index_in_data_array & 0x0F0) >> 4
	x_pos = index_in_data_array & 0x00F
	return (x_pos, z_pos, y_pos)

def uncompress_chunks(chunk_datas):
	uncompressed_chunk_datas = []
	for chunk_data in chunk_datas:
		uncomp_chunk_data = uncompress_data(chunk_data)
		if uncomp_chunk_data is not None:
			uncompressed_chunk_datas.append(uncomp_chunk_data)
	return uncompressed_chunk_datas


def get_rail_blocks(directory):
	rail_blocks = []
	
	for file_name in os.listdir(directory):
		f_in = open(''.join([directory, file_name]), 'rb')

		locations = extract_locations(f_in)
		chunk_datas = extract_chunk_datas(locations, f_in)

		uncompressed_chunk_datas = uncompress_chunks(chunk_datas)
	
		for uncomp_chunk_data in uncompressed_chunk_datas:
			chunk_pos = extract_chunk_pos(uncomp_chunk_data)
			block_data_arrays = extract_block_data_arrays(uncomp_chunk_data)
			block_id_arrays = extract_block_id_arrays(uncomp_chunk_data)
			for section_index in range(len(block_id_arrays)):
				for j in range(BLOCKS_PER_SECTION):
					block_id = block_id_arrays[section_index][j][0]
					if block_id == RAIL_ID or block_id == POWER_RAIL_ID or block_id == DETECTOR_RAIL_ID or block_id == ACTIVATOR_RAIL_ID:
						block_pos = calc_block_pos(j)

						r_b = RailBlock(chunk_pos[0], block_pos[0], chunk_pos[1], block_pos[1], block_pos[2], get_block_data(block_data_arrays[section_index], j))

						
						
						rail_blocks.append(r_b)
					
		f_in.close()
	
	return rail_blocks

if __name__ == '__main__':
	blocks = get_rail_blocks('region/')
	f = open('parsed_blocks', 'w')
	for block in blocks:
		f.write(str(block.x_pos_real))
		f.write(' ')
		f.write(str(block.z_pos_real))
		f.write(' ')
		f.write(str(block.data))
		f.write('\n')
	f.close()