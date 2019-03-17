**About the exercise**:
This looked to me like a combination of a knapsack and a bin packing problem:

**Knapsack**: Problem of combinatorial optimisation where given a set of items with weight (in this case duration) we 
 need to decide which ones would fit in the container without exceeding the limit. Here the decision problem is 
 NP-complete, the optimization problem is NP-hard. Also the resolution of this problem is at least as difficult as 
 the decision, and there is no known polynomial algorithm which can tell, given a solution, whether it is optimal 
 (which would mean that there is no solution with a larger V, thus solving the NP-complete decision problem).
 
**Bin packing**: Problem that says that object of different volumes (in this case duration) need to be packed into a
 finite number of containers, while each has a volume V in a way that minimises the containers used. Here the decision
 problem is NP-complete and in computational complexity theory it is a combinatorial NP-hard problem. 
   
_The solution is straightforward greedy approximation algorith, which processes activities in arbitrary order. This means
that it is solved in a deterministic way, and for the same input will always produce the same output._

**Usage**:
- the program contains a activities.json and activities.txt files for easier use, although different files can be 
specified upon running the program with: `mvn spring-boot:run -Dspring-boot.run.arguments=activities.txt`
- if the program is ran without arguments, then a logger will print that you can use the api in that case,
`http://localhost:8080/api/v1/activities` where you can send a POST request with a JSON body, and that is the format 
of the activities.json file
- the program can also be run without arguments using docker, (after what you can use the endpoint) with the command 
    1. `mvn clean package docker:build`
    2. `docker-compose up -d`

**Description**:
- the text processors are in the package `loader` where, there are three implementations of an interface, called 
by a factory class.
- the api controller has only one endpoint and it is in the package `controller`.
- the model into which the text is loaded is in the package `model`, and in addition to that is stores in an crud
repository. The application.properties file contains the specifications about h2 database and the docker file about
the mysql image taken from docker hub.
- the `ActivityService` is actually what calls the processor for the text data and is inside the `service` package
- the algorithm itself is in the class `BinPackingProcessor` and it is only called with the service. It uses the extra
parameters startOfDay, endOfDay, startOfLunchBreak which help determine where the activity will fall. So far the 
program works and is tested only with two teams as specified in the example, but changes can always be made.
- the class `ArgumentsProcessor` is what translates the arguments into data and calls the BinPackingProcessor