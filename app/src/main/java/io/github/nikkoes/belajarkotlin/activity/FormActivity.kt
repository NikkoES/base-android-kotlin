package io.github.nikkoes.belajarkotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import io.github.nikkoes.belajarkotlin.R
import io.github.nikkoes.belajarkotlin.adapter.PenghuniAdapter
import io.github.nikkoes.belajarkotlin.data.Constant
import io.github.nikkoes.belajarkotlin.model.Penghuni
import io.github.nikkoes.belajarkotlin.model.PenghuniResponse
import io.github.nikkoes.belajarkotlin.model.PostResponse
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_main.*

class FormActivity : AppCompatActivity() {

    lateinit var item: Penghuni

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        supportActionBar?.title = "Manajemen Penghuni Kosan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initActionButton()
    }

    private fun initActionButton() {
        if (intent.hasExtra("edit")) {
            item = intent.getSerializableExtra("item") as Penghuni
            nama.setText(item.nama)
            hp.setText(item.hp)
            alamat.setText(item.alamat)
            if (item.gender.equals("Laki-laki", true)) {
                gender.check(R.id.pria)
            } else {
                gender.check(R.id.wanita)
            }
            for (i in 0 until status.getCount()) {
                if (status.getItemAtPosition(i).toString().equals(item.status, true)) {
                    status.setSelection(i)
                    break
                }
            }
            if (item.fasilitas!!.contains("TV")) {
                tv.setChecked(true)
            }
            if (item.fasilitas!!.contains("Kulkas")) {
                kulkas.setChecked(true)
            }
            if (item.fasilitas!!.contains("AC")) {
                ac.setChecked(true)
            }
        }

        btn_simpan.setOnClickListener {
            val sNama = nama.text.toString()
            val sHp = hp.text.toString()
            val genderId = gender.checkedRadioButtonId
            var gender = ""
            if (genderId == R.id.pria) {
                gender = "Laki-laki"
            } else if (genderId == R.id.wanita) {
                gender = "Perempuan"
            }
            val sStatus = status.selectedItem.toString()
            var fasilitas = ""
            if (tv.isChecked) {
                fasilitas = fasilitas + "TV, "
            } else if (ac.isChecked) {
                fasilitas = fasilitas + "AC, "
            } else if (kulkas.isChecked) {
                fasilitas = fasilitas + "Kulkas, "
            }
            val sAlamat = alamat.text.toString()

            if (intent.hasExtra("edit")) {
                AndroidNetworking.post(Constant.UPDATE_PENGHUNI + item.id)
                    .setPriority(Priority.MEDIUM)
                    .addBodyParameter("id_penghuni", item.id)
                    .addBodyParameter("nama", sNama)
                    .addBodyParameter("no_hp", sHp)
                    .addBodyParameter("gender", gender)
                    .addBodyParameter("status", sStatus)
                    .addBodyParameter("fasilitas", fasilitas)
                    .addBodyParameter("alamat", sAlamat)
                    .build()
                    .getAsObject(PostResponse::class.java, object :
                        ParsedRequestListener<PostResponse> {
                        override fun onResponse(response: PostResponse) {
                            if (response.status.equals("success", true)) {
                                Toast.makeText(
                                    applicationContext,
                                    "Berhasil menyimpan data..",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            }
                        }

                        override fun onError(anError: ANError?) {
                            Toast.makeText(
                                applicationContext,
                                "Response / Connection Failure ! ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })
            } else {
                AndroidNetworking.post(Constant.CREATE_PENGHUNI)
                    .setPriority(Priority.MEDIUM)
                    .addBodyParameter("nama", sNama)
                    .addBodyParameter("no_hp", sHp)
                    .addBodyParameter("gender", gender)
                    .addBodyParameter("status", sStatus)
                    .addBodyParameter("fasilitas", fasilitas)
                    .addBodyParameter("alamat", sAlamat)
                    .build()
                    .getAsObject(PostResponse::class.java, object :
                        ParsedRequestListener<PostResponse> {
                        override fun onResponse(response: PostResponse) {
                            if (response.status.equals("success", true)) {
                                Toast.makeText(
                                    applicationContext,
                                    "Berhasil menambahkan data..",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            }
                        }

                        override fun onError(anError: ANError?) {
                            Toast.makeText(
                                applicationContext,
                                "Response / Connection Failure ! ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
