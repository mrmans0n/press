package compose.theme

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup.SCROLLBARS_INSIDE_OVERLAY
import android.widget.EdgeEffect
import android.widget.ScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.EdgeEffectFactory
import compose.util.titleView
import compose.widgets.DisplayUnit.Dp
import compose.widgets.dp
import me.saket.compose.R
import me.saket.compose.shared.theme.ThemePalette
import androidx.appcompat.widget.Toolbar as ToolbarView

abstract class AppStyle(
  palette: ThemePalette,
  val toolbar: ToolbarStyle = ToolbarStyle(palette),
  val recyclerView: RecyclerViewStyle = RecyclerViewStyle(palette),
  val scrollView: ScrollViewStyle = ScrollViewStyle()
)

class ToolbarStyle(
  palette: ThemePalette,
  val title: TextAppearance = TextAppearance(
      parentRes = R.style.TextAppearance_AppCompat_Title,
      color = palette.accentColor
  ),
  private val backgroundColor: Int = palette.primaryColor,
  private val elevation: Dp = 4.dp
) : Styleable<ToolbarView> {

  override fun style(view: ToolbarView) {
    title.style(view.titleView)
    view.background = ColorDrawable(backgroundColor)
    view.elevation = elevation.px(view.context)
  }
}

abstract class ScrollableLayoutStyle<T: View>: Styleable<T> {
  override fun style(view: T) {
    view.isVerticalScrollBarEnabled = true
    view.scrollBarStyle = SCROLLBARS_INSIDE_OVERLAY
  }
}

class ScrollViewStyle : ScrollableLayoutStyle<ScrollView>()

class RecyclerViewStyle(private val palette: ThemePalette) : ScrollableLayoutStyle<RecyclerView>() {
  override fun style(view: RecyclerView) {
    view.edgeEffectFactory = object : EdgeEffectFactory() {
      override fun createEdgeEffect(view: RecyclerView, direction: Int) =
        EdgeEffect(view.context).apply { color = palette.accentColor }
    }
  }
}