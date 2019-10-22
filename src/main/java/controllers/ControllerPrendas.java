package controllers;

import cron.RepositorioGuardarropas;
import cron.RepositorioUsuarios;
import domain.guardarropas.Guardarropas;
import domain.prenda.*;
import domain.usuario.Usuario;
import exceptions.NoExisteGuardarropasException;
import exceptions.UsuarioInexistente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;

public class ControllerPrendas {


    private HashMap<String, TipoDePrenda> tiposDePrenda = new HashMap<String, TipoDePrenda>() {{
        put("Sin accesorio", TipoDePrenda.SIN_ACCESORIO);
        put("Zapato", TipoDePrenda.ZAPATO);
        put("Remera", TipoDePrenda.REMERA);
        put("Camisa", TipoDePrenda.CAMISA);
        put("Pantalon", TipoDePrenda.PANTALON);
        put("Short", TipoDePrenda.SHORT);
        put("Blusa", TipoDePrenda.BLUSA);
        put("Zapatillas", TipoDePrenda.ZAPATILLA);
        put("Pollera", TipoDePrenda.POLLERA);
        put("Ojotas", TipoDePrenda.OJOTAS);
        put("Alpargatas", TipoDePrenda.ALPARGATAS);
        put("Pulsera", TipoDePrenda.PULSERA);
        put("Anteojos", TipoDePrenda.ANTEOJOS);
        put("Buso", TipoDePrenda.BUSO);
        put("Sweater", TipoDePrenda.SWEATER);
        put("Campera", TipoDePrenda.CAMPERA);
    }};

    private HashMap<String, Material> materialesDePrenda = new HashMap<String, Material>() {{
        put("Ninguno", Material.NINGUNO);
        put("Algodon", Material.ALGODON);
        put("Seda", Material.SEDA);
        put("Lino", Material.LINO);
        put("Cuero", Material.CUERO);
        put("Lana", Material.LANA);
        put("Licra", Material.LICRA);
        put("Poliester", Material.POLIESTER);
        put("Jean", Material.JEAN);
        put("Gamuza", Material.GAMUZA);
        put("Goma", Material.GOMA);
        put("Plastico", Material.PLASTICO);
        put("Pluma", Material.PLUMA);
        put("Gabardina", Material.GABARDINA);
    }};

    private HashMap<String, Trama> tramasDePrenda = new HashMap<String, Trama>() {{
        put("Lisa", Trama.LISA);
        put("Rayada", Trama.RAYADA);
        put("Cuadros", Trama.CUADROS);
        put("Gastado", Trama.GASTADO);
        put("Escocesa", Trama.ESCOCESA);
    }};

    public ModelAndView mostrarPrendas(Request req, Response res) {
        String nombreGuardarropas = req.params(":nombre");
        Guardarropas guardarropas;
        try{
            guardarropas = RepositorioGuardarropas.getInstance().buscarGuardarropas(nombreGuardarropas);

        }catch (NoExisteGuardarropasException e){
            return new ModelAndView(null, "forbidden.hbs");
        }

        return new ModelAndView(guardarropas, "prendas.hbs");
    }

    public ModelAndView creacionPrenda(Request req, Response res){
        String nombreGuardarropas = req.params(":nombre");
        Guardarropas guardarropas;
        try{
            guardarropas = RepositorioGuardarropas.getInstance().buscarGuardarropas(nombreGuardarropas);

        }catch (NoExisteGuardarropasException e){
            return new ModelAndView(null, "forbidden.hbs");
        }
        return new ModelAndView(guardarropas, "crearPrenda.hbs");
    }

    public ModelAndView crearPrenda(Request req, Response res) throws IOException {
        String nombreGuardarropas = req.params(":nombre");
        Guardarropas guardarropas;
        try{
            guardarropas = RepositorioGuardarropas.getInstance().buscarGuardarropas(nombreGuardarropas);

        }catch (NoExisteGuardarropasException e){
            return new ModelAndView(null, "forbidden.hbs");
        }

        BorradorPrenda borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirNombre(
                req.queryParams("query_nombre")
        );

        borradorPrenda.definirTipo(tiposDePrenda.get(req.queryParams("query_tipoPrenda")));
        borradorPrenda.definirMaterial(materialesDePrenda.get(req.queryParams("query_material")));
        borradorPrenda.definirColorPrimario(new Color(Integer.parseInt(req.queryParams("query_colorp_r")), Integer.parseInt(req.queryParams("query_colorp_g")), Integer.parseInt(req.queryParams("query_colorp_b"))));
        borradorPrenda.definirColorSecundario(new Color(Integer.parseInt(req.queryParams("query_colors_r")), Integer.parseInt(req.queryParams("query_colors_g")), Integer.parseInt(req.queryParams("query_colors_b"))));
        borradorPrenda.definirTrama(tramasDePrenda.get(req.queryParams("query_trama")));
        borradorPrenda.definirImagen(req.queryParams("query_imagen"));
        Prenda prenda = borradorPrenda.crearPrenda();
        guardarropas.agregarPrenda(prenda);
        return new ModelAndView(guardarropas, "prendas.hbs");
    }
}
