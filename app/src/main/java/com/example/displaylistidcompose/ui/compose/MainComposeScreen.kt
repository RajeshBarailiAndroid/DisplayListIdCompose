package com.example.displaylistidcompose.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.displaylistidcompose.R
import com.example.displaylistidcompose.model.GroupedList
import com.example.displaylistidcompose.model.Response
import com.example.displaylistidcompose.util.LISTPADDING
import com.example.displaylistidcompose.util.SORTLISTID
import com.example.displaylistidcompose.util.SORTNAME
import com.example.displaylistidcompose.util.TITLE
import com.example.displaylistidcompose.viewmodel.HiringViewModel


@Composable
fun MainPageView(navController: NavHostController) {
//load data and success and error
    val viewModel: HiringViewModel = hiltViewModel()
    val responseValue by viewModel.hiringState.collectAsStateWithLifecycle()

    if (responseValue==Response.Loading ){
        viewModel.getList()
    }
    responseValue?.let {
        when (it) {
            is Response.Loading -> CircularProgressLoading()
            is Response.Success -> showScreen(it.data, viewModel)
            is Response.Error -> ErrorMsg(it.exception.message,navController)
        }

    }

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showScreen(responseValue: List<GroupedList>?, viewModel: HiringViewModel) {
    var state by remember { mutableIntStateOf(0) }
    val selectedGrouped =  responseValue?.get(state)?.selectedGroup

    //Header with title
    Scaffold(
        topBar = {
            var menuList = listOf(SORTLISTID, SORTNAME)
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { }) {
                        Icon(
                            Icons.Filled.Home,
                            "Home Icon",
                        )
                    }
                },
                title = {
                    Text(
                        text = TITLE,
                        fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground
                    )

                }, actions = {

                    //DropDown menu for select sort ListId and name

                    var isExpanded by remember {
                        mutableStateOf(false)
                    }
                    IconButton(onClick = {
                        isExpanded = true
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_filter_list_24),
                            contentDescription = stringResource(id = R.string.app_name),
                        )
                    }
                    DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                        menuList.forEach { action ->
                            DropdownMenuItem(onClick = {
                                responseValue?.let {
                                    viewModel.update(
                                        responseValue,
                                        state,
                                        action
                                    )
                                }
                                isExpanded = false
                            }) {
                                Text(text = action, fontSize = 20.sp)
                            }
                        }
                    }
                }
            )


        }
    ) { paddingValues ->

        //ScrollableTab for ListId
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            ScrollableTabRow(
                selectedTabIndex = state,

            )

            {

                responseValue?.forEachIndexed { index, title ->

                    Tab(
                        text = {
                            Text(
                                "ListId " + title.listId,
                                fontSize = 17.sp, color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        selected = state == index,
                        onClick = { state = index })
                }
            }


            // LazyColumn to show id and name list
            selectedGrouped?.let {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    items(items =selectedGrouped , itemContent ={item->
                        TextImageView(item)
                        //divider
                        Divider(    modifier = Modifier.padding(horizontal = LISTPADDING, vertical = LISTPADDING),
                            color = Color.Gray.copy(0.2f))
                } )

                    }

                }
            }


        }




}

