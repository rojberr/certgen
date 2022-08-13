# CertGen

This is a Java Certificate Generator tool using Bouncy Castles library.

![Photo by Ekrulila from Pexels](pexels-certificate-2292837.jpg)

It is supposed to ease the pain of storing and creating certificates using Java,  
because right now this may be a little of a hassle.

It is easy to store and manage certificates using Java keystore tool, but this one helps
in the process of creation for a user unskilled in programming.

## Usage

[Install Docker](https://docs.docker.com/get-docker/) on your system and build an image using Gradle: 

```bash
./gradlew assemble docker
```

Then run the created Docker image by typing:

```bash
docker run -p 8081:8081 <image_name> -name certgen
```

From now on a self hosted API should be running on your system.

To create a X509 certificate use a simple webpage under:
```bash
http://localhost:8081/generateX509
```

or send a http API request as follows:
```bash
curl http://localhost:8081/generateX509?\
version=v3&\
rsaKeySize=2048&\
commonName=Example+name&\
country=DE&\
validFrom=1011-01-01&\
validTill=1010-01-01&\
distinguishedName=...
```

The program will save created .pem and .der files in the project root folder!

## Pushing to your DockerHub

To automatically build this image and push it to your DockerHub do the following:

- login:
```bash
docker login
```

- And then push it to the DockerHub:
```bash
./gradlew dockerPush
```

## Swagger - API Documentation

A dependency was added that creates Swagger API page automatically. To access it go to:
`http://localhost:8081/swagger-ui.html`

## Need further support?
Contact me if you need help at rojberr@outlook.com .

I am using hexagonal architecture for Java class organization.