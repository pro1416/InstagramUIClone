package com.pravesh.instagramuiclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pravesh.instagramuiclone.ui.theme.InstagramUICloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramUICloneTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    CreateTitleBar(username = R.string.username)
                    CreateFollowerRow(
                        modifier = Modifier.padding(vertical = 16.dp),
                        profilePicId = R.drawable.philipp,
                        posts = "169",
                        followers = "49.9K",
                        following = "118"
                    )
                    CreateBioRow(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        name = R.string.name,
                        bio = R.string.bio,
                        followedBy = R.string.followed_by,
                        otherCount = 18
                    )
                    CreateButtonRibbon(modifier = Modifier.padding(16.dp))
                    CreateHighlights(modifier = Modifier.padding(start= 16.dp, end = 16.dp,bottom=16.dp))
                    CreateTabRow(tabList = listOf(
                        Highlights("Posts", R.drawable.ic_grid),
                        Highlights("Reels", R.drawable.ic_reels),
                        Highlights("IGTV", R.drawable.ic_igtv),
                        Highlights("Profile", R.drawable.profile)
                    ), onTabSelected = {})
                    CreatePostGrid(postList = listOf(
                        Highlights("Build KMM",R.drawable.kmm),
                        Highlights("Intermediate Dev",R.drawable.intermediate_dev),
                        Highlights("Master thinking",R.drawable.master_logical_thinking),
                        Highlights("Bad Habits",R.drawable.bad_habits),
                        Highlights("Kotlin vs JS",R.drawable.multiple_languages),
                        Highlights("Learn faster",R.drawable.learn_coding_fast)
                    ))
                }
            }
        }
    }
}

@Composable
fun CreateTitleBar(modifier: Modifier = Modifier, @StringRes username: Int) {
    Row(
        modifier = modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back"
        )
        Text(
            modifier = Modifier
                .weight(5f)
                .padding(start = 16.dp),
            text = stringResource(id = username),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Icon(
            modifier = Modifier
                .heightIn(max = 20.dp)
                .widthIn(max = 20.dp),
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "Notify"
        )
        Icon(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More"
        )
    }
}

@Composable
fun CreateFollowerRow(
    modifier: Modifier = Modifier,
    @DrawableRes profilePicId: Int,
    posts: String,
    followers: String,
    following: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .sizeIn(maxHeight = 96.dp, maxWidth = 96.dp)
                .clip(CircleShape)
                .border(2.dp, Color.LightGray, shape = CircleShape),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = profilePicId),
            contentDescription = "Profile Pic"
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = posts,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(text = "Posts", fontSize = 14.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = followers,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(text = "Followers", fontSize = 14.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = following,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(text = "Following", fontSize = 14.sp)
        }
    }
}

@Composable
fun CreateBioRow(
    modifier: Modifier = Modifier,
    @StringRes name: Int,
    @StringRes bio: Int,
    @StringRes followedBy: Int,
    otherCount: Int
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = stringResource(id = name), fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(text = stringResource(id = bio), fontSize = 14.sp)
        Text(text = buildAnnotatedString {
            append("Followed by ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(stringResource(id = followedBy))
            }
            append(" and ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("$otherCount others")
            }
        }, fontSize = 14.sp)

    }
}

@Composable
fun CreateButtonRibbon(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 28.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ActionButton(
            modifier = Modifier.weight(1f),
            btnText = "Following",
            icon = Icons.Default.ExpandMore,
            contentDescription = "Following"
        )
        ActionButton(
            modifier = Modifier.weight(1f),
            btnText = "Message"
        )
        ActionButton(modifier = Modifier.weight(1f), btnText = "Email")
        ActionButton(icon = Icons.Default.ExpandMore, contentDescription = "More options")
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    btnText: String? = null,
    icon: ImageVector? = null,
    contentDescription: String? = null
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                1.5.dp,
                shape = RoundedCornerShape(6.dp),
                color = Color.LightGray
            )
            .fillMaxHeight()
            .clickable { }
            .padding(vertical = 2.dp)
    ) {
        btnText?.let {
            Text(
                text = btnText,
                fontWeight = FontWeight.Black,
                fontSize = 14.sp
            )
        }
        icon?.let {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription
            )
        }
    }

}

@Composable
fun CreateHighlights(modifier: Modifier = Modifier) {
    val highlightList = listOf(
        Highlights("Youtube", R.drawable.youtube),
        Highlights("Q&A", R.drawable.qa),
        Highlights("Discord", R.drawable.discord),
        Highlights("Telegram", R.drawable.telegram)
    )
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(highlightList) { item ->
            Column(
                modifier = Modifier.sizeIn(maxWidth = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .sizeIn(maxHeight = 56.dp, maxWidth = 56.dp)
                        .clip(CircleShape)
                        .clickable {  }
                        .border(2.dp, Color.LightGray, shape = CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = item.img),
                    contentDescription = "Profile Pic"
                )
                Text(text = item.name, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
            }
        }
    }
}

@Composable
fun CreateTabRow(
    modifier: Modifier = Modifier,
    tabList: List<Highlights>,
    onTabSelected: (index: Int) -> Unit
) {
    var selectedTab by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Color(0xFF777777)
    TabRow(
        modifier = modifier,
        contentColor = Color.Black,
        backgroundColor = Color.Transparent,
        selectedTabIndex = selectedTab
    ) {
        tabList.forEachIndexed { index, highlight ->
            Tab(
                selected = selectedTab == index,
                onClick = {
                    selectedTab = index
                    onTabSelected(index)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor
            ) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Icon(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = highlight.img),
                        contentDescription = highlight.name
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreatePostGrid(modifier: Modifier = Modifier, postList: List<Highlights>) {
    LazyVerticalGrid(cells = GridCells.Fixed(3)) {
        items(postList) { item ->
            Image(
                modifier = Modifier
                    .aspectRatio(1.01f)
                    .clickable {  }
                    .border(1.dp, Color.White),
                painter = painterResource(id = item.img),
                contentScale = ContentScale.Crop,
                contentDescription = item.name
            )
        }
    }
}

@Preview
@Composable
fun ShowTitleBar() {
    CreateTitleBar(username = R.string.username)
}

@Preview
@Composable
fun ShowFollowerRow() {
    CreateFollowerRow(
        profilePicId = R.drawable.philipp,
        posts = "169",
        followers = "49.9K",
        following = "118"
    )
}

@Preview
@Composable
fun ShowBioRow() {
    CreateBioRow(
        name = R.string.name,
        bio = R.string.bio,
        followedBy = R.string.followed_by,
        otherCount = 18
    )
}

@Preview
@Composable
fun ShowButtonRibbon() {
    CreateButtonRibbon()
}

@Preview
@Composable
fun ShowHighlights() {
    CreateHighlights()
}

@Preview
@Composable
fun ShowTabs() {
    CreateTabRow(tabList = listOf(
        Highlights("Posts", R.drawable.ic_grid),
        Highlights("Reels", R.drawable.ic_reels),
        Highlights("IGTV", R.drawable.ic_igtv),
        Highlights("Profile", R.drawable.profile)
    ), onTabSelected = {})
}

@Preview
@Composable
fun ShowPostGrid(){
    CreatePostGrid(postList = listOf(
        Highlights("Build KMM",R.drawable.kmm),
        Highlights("Intermediate Dev",R.drawable.intermediate_dev),
        Highlights("Master thinking",R.drawable.master_logical_thinking),
        Highlights("Bad Habits",R.drawable.bad_habits),
        Highlights("Kotlin vs JS",R.drawable.multiple_languages),
        Highlights("Learn faster",R.drawable.learn_coding_fast)
    ))
}

data class Highlights(val name: String, val img: Int)