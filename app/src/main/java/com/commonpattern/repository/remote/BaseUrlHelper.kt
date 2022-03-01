
package com.commonpattern.repository.remote

import java.net.URL

object BaseUrlHelper {
  const val REMOTE_URL="https://developers.zomato.com/api/v2.1/"
  private const val GOOGLE_MAP="https://maps.googleapis.com/maps/api/js?key=API_KEY=places"
  var BASE_URL:String?= REMOTE_URL
}