# Morphological Analysis Service

This is a simple web service for getting morphological analyses of natural language texts. 
Supports currently only Finnish language. Analyzing is based on 
[Helsinki Finite-State Technology](https://hfst.github.io).

## Used Technologies

- Analyzing is based on [Helsinki Finite-State Technology](https://hfst.github.io). 
- Web service is written in Java 8 and Spring Boot
- HFST lookup for Java is based on [seco-hfst](https://github.com/jiemakel/seco-hfst) which in self is 
based on [hfst-optimized-lookup-java](https://github.com/hfst/hfst-optimized-lookup/tree/master/hfst-optimized-lookup-java).
- Transducers are from [hfst/morphological-transducers](https://sourceforge.net/projects/hfst/files/resources/morphological-transducers/). 

## Running the Service

Java 8 and Gradle are needed to build and run the service.

To start service from command line (using dev profile), run:

    $ gradle bootRun --args='--spring.profiles.active=dev'
    
To call local development instance from command line, try e.g.:

    $ curl -u user:pass http://localhost:8090/api/analysis\?word=omenoiden
    
Response should be something like:
    
    [{"word":"omenoiden","lemma":"omena","tags":["N","Gen","Pl"],"weight":312.0}]

## Building
    
Production jar can be built with:

    $ gradle build
    
Artifact is located at:

    $ build/libs/*.jar
    
## License

Finnish transducers are GPLv3 licensed and so is this service.
