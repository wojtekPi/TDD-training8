public class StringCalculator {

    private static final String EMPTY_STRING = "";

    public int Add(String numbers) throws Exception {
        if (numbers.equals(EMPTY_STRING)) {
            return 0;
        } else {
            String list[];
            Integer newList[] = null;
            list = numbers.split(",");
            int summary = 0;
            for (String i : list) {
                if(Integer.parseInt(i)<0){
                    throw new Exception("Negatives are not allowed!");
                }
                try {
                    summary += Integer.parseInt(i);
                }catch (Exception e){e.printStackTrace();}
            }
            return summary;
        }
    }
}

