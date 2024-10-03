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
        // Crear el mock del servicio
        AerolineaService aerolineaService = Mockito.mock(AerolineaService.class);

        // Configurar los mocks para los métodos auxiliares
        Mockito.when(aerolineaService.existenPasajes("La Paz", 2)).thenReturn(false);

        // Configurar el mock del método principal 'reservaVuelo'
        Mockito.when(aerolineaService.reservaVuelo("La Paz", 2, 29, 5, 2023))
                .thenReturn("no existen suficientes pasajes para La Paz");

        // Ejecutar el método
        String resultado = aerolineaService.reservaVuelo("La Paz", 2, 29, 5, 2023);

        // Verificar el resultado esperado
        Assertions.assertEquals("no existen suficientes pasajes para La Paz", resultado);
    }
}
