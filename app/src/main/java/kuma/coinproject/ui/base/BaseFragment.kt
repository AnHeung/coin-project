package kuma.coinproject.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

//기본 fragment 클래스 livedata 와 inflate 를 진행한다.
abstract class BaseFragment<V: ViewDataBinding> : Fragment(){

    protected lateinit var dataBinding: V

    abstract var layoutId: Int

    abstract fun bindingLiveData()

    abstract fun initBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return with(dataBinding){
            lifecycleOwner = viewLifecycleOwner
            initBinding()
            bindingLiveData()
            executePendingBindings()
            dataBinding.root
        }
    }

    fun showSnackBar(msg:String?){
        msg?.let { Snackbar.make(dataBinding.root, it, Snackbar.LENGTH_SHORT).show() }
    }
}