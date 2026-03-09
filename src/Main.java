import java.util.List;

public class Main {
    public static void main(String[] args) {
        Transporte t = new Transporte();
        t.cargarDatosPrueba();

        List<Parada> rutaTiempo = t.dijkstra("A", "F", "tiempo");
        for (Parada p : rutaTiempo) {
            System.out.print(p.getNombre() + " -> ");
        }

        System.out.println();

        List<Parada> rutaDistancia = t.dijkstra("A", "F", "distancia");
        for (Parada p : rutaDistancia) {
            System.out.print(p.getNombre() + " -> ");
        }

        System.out.println();

        List<Parada> rutaCosto = t.dijkstra("A", "F", "costo");
        for (Parada p : rutaCosto) {
            System.out.print(p.getNombre() + " -> ");
        }
    }
}

