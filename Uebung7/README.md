### MPJ Express usage
Create a basic MPI cluster with one node.

It calculates a totally useless sum, it's just to understand how MPI works.

#### Dependencies
* mpj.jar
* starter.jar
* build.xml

#### Usage
Your need apache-ant to compile java mpi packages via console

Compile:

```ant compile```

Run: 

```ant mpjrun -Dclass=packagename.MainClass -Dnp=#ofProcesses```
