package com.musicore.musicoreandroid.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.musicore.musicoreandroid.R
import com.musicore.musicoreandroid.manager.PreCachingLayoutManager
import com.musicore.musicoreandroid.model.WidgetsItem
import com.musicore.musicoreandroid.utils.DeviceUtils
import com.musicore.musicoreandroid.view.adapter.ProductListAdapter
import com.musicore.musicoreandroid.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ProductListFragment : Fragment() {

    @Inject
    lateinit var viewModel: ProductListViewModel

    private lateinit var playListAdapter: ProductListAdapter

    //==============================================================================================
    /**
     * Fragment onCreateView Method..
     */
    //==============================================================================================
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    //==============================================================================================
    /**
     * Fragment OnViewCreated Method..
     */
    //==============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
        viewModel.refresh()

        prepareProductRecyclerView()

        observeViewModel()

    }

    //==============================================================================================
    /**
     * Prepare RecyclerView Data..
     */
    //==============================================================================================
    private fun prepareProductRecyclerView() {
        val mLayoutManager =
            PreCachingLayoutManager(activity?.applicationContext!!, RecyclerView.VERTICAL, false)

        mLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(activity?.applicationContext))

        playListAdapter = ProductListAdapter(
            activity?.applicationContext!!,
            arrayListOf()
        ) { view, itemOfData, type ->
            handleClickRecyclerItem(view, type, itemOfData)
        }

        productList.apply {
            layoutManager = mLayoutManager
            setItemViewCacheSize(5)
            adapter = playListAdapter
        }
    }

    //==============================================================================================
    /**
     * Handle Banner Single Type widget click listener
     */
    //==============================================================================================
    private fun handleClickRecyclerItem(view: View, type: String, itemOfData: WidgetsItem) {
        when (type) {
            "BANNER_SINGLE" -> {
                val sharedPreferences =
                    activity?.applicationContext?.getSharedPreferences(
                        "DUMMY",
                        Context.MODE_PRIVATE
                    )
                val productData = sharedPreferences?.getString("PRODUCT_ITEM", "")

                Navigation.findNavController(view).navigate(
                    ProductListFragmentDirections.actionDetailFragment(productData)
                )
            }
        }
    }

    //==============================================================================================
    /**
     * Observer Data from API..
     */
    //==============================================================================================
    private fun observeViewModel() {
        viewModel.playList.observe(viewLifecycleOwner, Observer { playListData ->
            playListData?.let {
                productList.visibility = View.VISIBLE

                val listOfProductType: MutableList<WidgetsItem> = mutableListOf()
                playListData.forEach { itemOfWidgetsItem ->
                    when (itemOfWidgetsItem.type + " " + itemOfWidgetsItem.displayType) {
                        "BANNER SINGLE" -> {
                            listOfProductType.add(itemOfWidgetsItem)
                        }
                        "BANNER SLIDER" -> {
                            listOfProductType.add(itemOfWidgetsItem)
                        }
                        "PRODUCT SLIDER" -> {
                            listOfProductType.add(itemOfWidgetsItem)

                            setDummyData4BannerType(itemOfWidgetsItem)

                        }
                        "PRODUCT LISTING" -> {
                            listOfProductType.add(itemOfWidgetsItem)
                        }
                    }
                }

                playListAdapter.updatePlayList(listOfProductType)
            }
        })

        viewModel.playListError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    listError.visibility = View.GONE
                    productList.visibility = View.GONE
                }
            }
        })
    }

    //==============================================================================================
    /**
     * Set Dummy Data for Banner Type Widget..
     */
    //==============================================================================================
    private fun setDummyData4BannerType(itemOfData: WidgetsItem) {
        val prefences = activity?.applicationContext?.getSharedPreferences(
            "DUMMY",
            Context.MODE_PRIVATE
        )
        val editor = prefences?.edit()
        editor?.putString(
            "PRODUCT_ITEM",
            Gson().toJson(itemOfData.products[12])
        )?.apply()
    }
}
