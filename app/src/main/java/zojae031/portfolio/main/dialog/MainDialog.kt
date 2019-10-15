package zojae031.portfolio.main.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.user_list_dialog.*
import org.koin.android.ext.android.inject
import zojae031.portfolio.BR
import zojae031.portfolio.R
import zojae031.portfolio.base.BaseActivity
import zojae031.portfolio.base.BaseRecyclerViewAdapter
import zojae031.portfolio.data.Repository
import zojae031.portfolio.data.dao.main.MainUserEntity
import zojae031.portfolio.databinding.UserListBinding
import zojae031.portfolio.databinding.UserListDialogBinding
import zojae031.portfolio.main.MainActivity
import zojae031.portfolio.util.UrlUtil


class MainDialog :
    BaseActivity<UserListDialogBinding>(R.layout.user_list_dialog) {
    private val repository: Repository by inject()
    private val urlUtil: UrlUtil by inject()
    private val mainDialogViewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainDialogViewModel(
                    repository,
                    urlUtil
                ) as T
            }
        }).get(MainDialogViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            vm = mainDialogViewModel.apply {

                error.observe(this@MainDialog, Observer {
                    Toast.makeText(this@MainDialog, it, Toast.LENGTH_SHORT).show()
                })

                userName.observe(this@MainDialog, Observer {
                    Toast.makeText(
                        this@MainDialog,
                        "$it 님의 포트폴리오를 확인합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    Intent(
                        this@MainDialog,
                        MainActivity::class.java
                    ).apply {
                        startActivity(this)
                        finish()
                    }
                })

            }.also { it.onCreate() }
        }

        recyclerView.adapter =
            object : BaseRecyclerViewAdapter<MainUserEntity, UserListBinding>(
                R.layout.user_list,
                BR.userData
            ) {

            }
    }

    override fun onPause() {
        mainDialogViewModel.onPause()
        super.onPause()
    }

    companion object {
        fun getIntent(context: Context) =
            Intent(context, MainDialog::class.java)
    }
}