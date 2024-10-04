package aerolineaStatic;

public class Aerolinea {
    public String reservaVuelo(String destino, int numeroPasajes, int dia, int mes, int gestion) {

        boolean existen = AerolineaServiceGlobal.existenPasajes(destino, numeroPasajes);

        if (!existen) {
            return "no existen suficientes pasajes para " + destino;
        }

        String diaSemana = AerolineaServiceGlobal.getDay(dia, mes, gestion);

        String nombreMes = convertirMes(mes);


        return "el dia " + diaSemana + " " + dia + " " + nombreMes + " " +
                gestion + " existen " + numeroPasajes + " pasajes para " + destino;
    }

    private String convertirMes(int mes) {
        switch (mes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                throw new IllegalArgumentException("Mes inválido: " + mes);
        }
    }
}