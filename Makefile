#-------------------------------------------------------------------------------
# I have no idea how to do this, I hope this works
#-------------------------------------------------------------------------------

project1.exe : prog2.c
	gcc -Wall prog2.c -o prog2.exe

clean :	
	rm *.o
