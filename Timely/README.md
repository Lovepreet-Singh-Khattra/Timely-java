# Timely (Core Java + JDBC + MySQL)

## Project Structure
```text
Timely/
├── db.properties.example
├── schema.sql
├── README.md
├── lib/
│   └── mysql-connector-j.jar   (place MySQL JDBC driver JAR here)
└── src/
    ├── Main.java
    ├── DBConnection.java
    ├── User.java
    ├── Task.java
    ├── UserService.java
    └── TaskService.java
```

## Setup & Run Instructions
1. Run `schema.sql` in MySQL:
   ```bash
   mysql -u root -p < schema.sql
   ```
2. Copy `db.properties.example` to `db.properties` and update your MySQL username and password.
3. Download `mysql-connector-j` (MySQL JDBC Driver `.jar`) and place it inside the `lib/` folder.
4. Compile from the `Timely/` root folder:
   - Linux/macOS:
     ```bash
     mkdir -p out
     javac -cp "lib/*" -d out src/*.java
     ```
   - Windows:
     ```cmd
     mkdir out
     javac -cp "lib\*" -d out src\*.java
     ```
5. Run from the `Timely/` root folder:
   - Linux/macOS:
     ```bash
     java -cp "out:lib/*" Main
     ```
   - Windows:
     ```cmd
     java -cp "out;lib\*" Main
     ```
