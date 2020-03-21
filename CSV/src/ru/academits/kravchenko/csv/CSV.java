package ru.academits.kravchenko.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

// "CSV/CSV.csv" name
// "CSV/tableCSV.HTML" filename

public class CSV {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            throw new ArrayIndexOutOfBoundsException("2 parameters required");
        }

        try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {
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

                boolean isCellWithQuotes = false;
                boolean isCellWithoutQuotes = false;

                for (int i = 0; i < line.length(); i++) {
                    char symbol = line.charAt(i);

                    if (!isCellWithQuotes && !isCellWithoutQuotes) {      // напечатали начало ячейки, установили флажок
                        if (symbol == '"') {
                            isCellWithQuotes = true;

                            writer.println("<td>");
                        } else {
                            isCellWithoutQuotes = true;

                            writer.println("<td>");
                            writer.print(symbol);
                        }

                        continue;
                    }

                    if (isCellWithoutQuotes && symbol == ',') {                       // печать конца ячейки без кавычек
                        writer.println("</td>");

                        isCellWithoutQuotes = false;

                        if (i == line.length() - 1) {
                            writer.println("<td>");
                            writer.println("</td>");
                        }

                        continue;
                    }

                    if (isCellWithQuotes && symbol == '"') {          // печать конца ячейки с кавычками и спец.символов
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

                        isCellWithQuotes = false;
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

                    if (isCellWithQuotes && i == line.length() - 1) {
                        writer.println("<br/>");
                        line = scanner.nextLine();
                        i = -1;
                    }
                }

                writer.println("</tr>");
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
