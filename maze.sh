#!/bin/bash

nMazes=$1
re='^[0-9]+$'

if ! [[ $nMazes =~ $re ]] ; then
    echo "Please provide number of mazes to generate and to solve as a parameter"
    exit 1
fi

# Prepare directories

if [ ! -d "build" ]; then
  mkdir build
fi

if [ ! -d "mazes" ]; then
  mkdir mazes
fi

# Clean mazes from previous run

rm mazes/*

# Compile java code

javac -d build/ src/*.java

# Generate mazes

echo "Generating mazes...."

# Create mazes with random amount of nodes, random path length from first node to last node and random amount of
# additional edges. Each mazes has at least one random solution from first node to last node
# Solve the mazes and append results to maze-solution file

echo "Number of mazes to solves: $nMazes" > maze-solution
echo "" >> maze-solution

for ((i=0;i < nMazes;i++)) do
    nNodes=$(( RANDOM % (100 - 10 + 1 ) + 10 ))
    nSteps=$(( RANDOM % ($nNodes / 2 - 2 + 1) + 2 ))
    nEdges=$(( RANDOM % ($nNodes - $nNodes / 2 + 1) + $nNodes / 2 ))

    java -cp build/ MazeGenerator $nNodes $nSteps $nEdges > mazes/$RANDOM
done

for f in mazes/*; do
    java -cp build/ MazeSolver $f 2>&1 | tee -a maze-solution
done

exit 0
