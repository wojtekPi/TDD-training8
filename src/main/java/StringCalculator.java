public class StringCalculator {

    public int add(String numbers) throws  NumberFormatException {
        int a =0;
        int b =0;


        try {
            if (numbers.matches("\\d*")) {
                a= Integer.parseInt(numbers);
                return a+b;
            } else if(numbers.matches("\\d*,\\d*")) {
                String[] table = numbers.trim().split(",");
                a = Integer.parseInt(table[0]);
                b = Integer.parseInt(table[1]);
                return a+b;
            } else if (numbers.matches("\\d*,-\\d*")) {
                String[] table = numbers.trim().split(",");
                a = Integer.parseInt(table[0]);
                b = Integer.parseInt(table[1]);
                return a+b;
            } else if (numbers.matches("-\\d*,\\d*")) {
                String[] table = numbers.trim().split(",");
                a = Integer.parseInt(table[0]);
                b = Integer.parseInt(table[1]);
                return a+b;
            } else if (numbers.matches("-\\d*,-\\d*")) {
                String[] table = numbers.trim().split(",");
                a = Integer.parseInt(table[0]);
                b = Integer.parseInt(table[1]);
                return a+b;
            } else if( numbers.matches("[0-9, \\-]*")) {
                String[] table = numbers.trim().split(",");
                Integer sum = 0;
                for (String s : table) {
                    sum = sum + Integer.parseInt(s);
                }
                return sum;
            }
            else
                return 0;
        } catch (NumberFormatException e) {
            return a+b;
        }
    }
}
