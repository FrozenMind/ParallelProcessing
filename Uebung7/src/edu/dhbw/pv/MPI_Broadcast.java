//usage of MPJ Express for Basic MPI implementation
//process 0 is the boss who sends an array to others
//others get it, build the quadrant of all values and send back to 0
//process 0 builds the sum
//with usage of broadcast
//bcast & reduce is way easier to use for small problems
package edu.dhbw.pv;

import mpi.MPI;

public class MPI_Broadcast {
  public static void main(String [] args ){
    //MPI.Init needs to be first thing to call in an MPI process
    MPI.Init(args);
    //get my id
    int myID = MPI.COMM_WORLD.Rank();
    //get number of processes
    int size = MPI.COMM_WORLD.Size();
    System.out.println("I am " + myID + " of " + size);

    //init an array
    int count = 100;
    int[] numbers = new int[count];

    //the one with id 0 is the boss, who creates & sends the field
    if(myID == 0){
      for(int i = 0; i < count; i++){
        numbers[i] = i;
      }
   }
   //broadcasat runs on every node
   //bcast is send and receive in one
   MPI.COMM_WORLD.Bcast(numbers, 0, count, MPI.INT, 0);
   System.out.println("all fields send/recv " + myID);

    //build quadrant of all numbers & at quadrant of own id
    //crazzy calculation, but whatever, just do sth
    //every process does that, even the boss process 0
    int quadrant = 0;
    for(int i = 0; i < numbers.length; i++){
      quadrant += numbers[i]*numbers[i] + myID*myID;
    }
    //result is set to numbers[0], cause we can only send arrays for now
    numbers[0] = quadrant;
    System.out.println("Quadrant of " + myID + " is " + quadrant);
    //send quadrants back to process with id 0
    int numRecv[] = new int[1];
    //reduce is run on every node again
    //items are send and automatically summed
    MPI.COMM_WORLD.Reduce(numbers, 0, numRecv, 0, 1, MPI.INT, MPI.SUM, 0);
    //only id one, receives them and sum it
    if(myID == 0){
      System.out.println("Total sum is: " + numRecv[0]);
    } 
    
    //last thing to call in an MPI process
    MPI.Finalize();
  }

}
