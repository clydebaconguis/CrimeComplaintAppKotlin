package com.example.online_crime_reporting_app.Public_user

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.online_crime_reporting_app.R

class ComplainAdapter(private val context:Context, private val complain:List<ComplaintData>) : RecyclerView.Adapter<ComplainAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item_style,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val com = complain[position]
        holder.setData(com,position)

    }

    override fun getItemCount(): Int {
        return complain.size
    }

     class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun setData(complain: ComplaintData? , pos: Int){
            val dt = itemView.findViewById<TextView>(R.id.txt_date)
            val st = itemView.findViewById<TextView>(R.id.txt_status)

            dt.text = complain!!.date
            st.text = complain.status
        }
    }

}