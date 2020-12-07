
import sys
import zmq
from random import randrange


context = zmq.Context()
socket = context.socket(zmq.PUB)
socket.bind("tcp://*:5556")

while True:
    zipcode = randrange(1, 100000)
    temperature = randrange(-80, 135)
    relhumidity = randrange(10, 60)

    socket.send_string("%i %i %i" % (zipcode, temperature, relhumidity))

'''
def PromptForTest(msg):
    msg.id = 23333
    mess = msg.message.add()
    mess.s = 'test'
    mess.num = 12222

msg = ptest_pb2.OrderList()
#for i in range(100000):
#    t = msg.torder.add()
#    t.id = i
#    mess = t.message.add()
#    mess.s = str(i)
#    mess.num = i+1
PromptForTest(msg.torder.add())

f = open('pbtest.file', "wb")
f.write(msg.SerializeToString())
f.close()
'''