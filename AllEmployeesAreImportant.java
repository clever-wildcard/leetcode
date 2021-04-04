//package com.example.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllEmployeesAreImportant {
    public int getImportance(List<Employee> employees, int id) {
        int summedValue = 0;
        List<Integer> support = new ArrayList<>();

        HashMap<Integer, Integer> supportIdValueMap = new HashMap<>();

        for (int i = 0; i < employees.size(); i++) {
            supportIdValueMap.put(employees.get(i).id, employees.get(i).value);
            if (employees.get(i).id == id) {
                support = employees.get(i).supportingEmployees;
                summedValue += employees.get(i).value;
            }
        }


        for (int i = 0; i < support.size(); i++) {
            summedValue += supportIdValueMap.get(support.get(i));
        }
        return summedValue;

    }

    public static void main(String[] args) {
        AllEmployeesAreImportant allEmployeesAreImportant = new AllEmployeesAreImportant();
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.id = 1;
        employee.value = 5;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(3);
        employee.supportingEmployees = arrayList;



        Employee employee1 = new Employee();
        employee1.id = 2;
        employee1.value = 3;
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        employee1.supportingEmployees = arrayList1;

        Employee employee2 = new Employee();
        employee2.id = 3;
        employee2.value = 3;
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        employee2.supportingEmployees = arrayList2;



        employees.add(employee1);
        employees.add(employee);
        employees.add(employee2);

        int value = allEmployeesAreImportant.getImportance(employees, 1);
        System.out.println(value);
    }
}

class Employee {
    int id;
    int value;
    List<Integer> supportingEmployees;
}