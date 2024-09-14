package com.example.fufu.ui.info_component

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fufu.R
import com.example.fufu.asset.Helper
import com.example.fufu.databinding.ActivityEditUserBinding

class EditUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditUserBinding

    private var edtName: String? = null
    private var edtGender: String? = null
    private var edtDob: String? = null
    private var edtEmail: String? = null
    private var edtAddress: String? = null
    private var edtBio: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (intent.getStringExtra("btnNumber")) {
            "form1" -> {
                binding.form1.visibility = View.VISIBLE
                binding.form2.visibility = View.VISIBLE
                binding.form3.visibility = View.VISIBLE
                binding.form4.visibility = View.GONE
                binding.form5.visibility = View.GONE
                binding.form6.visibility = View.GONE
                binding.form7.visibility = View.GONE
                binding.form8.visibility = View.GONE
                binding.form9.visibility = View.GONE
                binding.headerInfo.btnSaveInfo.setOnClickListener {
                    val queue: RequestQueue = Volley.newRequestQueue(this)
                    val url = "http://${ Helper().host }/fufuAPI/infoPersonal.php"
                    val stringRequest = object : StringRequest(
                        Method.POST, url,
                        Response.Listener<String> { response ->
                            if (response.equals("success")) {
                                Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show()
                            } else if (response.equals("failed")) {
                                Toast.makeText(this, "Lưu thất bại", Toast.LENGTH_SHORT).show()
                            }
                        },
                        Response.ErrorListener { error ->

                        }) {
                        override fun getParams(): Map<String, String> {
                            val params: MutableMap<String, String> = HashMap()
                            params["phone"] = intent.getStringExtra("userPhone")!!
                            params["name"] = binding.edtNameUser.text.toString()
                            params["gender"] = binding.edtGenderUser.text.toString()
                            params["dob"] = binding.edtDobUser.text.toString()
                            return params
                        }
                    }
                    queue.add(stringRequest)
                }
            }
            "form2" -> {
                binding.form1.visibility = View.GONE
                binding.form2.visibility = View.GONE
                binding.form3.visibility = View.GONE
                binding.form4.visibility = View.VISIBLE
                binding.form5.visibility = View.VISIBLE
                binding.form6.visibility = View.GONE
                binding.form7.visibility = View.GONE
                binding.form8.visibility = View.GONE
                binding.form9.visibility = View.GONE
                binding.headerInfo.btnSaveInfo.setOnClickListener {
                    val queue: RequestQueue = Volley.newRequestQueue(this)
                    val url = "http://${ Helper().host }/fufuAPI/infoPersonal.php"
                    val stringRequest = object : StringRequest(
                        Method.POST, url,
                        Response.Listener<String> { response ->
                            if (response.equals("success")) {
                                Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show()
                            } else if (response.equals("failed")) {
                                Toast.makeText(this, "Lưu thất bại", Toast.LENGTH_SHORT).show()
                            }
                        },
                        Response.ErrorListener { error ->

                        }) {
                        override fun getParams(): Map<String, String> {
                            val params: MutableMap<String, String> = HashMap()
                            params["phone"] = intent.getStringExtra("userPhone")!!
                            params["email"] = binding.edtEmailUser.text.toString()
                            params["address"] = binding.edtAddressUser.text.toString()
                            return params
                        }
                    }
                    queue.add(stringRequest)
                }
            }
            "form3" -> {
                binding.form1.visibility = View.GONE
                binding.form2.visibility = View.GONE
                binding.form3.visibility = View.GONE
                binding.form4.visibility = View.GONE
                binding.form5.visibility = View.GONE
                binding.form6.visibility = View.VISIBLE
                binding.form7.visibility = View.GONE
                binding.form8.visibility = View.GONE
                binding.form9.visibility = View.GONE
                binding.headerInfo.btnSaveInfo.setOnClickListener {
                    val queue: RequestQueue = Volley.newRequestQueue(this)
                    val url = "http://${ Helper().host }/fufuAPI/infoPersonal.php"
                    val stringRequest = object : StringRequest(
                        Method.POST, url,
                        Response.Listener<String> { response ->
                            if (response.equals("success")) {
                                Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show()
                            } else if (response.equals("failed")) {
                                Toast.makeText(this, "Lưu thất bại", Toast.LENGTH_SHORT).show()
                            }
                        },
                        Response.ErrorListener { error ->

                        }) {
                        override fun getParams(): Map<String, String> {
                            val params: MutableMap<String, String> = HashMap()
                            params["phone"] = intent.getStringExtra("userPhone")!!
                            params["bio"] = binding.edtBioUser.text.toString()
                            return params
                        }
                    }
                    queue.add(stringRequest)
                }
            }
            "form4" -> {
                binding.form1.visibility = View.GONE
                binding.form2.visibility = View.GONE
                binding.form3.visibility = View.GONE
                binding.form4.visibility = View.GONE
                binding.form5.visibility = View.GONE
                binding.form6.visibility = View.GONE
                binding.form7.visibility = View.VISIBLE
                binding.form8.visibility = View.VISIBLE
                binding.form9.visibility = View.VISIBLE
                binding.headerInfo.btnSaveInfo.setOnClickListener {
                    val queue: RequestQueue = Volley.newRequestQueue(this)
                    val url = "http://${ Helper().host }/fufuAPI/infoPersonal.php"
                    val stringRequest = object : StringRequest(
                        Method.POST, url,
                        Response.Listener<String> { response ->
                            if (response.equals("success")) {
                                Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show()
                            } else if (response.equals("failed")) {
                                Toast.makeText(this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show()
                            } else if (response.equals("wrong")) {
                                Toast.makeText(this, "Mật khẩu hiện tại nhập sai", Toast.LENGTH_SHORT).show()
                            } else if (response.equals("different")) {
                                Toast.makeText(this, "Nhập mật khẩu mới không trùng", Toast.LENGTH_SHORT).show()
                            }
                        },
                        Response.ErrorListener { error ->

                        }) {
                        override fun getParams(): Map<String, String> {
                            val params: MutableMap<String, String> = HashMap()
                            params["phone"] = intent.getStringExtra("userPhone")!!
                            params["pass"] = binding.edtPassNow.text.toString()
                            params["passNew"] = binding.edtPassNew.text.toString()
                            params["passNewRepeat"] = binding.edtPassNewRepeat.text.toString()
                            return params
                        }
                    }
                    queue.add(stringRequest)
                }
            }
        }

        binding.headerInfo.btnBackFromInfo.setOnClickListener {
            val i = Intent(this, InfoActivity::class.java)
            startActivity(i)
            finish()
        }

        getDataExtra()
        binding.edtNameUser.setText(edtName)
        binding.edtGenderUser.setText(edtGender)
        binding.edtDobUser.setText(edtDob)
        binding.edtEmailUser.setText(edtEmail)
        binding.edtAddressUser.setText(edtAddress)
        binding.edtBioUser.setText(edtBio)
    }

    private fun getDataExtra() {
        edtName = intent.getStringExtra("userName")
        edtGender = intent.getStringExtra("userGender")
        edtDob = intent.getStringExtra("userDob")
        edtEmail = intent.getStringExtra("userEmail")
        edtAddress = intent.getStringExtra("userAddress")
        edtBio = intent.getStringExtra("userBio")
    }


}