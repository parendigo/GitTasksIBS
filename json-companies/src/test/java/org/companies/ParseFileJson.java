package org.companies;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseFileJson {
    public static void main(String[] args) {

        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("src/test/resources/test.json"));

            JSONObject jo = (JSONObject) obj;
            JSONArray companies = (JSONArray) jo.get("companies");

            // Первый таск: Краткое название - дата основания
            System.out.println("Первый таск Краткое название - дата основания:");
            Iterator iterator1 = companies.iterator();
            while (iterator1.hasNext()) {
                JSONObject innerObj = (JSONObject) iterator1.next();
                System.out.println(innerObj.get("name") + " - " + innerObj.get("founded"));
            }
            System.out.println();

            // Второй таск: код ценной бумаги с истекшей датой и имя организации владельца и подсчет кол-ва бумаг
            System.out.println("Второй таск: код ценной бумаги с истекшей датой и имя организации владельца:");
            Iterator iterator2 = companies.iterator();
            int count = 0;
            while (iterator2.hasNext()) {
                JSONObject innerObj = (JSONObject) iterator2.next();
                JSONArray securities = (JSONArray) innerObj.get("securities");
                Iterator i = securities.iterator();
                while (i.hasNext()) {
                    JSONObject iObj = (JSONObject) i.next();
                    if (checkDate((String) iObj.get("date")) == false) {
                        System.out.printf("Code: %-15s | date: %-15s | owner organization: %s\n",
                                iObj.get("code"), iObj.get("date"), innerObj.get("name"));
                        count++;
                    }
                }
            }
            System.out.println("Number of outdated securities is " + count + "\n");

            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите дату или код валюты. Type exit to finish the program.");

                String input = scanner.nextLine();
                Pattern patternDate = Pattern.compile("([0-9]{1,2}[.,/,,]{1}[0-9]{2}[.,/,,]{1}([0-9]{2}|[0-9]{4}))|");
                Matcher matcher = patternDate.matcher(input);
                if (input.equals("exit")) {
                    break;
                }

                // По запросу пользователя выводим название организации и ее дату основания, если дата позже даты инпута
                if (matcher.find()) {
                    System.out.println("По запросу пользователя выводим название организации и ее дату основания, " +
                            "если дата позже даты инпута:");
                    Iterator iterator3 = companies.iterator();
                    while (iterator3.hasNext()) {
                        JSONObject innerObj = (JSONObject) iterator3.next();
                        if (checkDate((String) innerObj.get("founded"), input) == true) {
                            System.out.println("Name: " + innerObj.get("name") + ", date: " + innerObj.get("founded"));
                        }
                    }
                }

                Pattern patternCode = Pattern.compile("[A-Z]{2,3}");
                matcher = patternCode.matcher(input);

                // По запросу пользователя выведем id компании е все коды ее ценных бумаг, содержащих указанную валюту
                if (matcher.find()) {
                    System.out.println("По запросу пользователя выведем id компании е все коды ее ценных бумаг, " +
                            "содержащих указанную валюту:");
                    Iterator iterator4 = companies.iterator();
                    while (iterator4.hasNext()) {
                        JSONObject innerObj = (JSONObject) iterator4.next();
                        System.out.println("Company ID: " + innerObj.get("id"));
                        JSONArray securities = (JSONArray) innerObj.get("securities");
                        Iterator i = securities.iterator();
                        while (i.hasNext()) {
                            JSONObject iObj = (JSONObject) i.next();
                            JSONArray currency = (JSONArray) iObj.get("currency");
                            for (Object cur : currency) {
                                if (input.equals(cur)) {
                                    System.out.printf("Security code: %s\n", iObj.get("code"));
                                }
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    // Сверяем дату с текущей
    public static boolean checkDate (String date) {
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate now = LocalDate.now();
        String parsedDate;
        if (date.charAt(1) == '.') {
            parsedDate = "0" + date;
        } else
            parsedDate = date;
        LocalDate checkDate = LocalDate.parse(parsedDate, europeanDateFormatter);
        return ChronoUnit.DAYS.between(now, checkDate) < 0 ? false : true;
    }

    // Сверяем даты для сверки с инпутом пользователя
    public static boolean checkDate (String founded, String in) {
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String parsedDate;
        if (founded.charAt(1) == '.') {
            parsedDate = "0" + founded;
        } else
            parsedDate = founded;
        LocalDate foundedDate = LocalDate.parse(parsedDate, europeanDateFormatter);
        in = in.replaceAll("[/, ,]", ".");
        if (in.length() < 10) {
            parsedDate = in.substring(0, 6) + "19" + in.substring(6, in.length());
        } else
            parsedDate = in;
        LocalDate inputDate = LocalDate.parse(parsedDate, europeanDateFormatter);
        return ChronoUnit.DAYS.between(inputDate, foundedDate) <= 0 ? false : true;
    }
}
