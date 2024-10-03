package bancoTest;

import banco.AfpService;
import banco.AsfiService;
import banco.BancoUPB;
import banco.SegipService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

public class BancoParametizedTest {
    AfpService afpService = Mockito.mock(AfpService.class);
    SegipService segipService = Mockito.mock(SegipService.class);
    AsfiService asfiService = Mockito.mock(AsfiService.class);

    @ParameterizedTest
    @CsvSource({
            "888999, true, true, 1000, 3000, 'se le puede realizar el prestamo: 3000'",
            "8889998, false, true, 0, 3000, 'debe revisar su carnet de identidad'",
            "888999, true, false, 1000, 3000, 'usted no esta habilitado para prestamos'"
    })
    public void verifyLoanConditions(int userId, boolean isRealPerson, boolean isAbleToGetCredit, int afpAmount, int requestedLoan, String expectedMessage) {

        Mockito.when(segipService.isRealPerson(userId)).thenReturn(isRealPerson);
        Mockito.when(asfiService.isAbleToGetCredit(userId)).thenReturn(isAbleToGetCredit);
        Mockito.when(afpService.getAmount(userId)).thenReturn(afpAmount);

        BancoUPB bancoUPB = new BancoUPB();
        bancoUPB.setAsfiService(asfiService);
        bancoUPB.setAfpService(afpService);
        bancoUPB.setSegipService(segipService);

        Assertions.assertEquals(expectedMessage,
                bancoUPB.getAmountMoney(userId, requestedLoan),
                "ERROR el prestamo es incorrecto");

        Mockito.verify(segipService).isRealPerson(userId);
        if (isRealPerson) {
            Mockito.verify(asfiService).isAbleToGetCredit(userId);
            if (isAbleToGetCredit) {
                Mockito.verify(afpService).getAmount(userId);
            }
        }
    }
}
