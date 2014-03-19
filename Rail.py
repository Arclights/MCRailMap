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


    def __init__(self, x_pos_chunk, x_pos_block, z_pos_chunk, z_pos_block, y_pos_block, data):
        self.x_pos_real =  x_pos_chunk *16  + x_pos_block
        self.z_pos_real =  z_pos_chunk *16 + z_pos_block
        self.y_pos_real = y_pos_block;
        self.data = data
        self.color = (randint(50, 255), randint(50, 255), randint(50, 255))

        self.x_pos_chunk = x_pos_chunk
        self.z_pos_chunk = z_pos_chunk

    def __str__(self):
        out = 'XPos: {0}, ZPos: {1}, '.format(self.x_pos_real, self.z_pos_real)
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
        
