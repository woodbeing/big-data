import sys

for line in sys.stdin:
    # line = line.strip() 
    # words = line.split() 
    # for word in words:
    #     print('%s %s' % (word,1))
    line = line.strip()
    words = line.split()
    print(f"{words[-1]}:{words[-2]}")