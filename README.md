# Rail-AI-Leader

A automated signalling tool which uses an algorithm to perform transactions on a train layout.

## Installation

As it is a maven project, install the required dependencies, then navigate to the MainApplication class and run the file

## Usage

The program requires trains in order to run. A CSV file with the following rows of information is permitted

TRAIN ID, POINT OF ORIGIN, DESTINATION, SCHEDULED TIME, ARRIVAL TIME

Train ID - String, Used for layout identification

Point of Origin - Name of the node you want it to spawn in

Destination - Name of the node you want it to travel to

Scheduled Time - When it will appear on the graph.(entering "TEST" will add it immediately)

Arrival Time - Mostly used for performance tracking.

