package recuperatorio;

public class InvertirNumero {
    public int invertir(int numero) {
        int invertido = 0;
        while (numero != 0) {
            int digito = numero % 10;
            invertido = invertido * 10 + digito;
            numero /= 10;
        }
        return invertido;
    }
}
