#!/usr/bin/python3

import sys
import pysam

#The pysam module is the module that can process CRAM files

cramFile = pysam.AlignmentFile("HG00096.alt_bwamem_GRCh38DH.20150718.GBR.low_coverage.cram", "rc")

#This code opens up the cram file for reading. "rc" means "read" and "cram format". 

for line in cramFile.fetch():
        list = str(line).split()
		#every line in the cram file will be split by column. 
        print(list[9])
		# We need the 9th column. This is where the DNA sequence is. 
cramFile.close()
print("Done!")