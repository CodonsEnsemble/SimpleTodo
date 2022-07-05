package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    val TAG  = "MainActivity"
    var listOfTask = mutableListOf<String>()
    val rvTask = findViewById<View>(R.id.recyclerviewTasks) as RecyclerView
    lateinit var myAdapter: TaskItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListerner = object : TaskItemAdapter.OnLongClickListerner{
            override fun OnItemLongClicked(position: Int) {
                //remove item from the list
                listOfTask.removeAt(position)

                //notify adaoter for changing
                myAdapter.notifyDataSetChanged()
                //save item
                saveItem()

                toastMessage("item was removed succefully")

            }

        }
        //some element on the list
        loadItems()

        //my adapter
        myAdapter = TaskItemAdapter(listOfTask, onLongClickListerner)
        rvTask.layoutManager = LinearLayoutManager(this)

        // capture button click event
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            //get click by user
            Log.i(TAG, "User cliqued on button")

            val inputedUser = findViewById<EditText>(R.id.EdtAdd).text.toString();

            //add to list
            listOfTask.add(inputedUser)
                //notify adapter to change
            myAdapter.notifyItemChanged(listOfTask.size -1)

            //clear field input
            findViewById<EditText>(R.id.EdtAdd).text.clear()

            //save item
            saveItem()
            toastMessage("Item saved sucesfully")

        }

    }

fun toastMessage( msg: String){
    Toast. makeText(applicationContext,msg,Toast. LENGTH_SHORT).show()
}


    //get file
    fun getDataFile(): File {
        return  File(filesDir, "data.txt")
    }

    //load files
    fun loadItems(){
    try {
        listOfTask = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
    } catch (ioException :IOException){
        ioException.printStackTrace()
    }
    }

    // save items
    fun saveItem(){

        try {
            FileUtils.writeLines(getDataFile(), listOfTask)
        }catch (ioException :IOException){
            ioException.printStackTrace()
        }
    }

}