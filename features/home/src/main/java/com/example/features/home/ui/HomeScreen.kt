package com.example.features.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.ui.components.circularProgress.CircularProgressBar
import com.example.core.ui.components.inputField.SearchInputField
import com.example.core.ui.components.spacer.Spacer
import com.example.core.ui.effects.snackbar.SnackbarEvent
import com.example.core.ui.effects.snackbar.SnackbarViewModel
import com.example.core.ui.theme.ArchTheme
import com.example.core.ui.utils.activityViewModel
import com.example.domain.home.model.Book
import com.example.features.home.R
import com.example.features.home.navigation.HomeExternalNavigator
import com.example.features.home.navigation.HomeNavGraph
import com.example.features.home.ui.compose.BookItem
import com.example.features.home.ui.mvi.HomeEffect
import com.example.features.home.ui.mvi.HomeState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun DependenciesContainerBuilder<*>.HomeDependencies(
    externalNavigator: HomeExternalNavigator
) {
    dependency(externalNavigator)
}

@Destination<HomeNavGraph>(start = true)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    externalNavigator: HomeExternalNavigator,
    destinationsNavigator: DestinationsNavigator,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarViewModel = activityViewModel<SnackbarViewModel>()
    val localResources = LocalResources.current

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                HomeEffect.ShowNoInternetSnackbar ->
                    snackbarViewModel.showSnackbar(
                        SnackbarEvent(
                            type = SnackbarEvent.SnackbarType.WARNING,
                            message = localResources.getString(R.string.no_internet_connection)
                        )
                    )

                HomeEffect.ShowSomethingWentWrongSnackbar ->
                    snackbarViewModel.showSnackbar(
                        SnackbarEvent(
                            type = SnackbarEvent.SnackbarType.ERROR,
                            message = localResources.getString(R.string.something_went_wrong)
                        )
                    )
            }
        }
    }

    HomeScreenContent(
        state = state,
        intentListener = viewModel
    )
}

@Composable
fun HomeScreenContent(
    state: HomeState,
    intentListener: HomeIntent,
) {
    Box {
        if (state.isLoading) {
            CircularProgressBar(modifier = Modifier.align(Alignment.Center))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(16.dp)
            SearchInputField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = state.searchValue,
                onValueChange = {
                    intentListener.onSearchValueChanged(it)
                },
                hint = stringResource(R.string.search)
            )
            Spacer(20.dp)

            if (state.books.isEmpty() && state.searchValue.isNotEmpty() && !state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nothing found",
                        color = ArchTheme.colors.gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(state.books, key = { it.id }) { book ->
                        BookItem(book = book)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val intentListener = object : HomeIntent {
        override fun onSearchValueChanged(value: String) {}
    }

    HomeScreenContent(
        intentListener = intentListener,
        state = HomeState(
            isLoading = false,
            searchValue = "qq",
            books = listOf(
                Book(
                    id = 1,
                    title = "Title",
                    subtitle = "Subtitle",
                    image = "https://someimage",
                    authors = listOf(),
                    rating = com.example.domain.home.model.Rating(4.4)
                ),
                Book(
                    id = 2,
                    title = "Title",
                    subtitle = "Subtitle",
                    image = "https://someimage",
                    authors = listOf(),
                    rating = com.example.domain.home.model.Rating(4.4)
                )
            )
        )
    )
}
