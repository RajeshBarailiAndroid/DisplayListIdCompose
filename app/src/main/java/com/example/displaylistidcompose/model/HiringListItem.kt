package com.example.displaylistidcompose.model

data class HiringListItem(
    val id: Int?=null,
    val listId: Int?=null,
    val name: String?=null
)


data class GroupedList(
    val listId: Int?=null,
    var selectedGroup: List<SelectedGroup>
)

data class SelectedGroup(
    val id: Int?=null,
    val name: String?=null
)
