Homework 09: State Space Search
This homework is a bit different in style from the others.
The following code implements BFS and DFS for general problems (as we saw in prior lectures). In addition, a specific problem (Double Subtract One) is provided.
Note that `BFS` and `DFS` in `ProblemSolver.java` both return a path, not just a state. How they do this is with the `Node` class. Here, a `Node` instance represents a path from the initial state to some other state. Each node has a particular state and a pointer (object reference) to another `Node` that is its parent.
Start by downloading the following files:

https://www.dropbox.com/s/l91c9atqxh1tr04/DoubleSubtractOneProblem.java?dl=0
https://www.dropbox.com/s/aw3jrv3srhnmnjo/Problem.java?dl=0
https://www.dropbox.com/s/zxuzz18b5mkjyyg/ProblemSolver.java?dl=0

Double Subtract One Problem:

Our problem is defined as follows:
The initial state: 1
The (single) goal state: $$k$$
For any state $$n$$, up to two successors exist: $$2n$$ and $$n - 1$$
However, if $$n > k$$, then $$2n$$ is not a successor, and if $$n \leq 1$$, then $$n-1$$ is not a successor.

Note that $$k$$ is instantiated for any instance of the Double Subtract One Problem.

Example

Let $$k = 10$$. Here are several paths to the goal:
1 → 2 → 4 → 8 → 16 → 15 → 14 → 13 → 12 → 11 → 10
1 → 2 → 4 → 3 → 6 → 5 → 10

How `.contains` works

In `ProblemSolver.java` we have the line “`if (! considered.contains(s))`". This essentially goes through `considered` and sees if any `State` in it “equals” `s`. Here, some state `otherstate` “equaling” `s` is defined as “`otherstate.equals(s)`".
To encode what we mean by two states being equal (being “the same state”), we override the `.equals` method in `DoubleSubtractOneState`. Without doing this, the `considered` list will not work properly.

What you’ll submit:

You’ll submit one file: `OtherProblem.java`
Here you should implement another Problem (and corresponding State).
We will test your code by putting the following into the `main` method of ProblemSolver:

    OtherProblem p = new OtherProblem();
    System.out.println(BFS(p));
    System.out.println(DFS(p));

Note: Your defined Problem should be such that BFS and DFS do not go into an infinite loop.

In your constructor, you should print out a (human readable) description of the problem you’ve created.

Note: You can go all fancy with this and implement 8-Queens or the like, or be less creative and create a problem like “TripleSubtractTwo”. Your main goal in this lab is to go through the provided code to the point where you understand what it’s doing.

And don’t forget to fill out the survey:
https://forms.gle/kaVGuLySBAV7a2Cq8
