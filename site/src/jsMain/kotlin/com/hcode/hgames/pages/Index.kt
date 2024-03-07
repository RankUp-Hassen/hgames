package com.hcode.hgames.pages


import androidx.compose.runtime.*
import com.hcode.hgames.components.*
import com.hcode.hgames.data.bestGames
import com.hcode.hgames.data.hotGames
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.functions.CSSImage
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.url
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px
import org.w3c.dom.get


var fav by mutableStateOf(localStorage["favGamesHCode"] ?: "")
var history = localStorage["historyGamesHCode"] ?: ""


@Page
@Composable
fun HomePage() {
    val dark = remember { mutableStateOf(localStorage.getItem("darkModeGames").toBoolean()) }

    Box(
        modifier = Modifier
            .id("mainBox")
            .fillMaxSize()
            .transition(CSSTransition(property = "background", duration = 1000.ms))
            .background(if (dark.value) MyColor.DARK_BLUE.color else MyColor.LIGHT_BLUE.color)
            .backgroundImage(CSSImage.Companion.of(url("bg-diamante.svg")))
            .overflow(Overflow.Auto)

    ) {
        MainPage(
            dark = dark
        )
    }
}




@Composable
fun MainPage(
    dark : MutableState<Boolean>,
){

    val index = remember { mutableStateOf(0) }
    val text = remember { mutableStateOf("") }
    val indexPage = remember { mutableStateOf(0) }
    val listSelected = remember { mutableStateOf(0) }
    val currentGame = remember { mutableStateOf(FDG("","","","")) }
    val isPlay = remember { mutableStateOf(false) }
    val pageSelected = remember { mutableStateOf(0) }

    LaunchedEffect(pageSelected.value){
        println(pageSelected.value)
    }


    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(org.jetbrains.compose.web.css.Color.transparent)
    ) {
        if (isPlay.value) {
            GamePlace(isPlay = isPlay,  currentGame = currentGame, dark = dark.value)
        } else  {
            if (text.value.isEmpty()){
                when(pageSelected.value){
                    0 -> {
                        HomeGames(
                            dark = dark,
                            currentGame = currentGame,
                            data = bestGames,
                            isPlay = isPlay
                        )
                    }
                    1 -> {
                        HomeGames(
                            dark = dark,
                            currentGame = currentGame,
                            data = hotGames,
                            isPlay = isPlay
                        )
                    }
                    2 -> {
                        GamesLayout(
                            listGames = gamesListData[index.value],
                            modifier = Modifier,
                            dark = dark.value,
                            index = indexPage,
                            listSelected = listSelected,
                            current = currentGame,
                            isPlay = isPlay,
                        )
                    }
                    3 -> {

                        HomeGames(
                            dark = dark,
                            currentGame = currentGame,
                            data = getFavList(),
                            isPlay = isPlay
                        )
                    }

                    4 -> {
                        HomeGames(
                            dark = dark,
                            currentGame = currentGame,
                            data = getHistoryList(),
                            isPlay = isPlay
                        )
                    }
                }
            }else{

                DisplaySearch(
                    text = text.value,
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    current = currentGame,
                    isPlay = isPlay,

                )

            }

            TopBar(
                dark = dark,
                modifier = Modifier
                    .transition(CSSTransition(property = "all" , duration = 500.ms))
                    .fillMaxWidth()
                    .position(Position.Fixed)
                    .background(Color.argb(0.2f,255,255,255))
                    .backdropFilter(blur(15.px)),
                text = text
            )
        }
        CategoryChooser(
            dark = dark.value,
            index = index,
            indexPage = indexPage,
            listSelected = listSelected,
            pageSelected = pageSelected
        )

    }
}

