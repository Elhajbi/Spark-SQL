# Incident App

## Description
Incident App is a simple Apache Spark Java application that processes an incident CSV file and provides analytics such as the number of incidents per service and the top 2 years with the most incidents.

## Features
- Reads incident data from a CSV file (`incidents.csv`).
- Filters out the header and malformed lines.
- Counts the number of incidents grouped by service.
- Counts the number of incidents grouped by year.
- Displays the results in the console.

## Requirements
- Java 8 or higher
- Apache Spark 3.x
- Maven (or your preferred build tool)

## Setup and Run

1. **Clone or download the project.**

2. **Place your `incidents.csv` file in the working directory.**  
   The CSV file should have at least 5 columns, where:
    - Column 4 (index 3) is the **service** name
    - Column 5 (index 4) is the **date** in `YYYY-MM-DD` format

3. **Build the project** (if using Maven):
```bash
mvn clean package
```
4. **Run the application** :
## Example Output:
```` bash
📌 Nombre d'incidents par service :
ServiceA : 120
ServiceB : 90
ServiceC : 45

📌 Les 2 années avec le plus d’incidents :
2023 : 150
2022 : 130
````
### Notes
The app runs locally using Spark’s local mode.

Make sure the input CSV file has valid formatting.

Lines starting with Id are ignored as headers.

Malformed lines with missing fields are skipped.

### Author
Abdelkarim El Hajbi – Full Stack Developer