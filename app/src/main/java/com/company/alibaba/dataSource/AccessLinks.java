package com.company.alibaba.dataSource;
public interface AccessLinks {

   //String PREFIX ="http://192.168.1.130/aliBabaStore/webService/" ; //Local
   String PREFIX ="https://alibabastor.com/webService/" ; //global

   String POSTFIX =".php" ; //Local

   String GET_HOME_CATEGORIES_DATE  = PREFIX+"getHomeCategoriesData"+POSTFIX;
   String GET_ORDERS                = PREFIX+"getOrders"+POSTFIX;
   String GET_HOME_ADS_INFO         = PREFIX+"getHomeAdsInfo"+POSTFIX;
   String GET_ITEM_INFO_BY_ID       = PREFIX+"getItemInfoById"+POSTFIX;
   String GET_NUM_CART_ITEMS        = PREFIX+"getNumberOfCartItems"+POSTFIX;
   String GET_LAST_ADDED_ITEMS      = PREFIX+"getLastAddedItems"+POSTFIX;
   String GET_ITEMS_FOR_WISHLIST    = PREFIX+"getItemsForWishList"+POSTFIX;
   String GET_ITEMS_FOR_CART        = PREFIX+"getItemsForCart"+POSTFIX;
   String GET_ITEMS_BY_SUB_CATE     = PREFIX+"getItemsBySubCategoryId"+POSTFIX;
   String ADD_TO_WISHLIST           = PREFIX+"addToWishList"+POSTFIX;
   String ADD_TO_CART               = PREFIX+"addToCart"+POSTFIX;
   String SIGN_UP                   = PREFIX+"signUp"+POSTFIX;
   String LOG_IN                    = PREFIX+"logIn"+POSTFIX;
   String REMOVE_ONE                = PREFIX+"decrementCartItemQuantity"+POSTFIX;
   String ADD_MORE_ONE              = PREFIX+"incrementCartItemQuantity"+POSTFIX;
   String ADD_REVIEW                = PREFIX+"addReview"+POSTFIX ;
   String PICTURES_PREFIX           = "https://alibabastor.com/" ;
   String DELETE_ITEM_FROM_CART     = PREFIX+"deleteFromCart"+POSTFIX ;
   String DELETE_ITEM_FROM_WISHLIST = PREFIX+"deleteFromWishList"+POSTFIX ;
   String GET_6_SUPER_CATEGORIES    = PREFIX+"get6SuperCategories"+POSTFIX;
   String GET_PAYMENT_INFO          = PREFIX+"getPaymentInfo"+POSTFIX ;
   String VERIFY_PAYMENT            =  PREFIX+"verifyPayment"+POSTFIX ;
   String ADD_TO_CART_FROM_WISHLISTS= PREFIX+"addToCartFromWishList"+POSTFIX ;
   String GET_LOTTERIES             = PREFIX+"getLotteries"+POSTFIX ;
}