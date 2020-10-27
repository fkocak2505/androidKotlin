package com.musicore.musicoreandroid.model

import com.google.gson.annotations.SerializedName

data class Response4TrendyolData(

	@field:SerializedName("widgets")
	val widgets: MutableList<WidgetsItem>
)

data class Price(

	@field:SerializedName("marketPrice")
	val marketPrice: Double? = null,

	@field:SerializedName("salePrice")
	val salePrice: Double? = null
)

data class VariantsItem(

	@field:SerializedName("campaignId")
	val campaignId: Int? = null,

	@field:SerializedName("price")
	val price: Price? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("listingId")
	val listingId: String? = null,

	@field:SerializedName("variantId")
	val variantId: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class WidgetsItem(

	@field:SerializedName("endDate")
	val endDate: String? = null,

	@field:SerializedName("displayOrder")
	val displayOrder: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("refreshRequired")
	val refreshRequired: Boolean? = null,

	@field:SerializedName("products")
	val products: MutableList<ProductsItem>,

	@field:SerializedName("displayOptions")
	val displayOptions: DisplayOptions? = null,

	@field:SerializedName("marketing")
	val marketing: Marketing? = null,

	@field:SerializedName("displayType")
	val displayType: String? = null,

	@field:SerializedName("eventKey")
	val eventKey: String? = null,

	@field:SerializedName("displayCount")
	val displayCount: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("startDate")
	val startDate: String? = null,

	@field:SerializedName("bannerContents")
	val bannerContents: MutableList<BannerContentsItem>,

	@field:SerializedName("widgetNavigation")
	val widgetNavigation: WidgetNavigation? = null
)

data class Marketing(

	@field:SerializedName("delphoi")
	val delphoi: Delphoi? = null,

	@field:SerializedName("enhanced")
	val enhanced: Enhanced? = null,

	@field:SerializedName("facebook")
	val facebook: Facebook? = null
)

data class ProductsItem(

	@field:SerializedName("colorName")
	val colorName: String? = null,

	@field:SerializedName("mOriginalPrice")
	val mOriginalPrice: Double? = null,

	@field:SerializedName("marketPrice")
	val marketPrice: Double? = null,

	@field:SerializedName("businessUnit")
	val businessUnit: String? = null,

	@field:SerializedName("colorId")
	val colorId: Int? = null,

	@field:SerializedName("contentId")
	val contentId: Int? = null,

	@field:SerializedName("variants")
	val variants: MutableList<VariantsItem?>? = null,

	@field:SerializedName("categoryName")
	val categoryName: String? = null,

	@field:SerializedName("discountPercentage")
	val discountPercentage: String? = null,

	@field:SerializedName("merchantId")
	val merchantId: Int? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("averageRating")
	val averageRating: Double,

	@field:SerializedName("stockStatus")
	val stockStatus: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("discountedPriceInfo")
	val discountedPriceInfo: String? = null,

	@field:SerializedName("stamps")
	val stamps: MutableList<Any?>? = null,

	@field:SerializedName("brandName")
	val brandName: String,

	@field:SerializedName("categoryHierarchy")
	val categoryHierarchy: String? = null,

	@field:SerializedName("salePrice")
	val salePrice: Double,

	@field:SerializedName("freeCargo")
	val freeCargo: Boolean? = null,

	@field:SerializedName("ratingCount")
	val ratingCount: Int,

	@field:SerializedName("marketing")
	val marketing: Marketing? = null,

	@field:SerializedName("promotions")
	val promotions: MutableList<String?>? = null,

	@field:SerializedName("hasScheduledDelivery")
	val hasScheduledDelivery: Boolean? = null,

	@field:SerializedName("discountedPrice")
	val discountedPrice: Double? = null,

	@field:SerializedName("promotionMessage")
	val promotionMessage: String? = null,

	@field:SerializedName("rushDelivery")
	val rushDelivery: Boolean? = null,

	@field:SerializedName("imageUrls")
	val imageUrls: MutableList<String>,

	@field:SerializedName("isGroupColorOptionsActive")
	val isGroupColorOptionsActive: Boolean? = null,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("isDirectCartAdditionAvailable")
	val isDirectCartAdditionAvailable: Boolean? = null,

	@field:SerializedName("mainId")
	val mainId: Int? = null,

	@field:SerializedName("promotionList")
	val promotionList: MutableList<PromotionListItem?>? = null,

	@field:SerializedName("boutiqueId")
	val boutiqueId: Int? = null
)

data class Facebook(

	@field:SerializedName("quantity")
	val quantity: String? = null,

	@field:SerializedName("product_itemnumber")
	val productItemnumber: String? = null,

	@field:SerializedName("item_price")
	val itemPrice: String? = null,

	@field:SerializedName("product_listingid")
	val productListingid: String? = null,

	@field:SerializedName("product_contentid")
	val productContentid: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("product_merchantid")
	val productMerchantid: String? = null,

	@field:SerializedName("product_boutiqueid")
	val productBoutiqueid: String? = null
)

data class PromotionListItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class WidgetNavigation(

	@field:SerializedName("landingTitle")
	val landingTitle: String? = null,

	@field:SerializedName("deeplink")
	val deeplink: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("navigationType")
	val navigationType: String? = null
)

data class Enhanced(

	@field:SerializedName("dimension149")
	val dimension149: String? = null,

	@field:SerializedName("dimension140")
	val dimension140: String? = null,

	@field:SerializedName("dimension141")
	val dimension141: String? = null,

	@field:SerializedName("dimension152")
	val dimension152: String? = null,

	@field:SerializedName("dimension142")
	val dimension142: String? = null,

	@field:SerializedName("dimension155")
	val dimension155: String? = null,

	@field:SerializedName("dimension145")
	val dimension145: String? = null,

	@field:SerializedName("dimension156")
	val dimension156: String? = null,

	@field:SerializedName("dimension146")
	val dimension146: String? = null,

	@field:SerializedName("dimension147")
	val dimension147: String? = null
)

data class BannerContentsItem(

	@field:SerializedName("marketing")
	val marketing: Marketing? = null,

	@field:SerializedName("navigation")
	val navigation: Navigation? = null,

	@field:SerializedName("bannerPosition")
	val bannerPosition: String? = null,

	@field:SerializedName("bannerEventKey")
	val bannerEventKey: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("displayOrder")
	val displayOrder: Int? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class Delphoi(

	@field:SerializedName("tv072")
	val tv072: String? = null,

	@field:SerializedName("tv073")
	val tv073: String? = null,

	@field:SerializedName("tv097")
	val tv097: String? = null,

	@field:SerializedName("tv070")
	val tv070: String? = null,

	@field:SerializedName("tv067")
	val tv067: String? = null
)

data class StampsItem(

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("aspectRatio")
	val aspectRatio: Double? = null,

	@field:SerializedName("position")
	val position: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class Navigation(

	@field:SerializedName("landingTitle")
	val landingTitle: String? = null,

	@field:SerializedName("deeplink")
	val deeplink: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("navigationType")
	val navigationType: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class DisplayOptions(

	@field:SerializedName("showProductFavoredButton")
	val showProductFavoredButton: Boolean? = null,

	@field:SerializedName("showClearButton")
	val showClearButton: Boolean? = null,

	@field:SerializedName("paddingTopBottom")
	val paddingTopBottom: Int? = null,

	@field:SerializedName("paddingRightLeft")
	val paddingRightLeft: Int? = null,

	@field:SerializedName("showProductPrice")
	val showProductPrice: Boolean? = null,

	@field:SerializedName("showCountdown")
	val showCountdown: Boolean? = null
)
