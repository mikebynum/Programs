#!/bin/bash

#Set up some working paths
TC=~/java/apache-tomcat-6.0.35

# Grab the parameter for the project title.
PROJ=$1

# Set up destination dir
DST=$TC/webapps/$PROJ

#Simple Steps
echo "Creating Deployment environment..."
if [ ! -d $DST/WEB-INF ]; then
	mkdir $DST/WEB-INF -p
	mkdir $DST/WEB-INF/lib
	echo "Creating Deployment folders: DONE"
else
	echo "WEB-INF already exists. Skipping."
fi

## #
## Compile any model files in the com.example.model package
## #

#echo "Compiling project com.example.model files..."
#COM="javac -classpath $TC/lib/servlet-api.jar:classes -d $PROJ/classes $PROJ/src/com/example/model/*.java"
#echo "JAVAC COMMAND: $COM"
#$COM
#echo "Compiled com.example.model files.  Look for errors."
#echo "------------------------------------------------------"

## #
## Compile any java files in the com.example.web package
## #

echo "Compiling project com.example.web files..."
COM="javac -classpath $TC/lib/servlet-api.jar:classes -d $PROJ/classes $PROJ/src/com/example/web/*.java"
echo "JAVAC COMMAND: $COM"
$COM
echo "Compiled com.example.web files.  Look for errors."
echo "-------------------------------------------------------"

## # 
## Copy the files from teh development to deployment environements
## #

#Copy the classes tree over.
echo "Moving classes..."
cp -r $PROJ/classes $DST/WEB-INF

#Copy other libraries and TLD files
echo "Moving lib *.jar files..."
cp $PROJ/lib/*.jar $DST/WEB-INF/lib
echo "Moving TLD files..."
cp $PROJ/lib/*.tld $DST/WEB-INF

#Copy the web.xml file over
echo "Moving web.xml..."
cp $PROJ/etc/web.xml $DST/WEB-INF

#Move any web files over
echo "Moving web files..."
cp -r $PROJ/web/* $DST

# Shutdown tomcat
echo "Shutting down tomcat..."
sh $TC/bin/shutdown.sh > /dev/null


# wait just three seconds
echo "Waiting a few seconds to restart tomcat..."
sleep 3

# Start 'er up
sh $TC/bin/startup.sh > /dev/null
echo "Done"
