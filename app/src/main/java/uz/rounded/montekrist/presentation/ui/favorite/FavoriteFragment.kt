package uz.rounded.montekrist.presentation.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.rounded.montekrist.databinding.FragmentFavoriteBinding
import uz.rounded.montekrist.domain.model.starship.Starship
import uz.rounded.montekrist.presentation.base.BaseFragment
import uz.rounded.montekrist.presentation.ui.adapter.FavoriteAdapter
import uz.rounded.montekrist.presentation.ui.adapter.FavoriteItemClickListener

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(), FavoriteItemClickListener {

    private val viewModel: FavoriteViewModel by viewModels()

    private val adapter by lazy {
        FavoriteAdapter(this)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.savedRv.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.myListFlow.collect {
                it.collectLatest {
                    Log.d("reloaddddd", "created: $it")
                    adapter.submitList(it)
                }
            }
        }
    }

    override fun onItemClick(position: Int) {

    }

    override fun onItemDoubleTap(starshipModel: Starship) {

    }
}