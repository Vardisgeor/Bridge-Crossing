# Bridge-Crossing
This Java program calculates the optimal path for a family to cross a bridge, taking into account the individual crossing times of each family member. The program prompts the user to input the number of family members, the crossing time for each member, and a maximum total allowed time that can halt the exploration of paths when exceeded. In case of incorrect inputs, the program displays an error message and terminates. Subsequently, the program executes, computing the optimal path based on the given speeds of family members, and then displays the optimal moves for each member from one side of the bridge to the other. The program's capabilities include successfully calculating the best combination of movements within a user-defined time frame to move all family members from one side of the bridge to the other, minimizing the overall time. Additionally, for each crossing, it shows the position of each family member on the corresponding bank.

The program's architecture involves objects of the State type representing nodes in a state tree, aiding in problem-solving. These objects contain information about the current state of the problem, the previous state, members on the right side of the bridge, members on the left side, the total time, and the values of the functions f(n), h(n), and g(n). The State class also includes methods necessary for updating and expanding states. The solution utilizes the A* algorithm, where the program selects the path with the lowest overall cost from the root to a final state. The implementation requires the calculation of the function f(n) = h(n) + g(n). The g(n) function represents the distance of the n node from the root of the tree, specifically, the total time elapsed from the initial state of the family members' positions to the current state. The h(n) function estimates the cost of the optimal transition path from a state n to the final state, functioning as a lower bound by taking the maximum time of individuals who have not yet crossed the bridge. This heuristic function ensures that there can never be a lower cost to the final state than the h value. The f(n) function, an aggregate of the above functions, is used for selecting nodes to expand. In summary, the program dynamically chooses the node with the lowest f value for expansion, considering that the state is not in a final state (h(n) != 0), and it utilizes a list of possible states for comparison during the algorithm's expansion.

