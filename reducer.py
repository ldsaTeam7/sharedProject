#!/usr/bin/env python

import sys

#This code sums up the total GC count, the total lenght of all DNA, and will calculate the GC ratio.
totalgc = 0
total = 0

#input from STDIN
for line in sys.stdin:
        cols = line.split()
        totalgc = totalgc + int(cols[0])
        total = total + int(cols[1])

print(totalgc/total)