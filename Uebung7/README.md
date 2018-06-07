### MPJ Express usage
Create a basic MPI cluster with one node.

It calculates a totally useless sum, it's just to understand how MPI works.

#### Dependencies
* mpj.jar
* starter.jar
* build.xml
* apache-ant: command-line tool to start mpi

#### Setup build.xml for your system
Under section mpjrun you need to change the path to your MPJExpress library

#### Usage
Compile:

```ant compile```

Run: 

```ant mpjrun -Dclass=packagename.MainClass -Dnp=#ofProcesses```
