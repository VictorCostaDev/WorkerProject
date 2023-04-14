package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter department's name: ");
        String departmentName = sc.nextLine();

        System.out.println("Enter worker data: ");
        System.out.print("Name: ");
        String workerName = sc.nextLine();
        System.out.print("Level: ");
        String workerLevel = sc.nextLine();
        System.out.print("Base salary: ");
        double workerBaseSalary = sc.nextDouble();

        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), workerBaseSalary, new Department(departmentName));

        System.out.print("How many contracts to this worker ? ");
        int workerNumberOfContracts = sc.nextInt();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int index = 1; index <= workerNumberOfContracts; index++) {
            System.out.println("Enter contract #" +index+" data:");
            System.out.print("Date (DD/MM/YYYY): ");
            sc.nextLine();
            String date = sc.nextLine();
            System.out.print("Value per hour: ");
            Double valuePerHour = sc.nextDouble();
            System.out.print("Duration (hours): ");
            int duration = sc.nextInt();

            HourContract contract = new HourContract(
                    LocalDate.parse(date, formatter),
                    valuePerHour,
                    duration);
            worker.addContract(contract);
        }

        System.out.print("Enter number of month to calculate income: ");
        int month = sc.nextInt();
        System.out.print("Enter year to calculate income (yyyy): ");
        int year = sc.nextInt();

        double salary = worker.income(year, month);

        System.out.println("Name: " + worker.getName());
        System.out.println("Department = " + worker.getDepartment().getName());
        System.out.printf("Income for 0%d/%d: %s", month, year, String.format("%.2f", salary));
        sc.close();
    }
}