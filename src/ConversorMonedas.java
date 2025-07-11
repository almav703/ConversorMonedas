public class ConversorMonedas {
    public static void main(String[] args) {
        try {
            Menu.mostrarMenu();  // Inicia la aplicación mostrando el menú principal
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
