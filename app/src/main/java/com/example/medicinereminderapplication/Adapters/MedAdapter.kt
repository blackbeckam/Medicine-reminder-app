package com.example.medicinereminderapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapplication.Activities.medsmodel
import com.example.medicinereminderapplication.R

class MedAdapter(private var  medList: ArrayList<medsmodel>) :RecyclerView.Adapter<MedAdapter.ViewHolder>(){
    private lateinit var mListener:OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: OnItemClickListener){
        mListener=clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView=LayoutInflater.from(parent.context).inflate(R.layout.med_list,parent,false)

        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val currentMed= medList[position]
         holder.tvMedname.text = currentMed.Medname

    }


    override fun getItemCount(): Int {
        return ( medList.size)
    }
    class ViewHolder(   itemView: View,clickListener: OnItemClickListener):RecyclerView.ViewHolder(itemView){
    val  tvMedname:TextView =itemView.findViewById(R.id.tvMedname)

    init {
        itemView.setOnClickListener{
            clickListener.onItemClick(adapterPosition)
        }
    }
    }

}





