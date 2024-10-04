package aerolineaStaticTest;

import aerolineaStatic.Aerolinea;
import aerolineaStatic.AerolineaServiceGlobal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class AerolineaTest {
    @Test
    public void testReservaVueloExitoso() {
        try (MockedStatic<AerolineaServiceGlobal> aerolineaService = Mockito.mockStatic(AerolineaServiceGlobal.class)) {
            aerolineaService.when(() -> AerolineaServiceGlobal.existenPasajes("La Paz", 2))
                    .thenReturn(true);
            aerolineaService.when(() -> AerolineaServiceGlobal.getDay(29, 5, 2023))
                    .thenReturn("Lunes");

            Aerolinea aerolinea = new Aerolinea();

            String resultado = aerolinea.reservaVuelo("La Paz", 2, 29, 5, 2023);

            Assertions.assertEquals("el dia Lunes 29 Mayo 2023 existen 2 pasajes para La Paz", resultado);
        }
    }

    @Test
    public void testReservaVueloSinPasajes() {
        try (MockedStatic<AerolineaServiceGlobal> aerolineaService = Mockito.mockStatic(AerolineaServiceGlobal.class)) {
            aerolineaService.when(() -> AerolineaServiceGlobal.existenPasajes("La Paz", 2))
                    .thenReturn(false);

            Aerolinea aerolinea = new Aerolinea();

            String resultado = aerolinea.reservaVuelo("La Paz", 2, 29, 5, 2023);

            Assertions.assertEquals("no existen suficientes pasajes para La Paz", resultado);
        }
    }
}
