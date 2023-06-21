package uz.rounded.montekrist.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.rounded.montekrist.databinding.FragmentSearchBinding
import uz.rounded.montekrist.domain.model.starship.StarshipModel
import uz.rounded.montekrist.presentation.base.BaseFragment
import uz.rounded.montekrist.presentation.extensions.gone
import uz.rounded.montekrist.presentation.extensions.visible
import uz.rounded.montekrist.presentation.ui.adapter.RecyclerViewItemClickListener
import uz.rounded.montekrist.presentation.ui.adapter.StarshipAdapter

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(), RecyclerViewItemClickListener {

    private val viewModel: SearchViewModel by viewModels()

    private val adapter by lazy {
        StarshipAdapter(this)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.adapter = adapter
        binding.lottie.visible()
        close()

        search()
        clickClose()

        binding.backBtn.setOnClickListener { findNavController().popBackStack() }
    }

    private fun search() = binding.search.addTextChangedListener {
        binding.lottie.gone()
        lifecycleScope.launch {
            viewModel.searchStarship(it.toString()).collectLatest { result ->
                adapter.submitList(emptyList())
                adapter.submitList(result)
            }
            if (it.toString().isEmpty()) clickClose() else binding.close.visible()

        }
    }

    override fun onItemClick(position: Int) {

    }

    override fun onItemDoubleTap(starshipModel: StarshipModel) {
//        showToast(name)
    }

    private fun clickClose() = binding.close.setOnClickListener {
        binding.search.setText("")
        close()
    }

    private fun close() = binding.apply {
        close.gone()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    override fun onResume() {
        super.onResume()
        close()
    }

    override fun onStop() {
        super.onStop()
        close()
    }

    override fun onStart() {
        super.onStart()
        close()
    }
}