package com.januar.githubuser.mvpsupport



interface Presenter<T : View> {
    fun onAttach(view: T)
    fun onDetach()
}