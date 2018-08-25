# PRDCalculator

### What is PRD?
PRD stands for pseudo-random distribution. It's a method for getting a more "even" kind of randomness, which might be advantageous in games. Instead of (theoretically) having 50 misses before your 10% chance hits, it might be more towards having 8-12 misses. Given that humans are really bad at judging chance, this makes for what seems like a more "accurate" chance.

### So how does it work?
Simple! The chance of a success on any given hit is given by P(E) = C*N where C is `constant for the given chance`, and N is `1 + the amount of misses`. So for a 5% chance with 4 misses, you would fill in with `00380*5`.

### What's this thing then?
This is a small tool for calculating the (approximate) C for a desired chance. I tried to port the matlab script found on [this gaming stackexchange question.](https://gaming.stackexchange.com/questions/161430/calculating-the-constant-c-in-dota-2-pseudo-random-distribution) However, after a bit of testing and playing around with libraries, I realized that the matlab script calculated a matrix of complex numbers, which no library I could find actually supported. On top of that, I couldn't be bothered to figure out how to do it myself, so I went for the bruteforce route.

### This thing is pretty slow.
I know.

### I could've made this better in [language]
Please do!

### Your code is bad/ugly/not optimized.
I'm still learning, be gentle, father.

### I'm not good at computer, how do I use this?
Download [the .jar](https://github.com/Rawrior/PRDCalculator/blob/master/out/artifacts/PRDCalculator.jar) file, and [the .bat file](https://github.com/Rawrior/PRDCalculator/blob/master/out/artifacts/PRDCalculator.bat). Make sure [you have java installed](https://www.java.com/en/download/help/download_options.xml). After that, double-click the jar file. If no terminal appears, double-click the bat file. A terminal should come up, prompting you for a desired chance.

### Are you gonna fix that TODO in main.class?
Yes