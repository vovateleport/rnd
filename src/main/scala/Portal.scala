package rnd

import japgolly.scalajs.react._, vdom.prefix_<^._

import scala.scalajs.js
import org.scalajs.dom._

object Portal {
  def apply(children: ReactNode*) = component(js.Array(children))

  private val component = ReactComponentB[Unit]("Portal")
    .renderBackend[Backend]
    .componentDidMount(cdm => cdm.backend.init() >> cdm.backend.update())
    .componentDidUpdate(cdu => cdu.$.backend.update())
    .componentWillUnmount(cwu => cwu.backend.remove())
    .build

  class Backend($: BackendScope[Unit, Unit]) {
    val nodeWrap: Element = document.createElement("div")

    def render():ReactElement = <.div()
    def init() = Callback ( document.body.appendChild(nodeWrap) )
    def remove() = Callback( document.body.removeChild(nodeWrap) )

    def update() = $.propsChildren >>= { (pc: PropsChildren) =>
      ReactDOM.render(<.div(pc.toSeq), nodeWrap)
      Callback.empty
    }
  }
}
