package zojae031.portfolio.tec

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.synthetic.main.fragment_tec.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import zojae031.portfolio.R
import zojae031.portfolio.base.BaseFragment
import zojae031.portfolio.base.BaseRecyclerViewAdapter
import zojae031.portfolio.data.dao.tec.TecEntityOnListener
import zojae031.portfolio.databinding.FragmentTecBinding
import zojae031.portfolio.databinding.TecListBinding

class TecFragment : BaseFragment<FragmentTecBinding>(R.layout.fragment_tec) {

    private val tecViewModel by viewModel<TecViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = tecViewModel
        recycler.adapter = object : BaseRecyclerViewAdapter<TecEntityOnListener, TecListBinding>(
            R.layout.tec_list,
            BR.tecEntity
        ) {

        }
    }

    override fun onPause() {
        tecViewModel.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        tecViewModel.onResume()
    }

}