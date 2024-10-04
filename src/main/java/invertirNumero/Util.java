package invertirNumero;

public class Util {

    public String invertirNum(String numero){
        String inv = "";
        int num = Integer.parseInt(numero);
        for (int i = numero.length() - 1; i >= 0; i--) {
            inv += numero.charAt(i);
        }
        if (num > 1) {  
            boolean number = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    number = false;
                    break;
                }
            }
            if (number) {
                inv += "0";
            }
        }
        return inv;
    }
}
