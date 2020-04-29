package com.nbialas.listoapp.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nbialas.listoapp.R
import com.nbialas.listoapp.constant.Constant
import com.nbialas.listoapp.viewModels.DetailsViewModel
import kotlinx.android.synthetic.main.details_page.*

class DetailsPage : Fragment() {
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        getArgumentsFromBundle()
        return inflater.inflate(R.layout.details_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        viewModel.thingToDoDao.getSingleThing(viewModel.thingID)
            .observe(viewLifecycleOwner, Observer {
                thingName.text = it.name
                thingID.text = it.uniqueID
                counter.text = resources.getString(R.string.open_counter, it.openCounter.toString())
                viewModel.setTime(it.creationDate)
            })

        viewModel.passedTime.observe(viewLifecycleOwner, Observer {
            time.text = it
        })
    }

    private fun getArgumentsFromBundle() {
        arguments?.getString(Constant.THING_KEY)?.let {
            viewModel.thingID = it
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.calculateTime().cancel()
    }
}