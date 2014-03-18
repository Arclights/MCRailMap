'''
Created on 1 feb 2013

@author: Tommy
'''

from random import randint

class RailBlock(object):
    '''
                z-
                N
                |
    x- W ----   .   ---- E x+
                |
                S
                z+

    '''


    def __init__(self, xPosChunk, xPosBlock, zPosChunk, zPosBlock, yPosBlock, data):
        self.xPosReal =  xPosChunk *16  + xPosBlock
        self.zPosReal =  zPosChunk *16 + zPosBlock
        self.yPosReal = yPosBlock;
        self.data = data
        self.color = (randint(50, 255), randint(50, 255), randint(50, 255))

    def getCoords(self):
        return (self.xPosReal, self.zPosReal)
    
    '''
    For test
    '''
    def getColor(self):
        return self.color
        
    def __str__(self):
        out = 'XPos: {0}, ZPos: {1}, '.format(self.xPosReal, self.zPosReal)
        if self.data == 0:
            out += 'Going north-south'
        elif self.data == 1:
            out += 'Going west-east'
        elif self.data == 2:
            out += 'ascending to the east'
        elif self.data == 3:
            out += 'ascending to the west'
        elif self.data == 4:
            out += 'ascending to the north'
        elif self.data == 5:
            out += 'ascending to the south'
        elif self.data == 6:
            out += 'Going east turning south'
        elif self.data == 7:
            out += 'Going west turning south'
        elif self.data == 8:
            out += 'Going west turning north '
        elif self.data == 9:
            out += 'Going east turning north'
        else:
            out += 'Unknown data'
        return out
    
    def __repr__(self):
        return self.__str__()
        
