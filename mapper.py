#!/usr/bin/env python

#Written for python3
import sys

# input comes from STDIN (standard input)
for line in sys.stdin:
        list = line.split()
        #We'll need the 9th column, that's where the DNA data is.
        gcount=list[9].count('G')
        ccount=list[9].count('C')
        gccount=gcount+ccount
        #The total GC count is the sum of all letters G and C in the 9th column.
        total=list[9].count('')-1
        print(gccount, total)