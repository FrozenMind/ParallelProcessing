//hello world with JOMP (java open mp)

package edu.dhbw.pv;

import jomp.runtime.*;

public class HelloJomp {

  public static void main(String[] args) {

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.args = args;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  args = __omp_Object0.args;
}
// OMP PARALLEL BLOCK ENDS

 
    //now lets do some calculation
    int count = 100000000;
    int i;
    int[] numbers = new int[count];

    //init numbers array
    for(i = 0; i < count; i++) {
      numbers[i] = i*i;
    }

    long sum = 0;
    double start = System.currentTimeMillis();

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class1 __omp_Object1 = new __omp_Class1();
  // shared variables
  __omp_Object1.sum = sum;
  __omp_Object1.start = start;
  __omp_Object1.numbers = numbers;
  __omp_Object1.i = i;
  __omp_Object1.count = count;
  __omp_Object1.args = args;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object1);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  sum = __omp_Object1.sum;
  start = __omp_Object1.start;
  numbers = __omp_Object1.numbers;
  i = __omp_Object1.i;
  count = __omp_Object1.count;
  args = __omp_Object1.args;
}
// OMP PARALLEL BLOCK ENDS

    double end = System.currentTimeMillis();
    System.out.println("Sum is = " + sum + " in " + (end - start) + "ms");
  }

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class1 extends jomp.runtime.BusyTask {
  // shared variables
  long sum;
  double start;
  int [ ] numbers;
  int i;
  int count;
  String [ ] args;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
    // OMP USER CODE BEGINS

              { // OMP FOR BLOCK BEGINS
              // copy of firstprivate variables, initialized
              // copy of lastprivate variables
              // variables to hold result of reduction
              long _cp_sum;
              boolean amLast=false;
              {
                // firstprivate variables + init
                // [last]private variables
                // reduction variables + init to default
                long  sum = 0;
                // -------------------------------------
                jomp.runtime.LoopData __omp_WholeData3 = new jomp.runtime.LoopData();
                jomp.runtime.LoopData __omp_ChunkData2 = new jomp.runtime.LoopData();
                __omp_WholeData3.start = (long)( 0);
                __omp_WholeData3.stop = (long)( count);
                __omp_WholeData3.step = (long)(1);
                jomp.runtime.OMP.setChunkStatic(__omp_WholeData3);
                while(!__omp_ChunkData2.isLast && jomp.runtime.OMP.getLoopStatic(__omp_me, __omp_WholeData3, __omp_ChunkData2)) {
                for(;;) {
                  if(__omp_WholeData3.step > 0) {
                     if(__omp_ChunkData2.stop > __omp_WholeData3.stop) __omp_ChunkData2.stop = __omp_WholeData3.stop;
                     if(__omp_ChunkData2.start >= __omp_WholeData3.stop) break;
                  } else {
                     if(__omp_ChunkData2.stop < __omp_WholeData3.stop) __omp_ChunkData2.stop = __omp_WholeData3.stop;
                     if(__omp_ChunkData2.start > __omp_WholeData3.stop) break;
                  }
                  for(int i = (int)__omp_ChunkData2.start; i < __omp_ChunkData2.stop; i += __omp_ChunkData2.step) {
                    // OMP USER CODE BEGINS
 {
      sum += numbers[i] * numbers[i];
    }
                    // OMP USER CODE ENDS
                    if (i == (__omp_WholeData3.stop-1)) amLast = true;
                  } // of for 
                  if(__omp_ChunkData2.startStep == 0)
                    break;
                  __omp_ChunkData2.start += __omp_ChunkData2.startStep;
                  __omp_ChunkData2.stop += __omp_ChunkData2.startStep;
                } // of for(;;)
                } // of while
                // call reducer
                _cp_sum = (long) jomp.runtime.OMP.doPlusReduce(__omp_me, sum);
                jomp.runtime.OMP.doBarrier(__omp_me);
                // copy lastprivate variables out
                if (amLast) {
                }
              }
              // set global from lastprivate variables
              if (amLast) {
              }
              // set global from reduction variables
              if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                sum+= _cp_sum;
              }
              } // OMP FOR BLOCK ENDS

    // OMP USER CODE ENDS
  // call reducer
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS



// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  String [ ] args;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
    // OMP USER CODE BEGINS

    {
      int myID = OMP.getThreadNum();
      System.out.println("Hello from thread " + myID);
    }
    // OMP USER CODE ENDS
  // call reducer
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}

