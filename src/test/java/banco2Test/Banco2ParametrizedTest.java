package banco2Test;

import bancoStatic.AfpServiceGlobal;
import bancoStatic.AsfiServiceGlobal;
import bancoStatic.BancoUPB2;
import bancoStatic.SegipServiceGlobal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Banco2ParametrizedTest {

    private MockedStatic<SegipServiceGlobal> segip;
    private MockedStatic<AsfiServiceGlobal> asfi;
    private MockedStatic<AfpServiceGlobal> afp;

    @ParameterizedTest
    @CsvSource({
            "888999, true, true, 1000, 3000, 'se le puede realizar el prestamo: 3000'",
            "888999, false, true, 1000, 3000, 'debe revisar su carnet de identidad'",
            "888999, true, false, 1000, 3000, 'usted no esta habilitado para prestamos'",
            "888999, true, true, 1000, 6000, 'se le puede realizar el prestamo: 3000'"
    })
    public void verifyLoanScenarios(int ci, boolean isRealPerson, boolean isAbleToGetCredit, int afpAmount, int requestedAmount, String expectedMessage) {
        segip = Mockito.mockStatic(SegipServiceGlobal.class);
        asfi = Mockito.mockStatic(AsfiServiceGlobal.class);
        afp = Mockito.mockStatic(AfpServiceGlobal.class);

        segip.when(() -> SegipServiceGlobal.isRealPerson(ci)).thenReturn(isRealPerson);
        asfi.when(() -> AsfiServiceGlobal.isAbleToGetCredit(ci)).thenReturn(isAbleToGetCredit);
        afp.when(() -> AfpServiceGlobal.getAmount(ci)).thenReturn(afpAmount);

        BancoUPB2 bancoUPB = new BancoUPB2();

        Assertions.assertEquals(expectedMessage,
                bancoUPB.getAmountMoney(ci, requestedAmount),
                "ERROR el resultado del prestamo es incorrecto");
    }

    @AfterEach
    public void afterEach() {
        if (segip != null) {
            segip.close();
        }
        if (asfi != null) {
            asfi.close();
        }
        if (afp != null) {
            afp.close();
        }
    }
}
