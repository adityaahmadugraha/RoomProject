package com.aditya.roomproject

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.aditya.roomproject.adapter.UserAdapter
import com.aditya.roomproject.data.AppDatabase
import com.aditya.roomproject.data.entity.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var adapter: UserAdapter
    private lateinit var database: AppDatabase

    private var list = mutableListOf<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycle_view)
        fab = findViewById(R.id.fab)

        database = AppDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object : UserAdapter.Dialog {
            override fun onClick(position: Int) {
                //membuat dialog view
                val dialog = AlertDialog.Builder(this@MainActivity).also {
                    it.setTitle(list[position].fullName)
                    it.setItems(R.array.item_option, DialogInterface.OnClickListener { dialog, which ->
                            if (which == 0) {
                                //coding ubah
                                var intent = Intent(this@MainActivity, EditorActivity::class.java)
                                var putExtra = intent.putExtra("id", list[position].uid)
                                startActivity(intent)
                            } else if (which == 1) {
                                database.userDao().delete(list[position])
                            } else {
                                //coding batal
                                dialog.dismiss()
                            }
                        })
                }
                // menampilakan dialog
                val dialogView = dialog.create()
                dialogView.show()
            }

        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener {
            startActivity(Intent(this, EditorActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData() {
        list.clear()
        list.addAll(database.userDao().getAll())
        adapter.notifyDataSetChanged()
    }
}