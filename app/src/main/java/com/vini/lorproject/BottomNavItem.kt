package com.vini.lorproject

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Home : BottomNavItem("Home", R.drawable.ic_home, "home")
    object Leaderboards : BottomNavItem("Leaderboards", R.drawable.ic_trophy, "leaderboards")
    object Meta : BottomNavItem("Meta", R.drawable.ic_badge, "meta_decks")
}