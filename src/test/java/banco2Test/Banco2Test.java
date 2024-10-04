package banco2Test;

import bancoStatic.AfpServiceGlobal;
import bancoStatic.AsfiServiceGlobal;
import bancoStatic.BancoUPB2;
import bancoStatic.SegipServiceGlobal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


public class Banco2Test {

    MockedStatic<SegipServiceGlobal> segip = Mockito.mockStatic(SegipServiceGlobal.class);
    MockedStatic<AsfiServiceGlobal> asfi = Mockito.mockStatic(AsfiServiceGlobal.class);
    MockedStatic<AfpServiceGlobal> afp = Mockito.mockStatic(AfpServiceGlobal.class);

    @Test
    public void verifyTheUserIsAble(){
        segip.when(() -> SegipServiceGlobal.isRealPerson(888999)) .thenReturn(true);
        asfi.when(() ->AsfiServiceGlobal.isAbleToGetCredit(888999)).thenReturn(true);
        afp.when(() -> AfpServiceGlobal.getAmount(888999)).thenReturn(1000);
        BancoUPB2 bancoUPB = new BancoUPB2();
        Assertions.assertEquals("se le puede realizar el prestamo: 3000",
                bancoUPB.getAmountMoney(888999,3000),
                "ERROR el prestamo es incorrecto");
    }

    @Test
    public void verifyTheUserIsNotAbleToRequest(){
        segip.when(() -> SegipServiceGlobal.isRealPerson(888999)) .thenReturn(false);
        asfi.when(() ->AsfiServiceGlobal.isAbleToGetCredit(888999)).thenReturn(true);
        afp.when(() -> AfpServiceGlobal.getAmount(888999)).thenReturn(1000);
        BancoUPB2 bancoUPB = new BancoUPB2();
        Assertions.assertEquals("debe revisar su carnet de identidad",
                bancoUPB.getAmountMoney(888999,3000),
                "ERROR el prestamo es incorrecto");

    }

    @Test
    public void verifyTheUserIsNotAbleAsfi(){
        segip.when(() -> SegipServiceGlobal.isRealPerson(888999)) .thenReturn(true);
        asfi.when(() ->AsfiServiceGlobal.isAbleToGetCredit(888999)).thenReturn(false);
        afp.when(() -> AfpServiceGlobal.getAmount(888999)).thenReturn(1000);
        BancoUPB2 bancoUPB = new BancoUPB2();

        Assertions.assertEquals("usted no esta habilitado para prestamos",
                bancoUPB.getAmountMoney(888999,3000),
                "ERROR el prestamo es incorrecto");


    }
    @AfterEach
    public void after(){
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