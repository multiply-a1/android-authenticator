/*
 * Copyright (c) 2025 Proton AG
 * This file is part of Proton AG and Proton Authenticator.
 *
 * Proton Authenticator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Proton Authenticator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Proton Authenticator.  If not, see <https://www.gnu.org/licenses/>.
 */

package proton.android.authenticator.features.settings.master.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import proton.android.authenticator.features.settings.master.R
import proton.android.authenticator.features.settings.master.presentation.SettingsMasterEvent
import proton.android.authenticator.features.settings.master.presentation.SettingsMasterState
import proton.android.authenticator.features.settings.master.presentation.SettingsMasterViewModel
import proton.android.authenticator.shared.ui.domain.components.bars.SmallTopBar
import proton.android.authenticator.shared.ui.domain.models.UiIcon
import proton.android.authenticator.shared.ui.domain.models.UiText
import proton.android.authenticator.shared.ui.domain.modifiers.backgroundScreenGradient
import proton.android.authenticator.shared.ui.domain.screens.ScaffoldScreen
import proton.android.authenticator.shared.ui.domain.theme.ThemePadding
import proton.android.authenticator.shared.ui.R as uiR

@Composable
fun SettingsMasterScreen(
    snackbarHostState: SnackbarHostState,
    onNavigationClick: () -> Unit,
    onBackupsClick: () -> Unit,
    onSyncEnabled: () -> Unit,
    onSyncDisabled: () -> Unit,
    onImportClick: () -> Unit,
    onExportClick: () -> Unit,
    onHowToClick: (String) -> Unit,
    onFeedbackClick: (String) -> Unit,
    onViewLogsClick: () -> Unit,
    onDiscoverAppClick: (String, String) -> Unit,
    onVersionNameClick: () -> Unit
) = with(hiltViewModel<SettingsMasterViewModel>()) {
    val state by stateFlow.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    val isBlurred by remember {
        derivedStateOf { scrollState.value > 0 }
    }

    LaunchedEffect(key1 = state.event) {
        when (state.event) {
            SettingsMasterEvent.Idle -> Unit

            SettingsMasterEvent.OnSyncDisabled -> {
                onSyncDisabled()
            }

            SettingsMasterEvent.OnSyncEnabled -> {
                onSyncEnabled()
            }
        }

        onConsumeEvent(event = state.event)
    }

    ScaffoldScreen(
        modifier = Modifier
            .fillMaxSize()
            .backgroundScreenGradient(),
        snackbarHostState = snackbarHostState,
        topBar = {
            SmallTopBar(
                title = UiText.Resource(id = R.string.settings_screen_title),
                isBlurred = isBlurred,
                navigationIcon = UiIcon.Resource(id = uiR.drawable.ic_arrow_left),
                onNavigationClick = onNavigationClick
            )
        }
    ) { paddingValues ->
        when (val currentState = state) {
            SettingsMasterState.Loading -> Unit
            is SettingsMasterState.Ready -> {
                SettingsContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = scrollState)
                        .padding(paddingValues = paddingValues)
                        .padding(horizontal = ThemePadding.Medium),
                    state = currentState,
                    onDismissPassBanner = ::onUpdateIsPassBannerDismissed,
                    onBackupsClick = onBackupsClick,
                    onAppLockTypeChange = ::onUpdateAppLockType,
                    onTapToRevealChange = ::onUpdateIsTapToRevealEnabled,
                    onThemeTypeChange = ::onUpdateThemeType,
                    onSearchBarTypeChange = ::onUpdateSearchBarType,
                    onDigitTypeChange = ::onUpdateDigitType,
                    onCodeChangeAnimationChange = ::onUpdateIsCodeChangeAnimationEnabled,
                    onImportClick = onImportClick,
                    onExportClick = onExportClick,
                    onHowToClick = onHowToClick,
                    onFeedbackClick = onFeedbackClick,
                    onViewLogsClick = onViewLogsClick,
                    onVersionNameClick = onVersionNameClick
                )
            }
        }
    }
}
