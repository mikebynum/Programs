# Client and Server compile.

all :	DBclient DBserver

client :	DBclient.c portdefine.h
	gcc -g -o DBclient -Wall -pedantic DBclient.c

server : 	server.c portdefine.h
	gcc -g -o DBserver -Wall -pedantic DBserver.c

clean :
	-rm *.o *~ DBserver DBclient

