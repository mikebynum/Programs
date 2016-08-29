# This shell script demonstrates the execution of iprog and dprog.
#
# Copy it and the other files in the /home/stanw/csci8530/posixproject
# directory to a directory you own (that is, to which you have complete
# access).
#
# Also change the name of the shared memory object and the message queue
# name (see the appropriate lines below) to avoid conflict with other
# users.
#
# Then run the script by typing "sh demo.sh".
# It should take a little less than a minute to run.

# See if the current directory is /home/stanw/csci8530/posixproject.
# If it is, emit an appropriate error message and terminate.
if [ "/home/stanw/csci8530/posixproject" = `pwd` ]
then
    echo
    echo This script is to be executed from a directory to which you have
    echo write access. Please copy the contents of this directory to a
    echo directory to which you have complete access. Then change the
    echo names associated with the shared memory object and the message
    echo queue, and execute the script with the command "sh demo.sh"  .
    echo GOod luck\!
    echo
    exit 1
fi

# CHANGE THESE TO SOMETHING DIFFERENT. PERHAPS USE mem12345 AND msg12345
# WHERE 12345 IS YOUR STUDENT NUMBER.
smobjname="mem"
msgqname="msg"

if [ X$smobjname = "Xmem" ]
then
    echo
    echo YOU MUST CHANGE THE SHARED MEMORY OBJECT NAME.
    echo DO THIS BY EDITING THE demo.sh FILE.
    echo
    exit 1
fi

if [ X$msgqname = "Xmsg" ]
then
    echo
    echo YOU MUST CHANGE THE MESSAGE QUEUE NAME.
    echo DO THIS BY EDITING THE demo.sh FILE.
    echo
    exit 1
fi

if [ ! -f text0 ] || [ ! -f text1 ]
then 
    echo
    echo THE FILES text0 AND text1 MUST BE PRESENT FOR THE DEMO.
    echo COPY THEM FROM THE /home/stanw/csci8530/posixproject DIRECTORY.
    echo
    exit 1
fi

if [ ! -x iprog ] || [ ! -x dprog ]
then
    echo
    echo THE FILES iprog AND dprog MUST EXIST IN THE CURRENT DIRECTORY.
    echo THEY MUST ALSO BE EXECUTABLE.
    echo COPY THEM FROM /home/stanw/csci8530/posixproject.
    echo
    exit 1
fi

# At this point we've checked most of the errors that could have been made.
# Of courses there are always other mistakes that could occur.
 
# Initialize the text to be displayed.
cp text0 text

# Start the generator program (5 updates, 10 seconds each)
./iprog $smobjname $msgqname text 5 10 &

# Start the display program after a second
sleep 1; ./dprog $smobjname $msgqname &

# Wait 22 secpmds. then update the text
sleep 22; cp text1 text

# To avoid the shell prompt from upsetting our pretty display,
# we'll delay about the right amount of time before terminating.
sleep 28; echo DONE; rm -f text
