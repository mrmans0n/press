package press.di

import press.editor.EditorActivity
import press.home.HomeActivity
import dagger.Component
import io.reactivex.Observable
import me.saket.press.shared.theme.ThemePalette
import me.saket.press.shared.localization.Strings
import me.saket.press.shared.sync.SyncCoordinator
import press.PressApp
import press.sync.GitHostIntegrationActivity
import press.sync.PreferencesActivity

@Component(modules = [AppModule::class])
interface AppComponent {
  fun strings(): Strings
  fun themePalette(): Observable<ThemePalette>
  fun syncCoordinator(): SyncCoordinator

  fun inject(target: PressApp)
  fun inject(target: HomeActivity)
  fun inject(target: EditorActivity)
  fun inject(target: PreferencesActivity)
  fun inject(target: GitHostIntegrationActivity)
}
