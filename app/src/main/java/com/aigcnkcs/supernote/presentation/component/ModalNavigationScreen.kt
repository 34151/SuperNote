package com.aigcnkcs.supernote.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aigcnkcs.supernote.data.local.entity.FolderEntity
import kotlinx.collections.immutable.ImmutableList

@Composable
fun ModalNavigationScreen(
    drawerState: DrawerState,
    gesturesEnabled: Boolean,
    folderList: ImmutableList<FolderEntity>,
    selectedDrawerIndex: Int,
    content: @Composable () -> Unit,
    navigateTo: (String) -> Unit,
    selectDrawer: (Int, FolderEntity)-> Unit
) = ModalNavigationDrawer(
    modifier = Modifier.fillMaxSize(),
    drawerState = drawerState,
    gesturesEnabled = gesturesEnabled,
    drawerContent = {
        ModalDrawerSheet(drawerState = drawerState) {
            DrawerContent(
                folderList = folderList,
                selectedDrawerIndex = selectedDrawerIndex,
                navigateTo = { navigateTo(it) }
            ) { position, folder ->
                selectDrawer(position, folder)
            }
        }
    }
) {
    content()
}