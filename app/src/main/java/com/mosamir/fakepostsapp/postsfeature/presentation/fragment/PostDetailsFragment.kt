package com.mosamir.fakepostsapp.postsfeature.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mosamir.fakepostsapp.R
import com.mosamir.fakepostsapp.databinding.FragmentPostDetailsBinding
import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem
import com.mosamir.fakepostsapp.postsfeature.presentation.common.PostViewModel
import com.mosamir.fakepostsapp.util.IResult
import com.mosamir.fakepostsapp.util.NetworkState
import com.mosamir.fakepostsapp.util.getData
import com.mosamir.fakepostsapp.util.showToastLong
import com.mosamir.fakepostsapp.util.showToastShort
import com.mosamir.fakepostsapp.util.visibilityGone
import com.mosamir.fakepostsapp.util.visibilityVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    private var _binding: FragmentPostDetailsBinding? = null
    private val binding get() = _binding!!
    private val postViewModel by viewModels<PostViewModel>()
    private val args by navArgs<PostDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postViewModel.getPostById(args.postId)
        postObserve()

    }

    private fun postObserve(){
        lifecycleScope.launch {
            postViewModel.getPostById.collect{ networkState ->
                when(networkState?.status){
                    NetworkState.Status.SUCCESS ->{
                        binding.postDetailsProgressBar.visibilityGone()
                        val data = networkState.data as IResult<FakePostsItem>
                        updateUI(data.getData()!!)
                    }
                    NetworkState.Status.FAILED ->{
                        showToastShort(networkState.msg.toString())
                        binding.postDetailsProgressBar.visibilityGone()
                    }
                    NetworkState.Status.RUNNING ->{
                        binding.postDetailsProgressBar.visibilityVisible()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun updateUI(item: FakePostsItem){
        binding.postTitle.text = item.title
        binding.postBody.text = item.body
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}