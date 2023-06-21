package uz.rounded.montekrist.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.rounded.montekrist.R
import uz.rounded.montekrist.databinding.FragmentHomeBinding
import uz.rounded.montekrist.domain.model.starship.Starship
import uz.rounded.montekrist.domain.model.starship.StarshipModel
import uz.rounded.montekrist.presentation.base.BaseFragment
import uz.rounded.montekrist.presentation.extensions.SharedPref
import uz.rounded.montekrist.presentation.extensions.gone
import uz.rounded.montekrist.presentation.extensions.visible
import uz.rounded.montekrist.presentation.ui.adapter.RecyclerViewItemClickListener
import uz.rounded.montekrist.presentation.ui.adapter.StarshipAdapter
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), RecyclerViewItemClickListener {

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPref

    private var isShown = false

    private val adapter by lazy {
        StarshipAdapter(this)
    }

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        isShown = sharedPref.get(sharedPref.isShown, false)
        hideGuide()
        setRecycler()
        getStarship()
        binding.search.setOnClickListener { findNavController().navigate(R.id.searchFragment) }
    }

    private fun setRecycler() {
        binding.recyclerView.adapter = adapter
    }

    private fun getStarship() {
        lifecycleScope.launch {
            viewModel.starship.collectLatest {
                showProgress()
                it.data?.let { p ->
                    hideProgress()
                    adapter.submitList(p)
                    if (!isShown) showGuide()
                }
                if (it.error.isNotBlank()) {
                    showToast(it.error)
                    hideProgress()
                }
            }
        }
    }

    private fun showGuide() {
        binding.guide.visible()
        binding.gif.visible()
    }

    private fun hideGuide() {
        binding.guide.gone()
        binding.gif.gone()
    }

    override fun onItemClick(position: Int) {
        hideGuide()
    }

    override fun onItemDoubleTap(starshipModel: StarshipModel) {
        hideGuide()
        showToast(starshipModel.name)
        lifecycleScope.launch {
            sharedPref.save(sharedPref.isShown, true)
            viewModel.insertStarship(
                Starship(
                    id = starshipModel._id,
                    model = starshipModel.model,
                    name = starshipModel.name,
                    passengers = starshipModel.passengers,
                    starshipModel.starship_class,
                    starshipModel.url,
                    starshipModel.cost_in_credits,
                )
            ).collect {
                showToast(it)
            }
        }
    }

    fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

}