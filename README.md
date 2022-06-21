# CertGen

This is a Java Certificate Generator tool using Bouncy Castles library.

![Photo by Ekrulila from Pexels](pexels-certificate-2292837.jpg)

It is supposed to ease the pain of storing and creating certificates using Java,  
because right now this may be a little of a hassle.

It is easy to store and manage certificates using Java keystore tool, but this one helps
in the process of creation for a user unskilled in programming.

## Usage

[Install Docker](https://docs.docker.com/get-docker/) on your system and build an image by typing: 

> docker build . -t certgen-docker1  

Then run the created Docker image by typing:

> docker run -p 8081:8081 certgen-docker1 -name certgen

From now on a self hosted API should be running on your system.

To create a X509 certificate use a simple webpage under:
> http://localhost:8081/generateX509

or send an http API request as follows:
> http://localhost:8081/generateX509?
> version=v3&
> rsaKeySize=2048&
> commonName=Example+name&
> country=DE&
> validFrom=1011-01-01&
> validTill=1010-01-01&
> distinguishedName=...

The program will save created .pem and .der files in the project root folder!

## Running Jenkins

To run Jenkins Docker container and do the tests automatically you can use my other repo:

`git clone https://github.com/rojberr/build-your-repo-with-jenkins-container.git`

`cd build-your-repo-with-jenkins-container`

`git checkout poject/rojberr/certgen`

`./gradlew build docker dockerRun`

This will create preconfigured Jenkins container and you will be able to access it under localhost:8081 and see test results.

## Need further support?
Contact me if you need help at rojberr@outlook.com .