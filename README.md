# Build Instructions:
- mvn clean package

# Run instructions:
- The GettysburgAddress
  - time java -jar target/concordance-1.0-SNAPSHOT.jar src/test/resources/GettysburgAddress.txt > GA.out
- The King James Bible
  - time java -jar target/concordance-1.0-SNAPSHOT.jar src/test/resources/bible.txt > bible.out

# DEV.TO Article
[Generating a Concordance](https://dev.to/berryware/generating-a-concordance-1ie2)