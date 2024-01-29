package com.mosamir.fakepostsapp.postsfeature.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosamir.fakepostsapp.postsfeature.domain.use_case.IGetAllPostsUseCase
import com.mosamir.fakepostsapp.postsfeature.domain.use_case.IGetPostByIdUseCase
import com.mosamir.fakepostsapp.util.NetworkState
import com.mosamir.fakepostsapp.util.getError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val iGetAllPostsUseCase: IGetAllPostsUseCase,
    private val iGetPostByIdUseCase: IGetPostByIdUseCase
): ViewModel() {

    private val _getAllPosts: MutableStateFlow<NetworkState?> = MutableStateFlow(null)
    val getAllPosts: StateFlow<NetworkState?> = _getAllPosts

    private val _getPostById: MutableStateFlow<NetworkState?> = MutableStateFlow(null)
    val getPostById: StateFlow<NetworkState?> = _getPostById

    fun getAllPosts() {
        _getAllPosts.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = iGetAllPostsUseCase.getAllPosts()
                if (result.isSuccessful()){
                    _getAllPosts.value = NetworkState.getLoaded(result)
                }else{
                    _getAllPosts.value = NetworkState.getErrorMessage(result.getError().toString())
                }
            }catch (ex:Exception){
                ex.printStackTrace()
                _getAllPosts.value = NetworkState.getErrorMessage(ex)
            }
        }
    }

    fun getPostById(postId: Int) {
        _getPostById.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = iGetPostByIdUseCase.getPostById(postId)
                if (result.isSuccessful()){
                    _getPostById.value = NetworkState.getLoaded(result)
                }else{
                    _getPostById.value = NetworkState.getErrorMessage(result.getError().toString())
                }
            }catch (ex:Exception){
                ex.printStackTrace()
                _getPostById.value = NetworkState.getErrorMessage(ex)
            }
        }
    }

}