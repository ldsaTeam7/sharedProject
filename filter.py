#!/usr/bin/python3

import sys
import pysam

cramFile = pysam.AlignmentFile("HG00096.alt_bwamem_GRCh38DH.20150718.GBR.low_coverage.cram", "rc")
for line in cramFile.fetch():
        list = str(line).split()
        print(list[9])
cramFile.close()
print("Done!")