import java.util.HashMap;
import java.util.*;

public class Transporte {
    private Map<String, Parada> paradaMap;
    private Map<String, List<Ruta>> listaAdyacencia;

    public Transporte() {
        this.paradaMap = new HashMap<>();
        this.listaAdyacencia = new HashMap<>();
    }

    public void addParada(String id, String nombre) {
        if (!paradaMap.containsKey(id)) {
            Parada new_parada = new Parada(id, nombre);
            paradaMap.put(id, new_parada);
            listaAdyacencia.put(id, new ArrayList<>());
        }
    }

    public void editParada(String id, String nombre) {
        if (paradaMap.containsKey(id)) {
            Parada p = paradaMap.get(id);
            p.setNombre(nombre);
        } else {
            System.out.println("Error: La parada con ID " + id + " no existe.");
        }
    }
    public void deleteParada(String id) {
        if (paradaMap.containsKey(id)) {
            paradaMap.remove(id);
            listaAdyacencia.remove(id);
            for (List<Ruta> rutas : listaAdyacencia.values()) {
                rutas.removeIf(ruta -> ruta.getDestino().getId().equals(id));
            }
        }
    }

    public void addRuta(String id_Origin, String id_Destination, double tiempo, double distancia, double costo, boolean trasbordo) {
        if (paradaMap.containsKey(id_Origin) && paradaMap.containsKey(id_Destination)) {
            Parada destino = paradaMap.get(id_Destination);
            listaAdyacencia.get(id_Origin).add(new Ruta(destino, tiempo, distancia, costo, trasbordo));
        } else {
            System.err.println("No existe un origen o un destino mi rey/reina");

        }
    }



    public void deleteRuta(String id_Origin, String id_Destination) {
        if (listaAdyacencia.containsKey(id_Destination)) {
            List<Ruta> rutas = listaAdyacencia.get(id_Origin);
            rutas.removeIf(ruta -> ruta.getDestino().getId().equals(id_Destination));
        }
    }
    public void editRuta(String id_origin, String id_destination, double tiempo, double distancia, double costo, boolean trasbordo) {
        if (listaAdyacencia.containsKey(id_origin)) {
            List<Ruta> rutas = listaAdyacencia.get(id_origin);
            boolean encontrada = false;
            int i = 0;
            while (i < rutas.size() && !encontrada) {
                Ruta r = rutas.get(i);

                if (r.getDestino().getId().equals(id_destination)) {
                    r.setTiempoMinuto(tiempo);
                    r.setDistanciaKm(distancia);
                    r.setCosto(costo);
                    r.setRequiereTrasbordo(trasbordo);
                    encontrada = true;
                }
                i++;
            }

            if (!encontrada) {
                System.err.println("Error: La ruta de " + id_origin + " a " + id_destination + " no existe.");
            }
        } else {
            System.err.println("Error: El origen " + id_origin + " no existe en el sistema.");
        }
    }
}
