all: blink iprog dprog

iprog: iprog.c
	cc -o iprog iprog.c -lrt

dprog: dprog.c
	cc -o dprog dprog.c -lrt

blink: blink.c
	cc -o blink blink.c -lrt

clean:
	rm -f *.o
	rm -f iprog
	rm -f dprog
	rm -f blink
