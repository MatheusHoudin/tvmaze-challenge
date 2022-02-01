package com.matheus.tvmazechallenge.features.people.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.matheus.tvmazechallenge.databinding.GenericImageDescriptionListFragmentBinding
import com.matheus.tvmazechallenge.features.people.viewmodel.PeopleViewModel
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.util.EndOfScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class PeopleFragment : Fragment() {

    private val peopleAdapter = PeopleAdapter()
    private val viewModel: PeopleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = GenericImageDescriptionListFragmentBinding.inflate(inflater, container, false).also {
        configureBindings(it)
        configurePeopleListener(it)
    }.root

    private fun configureBindings(binding: GenericImageDescriptionListFragmentBinding) =
        with(binding) {
            thereIsError = false
            genericImageDescriptionListFragmentEmRetry.setOnRetryClickListener { fetchPeople() }
            genericImageDescriptionListFragmentRvShows.apply {
                val peopleLayoutManager = GridLayoutManager(context, PEOPLE_PER_ROW)
                layoutManager = peopleLayoutManager
                adapter = peopleAdapter.apply {
                    onClickListener = { personEntity ->
                        PeopleFragmentDirections.actionPeopleToPeopleDetails(personEntity).let {
                            findNavController().navigate(it)
                        }
                    }
                }
                addOnScrollListener(EndOfScrollListener(peopleLayoutManager) {
                    fetchPeople()
                })
            }
        }

    private fun configurePeopleListener(binding: GenericImageDescriptionListFragmentBinding) =
        with(binding) {
            viewModel.peopleResult.observe(viewLifecycleOwner) {
                when (it) {
                    is StateData.Success -> {
                        isLoadingItems = false
                        thereIsError = false
                        peopleAdapter.addItems(it.data)
                    }
                    is StateData.Loading -> {
                        thereIsError = false
                        isLoadingItems = true
                    }
                    is StateData.Failure -> {
                        genericImageDescriptionListFragmentEmRetry.setErrorMessage(it.message)
                        thereIsError = true
                        isLoadingItems = false
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchPeople()
    }

    private fun fetchPeople() = viewModel.fetchPeople()

    private companion object {
        const val PEOPLE_PER_ROW = 2
    }
}