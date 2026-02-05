from operator import itemgetter
import sys

current_word = None
current_amount = 0
word = None

for line in sys.stdin:
    line = line.strip() 
    word, amount = line.split(':', 1)

    try:
        amount = float(amount)
    except ValueError:
        continue

    if current_word == word:
        current_amount += amount
    else:
        if current_word:
            print('%s\t%f' % (current_word, current_amount))
        current_amount = amount
        current_word = word

if current_word == word:
    print('%s\t%f' % (current_word, current_amount))