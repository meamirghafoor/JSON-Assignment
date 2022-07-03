package com.example.json
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
class CustomAdapter(
    var context: Context,
    var personNames: ArrayList<String>,
    var emailIds: ArrayList<String>,
    var mobileNumbers: ArrayList<String>
) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.rowlayout, parent, false)
        return MyViewHolder(v)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = personNames[position]
        holder.email.text = emailIds[position]
        holder.mobileNo.text = mobileNumbers[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(context, personNames[position], Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return personNames.size
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var email: TextView
        var mobileNo
                : TextView
        init {
            name = itemView.findViewById<View>(R.id.name) as TextView
            email = itemView.findViewById<View>(R.id.email) as TextView
            mobileNo = itemView.findViewById<View>(R.id.mobileNo) as TextView
        }
    }
}