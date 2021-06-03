package com.db.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    companion object{
        val url = "http://10.0.2.2:8080"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(HomeFragment())

        var bottomNavigationView : BottomNavigationView = findViewById(R.id.bnv_main)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.first ->{
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.second->{
                    replaceFragment(DongnaeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.third->{
                    replaceFragment(AroundOfMeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.fourd ->{
                    replaceFragment(ChatFragmnet())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.fifth->{
                    replaceFragment(MySettingFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else ->{
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }
    private fun replaceFragment(fragment : Fragment){
        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_container,fragment)
        fragmentTransaction.commit()

    }
}


