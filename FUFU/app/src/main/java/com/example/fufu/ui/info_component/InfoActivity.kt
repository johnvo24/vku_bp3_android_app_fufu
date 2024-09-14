package com.example.fufu.ui.info_component

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fufu.asset.Helper
import com.example.fufu.databinding.ActivityInfoBinding
import com.example.fufu.ui.log_component.SignInSignUpActivity
import org.json.JSONObject

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE)
        if (sharedPreferences.getString("logged", "false").equals("false")) {
            val i = Intent(applicationContext, SignInSignUpActivity::class.java)
            startActivity(i)
            finish()
        }

        binding.userName.text = sharedPreferences.getString("name", "")
        binding.userNameEdit.text = sharedPreferences.getString("name", "")
        binding.userEmailEdit.text = sharedPreferences.getString("email", "")
        binding.userPhoneEdit.text = sharedPreferences.getString("phone", "")
        binding.userGenderEdit.text = sharedPreferences.getString("gender", "")
        binding.userDobEdit.text = sharedPreferences.getString("dob", "")
        binding.userAddressEdit.text = sharedPreferences.getString("address", "")
        binding.userBioEdit.text = sharedPreferences.getString("bio", "")
        binding.userBio.text = sharedPreferences.getString("bio", "")

        binding.btnLogOut.setOnClickListener {
//            val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
//            val url = "http://${ Helper().host }/fufuAPI/logOut.php"
//            val stringRequest = object : StringRequest(
//                Method.POST, url,
//                Response.Listener<String> { response ->
//                    if (response.equals("success")) {
//
//                    } else {
//                        Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
//                    }
//                },
//                Response.ErrorListener { error ->
//                    error.printStackTrace()
//                }) {
//                override fun getParams(): Map<String, String> {
//                    val params: MutableMap<String, String> = HashMap()
//                    params["phone"] = sharedPreferences.getString("phone", "")!!
//                    params["userStatus"] = sharedPreferences.getString("userStatus", "")!!
//                    return params
//                }
//            }
//            queue.add(stringRequest)
//            if (sharedPreferences.getString("role", "") == "1") {
                val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
                editor.putString("logged", "false")
                editor.putString("userId", "")
                editor.putString("phone", "")
                editor.putString("email", "")
                editor.putString("name", "")
                editor.putString("address", "")
                editor.putString("gender", "")
                editor.putString("dob", "")
                editor.putString("bio", "")
                editor.putString("role", "")
                editor.putString("userStatus", "")
                editor.putString("resId", "")
                editor.apply()
                val i = Intent(applicationContext, SignInSignUpActivity::class.java)
                startActivity(i)
                //SharedPreferences
                val sharedPref = this.getSharedPreferences("currentUser", MODE_PRIVATE)
                sharedPref.edit().putString("userId", "").apply()
                sharedPref.edit().putString("userRole", "0").apply()
                sharedPref.edit().putString("resId", "").apply()
        }

        binding.btnEdit1.setOnClickListener {
            val i = Intent(this, EditUserActivity::class.java)
            i.putExtra("btnNumber", "form1")
            i.putExtra("userName", binding.userNameEdit.text)
            i.putExtra("userGender", binding.userGenderEdit.text)
            i.putExtra("userDob", binding.userDobEdit.text)
            i.putExtra("userPhone", binding.userPhoneEdit.text)
            startActivity(i)
            finish()
        }

        binding.btnEdit2.setOnClickListener {
            val i = Intent(this, EditUserActivity::class.java)
            i.putExtra("btnNumber", "form2")
            i.putExtra("userEmail", binding.userEmailEdit.text)
            i.putExtra("userAddress", binding.userAddressEdit.text)
            i.putExtra("userPhone", binding.userPhoneEdit.text)
            startActivity(i)
            finish()
        }

        binding.btnEdit3.setOnClickListener {
            val i = Intent(this, EditUserActivity::class.java)
            i.putExtra("btnNumber", "form3")
            i.putExtra("userBio", binding.userBioEdit.text)
            i.putExtra("userPhone", binding.userPhoneEdit.text)
            startActivity(i)
            finish()
        }

        binding.btnChangePass.setOnClickListener {
            val i = Intent(this, EditUserActivity::class.java)
            i.putExtra("btnNumber", "form4")
            i.putExtra("userPhone", binding.userPhoneEdit.text)
            startActivity(i)
            finish()
        }
    }

}