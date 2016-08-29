# This makefile is used to create (or recreate) the executable file named
# prunw from the source file prunw.c in this directory.
#
# Lines in a makefile (or Makefile) that begin with # are comment lines,
# like these. They don't affect the makefile at all.
#
# There are (minimally) two other types of lines in a makefile: lines that
# define the dependencies between files, and lines that indicate how to create
# a file.
#
# A line that begins with a name (which is frequently the name of a file),
# then a colon, and a space-separated list of other names specifies that the
# first named "object" is dependent on the other objects. For example, look
# (below) at the line that begins with the word "prunw". It has only one name
# after the colon, specifically "prunw.c". This tells the make program that
# the object (in this case, the file prunw) is dependent on the file prunw.c
# If the make program needs to construct prunw, it then knows what files it
# must use. In addition, if prunw already exists, make will check the date of
# last modification for prunw (the so-called "target") and the files on which
# it is dependent (in this case, prunw.c). If prunw.c was modified more
# recently that prunw, then make knows it must recreate prunw from the modified
# prunw.c
#
# The other kind of line that appears in a makefile is a command. This always
# follow dependency lines and begin with a tab character (NOTE: THIS *MUST* BE
# A TAB, NOT JUST SOME NUMBER OF BLANKS). In this simple makefile we see that
# if the make program decides it needs to recreate prunw, then it must execute
# the command "cc -o prunw prunw.c". Of course there could be more than one
# command given, and they're used in the order given.
#
# Make is often invoked by just using the command "make". In this case, make
# will attempt to construct (if necessary) the first named target. In this
# makefile, that first target is "all". Of course, "all" depends on prunw, so
# make then looks for the dependency line for prunw.
#
# Make could be invoked with a target name on the command line, as in this
# example: "make clean". This causes make to attempt to build (if necessary)
# the specified target. In this case, "clean" really isn't a file that will
# ever exist, but make doesn't care -- it just notes that "clean" isn't there
# so it executes the command(s) needed to "construct" clean. Here that just
# deletes the executable file "prunw", which "cleans" up the directory.
#
# There are numerous other features of the make program, but this should give
# you enough information to get started using it. Also note that you will not
# be capable of successfully executing the make command in this (distribution)
# directory, since it is protected from writing. Instead, copy this makefile
# and prunw.c to one of your directories and then use the make command there.
#
all: prunw

prunw: prunw.c
	cc -o prunw prunw.c

clean:
	rm -f prunw
