package com.musicore.musicoreandroid.view


import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.musicore.musicoreandroid.R
import com.musicore.musicoreandroid.model.ProductsItem
import com.musicore.musicoreandroid.view.adapter.productDetail.SliderWithAnimAdapter
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_detail.*

class ProductDetailFragment : Fragment() {
    //==============================================================================================
    /**
     * Fragment onCreateView Method..
     */
    //==============================================================================================
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    //==============================================================================================
    /**
     * Fragment OnViewCreated Method..
     */
    //==============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            it.getString("strChat")?.let { itemOfString ->

                val products = Gson().fromJson(itemOfString, ProductsItem::class.java)

                setTagView(products.freeCargo)
                setSliderData(products.imageUrls)
                setBrandName(products.brandName)
                setProductname(products.name)
                setRatingData(products.averageRating, products.ratingCount)
                setPromotionsMessage(products.promotionMessage)
                setBottomButtonData(products)


            }
        }
    }

    //==============================================================================================
    /**
     * Set Tag View Data If Exist..
     */
    //==============================================================================================
    private fun setTagView(freeCargo: Boolean?) {
        freeCargo?.let {
            if (it){
                view1.visibility = View.VISIBLE
                tagView.visibility = View.VISIBLE
            }else{
                view1.visibility = View.INVISIBLE
                tagView.visibility = View.INVISIBLE
            }
        } ?: run {
            view1.visibility = View.INVISIBLE
            tagView.visibility = View.INVISIBLE
        }
    }

    //==============================================================================================
    /**
     * Set Slider Data from ImageURLs..
     */
    //==============================================================================================
    private fun setSliderData(images: MutableList<String>) {
        sliderList.setIndicatorAnimation(IndicatorAnimations.THIN_WORM)
        sliderList.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderList.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
        sliderList.scrollTimeInSec = 1
        sliderList.indicatorSelectedColor = Color.parseColor("#da6d21")
        sliderList.indicatorUnselectedColor = Color.parseColor("#ffffff")
        sliderList.isAutoCycle = false

        sliderList.setSliderAdapter(SliderWithAnimAdapter(activity?.applicationContext!!, images))
    }

    //==============================================================================================
    /**
     * Set BrandName..
     */
    //==============================================================================================
    private fun setBrandName(brandNameData: String) {
        brandName.text = brandNameData
    }

    //==============================================================================================
    /**
     * set Name Data..
     */
    //==============================================================================================
    private fun setProductname(nameData: String) {
        name.text = nameData
    }

    //==============================================================================================
    /**
     * Set Rating Data..
     */
    //==============================================================================================
    @SuppressLint("SetTextI18n")
    private fun setRatingData(averageRatingData: Double, ratingCountData: Int) {
        ratingAvarage.text = averageRatingData.toString()
        ratingBar.rating = averageRatingData.toFloat()
        ratingCount.text = "$ratingCountData Değerlendirme"
    }

    //==============================================================================================
    /**
     * Set PromotionMessage Data If Exist..
     */
    //==============================================================================================
    @SuppressLint("SetTextI18n")
    private fun setPromotionsMessage(promotionsMessageData: String?) {

        promotionsMessageData?.let {
            promotionsMessage.text = "$promotionsMessageData >"
        } ?: run {
            promotionsMessage.visibility = View.INVISIBLE
        }
    }

    //==============================================================================================
    /**
     * Set Bottom Button Data..
     */
    //==============================================================================================
    @SuppressLint("SetTextI18n")
    private fun setBottomButtonData(productsItem: ProductsItem) {
        productsItem.discountPercentage?.let {
            if (it == "-0%") {
                discountedPercentageConstraint.visibility = View.INVISIBLE
                zeroDiscountedPercentageConstraint.visibility = View.VISIBLE
                basketConstraint.visibility = View.INVISIBLE

                salePriceOrginal.text = "${productsItem.salePrice} TL"

            } else {
                discountedPercentageConstraint.visibility = View.VISIBLE
                zeroDiscountedPercentageConstraint.visibility = View.INVISIBLE
                basketConstraint.visibility = View.INVISIBLE

                discountedPercentage.text = it
                marketPrice.text = "${productsItem.marketPrice} TL"
                salePriceDiscounted.text = "${productsItem.salePrice} TL"
            }
        }

        productsItem.promotionMessage?.let {
            discountedPercentageConstraint.visibility = View.INVISIBLE
            zeroDiscountedPercentageConstraint.visibility = View.INVISIBLE
            basketConstraint.visibility = View.VISIBLE

            salePrice.text = "${productsItem.salePrice} TL"
            discoundedPrice.text = "${productsItem.discountedPrice} TL"
            infoPromotionsMessage.text = productsItem.promotionMessage

            salePrice.paintFlags = salePrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

}
