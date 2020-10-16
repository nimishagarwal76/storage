# storage
Simple storage server implemented using AWS S3 API. AWS provides its S3 bucket service which basically is used to store data over cloud, provide access to public or other users. The project aims to provide the same functionality, so for people having small projects, they can host this on their server and get signed links and get managed access to their resources. It follows S3 API which is public, so that to connect to the server people can plethora of existing tools in the market.

## Supported Methods
### Buckets
- Creating Bucket

### Object
- Put Object
- Post Object(Multipart Upload)

## Development
- Install these:
    - [Java SE Development Kit 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
    - After installing JDK 11, install [Intellij Idea IDE](https://www.jetbrains.com/idea/)
- Open the project in Intellij IDE
- Setup the port at which server would run and the upload directory (location where files would be uploaded) in application.properties
- Click Run (Shift + F10) to start the server, it will build the project and run the server on the specified port.

For further information, check out the [wiki](https://github.com/nimishagarwal76/storage/wiki).

## Supported Clients
As of now I have it tested with
- [aws-sdk](https://www.npmjs.com/package/aws-sdk)
