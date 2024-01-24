import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String yesNo;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Введите следующие данные: Фамилия, Имя, Отчество, Дата рождения, Номер телефона, Пол");
                System.out.println("Формат ввода: ФИО - латинские буквы, Дата - dd.mm.yyyy, Номер телефона - 1234567890, Пол - m, f");
                System.out.print("Введите данные через пробел: ");

                String input = scanner.nextLine();

                String[] data = input.split(" ");

                if (data.length != 6) {
                    System.out.println("Неверное количество данных");
                    break;
                }

                String lastName = data[0];
                String firstName = data[1];
                String secondName = data[2];
                String birthDay = data[3];
                String phoneNumber = data[4];
                String gender = data[5];

                if (!lastName.matches("[A-Za-z]+") || !firstName.matches("[A-Za-z]+") || !secondName.matches("[A-Za-z]+")) {
                    System.out.println("Фамилия, Имя и Отчество должны содержать только латинские буквы");
                    break;
                }

                try {
                    LocalDate date = LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                } catch (DateTimeParseException e) {
                    System.out.println("Неверный формат даты рождения");
                    break;
                }

                try {
                    Long.parseLong(phoneNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Номер телефона должен быть целым числом");
                    break;
                }

                if (!gender.equals("f") && !gender.equals("m")) {
                    System.out.println("Пол должен быть указан символом m или f");
                    break;
                }

                String personalData = "<" + lastName + ">" + "<" + firstName + ">" +
                                    "<" + secondName + ">" + "<" + birthDay + ">" +
                                    "<" + phoneNumber + ">" + "<" + gender + ">";

                String fileName = lastName + ".txt";
                try (FileWriter writer = new FileWriter(fileName, true)) {
                    writer.write(personalData + System.lineSeparator());
                    System.out.println("Данные записаны в файл " + fileName);
                } catch (IOException e) {
                    System.out.println("Ошибка при записи данных в файл");
                    e.printStackTrace();
                }
                break;
            }
            System.out.print("\nПродолжить запись? (y/n): ");
            yesNo = scanner.next();
            if (yesNo.equals("n")) {
                break;
            }
        }
    }
}