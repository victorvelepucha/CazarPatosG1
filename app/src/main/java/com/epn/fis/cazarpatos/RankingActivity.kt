package com.epn.fis.cazarpatos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epn.fis.cazarpatos.database.RankingPlayerDBHelper

class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ranking)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        /*
        var jugadores = arrayListOf<Player>()
        jugadores.add(Player("Victor.Velepucha",14))
        jugadores.add(Player("Jugador2",6))
        jugadores.add(Player("Jugador3",3))
        jugadores.add(Player("Jugador4",1))
        jugadores.add(Player("Jugador5",4))
        jugadores.add(Player("Jugador6",7))
        jugadores.add(Player("Jugador7",12))
        jugadores.add(Player("Jugador8",11))
        jugadores.add(Player("Jugador9",5))
        jugadores.sortByDescending { it.huntedDucks }

        val recyclerViewRanking: RecyclerView = findViewById(R.id.recyclerViewRanking);
        recyclerViewRanking.layoutManager = LinearLayoutManager(this);
        recyclerViewRanking.adapter = RankingAdapter(jugadores);
        recyclerViewRanking.setHasFixedSize(true);
         */
        OperacionesSqLite()
        GrabarRankingSQLite()
        LeerRankingsSQLite()
    }

    fun OperacionesSqLite(){
        RankingPlayerDBHelper(this).deleteAllRanking()
        RankingPlayerDBHelper(this).insertRankingByQuery(Player("Jugador9",10))
        val patosCazados = RankingPlayerDBHelper(this).readDucksHuntedByPlayer("Jugador9")
        RankingPlayerDBHelper(this).updateRanking(Player("Jugador9",5))
        RankingPlayerDBHelper(this).deleteRanking("Jugador9")
        RankingPlayerDBHelper(this).insertRanking(Player("Jugador9",7))
        val players = RankingPlayerDBHelper(this).readAllRankingByQuery()
    }
    fun GrabarRankingSQLite(){
        val jugadores = arrayListOf(
            Player("Victor.Velepucha", 10),
            Player("Jugador2", 6),
            Player("Jugador3", 3),
            Player("Jugador4", 9)
        )
        jugadores.sortByDescending { it.huntedDucks }
        for(jugador in jugadores){
            RankingPlayerDBHelper(this).insertRanking(jugador)
        }
    }
    fun LeerRankingsSQLite(){
        val jugadoresSQLite = RankingPlayerDBHelper(this).readAllRanking()
        val recyclerViewRanking: RecyclerView = findViewById(R.id.recyclerViewRanking)
        recyclerViewRanking.layoutManager = LinearLayoutManager(this)
        recyclerViewRanking.adapter = RankingAdapter(jugadoresSQLite)
        recyclerViewRanking.setHasFixedSize(true)
    }
}