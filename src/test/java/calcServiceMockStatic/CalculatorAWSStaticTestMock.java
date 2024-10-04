package calcServiceMockStatic;

import calcServiceStatic.CalcServiceStatic;
import calcServiceStatic.CalculatorAWS2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class CalculatorAWSStaticTestMock {


    @Test
    public void verifyFact(){
        //step2
        MockedStatic<CalcServiceStatic> mockedStatic = Mockito.mockStatic(CalcServiceStatic.class);
        // step3
        mockedStatic.when(()-> CalcServiceStatic.mult(1,1)).thenReturn(1);
        mockedStatic.when(()-> CalcServiceStatic.mult(1,2)).thenReturn(2);
        mockedStatic.when(()-> CalcServiceStatic.mult(2,3)).thenReturn(6);
        mockedStatic.when(()-> CalcServiceStatic.mult(6,4)).thenReturn(24);

        CalculatorAWS2 aws2 = new CalculatorAWS2();
        int actualResult = aws2.getFact(4);
        int expectedResult = 24;
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el factorial es incorrecto!");
        if (mockedStatic != null) {
            mockedStatic.close();  // Libera el mock estático
        }
    }
}
