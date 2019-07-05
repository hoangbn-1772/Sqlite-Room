package com.example.sqliteroomsample.ui.activity.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sqliteroomsample.data.model.room.User
import com.example.sqliteroomsample.data.repository.UserLocalRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RoomViewModel(private val repository: UserLocalRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val users: ArrayList<User> = ArrayList()

    private val _insertUserLiveData = MutableLiveData<Long>()

    val insertUserLiveData: LiveData<Long>
        get() = _insertUserLiveData

    private val _getUserLiveData = MutableLiveData<MutableList<User>>()

    val getUserLiveDate: LiveData<MutableList<User>>
        get() = _getUserLiveData

    private val _deleteLiveData = MutableLiveData<Int>()
    val deleteLiveData: LiveData<Int>
        get() = _deleteLiveData

    fun insertUser(user: User) {
        compositeDisposable.add(
            repository.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { rowID ->
                        _insertUserLiveData.value = rowID
                    },
                    {
                        _insertUserLiveData.value = -1L
                    }
                )
        )
    }

    fun getUser() {
        compositeDisposable.add(
            repository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { users ->
                        _getUserLiveData.value = users
                    },
                    { error ->
                        _getUserLiveData.value = mutableListOf()
                        Log.d("TAG", "Error: $error")
                    }
                )
        )
    }

    fun deleteUser(user: User) {
        compositeDisposable.add(
            repository.deleteUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _deleteLiveData.value = it
                    },
                    {
                        _deleteLiveData.value = -1
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
