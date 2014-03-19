'''
Created on 2 feb 2013

@author: Tommy
'''
from PIL import Image
from PIL.ImageDraw import ImageDraw
from Parser import get_rail_blocks
import sys

if __name__ == '__main__':
    blocks = get_rail_blocks('region/');

    min_x_pos = sys.maxint
    min_z_pos = sys.maxint
    max_x_pos = -sys.maxint - 1
    max_z_pos = -sys.maxint - 1

    for r_ in blocks:
        if(r_.x_pos_real > max_x_pos):
            max_x_pos = r_.x_pos_real
        elif(r_.x_pos_real < min_x_pos):
            min_x_pos = r_.x_pos_real

        if(r_.z_pos_real > max_z_pos):
            max_z_pos = r_.z_pos_real
        elif(r_.z_pos_real < min_z_pos):
            min_z_pos = r_.z_pos_real

    width = abs(max_z_pos - min_z_pos)+4
    height = abs(max_x_pos - min_x_pos)+4
    print('width {0}, height {1}'.format(width, height))
    
    img = Image.new("RGB", (width, height), "white")
    draw = ImageDraw(img)

    

    for r_ in blocks:
        chunk_x = r_.x_pos_chunk
        chunk_z = r_.z_pos_chunk
        for x_ in range(16):
            for z_ in range(16):
                draw.point((chunk_z + z_ - min_x_pos + 2, chunk_x + x_ - max_x_pos + 2), r_.color)
        draw.point((r_.x_pos_real - min_x_pos + 2, r_.z_pos_real - min_z_pos + 2), "black")
    img.save("img.png", "PNG")
