import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    static boolean isRoman;
    static char operator;
    static int number1;
    static int number2;
    static String resultString;
    static int result;

    public static void main(String[] args) {
        System.out.println("Введите арифметическую операцию с двумя операндами в строчку через пробел, используя числа от 1 до 10 (I до X) включительно (Напримрер: 1 + 2)");

        Scanner scanner = new Scanner(System.in);
        String readFromConsole = scanner.nextLine();
        System.out.println(calc(readFromConsole));
    }

    public static String calc(String input) {
        Converter converter = new Converter();
        String[] operands = input.split(" ");

        if (operands.length == 3) {
            if (numberMatchesCondition(operands[0]) & numberMatchesCondition(operands[2])) {
                if (isArabic(operands[0]) & isArabic(operands[2])) {
                    number1 = parseInt(operands[0]);
                    number2 = parseInt(operands[2]);
                    operator = getOperator(operands[1]);
                    isRoman = false;
                }

                if (isRoman(operands[0]) & isRoman(operands[2])) {
                    number1 = converter.RomanToArabic(operands[0]);
                    number2 = converter.RomanToArabic(operands[2]);
                    operator = getOperator(operands[1]);
                    isRoman = true;
                }
            } else {
                throw new NumberFormatException("Вы должны ввести числа от 1 до 10 (I до X) включительно, не более.");
            }
            if ((isArabic(operands[0]) & isRoman(operands[2])) | (isRoman(operands[0]) & isArabic(operands[2]))) {
                throw new NumberFormatException("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно");
            }
        } else if ((operands.length % 2 != 0) & (operands.length > 3)) {
            throw new IllegalArgumentException("формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        } else {
            throw new IllegalArgumentException("строка не является математической операцией");
        }

        switch (operator) {
            case '+' -> result = number1 + number2;
            case '-' -> result = number1 - number2;
            case '/' -> result = number1 / number2;
            case '*' -> result = number1 * number2;
            default -> throw new IllegalArgumentException("Укажите один из перечисленных операторов (+, -, /, *). Ваш оператор: " + operator);
        }
        if (isRoman) {
            if (result > 0) {
                return resultString = converter.arabicToRoman(result);
            } else {
                throw new IllegalArgumentException("В системе римских цифр отсутствует ноль и отрицательные числа");
            }

        } else {
            return resultString = Integer.toString(result);
        }
    }

    static boolean numberMatchesCondition(String string) {
        return (string.matches("[1-9]|10")) | (string.matches("I|II|III|IV|V|VI|VII|VIII|IX|X"));
    }

    static boolean isArabic(String string) {
        return string.matches("[1-9]|10");
    }

    static boolean isRoman(String string) {
        return string.matches("I|II|III|IV|V|VI|VII|VIII|IX|X");
    }

    static char getOperator(String string) {
        char[] charArray = string.toCharArray();
        return charArray[0];
    }

    static class Converter {

        String arabicToRoman(int number) {
            String romanNumber = "";
            String[] onesArray = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
            String[] tensArray = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
            String[] hundredsArray = {"C"};

            int ones = number % 10;
            number = (number - ones) / 10;

            int tens = number % 10;
            number = (number - tens) / 10;

            int hundreds = number % 10;

            if (hundreds > 0) {
                romanNumber = romanNumber + hundredsArray[hundreds - 1];
            }
            if (tens > 0) {
                romanNumber = romanNumber + tensArray[tens - 1];
            }
            if (ones > 0) {
                romanNumber = romanNumber + onesArray[ones - 1];
            }
            return romanNumber;
        }

        public int RomanToArabic(String romanNumber) {
            int number = switch (romanNumber) {
                case "I" -> 1;
                case "II" -> 2;
                case "III" -> 3;
                case "IV" -> 4;
                case "V" -> 5;
                case "VI" -> 6;
                case "VII" -> 7;
                case "VIII" -> 8;
                case "IX" -> 9;
                case "X" -> 10;
                default -> 0;
            };
            return number;
        }
    }

}
