'''
Created on 30 jan 2013

@author: Tommy
'''
from Rail import RailBlock
from struct import unpack
from zlib import decompress
import re
import os
import sys

sectorSize = blocksPerSection = 4096
nbrOfChunks = 16
railID = '\x42'
powerRailID = '\x1b'
detectorRailID = '\x1c'
activatorRailID = '\x9d'

def extractChunkDatas(locations, fileobj):
	chunkDatas = []
	for loc in locations:
		fileobj.seek(loc[0])
		chunkDatas.append(fileobj.read(loc[1]))
	return chunkDatas

""" 
Extracts the location table for the chunks from the raw file
"""		   
def extractLoactions(fileobj):
	locations = []
	count = 0
	while count < 1024:
		location = unpack('>BBBB', fileobj.read(4))
		offset = ((location[0] << 16) + (location[1] << 8) + location[2]) * sectorSize
		size = location[3] * sectorSize
		if offset > 0 or size > 0:
			locations.append((offset, size))
		count += 1
	return sorted(locations, key=lambda l:l[0])

def uncompressData(compChunkData):
	length = unpack('>i', compChunkData[:4])[0]
	compType = unpack('>B', compChunkData[4])[0]
	compData = compChunkData[5:length + 5]
	if compType == 1:
		raise NotImplementedError('Compressed with Gzip. This is not implemented')
	elif compType == 2:
		try:
			return decompress(compData)
		except Exception:
			print 'Failed to decompress with zlib'
			return None
	else:
		print 'Unknown compression type: {0}. This entry will be skipped'.format(compType)
		return None


def extractBlockDataArrays(chunkData):
	return re.findall('(?<=Data\x00\x00\x08\x00)[\x00-\xFF]{0,2048}', chunkData)

def extractBlockIDArrays(chunkData):
	return re.findall('(?<=Blocks\x00\x00\x10\x00)[\x00-\xFF]{0,4096}', chunkData)

def getBlockData(dataArray, index):
	data = unpack('>B', dataArray[index / 2])[0]
	if index % 2 == 0:
		return data ^ ((data >> 4) << 4)
	else:
		return data >> 4
	
def convertBlockId(Id):
	return unpack('>B', Id)[0]

def extractChunkPos(chunkData):
	xPos_raw = re.search('xPos[\x00\xFF]{2}([\x00-\xFF]{2})',  chunkData)
	yPos_raw = re.search('zPos[\x00\xFF]{2}([\x00-\xFF]{2})', chunkData)
	if xPos_raw != None and yPos_raw != None:
		xPos = unpack('>h', xPos_raw.group(1))[0]
		zPos = unpack('>h', yPos_raw.group(1))[0]
		return (xPos , zPos )

	
"""
Calculates a blocks position within a chunk based on it's position in the array of blocks
"""
def calcBlockPos(indexInDataArray):
	yPos = indexInDataArray >> 8
	zPos = (indexInDataArray & 0x0F0) >> 4
	xPos = indexInDataArray & 0x00F
	return (xPos, zPos, yPos)
	
def uncompress_chunks(chunkDatas):
	uncompressedChunkDatas = []
	for chunkData in chunkDatas:
		uncompChunkData = uncompressData(chunkData)
		if uncompChunkData != None:
			uncompressedChunkDatas.append(uncompChunkData)
	return uncompressedChunkDatas
	

def getRailBlocks(directory):
	railBlocks = []
	minXPos = sys.maxint
	minZPos = sys.maxint
	maxXPos = -sys.maxint - 1
	maxZPos = -sys.maxint - 1
	for file_name in os.listdir(directory):
		f_in = open(''.join([directory,file_name]), 'rb')
	
		locations = extractLoactions(f_in)
		chunkDatas = extractChunkDatas(locations, f_in)
	
		uncompressedChunkDatas = uncompress_chunks(chunkDatas);
	
		
		for uncompChunkData in uncompressedChunkDatas:
			chunkPos = extractChunkPos(uncompChunkData)
			BlockDataArrays = extractBlockDataArrays(uncompChunkData)
			blockIDArrays = extractBlockIDArrays(uncompChunkData)
			for sectionIndex in range(len(blockIDArrays)):
				for j in range(blocksPerSection):
					blockId = blockIDArrays[sectionIndex][j][0]
					if blockId == railID or blockId == powerRailID or blockId == detectorRailID or blockId == activatorRailID:
						blockPos = calcBlockPos(j)

						rb = RailBlock(chunkPos[0], blockPos[0], chunkPos[1], blockPos[1], blockPos[2], getBlockData(BlockDataArrays[sectionIndex], j));

						if(rb.xPosReal > maxXPos):
							maxXPos = rb.xPosReal
						elif(rb.xPosReal < minXPos):
							minXPos = rb.xPosReal;
					
						if(rb.zPosReal > maxZPos):
							maxZPos = rb.zPosReal
						elif(rb.zPosReal < minZPos):
							minZPos = rb.zPosReal
						
						
						railBlocks.append(rb)
					
		f_in.close()
	
	print('minXPos {0}, minZPos {1}, maxXPos {2}, maxZPos {3}'.format(minXPos, minZPos, maxXPos, maxZPos))
	return (minXPos, minZPos, maxXPos, maxZPos, railBlocks)

if __name__ == '__main__':
	print len(getRailBlocks('Regions2/r.0.-1.mca'))
