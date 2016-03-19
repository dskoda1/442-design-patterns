import random
import sys


def main():
  iterations = int(sys.argv[1])
  nums_made = []
  random.seed(1);
  
  MAX_NUM = 65536 #2^16
  
  total = 0

  for i in range(iterations):
    rand_int = random.randint(0, MAX_NUM)
    while(rand_int in nums_made):
      rand_int = random.randint(0, MAX_NUM)
    total += rand_int
    nums_made.append(rand_int)

  print(str(total) + ".txt")
  output = open(str(total) + ".txt", 'w')
  for num in nums_made:
    output.write(str(num) + "\n")
  output.close()

main()
