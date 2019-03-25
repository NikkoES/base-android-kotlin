package io.github.nikkoes.belajarkotlin.utils

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

class ActivityUtil {

    companion object {

        fun addFragment(context: Context, id: Int, fragment: Fragment) {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(id, fragment)
                .commit()
        }

        fun addFragment(context: Context, id: Int, fragment: Fragment, TAG: String) {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(id, fragment, TAG)
                .addToBackStack(null)
                .commit()
        }

        fun replaceFragment(context: Context, id: Int, fragment: Fragment) {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .replace(id, fragment)
                .commit()
        }

        fun replaceFragment(context: Context, id: Int, fragment: Fragment, TAG: String) {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .replace(id, fragment, TAG)
                .commit()
        }

        fun removeFragment(context: Context, fragment: Fragment) {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .remove(fragment)
                .commit()
        }

        fun openActivity(context: Context, destination: Class<Any>) {
            val intent = Intent(context, destination)
            context.startActivity(intent)
        }

    }

}