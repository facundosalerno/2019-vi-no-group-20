package controllers;

import cron.RepositorioGuardarropas;
import cron.RepositorioUsuarios;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import domain.usuario.*;
import exceptions.ContraseñaInvalidaException;
import exceptions.UsuarioInexistente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;


public class ControllerSesion {

    private String mensajeSesion = "Nunca compartas tu contraseña con nadie.";



    public ModelAndView mostrarLogin(Request req, Response res) {
        return new ModelAndView(new ControllerSesion(),"login.hbs");  //Indica con que se va a renderizar el template (.hbs)
    }



    public ModelAndView iniciarSesion(Request req, Response res){
        crearUsuarioEjemplo();
        Usuario usuario;

        try{
            usuario = RepositorioUsuarios.getInstance().buscarUsuario(req.queryParams("query_username"));
            usuario.validarContraseña(req.queryParams("query_password"));
        }catch(UsuarioInexistente e){
            mensajeSesion = "Usuario inexistente o contraseña invalida.";
            return new ModelAndView(this, "login.hbs");
        }catch (ContraseñaInvalidaException e){
            mensajeSesion = "Usuario inexistente o contraseña invalida.";
            return new ModelAndView(this, "login.hbs");
        }

        res.cookie("cookie_nombre", usuario.getNombre());

        res.redirect("/perfil");
        return new ModelAndView(null, "perfil.hbs");
    }



    public ModelAndView cerrarSesion(Request req, Response res){
        res.removeCookie("cookie_nombre");
        res.removeCookie("cookie_nombreGuardarropas");
        res.redirect("/login");
        return new ModelAndView(new ControllerSesion(), "login.hbs");
    }



    public String getMensajeSesion(){
        return mensajeSesion;
    }



    public void crearUsuarioEjemplo(){
        GuardarropasPremium guardarropasDelAdmin = new GuardarropasPremium(
                "guardarropas principal",
                Arrays.asList(armarUnaPrenda("remera", TipoDePrenda.REMERA, Material.ALGODON, new Color(0, 0, 255), new Color(255, 0, 0), Trama.CUADROS), armarUnaPrenda("buso", TipoDePrenda.BUSO, Material.ALGODON, new Color(0, 255, 0), new Color(0, 0, 255), Trama.LISA)),
                Arrays.asList(armarUnaPrenda("pantalon", TipoDePrenda.PANTALON, Material.JEAN, new Color(0, 255, 0), new Color(255, 0, 0), Trama.RAYADA)),
                Arrays.asList(armarUnaPrenda("zapatos", TipoDePrenda.ZAPATO, Material.CUERO, new Color(255, 0, 0), new Color(0, 0, 255), Trama.GASTADO)),
                Arrays.asList(armarUnaPrenda("anteojos", TipoDePrenda.ANTEOJOS, Material.PLASTICO, new Color(0, 255, 0), new Color(255, 0, 0), Trama.LISA))
        );

        GuardarropasPremium guardarropasDelAdminAuxiliar = new GuardarropasPremium(
                "guardarropas de emergencia",
                Arrays.asList(armarUnaPrenda("remera", TipoDePrenda.REMERA, Material.ALGODON, new Color(0, 0, 255), new Color(255, 0, 0), Trama.CUADROS), armarUnaPrenda("buso", TipoDePrenda.BUSO, Material.ALGODON, new Color(0, 255, 0), new Color(0, 0, 255), Trama.LISA)),
                Arrays.asList(armarUnaPrenda("pantalon", TipoDePrenda.PANTALON, Material.JEAN, new Color(0, 255, 0), new Color(255, 0, 0), Trama.RAYADA)),
                Arrays.asList(armarUnaPrenda("zapatos", TipoDePrenda.ZAPATO, Material.CUERO, new Color(255, 0, 0), new Color(0, 0, 255), Trama.GASTADO)),
                Arrays.asList(armarUnaPrenda("anteojos", TipoDePrenda.ANTEOJOS, Material.PLASTICO, new Color(0, 255, 0), new Color(255, 0, 0), Trama.LISA))
        );

        Usuario admin = new Usuario("admin", Arrays.asList(guardarropasDelAdmin, guardarropasDelAdminAuxiliar), TipoDeUsuario.PREMIUM);
        admin.setPassword("12345");
    }



    public Prenda armarUnaPrenda(String nombre, TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama){
        BorradorPrenda borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirNombre(nombre);
        borradorPrenda.definirTipo(tipoDePrenda);
        borradorPrenda.definirMaterial(material);
        borradorPrenda.definirColorPrimario(colorPrimario);
        borradorPrenda.definirColorSecundario(colorSecundario);
        borradorPrenda.definirTrama(trama);
        //borradorPrenda.definirImagen("/public/remera.png");
        return borradorPrenda.crearPrenda();
    }
}
