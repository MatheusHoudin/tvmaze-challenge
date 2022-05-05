package com.matheus.tvmazechallenge.features.persondetails.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.databinding.PersonDetailsLayoutBinding
import com.matheus.tvmazechallenge.features.people.entity.PersonEntity
import com.matheus.tvmazechallenge.features.persondetails.ui.components.CastCreditsShow
import com.matheus.tvmazechallenge.features.persondetails.ui.components.CastCreditsShowAdapter
import com.matheus.tvmazechallenge.features.persondetails.viewmodel.PersonDetailsViewModel
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailsFragment : Fragment() {

    private val viewModel: PersonDetailsViewModel by viewModels()

    private var personEntity: PersonEntity? = null

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
        personEntity = activity?.intent?.getParcelableExtra("person")
        person = personEntity
        personDetailsRvCastCredits.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        personDetailsEmRetry.setOnRetryClickListener { fetchPersonCastCredit() }
        peopleDetailsBtOpenOnWeb.setOnClickListener { openUrlOnWeb(personEntity?.url.orEmpty()) }
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
                    personDetailsRvCastCredits.setContent {
                        CastCreditsShow(
                            castCredits = it.data,
                            onSeeMoreShowDetailsClicked = ::openTVShowDetails,
                            onOpenOnWebClicked = ::openUrlOnWeb
                        )
                    }
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

    private fun fetchPersonCastCredit() = viewModel.fetchPersonCastCredit(personEntity?.id ?: 0)
}