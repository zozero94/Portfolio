package zojae031.portfolio.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import zojae031.portfolio.ui.profile.ProfileFragment
import zojae031.portfolio.ui.project.ProjectFragment
import zojae031.portfolio.ui.tec.TecFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragments = listOf(
        ProfileFragment(),
        ProjectFragment(),
        TecFragment()
    )

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
}