import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] MONEDAS_VALIDAS = {"ARS", "BOB", "BRL", "CLP", "COP", "USD", "MXN"};

    public static void mostrarMenu() throws IOException, InterruptedException {
        while (true) {
            System.out.println("Bienvenido al conversor de monedas.");
            System.out.println("Monedas disponibles:");
            System.out.println("ARS - Peso Argentino");
            System.out.println("BOB - Boliviano");
            System.out.println("BRL - Real Brasileño");
            System.out.println("CLP - Peso Chileno");
            System.out.println("COP - Peso Colombiano");
            System.out.println("USD - Dólar Estadounidense");
            System.out.println("MXN - Peso Mexicano\n");

            // Solicita la moneda de origen
            System.out.print("Ingresa la moneda de origen (ejemplo: USD): ");
            String origen = scanner.nextLine().toUpperCase();
            if (!esMonedaValida(origen)) {
                System.out.println("Moneda origen inválida.\n");
                continue;
            }

            // Solicita la moneda destino
            System.out.print("Ingresa la moneda destino (ejemplo: ARS): ");
            String destino = scanner.nextLine().toUpperCase();
            if (!esMonedaValida(destino)) {
                System.out.println("Moneda destino inválida.\n");
                continue;
            }

            // Pide la cantidad a convertir
            System.out.print("Ingresa la cantidad a convertir: ");
            double cantidad;
            try {
                cantidad = Double.parseDouble(scanner.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Cantidad inválida.\n");
                continue;
            }

            // Obtiene la tasa desde la API
            double tasa = ConsumoApi.obtenerTasaCambio(origen, destino);
            if (tasa == -1) {
                System.out.println("Error al obtener la tasa de cambio.\n");
                continue;
            }

            double resultado = CalculadoraCambio.convertir(cantidad, tasa);
            System.out.printf("%.2f %s equivalen a %.2f %s%n%n", cantidad, origen, resultado, destino);

            System.out.print("¿Deseas hacer otra conversión? (s/n): ");
            String resp = scanner.nextLine().toLowerCase();
            if (!resp.equals("s")) break;
        }

        System.out.println("Gracias por usar el conversor.");
    }

    private static boolean esMonedaValida(String codigo) {
        for (String moneda : MONEDAS_VALIDAS) {
            if (moneda.equals(codigo)) {
                return true;
            }
        }
        return false;
    }
}
