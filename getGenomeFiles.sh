#!/bin/bash

echo -n "Enter the HG names of the files to download from the 1000 Genome Project: "
read FILENAME
FULL_FILENAME="ftp://ftp-trace.ncbi.nih.gov/1000genomes/ftp/data/$FILENAME/alignment"
echo "Downloading $FULL_FILENAME..."
wget "$FULL_FILENAME/*"
RESULT=$?
echo ""
echo -n "The result of the download operation is: "
if [ $RESULT -eq 0 ]
then
    echo "OK"
else
    echo "NOK"
fi