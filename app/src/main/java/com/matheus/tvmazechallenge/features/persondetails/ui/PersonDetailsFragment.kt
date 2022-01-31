package com.matheus.tvmazechallenge.features.persondetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.databinding.PersonDetailsLayoutBinding

class PersonDetailsFragment : BottomSheetDialogFragment() {

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
        it.name = args.person.name
    }.root
}