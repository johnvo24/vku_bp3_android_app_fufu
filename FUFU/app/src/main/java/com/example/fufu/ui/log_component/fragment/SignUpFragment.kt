package com.example.fufu.ui.log_component.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fufu.R
import com.example.fufu.asset.Helper

class SignUpFragment : Fragment() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var edtName: EditText
    private lateinit var edtPass: EditText
    private lateinit var btnSignUp: Button

    private lateinit var email: String
    private lateinit var phone: String
    private lateinit var name: String
    private lateinit var pass: String

    private lateinit var tvError: TextView
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        btnSignUp.setOnClickListener {
            email = edtEmail.text.toString()
            phone = edtPhone.text.toString()
            name = edtName.text.toString()
            pass = edtPass.text.toString()
            progressBar.visibility = View.VISIBLE
            tvError.visibility = View.GONE
            val queue: RequestQueue = Volley.newRequestQueue(context)
            val url = "http://${ Helper().host }/fufuAPI/signUp.php"
            val stringRequest = object : StringRequest(Method.POST, url,
                Response.Listener<String> { response ->
                    progressBar.visibility = View.GONE
                    if (response.equals("success")) {
                        Toast.makeText(context, "Bạn đã đăng ký thành công!", Toast.LENGTH_SHORT).show()
                        edtEmail.setText("")
                        edtPhone.setText("")
                        edtName.setText("")
                        edtPass.setText("")
                    } else if (response.equals("not")) {
                        Toast.makeText(context, "Mật khẩu phải có độ dài hơn 5 ký tự", Toast.LENGTH_SHORT).show()
                    } else if (response.equals("exist")) {
                        Toast.makeText(context, "Số điện thoại này đã tồn tại", Toast.LENGTH_SHORT).show()
                    } else {
                        tvError.text = response
                        tvError.visibility = View.VISIBLE
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
                    params["email"] = email
                    params["name"] = name
                    params["pass"] = pass
                    return params
                }
            }
            queue.add(stringRequest)
        }
    }

    private fun initView() {
        edtEmail = view?.findViewById(R.id.edtEmail)!!
        edtPhone = view?.findViewById(R.id.edtPhone)!!
        edtName = view?.findViewById(R.id.edtName)!!
        edtPass = view?.findViewById(R.id.edtPass)!!
        btnSignUp = view?.findViewById(R.id.btnSignUp)!!
        tvError = view?.findViewById(R.id.tvError)!!
        progressBar = view?.findViewById(R.id.progressBar)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

}