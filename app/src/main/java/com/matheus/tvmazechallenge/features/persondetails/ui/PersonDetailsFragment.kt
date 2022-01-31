package com.matheus.tvmazechallenge.features.persondetails.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.databinding.PersonDetailsLayoutBinding
import com.matheus.tvmazechallenge.features.persondetails.viewmodel.PersonDetailsViewModel
import com.matheus.tvmazechallenge.features.persondetails.ui.adapter.CastCreditsShowAdapter
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonDetailsFragment : BottomSheetDialogFragment() {

    private val castCreditsAdapter = CastCreditsShowAdapter()
    private val viewModel: PersonDetailsViewModel by viewModel()
    private val args: PersonDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = PersonDetailsLayoutBinding.inflate(inflater, container, false).also {
        configureBindings(it)
        configureCastCreditsListener(it)
        fetchPersonCastCredit()
    }.root

    private fun configureBindings(binding: PersonDetailsLayoutBinding) = with(binding) {
        person = args.person
        personDetailsRvCastCredits.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = castCreditsAdapter.apply {
                seeMoreDetailsClickListener = ::openTVShowDetails
                openTVShowOnWebClickListener = ::openUrlOnWeb
            }
        }
        personDetailsIvClose.setOnClickListener {
            findNavController().popBackStack()
        }
        personDetailsEmRetry.setOnRetryClickListener { fetchPersonCastCredit() }
        peopleDetailsBtOpenOnWeb.setOnClickListener { openUrlOnWeb(args.person.url) }
    }

    private fun openTVShowDetails(tvShowEntity: TVShowEntity) =
        PersonDetailsFragmentDirections.actionPersonDetailToTvShowDetails(tvShowEntity)
            .let {
                findNavController().navigate(it)
            }

    private fun openUrlOnWeb(url: String) = Intent(Intent.ACTION_VIEW).let {
        it.data = Uri.parse(url)
        startActivity(it)
    }

    private fun configureCastCreditsListener(binding: PersonDetailsLayoutBinding) = with(binding) {
        viewModel.castCreditsResult.observe(viewLifecycleOwner) {
            when (it) {
                is StateData.Success -> {
                    isLoadingItems = false
                    thereIsError = false
                    castCreditsAdapter.addItems(it.data)
                }
                is StateData.Loading -> {
                    thereIsError = false
                    isLoadingItems = true
                }
                is StateData.Failure -> {
                    thereIsError = true
                    isLoadingItems = false
                    if (it == Failure.thereAreNoCastCreditsFailure) {
                        personDetailsEmRetry.hideRetryButton()
                    }
                    personDetailsEmRetry.setErrorMessage(it.message)
                }
            }
        }
    }

    private fun fetchPersonCastCredit() = viewModel.fetchPersonCastCredit(args.person.id)
}