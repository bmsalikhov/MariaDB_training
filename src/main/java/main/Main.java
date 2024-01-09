package main;

import data_source.DbManager;
import models.Employee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(100, "Steven", "King", "SKING", "515.123.4567", Date.valueOf("1987-06-17"), "AD_PRES", 24000));
        employees.add(new Employee(101, "Neena", "Konchar", "NIKONCHAR", "515.123.4568", Date.valueOf("1987-06-18"), "AD_VP", 17000));
        employees.add(new Employee(102, "Lex", "De Haan", "LDEHAAN", "515.123.4569", Date.valueOf("1987-06-19"), "AD_VP", 17000));
        employees.add(new Employee(103, "Alexander", "Hunold", "AHUNOLD", "515.123.4570", Date.valueOf("1987-06-20"), "IT_PROG", 9000));
        employees.add(new Employee(104, "Bruce", "Ernst", "BERNST", "515.123.4571", Date.valueOf("1997-06-21"), "IT_PROG", 9000));
        employees.add(new Employee(105, "David", "Austin", "DAUSTIN", "515.123.4572", Date.valueOf("1987-06-22"), "IT_PROG", 9000));
        employees.add(new Employee(106, "Valli", "Pataballa", "VPATABAL", "515.123.4573", Date.valueOf("1988-06-17"), "IT_PROG", 9000));

        DbManager dbManager = new DbManager("somedb", "someuser", "somepass", 3306);
        System.out.print("enter table name: ");
        String tableName = new Scanner(System.in).nextLine();

        dbManager.createTable(tableName);
        dbManager.insertEmployees(employees, tableName);
        dbManager.selectWithLetters(tableName);
        dbManager.selectWithYear(tableName);
        dbManager.selectMaxAndMinSalary(tableName);
        dbManager.selectEven(tableName);
        dbManager.selectOdd(tableName);
    }
}
