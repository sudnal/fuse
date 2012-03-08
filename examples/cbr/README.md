# Content-Based Router

## Overview
This example demonstrates how to use the content-based router (CBR) pattern in Camel to send a message
to a different destination based on the contents of the message.

In this example, orders from zoos all over the world will be copied from the input directory into a specific
output directory per country.

## What You Will Learn
In studying this example you will learn:

* how to define a Camel route using the Blueprint XML syntax
* how to build and deploy a Fuse Application Bundle (FAB) in Fuse ESB
* how to use the CBR enterprise integration pattern

## Prerequisites
Before building and running this example you need:

* Maven 3.0.3 or higher
* JDK 1.6
* Fuse ESB Enterprise 7

## Building the Example
To build the example:

1. Change your working directory to the examples/cbr directory
1. Run `mvn clean install` to build the example

## Running the Example
To run the example:

1. Start Fuse ESB Enterprise 7 by running bin/fuseesb (on Linux) or bin\fuseesb.bat (on Windows)
1. In the Fuse ESB console, enter the following command: `osgi:install -s fab:mvn:org.fusesource.examples/cbr/${project.version}`
1. As soon as the Camel route has been started, you will see a directory work/cbr/input in your Fuse ESB installation
1. Copy the files you find in this example's src/test/data directory to the newly created work/cbr/input directory
1. Wait a few moment and you will find the same files organised by country under work/cbr/output
        order1.xml in work/cbr/output/orders
        order2.xml and order4.xml in work/cbr/output/uk
        order3.xml and order5.xml in work/cbr/output/uk
1. Use `log:display` to check out the business logging
        Receiving order order1.xml
        Sending order order1.xml to another country
        Done processing order1.xml

## More information
For more information see:

* http://www.enterpriseintegrationpatterns.com/ContentBasedRouter.html for more information about the CBR EIP