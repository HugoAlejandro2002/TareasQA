package calcServiceStatic;

public class CalculatorAWS2 {
    public CalculatorAWS2(){

    }

    public int getFact(int number){
      int f=1;
        for (int i = 1; i <= number; i++) {
            f = CalcServiceStatic.mult(f,i);
        }
       return f;
    }
}

