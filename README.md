# EnergyTradingDB
This project is a console-based Java application designed to manage energy trade records using CRUD operations with a SQL database. The application connects to a database (`EnergyTradingDB`) and allows users to perform various actions such as adding, viewing, updating, and deleting trade records.

## Project Overview

This application manages trades of energy commodities like Power, Gas, and Oil using the following operations:
- **Add a Trade**: Insert a new trade record.
- **View All Trades**: Fetch and display all trades in the system.
- **Update Trade**: Update existing trade details like Price and Volume.
- **Delete Trade**: Remove a trade record by its TradeID.
- **Search Trades by Counterparty / Commodity**: Search for trades based on counterparty or commodity type.

The project uses JDBC to connect the Java application to a SQL database where the trade records are stored.

## Database Setup

The database used in this project is `EnergyTradingDB`, and the table structure is defined as follows:

```sql
CREATE TABLE Trades (
    TradeID INT PRIMARY KEY IDENTITY(1,1),
    TradeDate DATE NOT NULL,
    Counterparty VARCHAR(100) NOT NULL,
    Commodity VARCHAR(50) NOT NULL,
    Volume DECIMAL(10,2) NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
    TradeType VARCHAR(10) CHECK (TradeType IN ('BUY','SELL'))
);
```
## Database Setup

- **Java 8 or later**: The application requires Java 8 or newer for execution.
- **SQL Database**: A running instance of SQL Server or another relational database.
- **JDBC**: The Java program uses JDBC (Java Database Connectivity) to connect to the database.

## How to Run the Application

### 1. Set up the Database
Ensure that your database (e.g., `EnergyTradingDB`) is running. Use the provided SQL script to create the necessary table `Trades`.

### 2. Clone the Repository
Clone this project to your local machine using the following command:

```bash
git clone https://github.com/yourusername/your-repository-name.git
```

### 3. Configure Database Connection

Modify the `DB.java` file to include your database credentials (e.g., URL, username, and password).

- Update the `USER` and `PASS` variables in `DB.java` to match your database credentials.

### 4. Run the Application

To compile and run the Java application, execute the following commands:

```bash
javac -cp ".;lib\mssql-jdbc-13.2.0.jre11.jar" -d out src\DB.java src\Trade.java src\TradeDAO.java src\Main.java    
java -cp ".;out;lib\mssql-jdbc-13.2.0.jre11.jar" Main

