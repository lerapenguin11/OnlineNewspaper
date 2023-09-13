package com.example.onlinenewspaper.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.business.database.AppDatabase
import com.example.onlinenewspaper.business.model.FavoriteModel
import com.example.onlinenewspaper.business.model.repos.NewsRepository
import com.example.onlinenewspaper.databinding.FragmentHomeBinding
import com.example.onlinenewspaper.databinding.FragmentSaveBinding
import com.example.onlinenewspaper.presentation.adapter.FavoriteAdapter
import com.example.onlinenewspaper.presentation.adapter.HomePagerAdapter
import com.example.onlinenewspaper.presentation.adapter.listener.FavListener
import com.example.onlinenewspaper.viewModel.FavViewModelFactory
import com.example.onlinenewspaper.viewModel.NewsViewModel
import com.example.onlinenewspaper.viewModel.NewsViewModelFactory
import com.example.onlinenewspaper.viewModel.SaveViewModel

class SaveFragment : Fragment(), FavListener {
    private var _binding : FragmentSaveBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : FavoriteAdapter
    private lateinit var viewModelFav : SaveViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSaveBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getDatabase(application).newsDao()
        val favoriteDao = AppDatabase.getDatabase(application).favoriteDao()
        val repository = NewsRepository(dao, favoriteDao)

        val viewModelFactoryFav = FavViewModelFactory(repository)
        viewModelFav = ViewModelProvider(this, viewModelFactoryFav).get(SaveViewModel::class.java)

        adapter = FavoriteAdapter(viewModelFav, this)

        observeDataSave()

        return binding.root
    }

    private fun observeDataSave() {
        binding.rvTrending.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTrending.adapter = adapter

        viewModelFav.allFavorites.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                adapter.setItem(it)
            }
        })

        val swipeToDeleteCallback = adapter.SwipeToDeleteCallback(requireContext())
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvTrending)
    }

    override fun getDetailFav(fav: FavoriteModel) {
        val dialog = Dialog(requireContext(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(com.example.onlinenewspaper.R.layout.full_screen_deails_new)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        dialog.window?.statusBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)

        val icon : ImageView = dialog.findViewById(com.example.onlinenewspaper.R.id.icon_news)
        val titleNews : TextView = dialog.findViewById(com.example.onlinenewspaper.R.id.tv_title_news_detail)
        val textNews : TextView = dialog.findViewById(com.example.onlinenewspaper.R.id.tv_text_news_detail)
        val dateNews : TextView = dialog.findViewById(com.example.onlinenewspaper.R.id.tv_date_detail)
        val btArrow : ImageView = dialog.findViewById(com.example.onlinenewspaper.R.id.ic_arrow)
        val btSave : ConstraintLayout = dialog.findViewById(com.example.onlinenewspaper.R.id.bt_save)

        btSave.visibility = View.GONE
        btArrow.setOnClickListener { dialog.cancel() }

        Glide.with(requireContext()).load(fav.icon).into(icon)
        titleNews.setText(fav.title)
        textNews.setText(fav.text)
        dateNews.setText(fav.date)

        dialog.show()
    }
}