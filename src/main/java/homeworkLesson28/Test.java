package homeworkLesson28;

public class Test {
    static EmployeeManager employeeManager = new EmployeeManager();
    public static void main(String[] args) {
        Employee employee1 = new Employee(1000, "Max", "Development", 7000);
        Employee employee2 = new Employee(1001, "Alex", "Management", 10000);
        Employee employee3 = new Employee(1010,"Alonso","Data Science", 6500);
        Employee employee4 = new Employee(1100, "Diana", "Cybersecurity", 8000);
        Employee employee5 = new Employee(1101, "Rose","Marketing",7000);

        //Выведем информацию об одном из сотрудников
        System.out.println("\nВыведем информацию об одном из сотрудников");
        employee4.displayInfo();

        //Добавим всех сотрудников в HashMap
        System.out.println("\nДобавим всех сотрудников в HashMap");
        employeeManager.addEmployee(employee1);
        employeeManager.addEmployee(employee2);
        employeeManager.addEmployee(employee3);
        employeeManager.addEmployee(employee4);
        employeeManager.addEmployee(employee5);

        //Попытаемся добавить два дубликата
        System.out.println("\nПопытаемся добавить два дубликата");
        employeeManager.addEmployee(employee2);
        employeeManager.addEmployee(employee3);

        //Обновим данные сотрудника:
        System.out.println("\nОбновим данные сотрудника");
        employeeManager.updateEmployee(1001,"Alex", "Management",11000);

        System.out.println("\nЗарплата обновилась:");
        employee2.displayInfo();

        //Удаляем сотрудника по id
        System.out.println("\nУдаляем сотрудника по id");
        employeeManager.removeEmployee(1001);

        //Поиск сотрудника по id
        System.out.println("\nПоиск сотрудника по id");
        employeeManager.findEmployeeById(1001);
        Employee found = employeeManager.findEmployeeById(1000);
        System.out.println("\nВыведем на экран возвращенный объект");
        found.displayInfo();

        //Выведем на экран данные всех сотрудников
        System.out.println("\nВыведем на экран данные всех сотрудников");
        employeeManager.displayAllEmployees();
    }
}
