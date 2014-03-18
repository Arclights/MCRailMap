'''
Created on 2 feb 2013

@author: Tommy
'''
from PIL import Image
from PIL.ImageDraw import ImageDraw
from Parser import getRailBlocks

if __name__ == '__main__':
    blocks = getRailBlocks('region/');
    width = abs(blocks[3] - blocks[1])+4
    height = abs(blocks[2] - blocks[0])+4
    print('width {0}, height {1}'.format(width, height))
    
    img = Image.new("RGB", (width, height), "white")
    draw = ImageDraw(img)

    for r in blocks[4]:
        chunk_x = r.xPosChunk
        chunk_z = r.zPosChunk
        for x in range(16):
            for z in range(16):
                draw.point((chunk_z+ z- blocks[1]+2, chunk_x + x- blocks[0]+2), r.color)
    pixels = img.load()
    for r in blocks[4]:
        coord = r.getCoords();
        draw.point((coord[1] - blocks[1]+2, coord[0] - blocks[0]+2), "black")
    img.save("img.png", "PNG")
