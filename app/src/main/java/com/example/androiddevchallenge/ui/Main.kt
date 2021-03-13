package com.example.androiddevchallenge.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import dev.chrisbanes.accompanist.glide.GlideImage
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun MainScreen(isLight: Boolean = MaterialTheme.colors.isLight) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize(),
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 16.dp
            ) {
                NavigationItem.values().forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = null,
                                modifier = Modifier.requiredSize(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(item.titleRes),
                                style = MaterialTheme.typography.caption,
                            )
                        },
                        selected = index == 0,
                        onClick = {}
                    )
                }
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            var keyword: String by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = keyword,
                onValueChange = { keyword = it },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.body1,
                placeholder = { Text(text = stringResource(R.string.HOME_search)) },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                },
                singleLine = true
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Text(
                        text = stringResource(R.string.HOME_browse_themes),
                        modifier = Modifier
                            .paddingFromBaseline(top = 32.dp)
                            .padding(horizontal = 16.dp),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.h1
                    )
                    Spacer(modifier = Modifier.requiredHeight(16.dp))
                }

                item {
                    LazyRow(
                        modifier = Modifier
                            .requiredHeight(136.dp)
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(start = 16.dp, bottom = 1.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            rowItems.forEach {
                                RowListLayout(it)
                                Spacer(modifier = Modifier.requiredWidth(8.dp))
                            }
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(56.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Design your home garden",
                            modifier = Modifier
                                .paddingFromBaseline(top = 40.dp, bottom = 0.dp)
                                .padding(start = 16.dp),
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.h1
                        )

                        Icon(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .requiredSize(24.dp)
                                .align(Alignment.CenterVertically),
                            imageVector = Icons.Filled.FilterList,
                            contentDescription = null
                        )
                    }

                }

                item {
                    columnItems.forEach {
                        ColumnListLayout(it)
                        Spacer(modifier = Modifier.requiredHeight(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnListLayout(item: Item) {
    Row(
        modifier = Modifier
            .height(64.dp)
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
    ) {
        Card {
            GlideImage(
                item.url,
                modifier = Modifier.size(64.dp),
                contentDescription = null,
                fadeIn = true,
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = item.title,
                        modifier = Modifier
                            .paddingFromBaseline(top = 24.dp, bottom = 0.dp)
                            .padding(start = 16.dp),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.h2
                    )
                    Text(
                        text = "This is a description",
                        modifier = Modifier
                            .paddingFromBaseline(top = 0.dp, bottom = 20.dp)
                            .padding(start = 16.dp),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1
                    )

                }
                val checkedState = remember { mutableStateOf(false) }
                Checkbox(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            checkedState.value = !checkedState.value
                        },
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it },
                    colors = CheckboxDefaults.colors(checkmarkColor = MaterialTheme.colors.onSecondary),
                )
            }
            Divider(color = Color.Gray, thickness = 1.dp, startIndent = 10.dp, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun RowListLayout(item: Item) {
    Card(
        modifier = Modifier
            .requiredSize(136.dp)
            .clickable { },
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            GlideImage(
                item.url,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp),
                contentDescription = null,
                fadeIn = true,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .weight(1f, true)
                    .padding(start = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart),
                    text = item.title,
                    style = MaterialTheme.typography.h2
                )
            }
        }
    }
}

private enum class NavigationItem(@StringRes val titleRes: Int, val icon: ImageVector) {
    Home(R.string.HOME_menu_home, Icons.Filled.Home),
    Favorites(R.string.HOME_menu_favorites, Icons.Filled.FavoriteBorder),
    Profile(R.string.HOME_menu_profile, Icons.Filled.AccountCircle),
    Cart(R.string.HOME_menu_cart, Icons.Filled.ShoppingCart)
}

val rowItems = listOf(
    Item(
        "https://images.pexels.com/photos/2132227/pexels-photo-2132227.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "Desert chic"
    ),
    Item(
        "https://images.pexels.com/photos/1400375/pexels-photo-1400375.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "Tiny terrariums"
    ),
    Item(
        "https://images.pexels.com/photos/5699665/pexels-photo-5699665.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "Jungle vibes"
    ),
    Item(
        "https://images.pexels.com/photos/6208086/pexels-photo-6208086.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "Easy care"
    ),
    Item(
        "https://images.pexels.com/photos/3511755/pexels-photo-3511755.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "Statements"
    )
)

val columnItems = listOf(
    Item(
        "https://images.pexels.com/photos/3097770/pexels-photo-3097770.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "Monstera"
    ),
    Item(
        "https://images.pexels.com/photos/4751978/pexels-photo-4751978.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "Aglaonema"
    ),
    Item(
        "https://images.pexels.com/photos/4425201/pexels-photo-4425201.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "Peace lily"
    ),
    Item(
        "https://images.pexels.com/photos/6208087/pexels-photo-6208087.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "Fiddle leaf"
    ),
    Item(
        "https://images.pexels.com/photos/2123482/pexels-photo-2123482.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "Snake plant"
    ),
    Item(
        "https://images.pexels.com/photos/1084199/pexels-photo-1084199.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "Pothos"
    )
)

data class Item(val url: String, val title: String)