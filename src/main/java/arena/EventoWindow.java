package arena;

import domain.evento.Evento;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import domain.usuario.TipoDeUsuario;
import domain.usuario.Usuario;
import org.uqbar.arena.bindings.ValueTransformer;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MainWindow;

import java.time.LocalDateTime;
import java.util.Arrays;


/**
 * Punto 7: Interfaz de escritorio que permita Listar todos los eventos (de un usuario en particular) entre dos fechas,
 * mostrando la fecha, el título del evento, y si ya se cuentan con sugerencias para el mismo.
 */

public class EventoWindow extends MainWindow<Usuario> {

    static Prenda zapatos;
    static Prenda remera;
    static Prenda pantalon;
    static Prenda anteojos;
    static domain.prenda.Color rojo;
    static domain.prenda.Color verde;
    static domain.prenda.Color azul;
    static domain.prenda.Color gray;
    static Usuario panchoPepeGil;
    static Usuario yo;
    static Evento cumpleañosDePancho;
    static LocalDateTime fechaCumpleDePancho;



    /* Punto de entrada de la aplicacion */
    public static void main(String[] args) {
        rojo = new domain.prenda.Color(255, 0, 0);
        verde = new domain.prenda.Color(0, 255, 0);
        azul = new domain.prenda.Color(0, 0, 255);
        gray = new domain.prenda.Color(128, 128, 128);
        zapatos = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO);
        remera = armarUnaPrenda(TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS);
        pantalon = armarUnaPrenda(TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA);
        anteojos= armarUnaPrenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, verde, rojo, Trama.LISA);
        panchoPepeGil = new Usuario(Arrays.asList(new GuardarropasPremium(Arrays.asList(remera, remera), Arrays.asList(pantalon, pantalon, pantalon), Arrays.asList(zapatos, zapatos, zapatos), Arrays.asList(anteojos))), TipoDeUsuario.PREMIUM);
        LocalDateTime fechaCumpleDePancho= LocalDateTime.of(2020,06,20,20,30);
        cumpleañosDePancho = new Evento("Cumpleaños de juan", fechaCumpleDePancho,"Casa de Juan", panchoPepeGil);
        yo = new Usuario(Arrays.asList(new GuardarropasPremium(Arrays.asList(remera, remera), Arrays.asList(pantalon, pantalon, pantalon), Arrays.asList(zapatos, zapatos, zapatos), Arrays.asList(anteojos))), TipoDeUsuario.PREMIUM);
        yo.cargarEvento(cumpleañosDePancho);
        /* Parte importante */
        new EventoWindow().startApplication();
    }

    static Prenda armarUnaPrenda(TipoDePrenda tipoDePrenda, Material material, domain.prenda.Color colorPrimario, domain.prenda.Color colorSecundario, Trama trama){
        BorradorPrenda borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(tipoDePrenda);
        borradorPrenda.definirMaterial(material);
        borradorPrenda.definirColorPrimario(colorPrimario);
        borradorPrenda.definirColorSecundario(colorSecundario);
        borradorPrenda.definirTrama(trama);
        return borradorPrenda.crearPrenda();
    }





    /* Enlace (binding). Se usa un usuario de ejemplo */
    public EventoWindow() {
        //Aca habria que crear una instancia de la clase model. Pero como la cree static para probar le asigno eso mismo
        super(yo);
    }





    /* Enlazar botones con acciones */
    @Override
    public void createContents(Panel mainPanel){
        int windowWidth = 400;

        /* Titulo de la ventana */
        this.setTitle("Mis eventos");
        this.setMinWidth(windowWidth);

        /* Layout de la ventana: significa que los elementos se acumulan en forma de pila o stack, por eso es verticalLayout. El orden esta definido por el orden en que se llama a cada elemento */
        mainPanel.setLayout(new VerticalLayout());
        mainPanel.setWidth(windowWidth);

        Panel subPanelFechaInicio = new Panel(mainPanel).setLayout(new HorizontalLayout());
        new Label(subPanelFechaInicio).setText("Fecha de inicio").alignLeft();
        // Nota, el atributo filtroEventoInicial tiene que tener getter y setter por que si no, no arranca.
        new TextBox(subPanelFechaInicio).setWidth(290).alignRight().bindValueToProperty("filtroEventoInicial").setTransformer(new LocalDateTimeTransformer());

        Panel subPanelFechaFin = new Panel(mainPanel).setWidth(windowWidth).setLayout(new HorizontalLayout());
        // Nota, no pude hacer andar los align
        new Label(subPanelFechaFin).setText("Fecha de fin    ").alignLeft();
        new TextBox(subPanelFechaFin).setWidth(290).alignRight().bindValueToProperty("filtroEventoFinal").setTransformer(new LocalDateTimeTransformer());

        new Button(mainPanel).setCaption("Listar eventos")
                .onClick(()->this.getModelObject().filtrarEventosEntreRangoDeFechas());

        Table<Evento> tablaEventos = new Table<Evento>(mainPanel, Evento.class);
        tablaEventos.bindItemsToProperty("eventosFiltrados");
        //tablaEventos.bindValueToProperty("celularSeleccionado");

        new Column<Evento>(tablaEventos).setTitle("Titulo").bindContentsToProperty("nombre");
        new Column<Evento>(tablaEventos).setTitle("Fecha").bindContentsToProperty("fecha");
        new Column<Evento>(tablaEventos).setTitle("Sugerencias").bindContentsToProperty("existenSugerencias");
    }

}

/** Transformar de String a LocalDateTime. Se espera que se ingrese con el formato YYYY-MM-DD-hh-mm-ss y para cumplir con LocalDateTime se le agrega con el formato YYYY-MM-DD-hh-mm-ss.zzz (milisegundos)*/
class LocalDateTimeTransformer implements ValueTransformer<LocalDateTime, String>{

    @Override
    public LocalDateTime viewToModel(String valueFromView) {
        return LocalDateTime.parse(valueFromView);
    }

    @Override
    public String modelToView(LocalDateTime valueFromModel) {
        return valueFromModel.toString();
    }

    @Override
    public Class<LocalDateTime> getModelType() {
        return LocalDateTime.class;
    }

    @Override
    public Class<String> getViewType() {
        return String.class;
    }
}