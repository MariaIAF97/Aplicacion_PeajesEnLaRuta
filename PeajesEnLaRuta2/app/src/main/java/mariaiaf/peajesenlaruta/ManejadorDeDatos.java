package mariaiaf.peajesenlaruta;

import java.util.ArrayList;

/**
 * Created by MIAF on 5/05/2017.
 */

public class ManejadorDeDatos extends Object{

    public static ArrayList<Integer>id=new ArrayList<>();
    public static ArrayList<String> nombre = new ArrayList<>();
    public static ArrayList<Float> latitud = new ArrayList<>();
    public static ArrayList<Float> longitud = new ArrayList<>();
    public static ArrayList<String> informacion = new ArrayList<>();
    public static ArrayList<String> valorCategoria = new ArrayList<>();



    private static final ManejadorDeDatos INSTANCE = new ManejadorDeDatos();

    //====================================================
    // CONSTRUCTOR
    //====================================================

    public ManejadorDeDatos() {
    }

    //====================================================
    // GETTERS & SETTERS
    //====================================================
    public static ManejadorDeDatos getInstance(){
        return INSTANCE;
    }

    public static ArrayList<Integer> Obtenerid() {
        return id;
    }
    public static ArrayList<String> ObtenerListaNombres() {
        return nombre;
    }

    public static ArrayList<Float> ObtenerListaLatitud() {
        return latitud;
    }
    public static ArrayList<Float> ObtenerListaLongitud() {
        return longitud;
    }
    public static ArrayList<String> ObtenerListaInformacion() {
        return informacion;
    }
    public static ArrayList<String> ObtenerListaValorCategoria() {
        return valorCategoria;

    }

    public void SetearNombre(String nombres) {
        nombre.add(nombres);
    }
    public void SetearId(Integer numeroID) {
        id.add(numeroID);
    }
    public void SetearCoordenadas(Float coordLatitud, Float coordLongitud) {
        latitud.add(coordLatitud);
        longitud.add(coordLongitud);
    }
    public void Setearinformacion(String info, String valCategoriaI,String valCategoriaII,String valCategoriaIII,String valCategoriaIV,
                                  String valCategoriaV,String valCategoriaVI,String valCategoriaVII,String valCategoriaVIII) {

        informacion.add(info);
        valorCategoria.add("Valor categoría 1 "+valCategoriaI);
        valorCategoria.add("Valor categoría 2 "+valCategoriaII);
        valorCategoria.add("Valor categoría 3 "+valCategoriaIII);
        valorCategoria.add("Valor categoría 4 "+valCategoriaIV);
        valorCategoria.add("Valor categoría 5 "+valCategoriaV);
        valorCategoria.add("Valor categoría 6 "+valCategoriaVI);
        valorCategoria.add("Valor categoría 7 "+valCategoriaVII);
        valorCategoria.add("Valor categoría 8 "+valCategoriaVIII);

    }


}


