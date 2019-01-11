package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.ExhibitionArtworkData
import com.artoo.sopt23.artoo_client_android.Data.ExhibitionDisplayData

data class GetApplyExhibitionResponse (
        val status : Int,
        val message : String,
        val data : ApplyExhibitionViewData
)

data class ApplyExhibitionViewData (
        val displays : ArrayList<ExhibitionDisplayData>,
        val artworks : ArrayList<ExhibitionArtworkData>
)