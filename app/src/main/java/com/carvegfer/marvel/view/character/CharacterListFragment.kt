package com.carvegfer.marvel.view.character

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.carvegfer.marvel.R
import com.carvegfer.marvel.api.ApiClient
import com.carvegfer.marvel.api.model.CharacterResponse
import com.carvegfer.marvel.databinding.FragmentCharacterListBinding
import com.carvegfer.marvel.databinding.ViewholderCharacterRowBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null

    @NonNull
    var disposable: Disposable? = null

    private var listCharacter : List<CharacterResponse>? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerViewCharacters

        disposable = ApiClient.instance.getListOfCharacters(0,0).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listResponse ->
                val responseCode = listResponse.code()
                when (responseCode) {
                    200, 201, 202 -> {
                        setupRecyclerView(recyclerView)
                        listCharacter = listResponse.body()
                    }
                    401 -> { }
                    402 -> { }
                    500 -> { }
                    501 -> { }
                }
            }, { error ->
                if (error is HttpException) {
                    val response = (error as HttpException).response()
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

    override fun onStop() {
        if (disposable != null) {
            disposable!!.dispose()
        }
        super.onStop()
    }

    private fun setupRecyclerView(
            recyclerView: RecyclerView
    ) {

        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            listCharacter!!
        )
    }

    class SimpleItemRecyclerViewAdapter(
            private val values: List<CharacterResponse>,
    ) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding = ViewholderCharacterRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item : CharacterResponse = values[position]
            holder.textViewName.text = item.name
            holder.textViewDescription.text = item.description
            holder.textViewDateModified.text = item.modified

            with(holder.itemView) {
                tag = item
                setOnClickListener { itemView ->
                    val item = itemView.tag as CharacterResponse
                    val bundle = Bundle()
                    bundle.putInt(
                        CharacterDetailFragment.ARG_ITEM_ID,
                            item.id
                    )
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    setOnContextClickListener { v ->
                        val item = v.tag as CharacterResponse
                        Toast.makeText(
                                v.context,
                                "Context click of item " + item.id,
                                Toast.LENGTH_LONG
                        ).show()
                        true
                    }
                }
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(binding: ViewholderCharacterRowBinding) : RecyclerView.ViewHolder(binding.root) {
            val textViewName: TextView = binding.textViewName
            val textViewDescription: TextView = binding.textViewDescription
            val textViewDateModified : TextView = binding.textViewModified
            val imageViewCharacter : ImageView = binding.imageViewCharacter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}