package mx.edu.itson.potros.practica6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Catalogo : AppCompatActivity() {
    var adapter: PeliculaAdapter? = null
    var seriesAdapter: PeliculaAdapter? = null
    var peliculas = ArrayList<Pelicula>()
    var series = ArrayList<Pelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)

        cargarPeliculas()
        cargarSeries()

        adapter = PeliculaAdapter(context = this,peliculas)
        seriesAdapter = PeliculaAdapter(context = this, series)

        var gridPelis: GridView = findViewById(R.id.movies_catalogo)
        var gridSeries: GridView = findViewById(R.id.mseries_catalogo)

        gridPelis.adapter = adapter
        gridSeries.adapter = seriesAdapter
    }
    fun cargarPeliculas() {
        peliculas.add(Pelicula(titulo = "Demon Slayer: Kimetsu no Yaiba -To the Hashira Training", R.drawable.demon, R.drawable.demon, sinopsis = "Tanjiro y sus amigos comienzan un nuevo entrenamiento con los Hashira.",arrayListOf<Cliente>()))
        peliculas.add(Pelicula(titulo = "Dune", R.drawable.dune, R.drawable.dune2, sinopsis = "\"Duna: Parte Dos\" explorará el viaje mítico de Paul Atreides.",arrayListOf<Cliente>()))
        peliculas.add(Pelicula(titulo = "Ghostbusters Apocalipsis", R.drawable.ghostbusters, R.drawable.ghostbusters, sinopsis = "Los Cazafantasmas deben detener una invasión sobrenatural en Nueva York.",arrayListOf<Cliente>()))
        peliculas.add(Pelicula(titulo = "Héroe Por Encargo", R.drawable.heroexencargo, R.drawable.heroeencargo, sinopsis = "Un hombre común se convierte en un héroe inesperado.",arrayListOf<Cliente>()))
        peliculas.add(Pelicula(titulo = "Madame Web", R.drawable.madameweb, R.drawable.madame, sinopsis = "Mientras tanto en el universo de Spider-Man, una nueva heroína despierta.",arrayListOf<Cliente>()))
        peliculas.add(Pelicula(titulo = "Vidas Pasadas", R.drawable.vidaspasadas, R.drawable.vidaspasadas1, sinopsis = "Nora y Hae Sung, dos amigos de la infancia, se reencuentran después de años.",arrayListOf<Cliente>()))
    }

    fun cargarSeries() {
        series.add(Pelicula(titulo = "Avatar: La leyenda de Aang", R.drawable.ant, R.drawable.ant, sinopsis = "El joven Aang debe dominar los cuatro elementos para salvar el mundo.", arrayListOf<Cliente>()))
        series.add(Pelicula(titulo = "Halo", R.drawable.halo, R.drawable.halos, sinopsis = "Una evacuación mortal cambia la guerra entre humanos y el Covenant.",arrayListOf<Cliente>()))
        series.add(Pelicula(titulo = "Leveling", R.drawable.sololeveling, R.drawable.sololevelings, sinopsis = "En un mundo de cazadores y monstruos, un joven gana un misterioso poder.",arrayListOf<Cliente>()))
        series.add(Pelicula(titulo = "Mi adorable demonio", R.drawable.adorabledemonio, R.drawable.adorabledemonios, sinopsis = "Una chica se enamora de un demonio encantador.",arrayListOf<Cliente>()))
        series.add(Pelicula(titulo = "El Monstruo de la Vieja Seúl", R.drawable.elmonstruo, R.drawable.elmonstruovieja, sinopsis = "Criaturas extrañas emergen en la ciudad, causando terror.",arrayListOf<Cliente>()))
        series.add(Pelicula(titulo = "Witcher", R.drawable.thewitcher, R.drawable.thewitchers, sinopsis = "Geralt de Rivia, un cazador de monstruos, busca su destino en un mundo cruel.",arrayListOf<Cliente>()))
    }

    }
class PeliculaAdapter: BaseAdapter {
    var peliculas = ArrayList<Pelicula>()
    var context: Context? = null

    constructor(context: Context, peliculas: ArrayList<Pelicula>) : super() {
        this.peliculas = peliculas
        this.context = context
    }

    override fun getCount(): Int {
        return peliculas.size
    }

    override fun getItem(p0: Int): Any {
        return peliculas[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(pe: Int, p1: View?, p2: ViewGroup?): View {
        var pelicula = peliculas[pe]
        var inflator= context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflator.inflate(R.layout.pelicula, null)
        var image: ImageView = vista.findViewById(R.id.image_movie_cell)
        var title: TextView = vista.findViewById(R.id.movie_title_cell)

        image.setImageResource(pelicula.image)
        title.setText(pelicula.titulo)

        image.setOnClickListener(){
            val intento= Intent(context, detalle_pelicula::class.java)
            intento.putExtra("titulo",pelicula.titulo)
            intento.putExtra("imagen",pelicula.image)
            intento.putExtra("header",pelicula.header)
            intento.putExtra("sinopsis",pelicula.sinopsis)
            intento.putExtra("numberSeats",20-pelicula.seats.size)
            context!!.startActivity(intento)
        }
        return vista
    }
}