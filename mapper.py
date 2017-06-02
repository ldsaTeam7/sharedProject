#!/usr/bin/env python

import sys

# input comes from STDIN (standard input)
for line in sys.stdin:
	list = str(line).split()
	gcount=list[0].count('G')
	ccount=list[0].count('C')
	gccount=gcount+ccount
	total=list[0].count('')-1
	print '%s\t%s' % ("CG", gccount)
	print '%s\t%s' % ("ALL", total)
