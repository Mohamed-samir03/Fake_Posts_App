package com.mosamir.fakepostsapp.postsfeature.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mosamir.fakepostsapp.databinding.FragmentAllPostsBinding
import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem
import com.mosamir.fakepostsapp.postsfeature.presentation.common.PostAdapter
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
class AllPostsFragment : Fragment() {

    private var _binding: FragmentAllPostsBinding? = null
    private val binding get() = _binding!!
    private val postViewModel by viewModels<PostViewModel>()
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAllPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postAdapter = PostAdapter { selectedItem ->
            val action = AllPostsFragmentDirections.actionAllPostsFragmentToPostDetailsFragment(selectedItem.id)
            findNavController().navigate(action)
        }

        postViewModel.getAllPosts()
        allPostsObserve()

    }

    private fun setUpRecyclerView(items : List<FakePostsItem>){
        binding.rvAllPosts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = postAdapter
        }
        postAdapter.submitList(items)
    }

    private fun allPostsObserve(){
        lifecycleScope.launch {
            postViewModel.getAllPosts.collect{ networkState ->
                when(networkState?.status){
                    NetworkState.Status.SUCCESS ->{
                        binding.allPostsProgressBar.visibilityGone()
                        val data = networkState.data as IResult<List<FakePostsItem>>
                        setUpRecyclerView(data.getData()!!)
                    }
                    NetworkState.Status.FAILED ->{
                        showToastShort(networkState.msg.toString())
                        binding.allPostsProgressBar.visibilityGone()
                    }
                    NetworkState.Status.RUNNING ->{
                        binding.allPostsProgressBar.visibilityVisible()
                    }
                    else -> {}
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}