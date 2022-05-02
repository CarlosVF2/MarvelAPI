package com.carvegfer.marvel.view.character

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.carvegfer.marvel.api.ApiClient
import com.carvegfer.marvel.api.model.CharacterResponse
import com.carvegfer.marvel.databinding.FragmentItemDetailBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CharacterDetailFragment : Fragment() {

    private var item: CharacterResponse? = null

    lateinit var itemDetailTextView: TextView
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                ApiClient.instance.getCharacter(it.getInt(ARG_ITEM_ID)).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        val responseCode = response.code()
                        when (responseCode) {
                            200, 201, 202 -> {
                                item = response.body()
                                updateContent()
                            }
                            401 -> { }
                            402 -> { }
                            500 -> { }
                            501 -> { }
                        }
                    }, { error ->
                        if (error is HttpException) {
                            val response = error.response()
                            if (response != null) {
                                when (response.code()) {
                                    //Responce Code
                                }
                            }
                        } else {
                            //Handle Other Errors
                        }
                    })
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout
        itemDetailTextView = binding.itemDetail

        return rootView
    }

    private fun updateContent() {
        toolbarLayout?.title = item?.name

        item?.let {
            itemDetailTextView.text = it.description
        }
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}