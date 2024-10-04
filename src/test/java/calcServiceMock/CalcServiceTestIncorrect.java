package calcServiceMock;

import calcService.CalculatorAWS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CalcServiceTestIncorrect {

    CalculatorAWS calculatorAWS;

    @Disabled
    @Test
    public void verifyFact(){
        calculatorAWS = new CalculatorAWS();
        int expectedResult = 6;
        int actualResult =calculatorAWS.getFact(3);
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el factorial es incorrecto");

    }
}
