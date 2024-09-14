package com.example.fufu.ui.log_component.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fufu.MainActivity
import com.example.fufu.R
import com.example.fufu.asset.Helper
import org.json.JSONObject

class SignInFragment : Fragment() {

    private lateinit var btnSignIn: Button

    private lateinit var edtAccountLog: EditText
    private lateinit var edtPassLog: EditText

    private lateinit var userId: String
    private lateinit var email: String
    private lateinit var pass: String
    private lateinit var phone: String
    private lateinit var name: String
    private lateinit var address: String
    private lateinit var gender: String
    private lateinit var dob: String
    private lateinit var bio: String
    private lateinit var role: String
    private lateinit var userStatus: String
    private lateinit var resId: String

    private lateinit var tvError: TextView
    private lateinit var progressBar: ProgressBar
    //private lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        val sharedPreferences = activity?.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        if (sharedPreferences!!.getString("logged", "false").equals("true")) {
            val i = Intent(context, MainActivity::class.java)
            startActivity(i)
        }
        btnSignIn.setOnClickListener {
            phone = edtAccountLog.text.toString()
            pass = edtPassLog.text.toString()
            progressBar.visibility = View.VISIBLE
            tvError.visibility = View.GONE
            val queue: RequestQueue = Volley.newRequestQueue(context)
            val url = "http://${ Helper().host }/fufuAPI/signIn.php"
            val stringRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener<String> { response ->
                    progressBar.visibility = View.GONE
                    val jsonObject: JSONObject = JSONObject(response)
                    val status: String = jsonObject.getString("status")
                    if (status == "success") {
                        userId = jsonObject.getString("userId")
                        email = jsonObject.getString("email")
                        phone = jsonObject.getString("phone")
                        name = jsonObject.getString("name")
                        address = jsonObject.getString("address")
                        gender = jsonObject.getString("gender")
                        dob = jsonObject.getString("dob")
                        bio = jsonObject.getString("bio")
                        role = jsonObject.getString("role")
                        userStatus = jsonObject.getString("userStatus")

                        //SharedPreferences
                        val sharedPref = requireContext().getSharedPreferences("currentUser",
                            AppCompatActivity.MODE_PRIVATE
                        )
                        sharedPref.edit().putString("userId", userId).apply()
                        sharedPref.edit().putString("userRole", role).apply()
                        sharedPref.edit().putString("resId", "").apply()
                        if (role == "1") {
                            println("hihi")
                            resId = jsonObject.getString("resId")
                            val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
                            editor.putString("logged", "true")
                            editor.putString("userId", userId)
                            editor.putString("email", email)
                            editor.putString("phone", phone)
                            editor.putString("name", name)
                            editor.putString("address", address)
                            editor.putString("gender", gender)
                            editor.putString("dob", dob)
                            editor.putString("bio", bio)
                            editor.putString("role", role)
                            editor.putString("resId", resId)
                            editor.putString("userStatus", userStatus)
                            editor.apply()
                            sharedPref.edit().putString("resId", resId).apply()
                        } else {
                            println("hihihhahahaahaahhahahahahhaah")
                            val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
                            editor.putString("logged", "true")
                            editor.putString("userId", userId)
                            editor.putString("email", email)
                            editor.putString("phone", phone)
                            editor.putString("name", name)
                            editor.putString("address", address)
                            editor.putString("gender", gender)
                            editor.putString("dob", dob)
                            editor.putString("bio", bio)
                            editor.putString("role", role)
                            editor.putString("userStatus", userStatus)
                            editor.apply()
                            sharedPref.edit().putString("resId", "null").apply()
                        }
                        val i = Intent(context, MainActivity::class.java)
                        startActivity(i)
                    } else if (status == "failed") {
                        Toast.makeText(context, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener { error ->
                    progressBar.visibility = View.GONE
                    tvError.text = error.localizedMessage
                    tvError.visibility = View.VISIBLE
                }) {
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["phone"] = phone
                    params["pass"] = pass
                    return params
                }
            }
            queue.add(stringRequest)
        }
    }

    private fun initView(){
        btnSignIn = view?.findViewById(R.id.btnSignIn)!!
        edtAccountLog = view?.findViewById(R.id.edtAccountLogIn)!!
        edtPassLog = view?.findViewById(R.id.edtPassLogIn)!!
        tvError = view?.findViewById(R.id.tvErrorLog)!!
        progressBar = view?.findViewById(R.id.progressBarLog)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

}