package com.example.displaylistidcompose.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.displaylistidcompose.model.GroupedList
import com.example.displaylistidcompose.model.HiringListItem
import com.example.displaylistidcompose.model.Response
import com.example.displaylistidcompose.model.SelectedGroup
import com.example.displaylistidcompose.network.HiringRepository
import com.example.displaylistidcompose.util.SORTLISTID
import com.example.displaylistidcompose.util.SORTNAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HiringViewModel @Inject constructor(private val hiringRepository: HiringRepository) :
    ViewModel() {
    private val _hiringState = MutableStateFlow<Response<List<GroupedList>>>(Response.Loading)
    val hiringState = _hiringState.asStateFlow()


    fun getList() {
        viewModelScope.launch {
            _hiringState.value = Response.Loading
            hiringRepository.getHiringList().fold({ error ->
                _hiringState.value = Response.Error(error)
            }, { response ->
                _hiringState.value = Response.Success(response.converter())
            })
        }
    }


    //sort list id and name
    fun update(responseValue: List<GroupedList>?, index: Int, select: String) {

        var newGroupedList = when (select) {
            SORTLISTID -> responseValue?.sortedBy { it.listId }
            SORTNAME -> {
                responseValue?.map { selectedGroup ->
                    if (selectedGroup.listId == responseValue?.get(index)?.listId) {
                        val sortedSelectedGroup = selectedGroup.selectedGroup.sortedBy { it.name }
                        GroupedList(selectedGroup.listId, sortedSelectedGroup)
                    } else {
                        selectedGroup
                    }
                }
            }

            else -> {
                responseValue
            }
        }
        _hiringState.value =
            newGroupedList?.let { Response.Success(it) } as Response<List<GroupedList>>

    }

    //filter null and group list id
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun <E : Any> List<E>.converter(): List<GroupedList> {

        var list = this as List<HiringListItem>
        var gathered =
            // remove null or empty name
            list.filter { !it.name.isNullOrBlank() }
                //grouped listId
                .groupBy { it.listId }
                .map { (data, nameId) ->
                    GroupedList(
                        listId = data,
                        selectedGroup = nameId.map {
                            SelectedGroup(
                                id = it.id,
                                name = it.name.toString()
                            )
                        })
                }
        return gathered
    }
}
