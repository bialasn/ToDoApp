package com.nbialas.listoapp.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbialas.listoapp.R
import com.nbialas.listoapp.adapters.ThingToDoDataAdapter
import com.nbialas.listoapp.constant.Constant.BUNDLE_KEY
import com.nbialas.listoapp.viewModels.HomeViewModel
import kotlinx.android.synthetic.main.home_page.*

class HomePage : Fragment() {
    private lateinit var viewModel: HomeViewModel

    private val thingAdapter by lazy { ThingToDoDataAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setOnClickListener()
        setObservers()
    }

    private fun setObservers() {
        viewModel.thingToDoDao.getAll().observe(viewLifecycleOwner, Observer {
            thingAdapter.setData(it)
        })
    }

    private fun setAdapter() {
        thingToDoRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@HomePage.thingAdapter
        }
        thingAdapter.onClickAction = {
            viewModel.updateThingCounter(it)
            findNavController().navigate(R.id.detailsPage, bundleOf(Pair(BUNDLE_KEY, it.uniqueID)))
        }
        thingAdapter.removeItem = {
            viewModel.removeItem(it)
        }
    }

    private fun setOnClickListener() {
        addThingButton.setOnClickListener {
            viewModel.addThingToDo()
        }
    }
}
