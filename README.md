# FieldAnalysis

Author: Shannon Brown  
Email:  brow3649@umn.edu  

## RUNNING

Input (STDIN)  
- any number of barren sections, each separated by one space  
- each barren section must contain 4 integers separated by one space  
- the format of a barren section is as follows:  
"\<lower left x coordinate\> \<lower left y coordinate\> \<upper right x coordinate\> \<upper right y coordinate\>"  
e.g. "0 292 399 307"  

- the range of x is [0, 399]  
- the range of y is [0, 599]  

- example input with four barren sections:  
			"48 192 351 207" "48 392 351 407" "120 52 135 547" "260 52 275 547"  
 
Output (STDOUT)  
- the field's fertile areas (contiguous areas of non-barren land) listed from smallest to largest  

- example input -> output:  
"0 292 399 307" -> [116800, 116800]  
"48 192 351 207" "48 392 351 407" "120 52 135 547" "260 52 275 547" -> [22816, 192608]  
