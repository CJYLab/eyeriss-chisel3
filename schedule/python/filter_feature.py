import numpy as np
import math

BITS = 8
VARIANCE = 10

def getSign(num):
    if num >= 0x80:
        return num - (1 << BITS)
    else:
        return num

def getW(startAddr, outChannel,  path = "./filterMEM.hex"):

    """ Description
    :type startAddr:    Int
    :param startAddr:   the first of mem address which need to be readed

    :type outChannel:   Int
    :param outChannel:  the number of this conv layer's outChannel, in other word the number of filter

    :type path:         String
    :param path:        filter mem file path

    :raises:

    :rtype:             Filter 3D
    """
    SIZE = 3
    CHANNELMAX= 64
    W_int = []
    with open(path, "r") as f:
        for line in f.readlines():
            line = line.rstrip()
            numbers = [line[i:i+2] for i in range(0, len(line), 2)]
            W_int.append(list(map(int, numbers, [16]*len(numbers))))

    channel = 64 # if (inChannel > 64) else inChannel     #卷积核拆分后最大通道数
    W = np.zeros([SIZE, SIZE, CHANNELMAX])
    #W = [ 卷积核大小， 卷积核大小, 卷积核拆分后最大通道数 ]
    n = 0 #读取数据的第几行
    for w in range(SIZE):
        for h in range(SIZE):
                W[w, h, :] = W_int[startAddr + n]
                n += outChannel
    shape = W.shape
    W = np.array(list(map(getSign, W.flatten())))
    return W.reshape(shape)

def getFeature(startAddr, inFeatureSize, path = "./featureMEM.hex"):
    feature_int = []
    with open(path, "r") as f:
        for line in f.readlines():
            line = line.rstrip()
            numbers = [line[i:i+2] for i in range(0, len(line), 2)]
            feature_int.append(list(map(int, numbers, [16]*len(numbers))))

    CHANNELMAX = 64
    ROWMAX = 5
    feature = np.zeros([ROWMAX, inFeatureSize, CHANNELMAX])
    offset = 0
    for col in range(feature.shape[1]):
        col_data = np.array(feature_int[startAddr + offset])
        feature[:, col, :] = col_data.reshape(ROWMAX, CHANNELMAX)
        offset += 1
    shape = feature.shape
    feature = np.array(list(map(getSign, feature.flatten())))
    return feature.reshape(shape)

def conv_forward(feature, filter, stride):
    f = filter.shape[0]
    (n_H_prev, n_W_prev, n_C) = feature.shape
    n_H = int((n_H_prev - f)/stride + 1)
    n_W = int((n_W_prev - f)/stride + 1)
    Z = np.zeros([n_H, n_W, n_C])
    for h in range(n_H):
        for w in range(n_W):
            for c in range(n_C):
                vert_start = h * stride
                vert_end = vert_start + f
                horiz_start = w * stride
                horiz_end = horiz_start + f
                feature_slice = feature[vert_start:vert_end,horiz_start:horiz_end, :]
                Z[h, w, c] = np.sum(np.multiply(feature_slice[:, :, c], filter[:, :, c]))
    return Z

def layer(filterSize, inChannel, outChannel, featureSize):
    CHANNELMAX = 64
    ROWMAX = 5
    inChannelTime = math.ceil(inChannel / ROWMAX)
    pass