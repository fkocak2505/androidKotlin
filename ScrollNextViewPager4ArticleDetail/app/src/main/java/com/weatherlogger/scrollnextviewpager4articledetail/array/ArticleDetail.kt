package com.weatherlogger.scrollnextviewpager4articledetail.array

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.weatherlogger.scrollnextviewpager4articledetail.R
import kotlinx.android.synthetic.main.fragment_doppelganger.view.*
import java.util.*


class ArticleDetail : Fragment() {

    private var mActivity: Activity? = null

    private lateinit var toolBar: Toolbar

    private lateinit var viewP: View

    private var isAddedFavorite: Boolean = false

    private var pos = 0

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int, legacyId: Long): Fragment {
            val doppelgangerFragment =
                ArticleDetail()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            bundle.putLong("ARTICLE_DATA", legacyId)
            doppelgangerFragment.arguments = bundle
            return doppelgangerFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP = inflater.inflate(R.layout.fragment_doppelganger, container, false)

        return viewP
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pos = requireArguments().getInt(ARG_POSITION)

        viewP.positionInfo.text = pos.toString()

        //prepareToolbar()


    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        Handler().postDelayed({

            val bundle = this.arguments
            val legacyId = bundle?.getLong("ARTICLE_DATA")
            setData(legacyId!!)

        }, 500)

        isAddedFavorite = Random().nextBoolean()

    }

    private fun setData(legacyId: Long) {

        viewP.articleInfo.text = legacyId.toString()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            getData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        if(isAddedFavorite)
            menu.removeItem(R.id.actionAddFavorite)
        else
            menu.removeItem(R.id.actionDeleteFavorite)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        if(isAddedFavorite)
            menu.findItem(R.id.actionDeleteFavorite).setIcon(R.drawable.ic_added_favorite)
        else
            menu.findItem(R.id.actionAddFavorite).setIcon(R.drawable.ic_delete_favorited)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionAddFavorite -> {
                Toast.makeText(
                    mActivity?.applicationContext,
                    "Addddd Favorite $pos",
                    Toast.LENGTH_SHORT
                )
                    .show()

                aaa()

            }
            R.id.actionDeleteFavorite -> {
                Toast.makeText(
                    mActivity?.applicationContext,
                    "Deleteeeee Favorite $pos",
                    Toast.LENGTH_SHORT
                )
                    .show()

                aaa()
            }
            R.id.actionShare -> {
                Toast.makeText(
                    mActivity?.applicationContext,
                    "Shareeeee Favorite $pos",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun aaa(){
        isAddedFavorite = !isAddedFavorite
        mActivity?.invalidateOptionsMenu()
    }
}
