package com.ashutosh.mvvmdemoapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ashutosh.mvvmdemoapp.R
import com.ashutosh.mvvmdemoapp.databinding.ActivityLoginBinding
import com.ashutosh.mvvmdemoapp.db.entities.User
import com.ashutosh.mvvmdemoapp.ui.home.HomeActivity
import com.ashutosh.mvvmdemoapp.utils.hide
import com.ashutosh.mvvmdemoapp.utils.show
import com.ashutosh.mvvmdemoapp.utils.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {


    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ****************************************************************/
        // tightly coupled because we are creating instance of so many class
        // ****************************************************************/
        /*val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)*/

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer {user ->

            if (user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }

        })



    }

    override fun onStarted() {

        progress_bar.show()

    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
//            root_layout.snackbar("${user.name} is Logged In")
        }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }

}

