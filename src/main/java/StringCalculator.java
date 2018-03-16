public class StringCalculator {

    public int Add(String numbers) {
        String j = "";
        if (numbers.equals(j)) {
            return 0;
        } else {
            String list[] = null;
            Integer newList[] = null;
            list = numbers.split(",");
            int summary = 0;
            for (String i : list) {
                summary += Integer.parseInt(i);
            }
            return summary;
        }
    }
}

