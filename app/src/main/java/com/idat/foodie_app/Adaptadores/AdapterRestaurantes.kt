package com.idat.foodie_app.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.idat.foodie_app.Modelos.Restaurantes
import com.idat.foodie_app.R

class AdapterRestaurantes (private var items: ArrayList<Restaurantes>):
    RecyclerView.Adapter<AdapterRestaurantes.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterRestaurantes.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prueba_rv, parent, false)
        return AdapterRestaurantes.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterRestaurantes.ViewHolder, position: Int) {
        val item = items[position]
        holder.nombreV.text = item.nombre
        holder.descripcionV.text = item.descripcion
        holder.imagenV.text = item.imagen

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view :View): RecyclerView.ViewHolder(view) {
        val nombreV: TextView = view.findViewById(R.id.cardNombre)
        val descripcionV: TextView = view.findViewById(R.id.cardDescripcion)
        val imagenV: TextView = view.findViewById(R.id.cardImagen)
    }
}