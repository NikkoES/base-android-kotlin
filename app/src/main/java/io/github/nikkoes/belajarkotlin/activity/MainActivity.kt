package io.github.nikkoes.belajarkotlin.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener
import com.google.gson.Gson
import io.github.nikkoes.belajarkotlin.R
import io.github.nikkoes.belajarkotlin.adapter.PenghuniAdapter
import io.github.nikkoes.belajarkotlin.data.Constant
import io.github.nikkoes.belajarkotlin.model.Penghuni
import io.github.nikkoes.belajarkotlin.model.PenghuniResponse
import io.github.nikkoes.belajarkotlin.model.PostResponse
import io.github.nikkoes.belajarkotlin.utils.DialogUtil
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var list = ArrayList<Penghuni>()
    lateinit var adapter: PenghuniAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Data Penghuni Kosan"

        initRecyclerView()
        initActionButton()
    }

    override fun onResume() {
        super.onResume()
        loadItems()
    }

    private fun loadItems() {
        DialogUtil.openDialog(this)
        AndroidNetworking.get(Constant.GET_PENGHUNI)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsObject(
                PenghuniResponse::class.java,
                object : ParsedRequestListener<PenghuniResponse> {
                    override fun onResponse(response: PenghuniResponse) {
                        DialogUtil.closeDialog()
                        if (response.status.equals("success")) {
                            adapter.swap(response.data)
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Data tidak ditemukan ! ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Log.e("response: ", anError?.errorBody)
                        Toast.makeText(
                            applicationContext,
                            "Response / Connection Failure ! ",
                            Toast.LENGTH_SHORT
                        ).show()
                        DialogUtil.closeDialog()
                    }

                })
    }

    private fun initActionButton() {
        btn_add.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }
    }

    private fun initRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PenghuniAdapter(applicationContext)
        adapter.setOnItemClickListener(object : PenghuniAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(applicationContext, FormActivity::class.java)
                intent.putExtra("edit", true)
                intent.putExtra("item", adapter.getItem(position))
                startActivity(intent)
            }

            override fun onDelete(position: Int) {
                delete(position)
            }

        })

        recyclerView.adapter = adapter
    }

    private fun delete(position: Int) {
        DialogUtil.openDialog(this)
        AndroidNetworking.post(Constant.DELETE_PENGHUNI + adapter.getItem(position).id)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsObject(PostResponse::class.java, object :
                ParsedRequestListener<PostResponse> {
                override fun onResponse(response: PostResponse) {
                    DialogUtil.closeDialog()
                    if (response.status.equals("success", true)) {
                        Toast.makeText(
                            applicationContext,
                            "Berhasil menghapus data..",
                            Toast.LENGTH_SHORT
                        ).show()
                        loadItems()
                    }
                }

                override fun onError(anError: ANError?) {
                    Toast.makeText(
                        applicationContext,
                        "Response / Connection Failure ! ",
                        Toast.LENGTH_SHORT
                    ).show()
                    DialogUtil.closeDialog()
                }

            })
    }
}