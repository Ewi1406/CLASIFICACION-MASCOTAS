package com.tuuser.chandas.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tuuser.chandas.fragments.ContactFragment
import com.tuuser.chandas.fragments.HomeFragment
import com.tuuser.chandas.fragments.ProfileFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    
    override fun getItemCount(): Int = 3
    
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ProfileFragment()
            2 -> ContactFragment()
            else -> HomeFragment()
        }
    }
}
