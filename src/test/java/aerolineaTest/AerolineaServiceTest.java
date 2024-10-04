package aerolineaTest;

import aerolinea.AerolineaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.junit.jupiter.api.Assertions;

public class AerolineaServiceTest {
    @Test
    public void testReservaVuelo_ExistenPasajes() {
        AerolineaService aerolineaService = Mockito.mock(AerolineaService.class);

        Mockito.when(aerolineaService.existenPasajes("La Paz", 2)).thenReturn(true);
        Mockito.when(aerolineaService.getDay(29, 5, 2023)).thenReturn("Lunes");

        Mockito.when(aerolineaService.reservaVuelo("La Paz", 2, 29, 5, 2023))
                .thenReturn("el dia Lunes 29 Mayo 2023 existen 2 pasajes para La Paz");

        String resultado = aerolineaService.reservaVuelo("La Paz", 2, 29, 5, 2023);

        Assertions.assertEquals("el dia Lunes 29 Mayo 2023 existen 2 pasajes para La Paz", resultado);
    }

    @Test
    public void testReservaVuelo_NoExistenPasajes() {
        AerolineaService aerolineaService = Mockito.mock(AerolineaService.class);

        Mockito.when(aerolineaService.existenPasajes("La Paz", 2)).thenReturn(false);

        Mockito.when(aerolineaService.reservaVuelo("La Paz", 2, 29, 5, 2023))
                .thenReturn("no existen suficientes pasajes para La Paz");

        String resultado = aerolineaService.reservaVuelo("La Paz", 2, 29, 5, 2023);

        Assertions.assertEquals("no existen suficientes pasajes para La Paz", resultado);
    }
}
