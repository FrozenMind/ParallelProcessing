//usage of MPJ Express for Basic MPI implementation
//process 0 is the boss who sends an array to others
//others get it, build the quadrant of all values and send back to 0
//process 0 builds the sum
package edu.dhbw.pv;

import mpi.MPI;

public class Uebung7 {
  public static void main(String [] args ){
    //MPI.Init needs to be first thing to call in an MPI process
    MPI.Init(args);
    System.out.println("Init done");
    //get my id
    int myID = MPI.COMM_WORLD.Rank();
    //get number of processes
    int size = MPI.COMM_WORLD.Size();
    System.out.println("I am " + myID + " of " + size);

    //init an array
    int count = 100;
    int[] numbers = new int[count];

    //the one with id 0 is the boss, who sends the field
    if(myID == 0){
      for(int i = 0; i < count; i++){
        numbers[i] = i;
      }
      for(int i = 1; i < size; i ++){
        //Send(files, start indes, size, datatype, receiver, msg tag)
        //msg tag = just a number, for now no real usage
        MPI.COMM_WORLD.Send(numbers, 0, count, MPI.INT, i, 99);
      }
      System.out.println("Send all fields");
    } else { //other processe are the receiver
      //field, startoffset, size, datatype, sender, msg tag
      MPI.COMM_WORLD.Recv(numbers, 0, count, MPI.INT, 0, 99);
      System.out.println("Received field from " + myID);
    }

    //build quadrant of all numbers & at quadrant of own id
    //crazzy calculation, but whatever, just do sth
    //every process does that, even the boss process 0
    int quadrant = 0;
    for(int i = 0; i < numbers.length; i++){
      quadrant += numbers[i]*numbers[i] + myID*myID;
    }
    System.out.println("Quadrant of " + myID + " is " + quadrant);

    //send quadrants back to process with id 0
    if(myID == 0){
      //add my own quadrant to sum
      int sum = quadrant;
      for(int i = 1; i < size; i++){
        //process 0 receives a field with size 1
        MPI.COMM_WORLD.Recv(numbers, 0, 1, MPI.INT, i, 99);
        sum += numbers[i];
      } 
      System.out.println("Total crazzy quadrant is " + sum);
    } else {
      numbers[0] = quadrant;
      //only send the first number to process with id 0
      MPI.COMM_WORLD.Send(numbers, 0, 1, MPI.INT, 0, 99);    
    }

    //last thing to call in an MPI process
    MPI.Finalize();
  }

}
