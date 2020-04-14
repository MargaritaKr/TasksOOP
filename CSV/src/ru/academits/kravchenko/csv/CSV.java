package ru.academits.kravchenko.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

// "CSV/CSV.csv" "CSV/tableCSV.HTML" - аргументы

public class CSV {
    public static void main(String[] args) {
        String csvFile;
        String htmlFile;

        if (args.length == 2){
           csvFile = args[0];
           htmlFile = args[1];
        } else {
           System.out.println("Необходимо ввести пути к файлам.");

           Scanner scanner = new Scanner(System.in);

           System.out.println("Введите путь к файлу \".csv\" для обработки: ");
           csvFile = scanner.nextLine();

           System.out.println("Введите путь к файлу \".html\" для сохранения результата: ");
           htmlFile = scanner.nextLine();
        }

        try (Scanner scanner = new Scanner(new FileInputStream(csvFile));
             PrintWriter writer = new PrintWriter(htmlFile)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("<title>Таблица CSV</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table border=\"1\">");

            while (scanner.hasNextLine()) {
                writer.println("<tr>");

                String line = scanner.nextLine();

                if (line.isEmpty()) {
                    continue;
                }

                boolean startCellWithQuotes = false;
                boolean startCellWithoutQuotes = false;

                for (int i = 0; i < line.length(); i++) {
                    char symbol = line.charAt(i);

                    if (!startCellWithQuotes && !startCellWithoutQuotes) {      // напечатали начало ячейки, установили флажок
                        if (symbol == '"') {
                            startCellWithQuotes = true;

                            writer.println("<td>");

                            continue;
                        } else if (symbol != ',') {
                            startCellWithoutQuotes = true;

                            writer.println("<td>");
                            writer.print(symbol);

                            if (i == line.length() - 1) {
                                writer.println("</td>");
                            }

                            continue;
                        }

                        startCellWithoutQuotes = true;

                        writer.println("<td>");
                    }

                    if (startCellWithoutQuotes && symbol == ',') {                       // печать конца ячейки без кавычек
                        writer.println("</td>");

                        startCellWithoutQuotes = false;

                        if (i == line.length() - 1) {
                            writer.println("<td>");
                            writer.println("</td>");
                        }

                        continue;
                    }

                    if (startCellWithQuotes && symbol == '"') {          // печать конца ячейки с кавычками и спец.символами
                        if (i == line.length() - 1) {
                            writer.println("</td>");
                            break;
                        } else if (line.charAt(i + 1) == '"') {
                            writer.print('"');
                            i++;
                            continue;
                        }

                        writer.println("</td>");

                        if (i == line.length() - 2) {
                            writer.println("<td>");
                            writer.println("</td>");
                        }

                        startCellWithQuotes = false;
                        i++;
                        continue;
                    }

                    if (symbol == '<') {
                        writer.print("&lt;");
                    } else if (symbol == '>') {
                        writer.print("&gt;");
                    } else if (symbol == '&') {
                        writer.print("&amp;");
                    } else {
                        writer.print(symbol);
                    }

                    if (startCellWithQuotes && i == line.length() - 1) {
                        writer.println("<br/>");
                        line = scanner.nextLine();
                        i = -1;
                    }

                    if (startCellWithoutQuotes && i == line.length() - 1) {
                        writer.println("</td>");
                    }
                }

                writer.println("</tr>");
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
}