package recuperatorioTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recuperatorio.InvertirNumero;

public class InvertirNumeroTest {

    @Test
    public void testInvertir() {
        InvertirNumero invertirNumero = new InvertirNumero();

        Assertions.assertEquals(321, invertirNumero.invertir(123));
        Assertions.assertEquals(-654, invertirNumero.invertir(-456));
        Assertions.assertEquals(1, invertirNumero.invertir(1000));
        Assertions.assertEquals(0, invertirNumero.invertir(0));
        Assertions.assertEquals(987654321, invertirNumero.invertir(123456789));
    }
}