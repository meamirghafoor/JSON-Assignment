package com.example.json
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
class MainActivity : AppCompatActivity() {
    var personNames = ArrayList<String>()
    var emailIds = ArrayList<String>()
    var mobileNumbers = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val userArray = obj.getJSONArray("users")
            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                personNames.add(userDetail.getString("name"))
                emailIds.add(userDetail.getString("email"))
                val contact = userDetail.getJSONObject("contact")
                mobileNumbers.add(contact.getString("mobile"))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val customAdapter = CustomAdapter(this@MainActivity, personNames, emailIds, mobileNumbers)
        recyclerView.adapter = customAdapter // set the Adapter to RecyclerView
    }
    fun loadJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val `is` = assets.open("users_list.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}